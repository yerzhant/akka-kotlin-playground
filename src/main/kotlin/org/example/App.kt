package org.example

import akka.actor.typed.Behavior
import akka.actor.typed.javadsl.AbstractBehavior
import akka.actor.typed.javadsl.ActorContext
import akka.actor.typed.javadsl.Behaviors
import akka.actor.typed.javadsl.Receive

class App private constructor(context: ActorContext<String>) : AbstractBehavior<String>(context) {
    override fun createReceive(): Receive<String> = newReceiveBuilder()
        .onMessageEquals("start", ::start)
        .build()

    private fun start(): Behavior<String> {
        val scalaDsl = context.spawn(ScalaDsl.create(), "scala-dsl")
        scalaDsl.tell(ScalaDsl.PrintMe("Hi scala dsl"))

        val worker = context.spawn(Worker.create(), "worker")
        worker.tell(Worker.SomeMsg("Hi", scalaDsl))

        return this
    }

    companion object {
        fun create(): Behavior<String> = Behaviors.setup(::App)
    }
}