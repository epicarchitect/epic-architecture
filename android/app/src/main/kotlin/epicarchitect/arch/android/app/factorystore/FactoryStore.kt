package epicarchitect.arch.android.app.factorystore

import kotlin.reflect.KClass

typealias Factory<RESULT> = () -> RESULT
typealias FactoryStore = MutableMap<KClass<*>, Factory<*>>

inline fun <reified RESULT : Any> FactoryStore.set(noinline factory: Factory<RESULT>) =
    put(RESULT::class, factory)

@Suppress("UNCHECKED_CAST")
inline fun <reified RESULT : Any> FactoryStore.get() =
    get(RESULT::class) as Factory<RESULT>


fun factoryStore(setup: FactoryStore.() -> Unit= {}): FactoryStore =
    mutableMapOf<KClass<*>, Factory<*>>().apply(setup)