package io.ktor.client.engine

import io.ktor.util.*
import kotlin.native.concurrent.*

@InternalAPI
@Suppress("KDocMissingDocumentation")
@ThreadLocal
val engines: MutableList<HttpClientEngineFactory<HttpClientEngineConfig>> by lazy {
    mutableListOf<HttpClientEngineFactory<HttpClientEngineConfig>>()
}
