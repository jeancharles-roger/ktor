package io.ktor.client.call

/**
 * Information about type.
 */
actual interface Type

@PublishedApi
internal object JsType : Type

/**
 * Returns [TypeInfo] for the specified type [T]
 */
actual inline fun <reified T> typeInfo(): TypeInfo {
    return TypeInfo(T::class, JsType)
}
