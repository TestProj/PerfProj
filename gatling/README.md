Gatling Health Check
=========================

Simple showcase of a maven project using the gatling-maven-plugin.

* If you have Gatling Tests , this might be reference project to validate your Application health point

* To Test using Docker Image:

```
mvn gatling:test -Dgatling.simulationClass=Echo.EchoSimulation -DpeakTPS=1 -DrampupTime=1 -DsteadyStateTime=1
```

* To test it out on local , simply execute the following command for 1 TPS , Ramp up 1 mins and Steady duration for 1 min:
```
mvn clean install -Dgatling.simulationClass=Echo.EchoSimulation  -DpeakTPS=1 -DrampupTime=1 -DsteadyStateTime=1 -Durl=http://localhost:8080 -Dquery=/
```
