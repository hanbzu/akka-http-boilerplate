package com.example

import akka.actor.ActorSystem

object ActorSystemHelper {
  val system = ActorSystem("example-api")
}
