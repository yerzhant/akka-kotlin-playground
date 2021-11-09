package org.example

import akka.actor.typed.ActorSystem

fun main() {
    val app = ActorSystem.create(App.create(), "app")
    app.tell("start")
}