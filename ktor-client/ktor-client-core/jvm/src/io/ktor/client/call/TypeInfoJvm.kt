package io.ktor.client.call

import java.lang.reflect.*

/**
 * Information about type.
 */
actual typealias Type = java.lang.reflect.Type

@PublishedApi
internal open class TypeBase<T>

/**
 * Returns [TypeInfo] for the specified type [T]
 */
actual inline fun <reified T> typeInfo(): TypeInfo {
    val base = object : TypeBase<T>() {}
    val superType = base::class.java.genericSuperclass!!

    val reifiedType = (superType as ParameterizedType).actualTypeArguments.first()!!
    return TypeInfo(T::class, reifiedType)
}
