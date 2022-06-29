package epicarchitect.architecture.io

import epicarchitect.architecture.core.EpicArchitecture

class IoDrivenArchitectureBuilder {
    val outputs = mutableListOf<IoDrivenArchitecture.Output<*, *>>()
    val inputs = mutableListOf<IoDrivenArchitecture.Input<*>>()
    val outputConfigs = mutableListOf<EpicArchitecture.OutputConfig>()
    val inputConfigs = mutableListOf<EpicArchitecture.InputConfig>()

    inline fun <reified KEY : Any?, reified VALUE : Any?, reified OUTPUT : IoDrivenArchitecture.Output<KEY, VALUE>> output(
        provide: () -> OUTPUT
    ) {
        outputs.add(provide())
        outputConfigs.add(
            EpicArchitecture.OutputConfig(
                KEY::class,
                VALUE::class,
                OUTPUT::class
            )
        )
    }


    inline fun <reified VALUE : Any?, reified PROVIDER : IoDrivenArchitecture.Input<VALUE>> input(
        providerFactory: () -> PROVIDER
    ) {
        inputs.add(providerFactory())
        inputConfigs.add(
            EpicArchitecture.InputConfig(
                VALUE::class,
                PROVIDER::class
            )
        )
    }

    fun build() = IoDrivenArchitecture(
        outputs,
        inputs,
        outputConfigs,
        inputConfigs
    )
}

fun IoDrivenArchitecture(setup: IoDrivenArchitectureBuilder.() -> Unit) = IoDrivenArchitectureBuilder().apply(setup).build()
