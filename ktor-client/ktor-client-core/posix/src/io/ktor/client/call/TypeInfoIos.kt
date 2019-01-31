package io.ktor.client.call

/**
 * Information about type.
 */
actual interface Type

@PublishedApi
internal object IosType : Type

/**
 * Returns [TypeInfo] for the specified type [T]
 */
actual inline fun <reified T> typeInfo(): TypeInfo {
    val kClass = T::class
    return TypeInfo(kClass, IosType)
}
