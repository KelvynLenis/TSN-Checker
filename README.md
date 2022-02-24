
# TSN-logs-checker
The <a href="https://github.com/ACassimiro/TSNsched">TSNSched</a> provides three outputs files: .log, .out and a JSON file. The log and JSON files brings the same information but in some different ways as they are structured differently. That being said it's possible to be generated unexpected results that don't raise erros at runtime. Checking either the logs or the JSON line by line might be infeasible due to the great extension of the file so we made a Checker to validate the output on those files. This checker receives both of them as input that have an order to be passed, the json file as the first argument followed by the log file. Those files however must meet the required format as they were made to find patterns and specific nomenclatures to make its validation so changes on the spelling causes this checker to no longer work appropriately. Notice that this project is constantly being updated and we intend to extend this more accordingly to the needs, so we may be adding more validations, features and increasing this documentation.

### Table of Contents

- [Criterias](#criterias)
  - [Typechecking value](#typechecking-value)
  - [Well formed hops ](#well-formed-hops)
  - [Consistent path nodes](#consistent-path-nodes)
  - [Transmission windows consistency](#transmission-windows-consistency)
- [Categories](#categories)
  * [Sintax](#sintax)
    * [Data Type](#data-type)
    * [File Type](#file-type)
  * [Semantic](#semantic)
    * [Switches](#switches)
      * [Ports](#ports)
    * [Flow](#flow)
      * [Paths](#paths)
      * [Packet times](#packet-times)
 - [How to run](#how-to-run)

# Criterias

There are some topics we need to check. They were selected based on the following criterias:

- Criteria 1: All values of time(Departure, arrival and scheduled times) are positive (Typechecking-value)
- Criteria 2: Time of sent plus duration time of transmission must be equal to the scheduled time. (Well formed hops)
- Criteria 3: Consistent path nodes.
- Criteria 4: Each packet must be transmitted at its correspondent priority window.
- Criteria 5: Two packets can't be transmitted at same time at same port.
- Criteria 6: Packets at the same port of same priority must be sent in arrival order(FIFO like).

# Categories

Amoung the many validations we've made, it may become hard to track so we considered an approach to categorize them in order to ease management and search for a specific issue. We then separated or validations into topics and subtopics which are constantly growing as we develop more validations. It's divided by two major groups: semantic and syntactic. Syntactic category is about the syntactic part as for misspeling whilst semantic is related to more deep validation of the data and what they represent because may all the data look good but hold a critical error.

![image](https://user-images.githubusercontent.com/52057929/152810672-e8107856-7116-404c-aa3c-d24552c08b0c.png)
<h6>Categories' Tree</h6>


# Sintax
Throughout the checker we make some validation which may be separated at two major groups, each with different purposes. We are assigning here all the syntactic validations. This checker receives two files from the user and the first step is to ensure all data meet the requirements such as data and file types to proceed its validations as it's the base for semantic validation. Syntactic errors are more easy to be noticed as they raise attetion as soon as spotted, for instace some letters in the place of a number.

### Data Type
This subtopic refers to the validation of data type, as mentioned earlier a letter in the place of a number and others validations like that. The checker verifies the data type of information brought into the file to be validated to ensure they are the type it's meant to be.

### File Type
This subtopic refers to the validation of files type, just like the one before but now aiming the files only. It's already known by now that this checker receives two files one being a JSON file and the other a log file that can be on the .log format extension or a simples text(.txt) extension. So we make a quick verification to ensure the file passed corresponds to one of the files mentioned before.

# Semantic
For we have made verification of the syntactic of our file now we are to face the semantic verification, this means the correctness of the data in a more depth point of view. The semantic differs from the sintax at adopting a more technical view of situation, so it may not be so clear to the user that doesn't comprehend the behavior of the TSNSched or it may just pass unnoticed by even experienced users as the topology may be too large, which is the purpose of this project, that is, to help users make verifications. In short, semantic validations aims logical question, for instance, transmission of packet on a port and its transmission window.


## Switches 
This is a subtopic which validates the data found on a switch. But What does a switch holds? it holds the cycle duration of each switch, the priorities assigned to flows, the TSN time slots allocated for each priority at each port of each switch.

### Ports
This subtopic refers to validations of the ports data to ensure they make sense. The ports contain some important data for TSNSched like the start of transmission of the packets, its cycle duration and time duration of the transmission took to be completed. These data are critical as they represent the main subject of the TSNSched as it proposes a solution to the scheduling problem of TSN <a href="https://github.com/ACassimiro/TSNsched" target="_blank">Check it out</a> for more details about it.

## Flow
This is a subtopic which validates the flow data and questions related to it. You may find on a flow the average latency, jitter, data time of the packets and hops to end devices all inside the json file, the complete details for the fragments can be found on the log file. The data on the log file is the combination of data found on switches on json file and the flow on json file for each hop so the data brought is completly detailed of a flow fragment. The flow then can be divided int couple more specific subtopics, they're paths and packet times. We discusss more about them on their respectives subtopics.

### Paths
The flows contain data about the hops from the origin device to the end device. It describes each hop from the first device to the end device by presenting the current node, the destination node and the priority of the hop. If you look to the log file  you'll also see the data of each flow fragment in details. So there may occour that a node be missing or the scheduling insert a node in the wrong place. We then made a validation to ensure the path to the end devices follows as they are supposed to on each flow.


### Packet Times
A flow is broken into fragments and it also contains data of the packets. This data on the json file includes the time when it left the previous node, the time it arrived at the current node and the time when it was sent to the next node. On the log file under the respective flow fragment the information is more detailed as it brings the current hop(the origin and destination), the priority of the fragment and those informations mentioned before: the arrival and departure times. These information are the core of TSNsched, they are critical for the deterministic network we look to accomplish so we verify each of these data to ensure they are correct and the schedule works as intended.

## How to run

There is a jar file inside the folder Checker, in a terminal just run the following command passing the path to the json file followed by the log file, the result will the been shown in the terminal:

```
java -jar CheckerJar.jar [path to the .json file] [path to the .log file]
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
