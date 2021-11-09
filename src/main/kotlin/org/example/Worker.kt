package org.example

import akka.actor.typed.Behavior
import akka.actor.typed.javadsl.AbstractBehavior
import akka.actor.typed.javadsl.ActorContext
import akka.actor.typed.javadsl.Behaviors
import akka.actor.typed.javadsl.Receive

class Worker private constructor(context: ActorContext<Command>) : AbstractBehavior<Worker.Command>(context) {
    interface Command
    data class SomeMsg(val aString: String) : Command

    override fun createReceive(): Receive<Command> = newReceiveBuilder()
        .onMessage(SomeMsg::class.java, ::onSomeMsg)
        .build()

    private fun onSomeMsg(msg: SomeMsg): Behavior<Command> {
        context.log.info(msg.aString)
        return this
    }

    companion object {
        fun create(): Behavior<Command> = Behaviors.setup(::Worker)
    }
}