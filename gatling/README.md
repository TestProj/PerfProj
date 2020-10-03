Gatling Health Check
=========================

Simple showcase of a maven project using the gatling-maven-plugin.

* If you have Gatling Tests , then add in Test folder

* To Test using Docker Image:
```
* Docker Image of Gatling test of Greeting Hello:

* Command to run :
mvn gatling:test -Dgatling.simulationClass=Echo.EchoSimulation -DpeakTPS=1 -DrampupTime=1 -DsteadyStateTime=1
```

To test it out on local , simply execute the following command for 10 TPS , Ramp up 5 mins and Steady duration for 30 mins:

    $mvn clean install -Dgatling.simulationClass=Echo.EchoSimulation  -DpeakTPS=1 -DrampupTime=1 -DsteadyStateTime=1 -Durl=http://localhost:8080 -Dquery=/

