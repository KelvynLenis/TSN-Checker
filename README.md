# TSN-logs-checker
TSN provides a three outputs files: .log, .out and a json file. Both .log and .out files contain logs of time and paths of flows and switches of the run. That being said it's possible to be generated unexpected results that don't raise erros at runtime. Checking line by line might be infeasible due to the great extension of the file so it was made a Checker to validate the output. This checker works on any of the three files.

### Criterias:

There are some topics we need to check. They were selected based on the following criterias:

- Criteria 1: All values of time(Departure, arrival and scheduled times) are positive (Typechecking-value)
- Criteria 2: Time of sent plus duration time of transmission must be equal to the scheduled time. (Well formed hops)
- Criteria 3: Consistent path nodes.
- Criteria 4: Transmission windows consistency.

# Categories

We have to consider what kind of issue we are facing and how to manage and solve them. We separated them into subjects to categorize for better visualization. 

![image](https://user-images.githubusercontent.com/52057929/151662577-5f5d1c35-ba44-4e76-a395-4be282bc7a7b.png)

<!--
-> Network -> Protocol -> Layer -> TSN -> Output -> Values(Calculation and Generation) - Path(Hops) - Scheduling(Transmission and Window)
-->

## Value Error
This refers to any problem found on the values presented on the output file, like values that are different from what is expected, for instance, a negative value, wrong cycle duration or wrong calculation.

### Calculation
This is as subtopic of the value verification which validates calculation errors.

### Generation
This is a subtopic of the value verification which validates the generation of values on the output. A wrong value may not be exactly a problem of the network, but rather a simple mistake when building the output file.

## Path Error
This refers to packets verification on a determined flow so that they are not breaking its flow and going some node outside the path described by the scheduling to its destination.

### Hops
This is a subtopic which aims to validate the flow of a packet to a certain device to ensure it follows as it's supposed.

## Scheduling Error
This refers to any kind of problem found on the scheduling, for instance, whenever a packet is being mistakely transmitted, which can be two or more packets on same port at same time or trespassing its cycle and being transmitted over its transmission window.

### Port
This is a subtopic which validates the transmission ports and times to ensure they are not colliding or there are any other errors alike.

### Window

This validates questions about the transmission window, so whether or not a packet is overpassing its limits and other question alike.

<!--
NOTES:
- LAYER 2 NETWORK
- IEEE 802.1Q OSI LAYER 2
- PTP(PRECISION TIME PROTOCOL)
- ETHERNET HEADER CONTENT NOT LIMITED BY INTERNET PROTOCOL
- CENTRAL NETWORK CONTROL & CENTRAL USER CONTROL(CNC AND CUC)
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
