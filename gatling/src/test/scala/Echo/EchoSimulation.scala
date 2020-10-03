package Echo

import java.text.SimpleDateFormat
import java.util
import java.util.{Date, TimeZone}

import scala.collection.JavaConversions._
import scala.util.parsing.json.JSON

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import io.gatling.core.structure.PopulationBuilder
import jdk.nashorn.internal.parser.JSONParser

import scala.collection.mutable
import scala.concurrent.duration._
import scala.io.Source
import org.apache.commons.lang3.StringEscapeUtils

import scala.concurrent.duration._

class EchoSimulation extends Simulation {

  val logger = org.slf4j.LoggerFactory.getLogger(this.getClass)

  System.setProperty("sse.enableSNIExtension", "false");
  var BASE_URL = System.getProperty("url", "http://localhost:8080").toString
  var baseurl=""
  var query = System.getProperty("query", "/").toString

  var httpProtocol = http;

  httpProtocol = http
    .baseUrl(BASE_URL)
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .connectionHeader("keep-alive")
    .doNotTrackHeader("1")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:1.0) Gecko/20100101 Firefox/19.0")
    .header("Referer", BASE_URL)
    .disableCaching


  /** *TPS Injection ***/
  var npeakTps: Double = System.getProperty("peakTPS", "1.0").toDouble
  // Peak trasaction per sec. Default 1 per sec.
  var nrampUpTime: Double = System.getProperty("rampupTime", "1").toInt
  // Time to ramp up load from 0 to peak load. Default 1 min.
  var nsteadyStateTime: Double = System.getProperty("steadyStateTime", "1").toInt // Duration to run the test at Peak load. Default is 1 min.

  val EchoScenario = scenario("EchoScenario")
               .exec(EchoRunner.getHttp(BASE_URL, query))

  setUp(
    EchoScenario.inject(
      rampUsersPerSec(0.5) to npeakTps.toDouble during (nrampUpTime.toInt minutes),
      constantUsersPerSec(npeakTps.toDouble) during (nsteadyStateTime.toInt minutes)
    )
  ).protocols(httpProtocol)
}