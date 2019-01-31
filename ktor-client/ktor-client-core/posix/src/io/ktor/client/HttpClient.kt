package io.ktor.client

import io.ktor.client.engine.*

/**
 * Constructs an asynchronous [HttpClient] using optional [block] for configuring this client.
 *
 * The [HttpClientEngine] is selected from the dependencies.
 * https://ktor.io/clients/http-client/engines.html
 */
@HttpClientDsl
actual fun HttpClient(
    block: HttpClientConfig<*>.() -> Unit
): HttpClient = engines.firstOrNull()?.let { HttpClient(it, block) } ?: error(
    "Failed to find HttpClientEngineContainer. Consider adding [HttpClientEngine] implementation in dependencies."
)
