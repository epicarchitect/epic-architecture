package epicarchirect.arch.console.architecture

class IoDrivenArchitectureBuilder(
    val outputProviders: MutableCollection<IoDrivenArchitecture.OutputProvider<*, *>> = mutableListOf(),
    val outputProviderConfigs: MutableCollection<IoDrivenArchitecture.OutputProviderConfig> = mutableListOf(),
    val inputProviders: MutableCollection<IoDrivenArchitecture.InputProvider<*>> = mutableListOf(),
    val inputProviderConfigs: MutableCollection<IoDrivenArchitecture.InputProviderConfig> = mutableListOf()
) {

    inline fun <reified KEY : Any?, reified VALUE : Any?, reified PROVIDER : IoDrivenArchitecture.OutputProvider<KEY, VALUE>> output(
        providerFactory: () -> PROVIDER
    ) {
        outputProviders.add(providerFactory())
        outputProviderConfigs.add(
            IoDrivenArchitecture.OutputProviderConfig(
                KEY::class,
                VALUE::class,
                PROVIDER::class
            )
        )
    }


    inline fun <reified VALUE : Any?, reified PROVIDER : IoDrivenArchitecture.InputProvider<VALUE>> input(
        providerFactory: () -> PROVIDER
    ) {
        inputProviders.add(providerFactory())
        inputProviderConfigs.add(
            IoDrivenArchitecture.InputProviderConfig(
                VALUE::class,
                PROVIDER::class
            )
        )
    }

    fun build() = IoDrivenArchitecture(
        outputProviders,
        inputProviders,
        outputProviderConfigs,
        inputProviderConfigs
    )
}

fun IoDrivenArchitecture(setup: IoDrivenArchitectureBuilder.() -> Unit) = IoDrivenArchitectureBuilder().apply(setup).build()
