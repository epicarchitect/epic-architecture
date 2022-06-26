package epicarchitect.arch.android.app.architecture

import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KClass

class FlowArchitecture(
    private val outputProviders: Collection<OutputProvider<*, *>>,
    private val outputProviderConfigs: Collection<ProviderConfig>
) {

    fun getOutputProviderConfig(
        keyClass: KClass<*>,
        valueClass: KClass<*>
    ) = outputProviderConfigs.first {
        it.keyClass == keyClass && it.valueClass == valueClass
    }

    fun getOutputProvider(
        providerClass: KClass<out OutputProvider<*, *>>
    ) = outputProviders.first {
        it::class == providerClass
    }

    data class ProviderConfig(
        val keyClass: KClass<*>,
        val valueClass: KClass<*>,
        val providerClass: KClass<out OutputProvider<*, *>>
    )

    interface OutputProvider<KEY, out VALUE : Any?> {
        fun provide(key: KEY): Flow<VALUE>
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <reified KEY, reified VALUE> FlowArchitecture.outputFlow(key: KEY): Flow<VALUE> {
    val config = getOutputProviderConfig(KEY::class, VALUE::class)
    val provider = getOutputProvider(config.providerClass) as FlowArchitecture.OutputProvider<KEY, VALUE>
    return provider.provide(key)
}

