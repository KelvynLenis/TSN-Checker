
# TSN-logs-checker
TSN provides a three outputs files: .log, .out and a json file. Both .log and .out files contain logs of time and paths of flows and switches of the run. That being said it's possible to be generated unexpected results that don't raise erros at runtime. Checking line by line might be infeasible due to the great extension of the file so it was made a Checker to validate the output. This checker works on any of the three files. Those files however must meet the required format as they were made to find patterns and specific nomenclatures to make its validation.

### Table of Contents

- [Criterias used for validation](#criterias)
- [Categories](#categories)
  * [Sintax](#sintax)
  	* [Data Type](#data-type)
  	* [File Type](#file-type)
  * [Semantic](#semantic)
  	* [Flow](#flow)
  		* [Paths](#paths)
  		* [Ports](#ports)
  - [How to run](#how-to-run)



# Criterias

There are some topics we need to check. They were selected based on the following criterias:

- Criteria 1: All values of time(Departure, arrival and scheduled times) are positive (Typechecking-value)
- Criteria 2: Time of sent plus duration time of transmission must be equal to the scheduled time. (Well formed hops)
- Criteria 3: Consistent path nodes.
- Criteria 4: Transmission windows consistency.

# Categories

Amoung the many validation it may become hard to track so we considered and approach to categorize them in order to ease management and search for a specific issue. We then separated or validations into subjects and subtopics which are constantly growing as we develop more validation, but it starts by the principle of semantic and sintatic. Sintatic category is about the sintatic part as for misspeling  


<!-- Sintaxe -> is it a number? is it a json file?
Semantica -> flows ->  path -->

![image](https://user-images.githubusercontent.com/52057929/152686739-082f7a50-f8e4-4a31-b046-7db7fe63d36b.png)


<!--
-> Network -> Protocol -> Layer -> TSN -> Output -> Values(Calculation and Generation) - Path(Hops) - Scheduling(Transmission and Window)
-->

# Sintax
Throughout the checker we make some validation which may be separated at two large groups of target. We are assigning here all the sintatic validations and what we mean by it. This checker receives a file from the user and the first step is to ensure all data meet the requirements to proceed its validations as it's the base for semantic validation. Sintatic validations are more easy to be seen as they raise attetion of the viewer, for instace a sequence of random characters instead of a two digit number.

### Data Type
This subtopic refers to the validation of data type as mentioned earlier a letter in the place of a number, but not only that as this tool is subject to be constantly updated then we may be adding some others sintatic validations. The checker verifies the data type of information brought into the file to be validated to ensure they are the type it's meant to be.

### File Type
This subtopic refers to the validation of files type, just like the one before but now aiming the files only. It's already known by now that this checker may receive two files one being a JSON file and the other an output file with .OUT extension. So we make a quick verification to ensure the file passed correspond to one of the files mentioned before.

# Semantic
For we have made verification of the sintatic of our file now we are to face the semantic verification, this means the correctness of the data in a more depth point of view. The semantic differs from the sintax at adopting a more technical view of situation, so it may not be so clear to the user that doesn't comprehend the situation and the behavior of the TSNSched or it may just pass unnoticed by even experienced users as the topology may the too large, which is the purpose of this project, that is help users make validation. In short, semantic validations aims logical question, for instance, transmission issues like the flow and its transmission of packet on a port and transmission window.


## Flow
This is a subtopic which validates the flow data and issues related to it we could cite transmission ports, transmission window and hops to end devices. The flow then can be divided again into more specific subtopic like the Ports and Path questions. Following we detail more of them. Notice that this project is constantly being updated and we intend to extend this more accordingly to the needs, so may add others topics.

### Paths
The flows contain data about the hops from the origin device to the end device and so there may occour that a node be missing or the scheduling inserting a node in the wrong place. We then made a validation to ensure the path to the end devices follows as they are supposed to on each flow.

### Ports
This subtopic refers to validations of the ports data to ensure they make sense. The ports contain some important data for TSNSched like transmission of packets and its cycle and window, those are critical to us as they represent the core of TSN and the Scheduling problem, <a href="https://github.com/ACassimiro/TSNsched" target="_blank">Check it out</a> for more details about it.

<!--
NOTES:
- SINTATIC
- SEMANTIC
- FLOWS
- WHAT'S INSIDE FLOWS?
-->
## How to run

There is a jar file in the folder Checker, in a terminal just run the following command passing the path to the .log or json file, the result will be show in the terminal:

```
java -jar CheckerJar.jar [path to the .log file]
```

## Typechecking value

Below its an example of how the TSN outputs logs of time in the .log file. It brings data about the departure, arrival and scheduled for there can't be negative time in the real world the Checker validates whether it happens or not for every time log.

```
(0) Fragment departure time: 41.0
(0) Fragment arrival time: 42.0
(0) Fragment scheduled time: 55.002
```

## Well formed hops

TSN schedules and control the transmission of packets on the flows based on time, as it's a time sensitiy network, so time is the most important thing to be considerated. It's important that flows respect its schedule to minimize latency and jitter. It can be validated by adding slot start and the slot duration and compare to the scheduled time to validate it as show below:

```
Fragment slot start 0: 42.01 ; 4201/100
Fragment slot duration 0 : 13.0 ; 13
Fragment scheduled time: 55.01
```


## Consistent path nodes
During runtime it might happen TSN generate a path that is not supposed to exist or to be at certain flow. There is a need then to check whether the paths are well made or not, so this checker also validates it by comparing the path to a device with the hops data.


This is the path to a device called dev5, we hope the flow to follow as it says.
```
 Path to dev5: 
dev38, 
switch7(flow1Fragment1), 
switch0(flow1Fragment2), 
switch1(flow1Fragment3), 
dev5,
```

So we check the hops to see if it follows that flow and get to where it's supposed to.

```
Fragment name: flow1Fragment1
        Fragment node: switch7
        Fragment next hop: switch0
```
```
Fragment name: flow1Fragment2
        Fragment node: switch0
        Fragment next hop: switch1
```
```
Fragment name: flow1Fragment3
        Fragment node: switch1
        Fragment next hop: dev5
```

## Transmission windows consistency
So basically there can't be two or more packets being transmitted at the same time on the same port. Other thing to considerate is that there is a maximum limit for sending packets so if a packt is to be sent somewhere between 200 and 250 microsseconds(50 is the maximu value allowed) it can't be sent after this interval, so we also need to check it out. Those informations are brought in the json file.


This is how both start of transmission and transmission time are showed. We need to check if they start at the same time and if they don't overpass its limits.
```
"cycleDuration": 500.0,
"name": "eth203",
"firstCycleStart": 0.0
"prioritySlotsData": 
[
	{
	  "slotsData": [
	    {
	      "slotDuration": 0.576,
	      "slotStart": 499.424
	    }
	  ],
	  "priority": 0
	},
	{
	  "slotsData": [
	    {
	      "slotDuration": 0.576,
	      "slotStart": 489.568
	    }
	  ],
	  "priority": 1
	}
]
```
