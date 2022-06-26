package epicarchitect.arch.android.app.architecture

class FlowArchitectureBuilder(
    val outputProviders: MutableCollection<FlowDrivenArchitecture.OutputProvider<*, *>> = mutableListOf(),
    val outputProviderConfigs: MutableCollection<FlowDrivenArchitecture.OutputProviderConfig> = mutableListOf(),
    val inputProviders: MutableCollection<FlowDrivenArchitecture.InputProvider<*>> = mutableListOf(),
    val inputProviderConfigs: MutableCollection<FlowDrivenArchitecture.InputProviderConfig> = mutableListOf()
) {

    inline fun <reified KEY : Any?, reified VALUE : Any?, reified PROVIDER : FlowDrivenArchitecture.OutputProvider<KEY, VALUE>> output(
        providerFactory: () -> PROVIDER
    ) {
        outputProviders.add(providerFactory())
        outputProviderConfigs.add(
            FlowDrivenArchitecture.OutputProviderConfig(
                KEY::class,
                VALUE::class,
                PROVIDER::class
            )
        )
    }


    inline fun <reified VALUE : Any?, reified PROVIDER : FlowDrivenArchitecture.InputProvider<VALUE>> input(
        providerFactory: () -> PROVIDER
    ) {
        inputProviders.add(providerFactory())
        inputProviderConfigs.add(
            FlowDrivenArchitecture.InputProviderConfig(
                VALUE::class,
                PROVIDER::class
            )
        )
    }

    fun build() = FlowDrivenArchitecture(
        outputProviders,
        inputProviders,
        outputProviderConfigs,
        inputProviderConfigs
    )
}

fun FlowDrivenArchitecture(setup: FlowArchitectureBuilder.() -> Unit) = FlowArchitectureBuilder().apply(setup).build()
