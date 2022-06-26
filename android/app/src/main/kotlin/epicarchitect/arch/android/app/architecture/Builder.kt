package epicarchitect.arch.android.app.architecture

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

fun FlowArchitecture(setup: FlowArchitectureBuilder.() -> Unit) = FlowArchitectureBuilder().apply(setup).build()
