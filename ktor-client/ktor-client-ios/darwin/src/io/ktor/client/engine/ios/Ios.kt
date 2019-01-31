package io.ktor.client.engine.ios

import io.ktor.client.engine.*
import kotlin.native.concurrent.*

@ThreadLocal
private val initHook = Ios

/**
 * [HttpClientEngineFactory] using a [NSURLRequest] implementation to execute requests on iOS.
 * See also [IosClientEngineConfig].
 */
object Ios : HttpClientEngineFactory<IosClientEngineConfig> {

    init {
        engines.add(this)
    }

    override fun create(block: IosClientEngineConfig.() -> Unit): HttpClientEngine =
        IosClientEngine(IosClientEngineConfig().apply(block))
}
