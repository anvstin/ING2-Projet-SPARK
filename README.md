# SPARK

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

