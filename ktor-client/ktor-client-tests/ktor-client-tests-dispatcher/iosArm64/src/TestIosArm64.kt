@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package io.ktor.client.tests.utils.dispatcher

import kotlinx.coroutines.*
import kotlin.coroutines.*
import platform.Foundation.*

actual fun testSuspend(
    context: CoroutineContext,
    block: suspend CoroutineScope.() -> Unit
): Unit = runBlocking {
    val loop = coroutineContext[ContinuationInterceptor] as EventLoop

    val task = launch { block() }
    while (!task.isCompleted) {
        val date = NSDate().addTimeInterval(1.0) as NSDate
        NSRunLoop.mainRunLoop.runUntilDate(date)

        loop.processNextEvent()
    }
}
