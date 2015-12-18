package com.example.main

import akka.pattern.ask
import akka.actor.ActorRef
import akka.util.Timeout
import com.example.exampleCustomSerializers
import org.joda.time.format.DateTimeFormat
import org.json4s._
import spray.http.{MediaTypes}
import spray.http.StatusCodes._
import spray.httpx.Json4sSupport
import language.postfixOps
import scala.concurrent.duration._
import scala.concurrent._
import scala.util.{Success, Failure}
import ExecutionContext.Implicits.global
import spray.routing._
import spray.routing.directives.DebuggingDirectives
import MediaTypes._

object Json4sProtocol extends Json4sSupport {
  implicit def json4sFormats: Formats = DefaultFormats ++ exampleCustomSerializers.all
}

object Main extends App with SimpleRoutingApp {

  implicit val actorSystem = com.example.ActorSystemHelper.system

  // Actor refs
  val uppercaser = actorSystem.actorOf(Uppercaser.props, "uppercaser")

  val portEnv = sys.env.getOrElse("PORT", "8088").toInt

  startServer(interface = "0.0.0.0", port = portEnv) {

    import Json4sProtocol._

    DebuggingDirectives.logRequest("get-user")

    get {
      path("uppercase" / "\\w+".r) { word ⇒
        implicit val timeout = Timeout(5 seconds)
        val reply = uppercaser ? word
        onComplete(reply) {
          case Success(uword: String) => complete(uword)
          case Success(_) => complete("server error, unknown response")
          case Failure(ex)
          => complete("server error")
        }
      }
    }
  }
}
