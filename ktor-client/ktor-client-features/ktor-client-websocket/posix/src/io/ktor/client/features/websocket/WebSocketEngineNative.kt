package io.ktor.client.features.websocket

import io.ktor.util.*

@InternalAPI
val websocketEngines = mutableListOf<WebSocketEngine>()

internal actual fun findWebSocketEngine(): WebSocketEngine = websocketEngines.firstOrNull() ?: error(
    "Failed to find WebSocketEngine. Consider adding [WebSocketEngine] implementation in dependencies."
)
