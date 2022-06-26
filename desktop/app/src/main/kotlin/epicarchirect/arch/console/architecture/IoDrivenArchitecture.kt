@file:Suppress("UNCHECKED_CAST")

package epicarchirect.arch.console.architecture

import kotlin.reflect.KClass

class IoDrivenArchitecture(
    private val outputProviders: Collection<OutputProvider<*, *>>,
    private val inputProviders: Collection<InputProvider<*>>,
    private val outputProviderConfigs: Collection<OutputProviderConfig>,
    private val inputProviderConfigs: Collection<InputProviderConfig>
) {

    fun getOutputProviderConfig(
        keyClass: KClass<*>,
        valueClass: KClass<*>
    ) = outputProviderConfigs.first {
        it.keyClass == keyClass && it.valueClass == valueClass
    }

    fun getInputProviderConfig(
        valueClass: KClass<*>
    ) = inputProviderConfigs.first {
        it.valueClass == valueClass
    }

    fun getOutputProvider(
        providerClass: KClass<out OutputProvider<*, *>>
    ) = outputProviders.first {
        it::class == providerClass
    }

    fun getInputProvider(
        providerClass: KClass<out InputProvider<*>>
    ) = inputProviders.first {
        it::class == providerClass
    }

    data class OutputProviderConfig(
        val keyClass: KClass<*>,
        val valueClass: KClass<*>,
        val providerClass: KClass<out OutputProvider<*, *>>
    )

    data class InputProviderConfig(
        val valueClass: KClass<*>,
        val providerClass: KClass<out InputProvider<*>>
    )


    interface OutputProvider<KEY, VALUE : Any?> {
        fun provide(key: KEY): VALUE
    }

    interface InputProvider<VALUE : Any?> {
        fun provide(value: VALUE)
    }
}

inline fun <reified KEY, reified VALUE> IoDrivenArchitecture.output(key: KEY): VALUE {
    val config = getOutputProviderConfig(KEY::class, VALUE::class)
    val provider = getOutputProvider(config.providerClass) as IoDrivenArchitecture.OutputProvider<KEY, VALUE>
    return provider.provide(key)
}

inline fun <reified VALUE> IoDrivenArchitecture.input(value: VALUE) {
    val config = getInputProviderConfig(VALUE::class)
    val provider = getInputProvider(config.providerClass) as IoDrivenArchitecture.InputProvider<VALUE>
    return provider.provide(value)
}

