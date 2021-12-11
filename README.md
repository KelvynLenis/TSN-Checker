# TSN-logs-checker
TSN provides a three outputs files: .log, .out and a json file. Both .log and .out files contain logs of time and paths of flows and switches of the run. That being said it's possible to be generated unexpected results that don't raise erros at runtime. Checking line by line might be infeasible due to the great extension of the file so it was made a Checker to validate the output. This checker works on any of the three files.

### Criterias:

There are some points we need to check. They were selected based on these criterias:

- Criteria 1: All values of time(Departure, arrival and scheduled times) are positive (Typechecking-value)
- Criteria 2: Time of sent plus duration time of transmission must be equal to the scheduled time. (Well formed hops)
- Criteria 3: Consistent path nodes.
- Criteria 4: Transmission windows consistency.


## How to run

There is a jar file in the folder Checker called "Checker.jar", in a terminal just run the following command passing the path to the json and .log file. The JSON must be passed first followed by the .log file. The result will be show in the terminal:

```
java -jar CheckerJar.jar [path to the json file] [path to the .log file]
```

## Typechecking value

Below its an example of how the TSN outputs logs of time in the .log file. It brings data about the departure, arrival and scheduled for there can't be negative time in the real world the Checker validates wheter it happens or not for every time log.

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
During runtime it might happen TSN generate a path that is not supposed to exist or to be at certain flow. There is a need then to check wheter the paths are well made or not, so this checker also validates it by comparing the path to a device with the hops data.


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

## Tranmission windows consistency

So basically there can't be two or more packets being transmitted at the same time on the same port. Other thing to considerate is that there is a maximum limit for sending packets so if a packt is to be sent somewhere between 200 and 250 microsseconds(50 is the maximu value allowed) it can't be sent after this interval, so we also need to check it out. Those informations are brought in the json file.

This is how both start of transmission and transmission time are showed. We need to check if they start at the same time and if they don't overpass its limits.
```
"prioritySlotsData": [
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
