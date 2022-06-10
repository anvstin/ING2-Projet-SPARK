# ING2-Projet-SPARK

To launch the application, run the following commands:
```bash
./setup.sh
./start.sh
```

## Subject

Peaceland understands this is beyond their team limits, it can not put in place a programm to deal with the drone’s data. Peaceland asks you for advice to design an architecture allowing them to create a product they could sell to different police forces.

## Situation

Peaceland is a blessed country, led by an affable and clear-sighted ruler. He takes great pride in its effort to bring peace, happiness, and harmony to all its citizens. 
To do so, they heavily rely on their peacemakers. A governmental agency dedicated to make peace around the country. To reach their ambition, they bring assistance to any agitated person and help them to recover peace. More generally they help citizen to stay in line with their country harmonious goal

To help its peacemakers squads, Peaceland engineers have created a working autonomous drone called peacewatcher.
They need you to create the program that will receive and manage peacewatchers’s data.
This program must 
store every peacewatcher data
trigger alerts
enable peacemaker officers to perform analysis on peacewatcher data


## Question 1

The constraints are like the problem Amazon faced in the authentication of Amazon employees in its warehouses. Our solution must:

*	Endure activity peaks (likely in the morning when people go to work, at lunch time, after work, …)
*	Store a large quantity of small reports quickly (fast to write but not to read archives)
*	Real-time processing of data

As such, we will use stream retention to allow the data to be stored quickly across multiple machines. It also allows us to store data quickly during activity and is best suited to store a large quantity of small reports. However, this implies slowness when reading archived data (the search for an event requires to replay a stream). It is not a problem in our case since we only need to access archived data to create statistics.


## Question 2

The detection of case to send an alert must be as rapid as possible.
We need to avoid the spread of the lack of peace and stop the troublemaker as quicky as possible.
We will then use a real-time monitoring consumer to allow the management of the data quickly and efficiently.

## Question 3

The Peaceland team is composed only of Data Scientists. For a project that meets such expectations, it appears that combining Data Analyst, Data Engineer or Architect and finally Data DevOps with Data Scientist is essential. 

By the lack of Data Engineer/Architect in their team, it is possible that their choice related to storage was inappropriate. For example, if their team chooses an SQL approach, there are several problems such as: 

* Very ill-suited solution to this type of situation 
* Difficult to scale  
* Slow to write large volumes of small data 

## Question 4

Here is some technical information that could improve peaceland drones’ efficiency: 

- If an alert is sent, it potentially misses the action performed by the citizen that made him lose his score, which will then allow to try to prevent rather than cure.  

- The date and time of the report are missing which can be useful for analyzing data and making report. 
