package io.ktor.client.engine.js

import io.ktor.client.engine.mergeHeaders
import io.ktor.client.request.*
import io.ktor.http.content.*
import kotlinx.coroutines.*
import kotlinx.coroutines.io.*
import kotlinx.io.core.*
import org.khronos.webgl.*
import org.w3c.fetch.*

internal suspend fun HttpRequest.toRaw(scope: CoroutineScope): RequestInit {
    val jsHeaders = js("({})")
    mergeHeaders(headers, content) { key, value ->
        jsHeaders[key] = value
    }

    val content = content
    val bodyBytes = when (content) {
        is OutgoingContent.ByteArrayContent -> content.bytes()
        is OutgoingContent.ReadChannelContent -> content.readFrom().readRemaining().readBytes()
        is OutgoingContent.WriteChannelContent -> scope.writer(scope.coroutineContext) { content.writeTo(channel) }
            .channel.readRemaining().readBytes()
        else -> null
    }

    return buildObject {
        method = this@toRaw.method.value
        headers = jsHeaders

        bodyBytes?.let { body = Uint8Array(it.toTypedArray()) }
    }
}



internal fun <T> buildObject(block: T.() -> Unit): T = (js("{}") as T).apply(block)
