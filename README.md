# Recommender-System
We have designed a collaborative user-based recommendation system by taking GroupLens data set of size - 100K. We have used Apache Mahout 
to build recommendation system which provides rich set of components.
Project (maven based) conists of 3 Java files: HelloWorldService.java, EvaluateRecommender.java, OutputFormat.java. <br />  
Jersey (RestAPI) and Jackson are used to connect User Interface (web browser) to local host. To run the project we should have Jersey
and Jackson jar files, in addition, slf4j-nop-1.7.25 jar file; and mahout-core dependency in pom.xml file.

## HelloWorldService.java: <br />
Program execution starts with HelloWorldService.java. 
User enters his/her id and required number of recommendations through user interface (web browser) to developed system and the user details
are send to HelloWorldService.java through ajax, then based on the userid and similarity, neighborhood is created and recommedations from 
that neighborhood are displayed in the browser. <br />

## EvaluteRecommender.java: <br />
This class checks whether our recommendation results are correct or not. To check the accuracy of our results, we used hold-out test. 
We partitioned our dataset into two sets: a training data set consisting of 80% and 90% while testing data consisting of 20% and 10% data 
respectively. Then we train our recommender using the training set and look how well it predicts the unknown values for the given data set.
To test our recommender, we implement buildRecommender() internally and make it setup for our user-based recommender.
After testing with 20% and 10% testing each for 5 instances, we got error within [0.7, 4.8].

