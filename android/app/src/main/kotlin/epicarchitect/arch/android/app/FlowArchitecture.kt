package epicarchitect.arch.android.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KClass


@Suppress("UNCHECKED_CAST")
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

fun FlowArchitecture(setup: FlowArchitectureBuilder.() -> Unit) = FlowArchitectureBuilder().apply(setup).build()

class FlowArchitectureBuilder(
    val outputProviders: MutableCollection<FlowArchitecture.OutputProvider<*, *>> = mutableListOf(),
    val outputProviderConfigs: MutableCollection<FlowArchitecture.ProviderConfig> = mutableListOf()
) {

    inline fun <reified KEY : Any?, reified VALUE : Any?, reified PROVIDER : FlowArchitecture.OutputProvider<KEY, VALUE>> output(
        providerFactory: () -> PROVIDER
    ) {
        outputProviders.add(providerFactory())
        outputProviderConfigs.add(
            FlowArchitecture.ProviderConfig(
                KEY::class,
                VALUE::class,
                PROVIDER::class
            )
        )
    }

    fun build() = FlowArchitecture(
        outputProviders,
        outputProviderConfigs
    )
}

@Suppress("UNCHECKED_CAST")
inline fun <reified KEY, reified VALUE> FlowArchitecture.outputFlow(key: KEY): Flow<VALUE> {
    val config = getOutputProviderConfig(KEY::class, VALUE::class)
    val provider = getOutputProvider(config.providerClass) as FlowArchitecture.OutputProvider<KEY, VALUE>
    return provider.provide(key)
}

@Composable
inline fun <reified KEY : Any?, reified VALUE : Any?> FlowArchitecture.stateBy(key: KEY, initial: VALUE? = null): VALUE? {
    val state by outputFlow<KEY, VALUE>(key).collectAsState(initial = initial)
    return state
}