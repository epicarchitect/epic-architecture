package epicarchitect.arch.android.app.architecture

class FlowArchitectureBuilder(
    val outputProviders: MutableCollection<FlowArchitecture.OutputProvider<*, *>> = mutableListOf(),
    val outputProviderConfigs: MutableCollection<FlowArchitecture.OutputProviderConfig> = mutableListOf(),
    val inputProviders: MutableCollection<FlowArchitecture.InputProvider<*>> = mutableListOf(),
    val inputProviderConfigs: MutableCollection<FlowArchitecture.InputProviderConfig> = mutableListOf()
) {

    inline fun <reified KEY : Any?, reified VALUE : Any?, reified PROVIDER : FlowArchitecture.OutputProvider<KEY, VALUE>> output(
        providerFactory: () -> PROVIDER
    ) {
        outputProviders.add(providerFactory())
        outputProviderConfigs.add(
            FlowArchitecture.OutputProviderConfig(
                KEY::class,
                VALUE::class,
                PROVIDER::class
            )
        )
    }


    inline fun <reified VALUE : Any?, reified PROVIDER : FlowArchitecture.InputProvider<VALUE>> input(
        providerFactory: () -> PROVIDER
    ) {
        inputProviders.add(providerFactory())
        inputProviderConfigs.add(
            FlowArchitecture.InputProviderConfig(
                VALUE::class,
                PROVIDER::class
            )
        )
    }

    fun build() = FlowArchitecture(
        outputProviders,
        inputProviders,
        outputProviderConfigs,
        inputProviderConfigs
    )
}

fun FlowArchitecture(setup: FlowArchitectureBuilder.() -> Unit) = FlowArchitectureBuilder().apply(setup).build()
