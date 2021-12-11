import java.io.File;  // Import the File class
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Checker {
	public static void main(String[] args) throws JSONException, IOException {

        // File file = new File(args[0]);
        // File logfile = new File(args[1]);
        File file = new File("logs/GeneratedCode/1_new/output.json");
        File logfile = new File("logs/GeneratedCode/1_new/log.txt");

        // define the patttern that will be used to find in the file
        Pattern departurePattern = Pattern.compile("\\sFragment departure time:\\s(\\d*.\\d*)");
        Pattern arrivalPattern = Pattern.compile("\\sFragment arrival time:\\s(\\d*.\\d*)");
        Pattern scheduledPattern = Pattern.compile("\\sFragment scheduled time:\\s(\\d*.\\d*)");
        Pattern slotStartPattern = Pattern.compile("\\sFragment slot start 0:\\s(\\d*.\\d*)");
        Pattern slotDurationPattern = Pattern.compile("\\sFragment slot duration 0\\s:\\s(\\d*.\\d*)");

        // creating a scanner to scan the file line by line
        Scanner sc = new Scanner(logfile);

        // variables to auxiliate checking time logs
        int lineCounter = 0;
        Float slotStart = (float) 0.0;
        Float slotDuration = (float) 0.0;

        // parse the file line by line
        while (sc.hasNextLine()){

            // track lines to ease visualization
            lineCounter++;
            String line = sc.nextLine().toString();

            // matchers to find the patterns
            Matcher departureMatcher = departurePattern.matcher(line);
            Matcher arrivalMatcher = arrivalPattern.matcher(line);
            Matcher scheduledMatcher = scheduledPattern.matcher(line);

            Matcher slotStartMatcher = slotStartPattern.matcher(line);
            Matcher slotDurationMatcher = slotDurationPattern.matcher(line);

            Float departure = (float) 0.0;
            Float arrival = (float) 0.0;
            Float scheduled = (float) 0.0;

            // find the start time packets are sent
            if(slotStartMatcher.find()){
                slotStart = Float.parseFloat(slotStartMatcher.group(1));

                System.out.println("=======================");

                if(slotStart < 0){
                    System.out.print("Typechecking value: Slot start time can't be negative (line " + lineCounter + ")");
                    return;
                }
                System.out.println("Slot Start time: "+slotStart+ " (line " + lineCounter + ")");
            }

            // find the time a packet takes to arrive
            if(slotDurationMatcher.find()){
                slotDuration = Float.parseFloat(slotDurationMatcher.group(1));

                if(slotDuration < 0){
                    System.out.print("Typechecking value: Slot duration time can't be negative (line " + lineCounter + ")");
                    return;
                }
                System.out.println("Slot duration time: "+slotDuration+ " (line " + lineCounter + ")");
            }

            // find the derpature time of the log file
            if(departureMatcher.find()){
                departure = Float.parseFloat(departureMatcher.group(1));

                if(departure < 0){
                    // throw new IllegalArgumentException("Negative number found!");
                    System.out.print("Typechecking value: Departure time can't be negative (line " + lineCounter + ")");
                    return;
                }
                // System.out.println(departureMatcher.group(1));
            }

            // find the arrival time of the log file
            if(arrivalMatcher.find()){
                arrival = Float.parseFloat(arrivalMatcher.group(1));

                if(arrival < 0){
                    System.out.print("Typechecking value: Arrival time can't be negative (line " + lineCounter + ")");
                    return;
                }
                // System.out.println(arrivalMatcher.group(1));
            }

            // find the schedule time, which is start time + duration
            if(scheduledMatcher.find()){
                scheduled = Float.parseFloat(scheduledMatcher.group(1));

                if(scheduled < 0){
                    System.out.print("Typechecking value: Shceduled time can't be negative (line " + lineCounter + ")");
                    return;
                }
                System.out.println("Scheduled time: "+scheduledMatcher.group(1) + " (line " + lineCounter + ")");
                System.out.println("Expected scheduled time: " + round(slotDuration + slotStart, 3));
                
                if(scheduled == round(slotDuration + slotStart, 3)){
                    System.out.println("Ok");
                }
                else {
                    System.out.println("Not ok! (line " + lineCounter + ")");
                }
                System.out.println("=======================");
                System.out.println();
            }    
        }

        JSONObject switchData = getPriorityData(file);
        JSONObject timePacketsObject = getTimePackets(file);
        JSONObject flowsFrag = getFlowsFragments(logfile);

        // checking if a port has more than one slot
        // if(!hasMoreSlots(switchData).equals("false")){
        //     System.out.println("Found");
        //     System.out.println(hasMoreSlots(switchData));
        // }

        checkPriority(switchData);
        checkTransmissionWindow(switchData); 
        CheckTimePackets(timePacketsObject, logfile);
        checkHop(logfile, flowsFrag);
        
        // JSONObject path = getMultiPath(logfile);
        // System.out.println(path);
        
        // System.out.println(flowsFrag);
        
        // System.out.println(timePacketsObject);

        // for(int i = 0; i < timePacketsObject.length(); i++){
        //     System.out.println("flow"+i);

        //     for(int j = 0; j < timePacketsObject.getJSONArray("flow"+i).length() ; j++){
        //         System.out.println(timePacketsObject.getJSONArray("flow"+i).getJSONObject(j));
        //     }
        // }

        // System.out.println(timePacketsObject.length());

        sc.close();
    }

    public static JSONObject getTimePackets(File file) throws FileNotFoundException{
        Scanner sc = new Scanner(file);
        String jsonString= "";

        while (sc.hasNextLine()){
            String line = sc.nextLine().toString();
            jsonString += line;
            jsonString += "\n";
        }

        JSONObject jsonObj = new JSONObject(jsonString);
        JSONObject finalObj = new JSONObject();
        
        for(int i = 0; i < jsonObj.optJSONArray("flows").length(); i++){
            int packetTimesLength = jsonObj.optJSONArray("flows").getJSONObject(i).getJSONArray("packetTimes").length();
            JSONArray packetTimeArray = new JSONArray();

            for(int j = 0; j < packetTimesLength; j++){
                // JSONObject packetTimeObject = jsonObj.optJSONArray("flows").optJSONObject(i).getJSONArray("packetTimes").getJSONObject(j);
                // packetTimeArray.put(packetTimeObject);
                
                for(int k = 0; k < packetTimesLength; k++){
                    
                    try {
                        JSONArray flowFragmentArray = jsonObj.optJSONArray("flows").optJSONObject(i).getJSONArray("packetTimes").getJSONObject(j).getJSONArray("flow"+i+"Fragment"+k);
                        for(int l = 0; l < flowFragmentArray.length(); l++){
                            JSONObject packetTimeData = new JSONObject();
                            Double packetArrivalTime = flowFragmentArray.getJSONObject(l).getDouble("packet"+0+"ArrivalTime");
                            Double packetDepartureTime = flowFragmentArray.getJSONObject(l).getDouble("packet"+0+"DepartureTime");
                            Double packetScheduledTime = flowFragmentArray.getJSONObject(l).getDouble("packet"+0+"ScheduledTime");
                            int packetNumber = flowFragmentArray.getJSONObject(l).getInt("packetNumber");

                            packetTimeData.put("flow", i);
                            packetTimeData.put("fragment", k);
                            packetTimeData.put("packetNumber", packetNumber);
                            packetTimeData.put("packetArrivalTime", packetArrivalTime);
                            packetTimeData.put("packetDepartureTime", packetDepartureTime);
                            packetTimeData.put("packetScheduledTime", packetScheduledTime);

                            if(!hasDuplicatedPacketObjects(packetTimeArray, packetTimeData)){
                                packetTimeArray.put(packetTimeData);
                            }

                        }
                        // System.out.println(flowFragmentArray.getJSONObject(0).getDouble("packet"+0+"ArrivalTime"));
                    
                    } catch (Exception e) {
                        continue;
                    }

                }
            }
            finalObj.put("flow"+i, packetTimeArray);
        }

        // CheckTimePackets(packetTimeArray, jsonObj);

        // System.out.println(jsonObj.optJSONArray("flows")
        // .getJSONObject(0).getJSONArray("packetTimes")
        // .getJSONObject(0).getJSONArray("flow"+i+"Fragment"+j)
        // .getJSONObject(0)
        // .getDouble("packet"+i+"ArrivalTime")
        // );

        sc.close();
        return finalObj;
    }

    public static boolean hasDuplicatedPacketObjects(JSONArray packetTimeArray, JSONObject packetTimeData){
        for(int i = 0; i < packetTimeArray.length(); i++){
            // JSONObject objectToCompare = packetTimeArray.getJSONObject(i);
            int flowNumber = packetTimeArray.getJSONObject(i).getInt("flow");
            int fragmentNumber = packetTimeArray.getJSONObject(i).getInt("fragment");
            if(flowNumber == packetTimeData.getInt("flow")){
                if(fragmentNumber == packetTimeData.getInt("fragment")){
                    return true;
                }
            }
        }

        return false;
    }

    public static void CheckTimePackets(JSONObject timePacketsObject, File logFile) throws FileNotFoundException, JSONException{
        for(int i = 0; i < timePacketsObject.length(); i++){
            for(int j = 0; j < timePacketsObject.getJSONArray("flow"+i).length(); j++){
                for(int k = 0; k < timePacketsObject.getJSONArray("flow"+i).length(); k++){
                    if(j!=k){
                        Double packetArrival = (Double) timePacketsObject.getJSONArray("flow"+i).getJSONObject(j).getDouble("packetArrivalTime");
                        Double packetDeparture = (Double) timePacketsObject.getJSONArray("flow"+i).getJSONObject(j).getDouble("packetDepartureTime");
                        Double packetScheduled = (Double) timePacketsObject.getJSONArray("flow"+i).getJSONObject(j).getDouble("packetScheduledTime");
                        
                        Double packetArrivalTocompare = (Double) timePacketsObject.getJSONArray("flow"+i).getJSONObject(k).getDouble("packetArrivalTime");
                        Double packetDepartureTocompare = (Double) timePacketsObject.getJSONArray("flow"+i).getJSONObject(k).getDouble("packetDepartureTime");
                        Double packetScheduledTocompare = (Double) timePacketsObject.getJSONArray("flow"+i).getJSONObject(k).getDouble("packetScheduledTime");
                        if(packetArrival < 0){
                            System.out.println("Negative number found");
                            System.out.println(timePacketsObject.getJSONArray("flow"+i).getJSONObject(j));
                            return;
                        }else if(packetDeparture < 0){
                            System.out.println("Negative number found");
                            System.out.println(timePacketsObject.getJSONArray("flow"+i).getJSONObject(j));
                            return;
                        }else if(packetScheduled < 0){
                            System.out.println("Negative number found");
                            System.out.println(timePacketsObject.getJSONArray("flow"+i).getJSONObject(j));
                            return;
                        }

                        if( packetArrival.equals(packetArrivalTocompare) || 
                            packetDeparture.equals(packetDepartureTocompare) || 
                            packetScheduled.equals(packetScheduledTocompare)){
                            if(sameSwitch(logFile, timePacketsObject.getJSONArray("flow"+i).getJSONObject(j), timePacketsObject.getJSONArray("flow"+i).getJSONObject(k)).equals("false")){
                                System.out.println("Same time data found, fragments:");
                                // System.out.println(sameSwitch(logFile, timePacketsObject.getJSONArray("flow"+i).getJSONObject(j), timePacketsObject.getJSONArray("flow"+i).getJSONObject(k)));
                                System.out.println(timePacketsObject.getJSONArray("flow"+i).getJSONObject(j));
                                System.out.println(timePacketsObject.getJSONArray("flow"+i).getJSONObject(k));
                            }
                        }
                    }
                }
            }
        }
    }

    public static String sameSwitch(File file, JSONObject objToCompare, JSONObject objToBeCompared) throws FileNotFoundException{
        Scanner sc = new Scanner(file);

        ArrayList<Integer> flowFragmentList = new ArrayList<Integer>();
        ArrayList<String> currentFragmentNodeList = new ArrayList<String>();

        // define the patttern that will be used to find in the file 
        Pattern flowFragmentPattern = Pattern.compile("\\sFragment name:\\s(flow\\d*)Fragment(\\d*)");
        Pattern fragmentNodePattern = Pattern.compile("\\sFragment node:\\s(switch\\d*)");
        Pattern fragmentNextNodePattern = Pattern.compile("\\sFragment next hop:\\s(switch\\d*|dev\\d*)");
        Pattern devPattern = Pattern.compile("(dev\\d*),");
        Pattern leavesPattern = Pattern.compile("\\sList of leaves:");
        Pattern pathToPattern = Pattern.compile("\\sPath\\sto\\s(dev\\d*):");

        // varibles to auxiliate and control actions
        String flowFragmentName = "";
        String flowFragment = "";
        String controller = "";
        int lineCounter = 0;

        // parse the file
        while (sc.hasNextLine()){
            String line = sc.nextLine().toString();
            lineCounter++;

            // matchers to find patterns
            Matcher flowFragmentNameMatcher = flowFragmentPattern.matcher(line);
            Matcher fragmentNodeMatcher = fragmentNodePattern.matcher(line);
            Matcher fragmentNextNodeMatcher = fragmentNextNodePattern.matcher(line);

            // find the flow fragment name
            if(flowFragmentNameMatcher.find()){
                flowFragmentName = flowFragmentNameMatcher.group(1);
                flowFragment = flowFragmentNameMatcher.group(2);
                
                int frag = objToCompare.getInt("fragment");
                int compareFrag = objToBeCompared.getInt("fragment");
                if(frag == Integer.parseInt(flowFragment) || compareFrag == Integer.parseInt(flowFragment)){
                    flowFragmentList.add(Integer.parseInt(flowFragment));
                    controller = "first";
                }

                if(flowFragmentList.size() == 2){
                    if(Math.abs(flowFragmentList.get(0) - flowFragmentList.get(1)) == 1){
                        controller = "second";
                    }
                }
                // System.out.println(flowFragment);
            }

            // find the current node
            if(fragmentNodeMatcher.find()){
                String fragmentNode = fragmentNodeMatcher.group(1);

                if(controller == "second" || controller == "first"){
                    currentFragmentNodeList.add(fragmentNode);
                    // System.out.println(fragmentNodeMatcher.group(1) + " " + lineCounter);
                }

                if(currentFragmentNodeList.size() == 2){
                    if(currentFragmentNodeList.get(0).equals(currentFragmentNodeList.get(1))){
                        // System.out.println(currentFragmentNodeList.get(0) + " ==? " + currentFragmentNodeList.get(1));
                        JSONObject path = getMultiPath(file);
                        
                        // System.out.println(path.get(flowFragmentName));
                        sc.close();
                        return "true";
                    }
                }
            }
        }

        
        sc.close();
        return "false";
    }

    public static int has(JSONObject info, String name){
        if(!info.isEmpty()){
            for(int l = 0; l < info.getJSONArray("switches").length(); l++){
                if(info.getJSONArray("switches").getJSONObject(l).getString("name").equals(name)){
                    return l;
                }
            }
        }

        return -1;
    }

    public static JSONObject getPriorityData(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String jsonString= "";
        
        while (sc.hasNextLine()){
            String line = sc.nextLine().toString();
            jsonString += line;
            jsonString += "\n";
        }

        JSONObject jsonObj = new JSONObject(jsonString);
        JSONObject info = new JSONObject();

        int switchesArrayLength = jsonObj.getJSONArray("switches").length();
        // info.append("switches", new JSONArray());
        
        for(int i = 0; i < switchesArrayLength; i++){

            int portsArrayLength = jsonObj.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").length();

            for(int j = 0; j < portsArrayLength; j++){
                if(!jsonObj.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").getJSONObject(j).getJSONArray("prioritySlotsData").isEmpty()){
                    
                    String name = jsonObj.getJSONArray("switches").getJSONObject(i).getString("name");

                    int priorityArraySize = jsonObj.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").getJSONObject(j).getJSONArray("prioritySlotsData").length();
                    JSONObject data = new JSONObject();
                    JSONObject switcher = new JSONObject();
                    
                    for(int k = 0; k < priorityArraySize; k++){
                        JSONObject priorityData = jsonObj.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").getJSONObject(j).getJSONArray("prioritySlotsData").getJSONObject(k);
    
                        String port = jsonObj.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").getJSONObject(j).getString("name");
                        Double cycleDuration = jsonObj.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").getJSONObject(j).getDouble("cycleDuration");
                        
                        // data.put("name", name);
                        data.put("portName", port);
                        data.put("cycleDuration", cycleDuration);
                        data.append("prioritySlotsData", priorityData);

                        if(has(info, name) != -1){
                            int index = has(info, name);

                            info.getJSONArray("switches").getJSONObject(index).getJSONArray("ports").put(data);
                        }else{
                            switcher.put("name", name);
                            switcher.append("ports", data);
        
                            info.append("switches", switcher);
                        }
                        
                    }
                    
                    // System.out.println(switcher);

                    // System.out.println(jsonObj.getJSONArray("switches").getJSONObject(i).getString("name"));
                    // System.out.println(jsonObj.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").getJSONObject(j).getJSONArray("prioritySlotsData"));
    
                }
            }
        }   
        // System.out.println(info.getJSONArray("switches"));

        // System.out.println(info.getJSONArray("switches")
        // .getJSONObject(0)
        // .getJSONArray("ports")
        // .getJSONObject(0)
        // .getJSONArray("prioritySlotsData")
        // .getJSONObject(0)
        // .getJSONArray("slotsData")
        // .getJSONObject(0)
        // .getDouble("slotStart"));

        // System.out.println(info.getJSONArray("switches")
        // .getJSONObject(3));

        // System.out.println(info.getJSONArray("switches").length());

        // System.out.println(info.getJSONArray("switches")
        // .getJSONObject(0)
        // .getJSONArray("ports")
        // .getJSONObject(0)
        // .getDouble("cycleDuration"));

        sc.close();
        return info;
    }

    public static String hasMoreSlots(JSONObject info){
        for(int i = 0; i < info.getJSONArray("switches").length(); i++){
            for(int j = 0; j < info.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").length() ; j++){
                if(info.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").getJSONObject(j).getJSONArray("prioritySlotsData").length() > 1){
                    String name = info.getJSONArray("switches").getJSONObject(i).getString("name");
                    String portName = info.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").getJSONObject(j).getString("portName");

                    return name + " " + portName;
                }
            }
        }
        return "false";
    }

    /* 
        function to get the next closest number of a that is divided by a number 'b',
    */
    public static Double closestInteger(Double a, int b) {
        Double c2 = (a + b) - (a % b);
        return c2;
    }

    // function to check if a flow is not sending packets after it's transmission window's limits
    public static void checkTransmissionWindow(JSONObject info){

        for(int i = 0; i < info.getJSONArray("switches").length(); i++){
            for(int j = 0; j < info.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").length() ; j++){
                int prioritySlotDataLength = info.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").getJSONObject(j).getJSONArray("prioritySlotsData").length();  
                if(prioritySlotDataLength > 0){
                    String name = info.getJSONArray("switches").getJSONObject(i).getString("name");
                    String portName = info.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").getJSONObject(j).getString("portName");
                    for(int k = 0; k < prioritySlotDataLength; k++){
                        int slotDataLength = info.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").getJSONObject(j).getJSONArray("prioritySlotsData").getJSONObject(k).getJSONArray("slotsData").length();
                        for (int l = 0; l < slotDataLength; l++) {
                            Double slotStart = info.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").getJSONObject(j).getJSONArray("prioritySlotsData").getJSONObject(k).getJSONArray("slotsData").getJSONObject(l).getDouble("slotStart");
                            Double slotDuration = info.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").getJSONObject(j).getJSONArray("prioritySlotsData").getJSONObject(k).getJSONArray("slotsData").getJSONObject(l).getDouble("slotDuration");
                            Double cycleDuration = info.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").getJSONObject(j).getDouble("cycleDuration");

                            // Double cycleDuration = Double.parseDouble(cycle);
                            if(slotStart + slotDuration > cycleDuration){
                                System.out.println("Maxed out Transmission");
                                System.out.println(name + " " + portName);
                                System.out.println("logs: " + slotStart + " + " + slotDuration + " = " + (slotStart+slotDuration) + " > " + cycleDuration);
                            }
                        }
                    }
                }
            }
        }
    }

    // aux function to make checkPriotiy shorter, currently not used
    /*public static Boolean verifyPriority(JSONArray ports){
        for(int i = 0; i < ports.length(); i++){
            int prioritySlotDataLength = ports.getJSONObject(i).getJSONArray("prioritySlotsData").length();
            for (int j = 0; j < prioritySlotDataLength; j++) {
                int slotDataLength = ports.getJSONObject(j).getJSONArray("prioritySlotsData").getJSONObject(j).getJSONArray("slotsData").length();
                for(int k = 0; k < slotDataLength; k++){
                    Double slotStart = ports.getJSONObject(j).getJSONArray("prioritySlotsData").getJSONObject(j).getJSONArray("slotsData").getJSONObject(k).getDouble("slotStart");
                    int counter = 0;

                    for(int a = 0; a < slotDataLength; a++){
                        Double slotStartToBeCompared = ports.getJSONObject(j).getJSONArray("prioritySlotsData").getJSONObject(j).getJSONArray("slotsData").getJSONObject(a).getDouble("slotStart");
                        if(slotStart.equals(slotStartToBeCompared)){
                            counter++;
                            if(counter > 1){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }*/

    // function to check if two ports have the same slotStart
    public static void checkPriority(JSONObject info){
        for(int i = 0; i < info.getJSONArray("switches").length(); i++){
            for(int j = 0; j < info.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").length() ; j++){
                int prioritySlotDataLength = info.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").getJSONObject(j).getJSONArray("prioritySlotsData").length();  
                if(prioritySlotDataLength > 1){
                    String name = info.getJSONArray("switches").getJSONObject(i).getString("name");
                    String portName = info.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").getJSONObject(j).getString("portName");
                    for(int k = 0; k < prioritySlotDataLength; k++){
                        int slotDataLength = info.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").getJSONObject(j).getJSONArray("prioritySlotsData").getJSONObject(k).getJSONArray("slotsData").length();
                        for (int l = 0; l < slotDataLength; l++) {
                            Double slotStart = info.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").getJSONObject(j).getJSONArray("prioritySlotsData").getJSONObject(k).getJSONArray("slotsData").getJSONObject(l).getDouble("slotStart");
                            int counter = 0;

                            for(int a = 0; a < prioritySlotDataLength; a++){
                                for (int b = 0; b < slotDataLength; b++) {
                                    Double slotStartToBeCompared = info.getJSONArray("switches").getJSONObject(i).getJSONArray("ports").getJSONObject(j).getJSONArray("prioritySlotsData").getJSONObject(a).getJSONArray("slotsData").getJSONObject(b).getDouble("slotStart");
                                    if(slotStart.equals(slotStartToBeCompared)){
                                        counter++;
                                        if(counter > 1){
                                            System.out.println(name + " " + portName);
                                            System.out.println("Time scheduling error! Duplicated SlotStart on the same port");
                                            return;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // function to round float numbers
    public static float round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, RoundingMode.HALF_UP);
        return bd.floatValue();
    }

    // function to check if leaves and destinations devices are correct
    public static void checkHop(File file, JSONObject flows) throws FileNotFoundException{

        // scanner to parse the file again
        Scanner sc2 = new Scanner(file);

        JSONObject flowsFragmentData = getFlowsFragments(file);

        // define the patttern that will be used to find in the file 
        Pattern fragmentNamePattern = Pattern.compile("\\sFragment name:\\s((flow\\d*)(Fragment\\d*))");
        Pattern fragmentNodePattern = Pattern.compile("\\sFragment node:\\s(switch\\d*)");
        Pattern flowNamePattern = Pattern.compile("Flow name:\\s(Flow\\d*)");
        Pattern fragmentNextNodePattern = Pattern.compile("\\sFragment next hop:\\s(switch\\d*|dev\\d*)");
        Pattern devPattern = Pattern.compile("(dev\\d*),");
        Pattern leavesPattern = Pattern.compile("\\sList of leaves:");
        Pattern pathToPattern = Pattern.compile("\\sPath\\sto\\s(dev\\d*):");

        // varibles to auxiliate and control actions
        String flowFragmentName = "";
        String flowName = "";
        String flow = "";
        String controller = "";
        int lineCounter = 0;

        // parse the file
        while (sc2.hasNextLine()){
            lineCounter++;
            String line = sc2.nextLine().toString();

            // matchers to find patterns
            Matcher fragmentNameMatcher = fragmentNamePattern.matcher(line);
            Matcher fragmentNodeMatcher = fragmentNodePattern.matcher(line);
            Matcher flowNameMatcher = flowNamePattern.matcher(line);

            if(fragmentNameMatcher.find()){
                String fragmentName = fragmentNameMatcher.group(1).toString();
                String flowPart = fragmentNameMatcher.group(2).toString();

                flowFragmentName = fragmentName;
                flowName = flowPart;

                for(int i = 0; i < flowsFragmentData.optJSONArray(flowPart).length(); i++){
                    for(int j = 0; j < flowsFragmentData.optJSONArray(flowPart).optJSONArray(i).length(); j++){
                        JSONObject objInfo = flowsFragmentData.optJSONArray(flowPart).optJSONArray(i).optJSONObject(j);
                        if(objInfo.optString("FlowAndFragment").equals(fragmentName)){
                            controller = "true";
                            // System.out.println(objInfo.optString("FlowAndFragment") + " Line:" + lineCounter);
                        }
                    }
                }
            }

            if(fragmentNodeMatcher.find() && controller.equals("true")){
                String switchName = fragmentNodeMatcher.group(1).toString();

                for(int i = 0; i < flowsFragmentData.optJSONArray(flowName).length(); i++){
                    for(int j = 0; j < flowsFragmentData.optJSONArray(flowName).optJSONArray(i).length(); j++){
                        JSONObject objInfo = flowsFragmentData.optJSONArray(flowName).optJSONArray(i).optJSONObject(j);
                        String ObjSwitch = objInfo.optString("switch");

                        if(controller.equals("false")){
                            continue;
                        }

                        if(objInfo.optString("FlowAndFragment").equals(flowFragmentName)){
                            if(objInfo.optString("switch").equals(switchName)){
                                controller = "false";
                                // System.out.println(objInfo.optString("switch") + " " + switchName + " Line:" + lineCounter);
                            }else{
                                System.out.println("found " + "Line: " + lineCounter);
                            }
                        }
                    }
                } 
            }

        }

        // System.out.println(flowsFragmentData.optJSONArray("flow0").optJSONArray(0));
        // System.out.println(flowsFragmentData.optJSONArray("flow0"));
        // System.out.println(flowsFragmentData.optJSONArray("flow0").optJSONArray(0).optJSONObject(0));
        // System.out.println(flowsFragmentData.optJSONArray("flow0").length());

        sc2.close();
    }    

    public static JSONObject getMultiPath(File file) throws FileNotFoundException{

        Scanner sc2 = new Scanner(file);

        JSONObject paths = new JSONObject();
        // JSONObject paths2 = new JSONObject();

        Pattern pathToPattern = Pattern.compile("\\sPath\\sto\\s(dev\\d*):");
        Pattern pathToWholeLinePattern = Pattern.compile("\\sPath\\sto\\s(dev\\d*):\\s(dev\\d*),\\s(switch\\d*)\\(flow\\d*Fragment\\d*\\),\\s(switch\\d*)\\(flow\\d*Fragment\\d*\\),\\s(dev\\d*),");
        Pattern devPattern = Pattern.compile("\\s(dev\\d*),\\s");
        Pattern switchPattern = Pattern.compile("(switch\\d*)\\(flow");
        Pattern leavesPattern = Pattern.compile("\\sList of leaves:");
        Pattern flowNamePattern = Pattern.compile("\\sFlow name:\\s(flow\\d*)");

        
        int lineCounter = 0;
        // String controller = "";
        int flowNumber = -1;

        while (sc2.hasNextLine()){
            lineCounter++;
            String line = sc2.nextLine().toString();

            Matcher pathToMatcher = pathToPattern.matcher(line);
            Matcher pathToWholeLineMatcher = pathToWholeLinePattern.matcher(line);
            Matcher devMatcher = devPattern.matcher(line);
            Matcher switchMatcher = switchPattern.matcher(line);
            Matcher leavesMatcher = leavesPattern.matcher(line);
            Matcher flowMatcher = flowNamePattern.matcher(line);
            
            if(flowMatcher.find()){
                flowNumber++;
                paths.put("flow"+flowNumber, new JSONArray());
                // controller = "false";
                // System.out.println(flowMatcher.group(1));
            }

            // if(pathToWholeLineMatcher.find()){
            //     JSONArray hops = new JSONArray();

            //     hops.put(pathToWholeLineMatcher.group(2));
            //     hops.put(pathToWholeLineMatcher.group(3));
            //     hops.put(pathToWholeLineMatcher.group(4));
            //     hops.put(pathToWholeLineMatcher.group(5));

            //     JSONArray aux = new JSONArray();
            //     aux.putAll(hops);
            //     paths2.append("flow"+flowNumber, aux);
            // }

            // find the start of the path then create a hashmap to save data
            if(pathToMatcher.find()){
                // controller = "true";
                JSONArray hops = new JSONArray();

                if(devMatcher.find()){
                    String dev = devMatcher.group(1);

                    hops.put(dev);

                    // System.out.println(dev);                    
                }

                while(switchMatcher.find()){
                    String switchName = switchMatcher.group(1);

                    hops.put(switchName);

                    // System.out.println(switchName);                    
                }
                
                if(devMatcher.find()){
                    String dev = devMatcher.group(1);

                    hops.put(dev);

                    // System.out.println(dev);                
                }

                JSONArray aux = new JSONArray();
                aux.putAll(hops);
                paths.append("flow"+flowNumber, aux);
                
                // System.out.println(pathToMatcher.group(1));
            }
        }

        sc2.close();
        return paths;
    }

    public static JSONObject getFlowsFragments(File file) throws FileNotFoundException{

        Scanner sc2 = new Scanner(file);

        JSONObject fragments = new JSONObject();
        // JSONObject paths2 = new JSONObject();

        Pattern pathToPattern = Pattern.compile("\\sPath\\sto\\s(dev\\d*):");
        Pattern devPattern = Pattern.compile("\\s(dev\\d*),\\s");
        Pattern flowFragmentPattern = Pattern.compile("(switch\\d*)\\((flow\\d*Fragment\\d*)\\),");
        Pattern flowNamePattern = Pattern.compile("\\sFlow name:\\s(flow\\d*)");

        
        int lineCounter = 0;
        int flowNumber = -1;

        while (sc2.hasNextLine()){
            lineCounter++;
            String line = sc2.nextLine().toString();

            Matcher pathToMatcher = pathToPattern.matcher(line);
            Matcher devMatcher = devPattern.matcher(line);
            Matcher flowFragmentMatcher = flowFragmentPattern.matcher(line);
            Matcher flowMatcher = flowNamePattern.matcher(line);
            
            if(flowMatcher.find()){
                flowNumber++;
                fragments.put("flow"+flowNumber, new JSONArray());

                // System.out.println(flowMatcher.group(1));
            }

            if(pathToMatcher.find()){
                // controller = "true";
                JSONArray flowsFragmentsNode = new JSONArray();

                while(flowFragmentMatcher.find()){
                    JSONObject switchFlowAndFragment = new JSONObject();
                    String flowFragment = flowFragmentMatcher.group(2);
                    String switchName = flowFragmentMatcher.group(1);

                    switchFlowAndFragment.put("switch", switchName);
                    switchFlowAndFragment.put("FlowAndFragment", flowFragment);

                    flowsFragmentsNode.put(switchFlowAndFragment);

                    // System.out.println(flowFragment);                    
                }
        
                JSONArray aux = new JSONArray();
                aux.putAll(flowsFragmentsNode);
                fragments.append("flow"+flowNumber, aux);
                
                // System.out.println(pathToMatcher.group(1));
            }
        }

        // System.out.println(((JSONArray) fragments.get("flow0")).getJSONArray(0));

        sc2.close();
        return fragments;
    }
}



