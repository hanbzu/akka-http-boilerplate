package com.example.main

import akka.actor._
import akka.util.Timeout
import scala.concurrent.duration._
import scala.language.postfixOps

class Uppercaser extends Actor with ActorLogging {

  implicit val timeout = Timeout(5 seconds)

  def receive: Receive = {
    case word: String ⇒
      sender ! word.toUpperCase
  }
}

object Uppercaser {
  def props: Props =
    Props(new Uppercaser)
}
