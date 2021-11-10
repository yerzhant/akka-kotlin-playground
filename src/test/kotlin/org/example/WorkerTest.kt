package org.example

import akka.actor.testkit.typed.javadsl.TestKitJunitResource
import akka.actor.typed.ActorRef
import org.junit.Assert
import org.junit.Test

internal class WorkerTest {

    private val testKit = TestKitJunitResource()

    @Test
    fun `should reply with 123`() {
        val probe = testKit.createTestProbe(ScalaDsl.PrintMe::class.java)

        val underTest = testKit.spawn(Worker.create())
        underTest.tell(Worker.SomeMsg("xxx", probe.ref as ActorRef<ScalaDsl.Cmd>))

        val reply = probe.receiveMessage()
        Assert.assertEquals("123", reply.msg)
    }
}