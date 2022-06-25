package epicarchitect.arch.android.app.factorystore

import kotlin.reflect.KClass

typealias Factory<T> = () -> T
typealias FactoryStore = MutableMap<KClass<*>, Factory<*>>

inline fun <reified T : Any> FactoryStore.set(noinline factory: Factory<T>) = put(T::class, factory)

@Suppress("UNCHECKED_CAST")
inline fun <reified T : Any> FactoryStore.get() = get(T::class) as Factory<T>

fun factoryStore(): FactoryStore = mutableMapOf()