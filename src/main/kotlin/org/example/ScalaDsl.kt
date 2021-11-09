package org.example

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.AbstractBehavior
import akka.actor.typed.scaladsl.ActorContext
import akka.actor.typed.scaladsl.Behaviors

class ScalaDsl private constructor(context: ActorContext<Cmd>) : AbstractBehavior<ScalaDsl.Cmd>(context) {

    interface Cmd
    data class PrintMe(val msg: String) : Cmd

    override fun onMessage(msg: Cmd?): Behavior<Cmd> {
        when (msg) {
            is PrintMe -> (::onPrintMe)(msg)
        }

        return this
    }

    private fun onPrintMe(cmd: PrintMe) {
        context().log().info(cmd.msg)
    }

    companion object {
        fun create(): Behavior<Cmd> = Behaviors.setup(::ScalaDsl)
    }
}