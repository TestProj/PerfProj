package Echo

import common._
import com.typesafe.config._
import java.text.SimpleDateFormat
import java.util.{Date, TimeZone}

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._

object EchoRunner {


  def postHttp (payload: String, requestName: String, variables: String) : ChainBuilder = {

    val logger = org.slf4j.LoggerFactory.getLogger(this.getClass)
    val requestPayload = payload
    val requestPath = "/health/full"

    val op_post =

     exec(
          http(requestName)
            .post(requestPath)
            .header("Accept","text/plain")
            .header("Content-Type","application/json; charset=UTF-8")
            .header("Accept-Encoding","gzip,deflate")
            .body(StringBody("""{"query":""""+requestPayload+"""","variables":"""" + variables + """"}"""))
            .check(status.is(200))
            .check(status.saveAs("HttpStatus"))
            .check(responseTimeInMillis.saveAs("execLatency"))
            .check(bodyString.saveAs("responsePayload"))
        )

    return op_post
  }

  def getHttp (query: String, requestName: String ) : ChainBuilder = {

    val logger = org.slf4j.LoggerFactory.getLogger(this.getClass)
    val requestPath = query

    val op_get =

      exec(
        http(requestName)
          .get(requestPath)
            .header("Accept","text/plain")
//          .header("Accept","application/json")
          .header("Content-Type","application/json; charset=UTF-8")
          .header("Accept-Encoding","gzip,deflate")
          .check(status.is(200))
          .check(status.saveAs("HttpStatus"))
          .check(responseTimeInMillis.saveAs("execLatency"))
      )

    return op_get
  }

  }
