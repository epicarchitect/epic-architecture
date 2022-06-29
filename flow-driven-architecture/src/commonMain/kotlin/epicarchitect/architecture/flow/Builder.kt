package epicarchitect.architecture.flow

import epicarchitect.architecture.core.EpicArchitecture

class FlowArchitectureBuilder {
    val outputs = mutableListOf<FlowDrivenArchitecture.Output<*, *>>()
    val inputs = mutableListOf<FlowDrivenArchitecture.Input<*>>()
    val outputConfigs = mutableListOf<EpicArchitecture.OutputConfig>()
    val inputConfigs = mutableListOf<EpicArchitecture.InputConfig>()

    inline fun <reified KEY : Any?, reified VALUE : Any?, reified OUTPUT : FlowDrivenArchitecture.Output<KEY, VALUE>> output(
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


    inline fun <reified VALUE : Any?, reified INPUT : FlowDrivenArchitecture.Input<VALUE>> input(
        provide: () -> INPUT
    ) {
        inputs.add(provide())
        inputConfigs.add(
            EpicArchitecture.InputConfig(
                VALUE::class,
                INPUT::class
            )
        )
    }

    fun build() = FlowDrivenArchitecture(
        outputs,
        inputs,
        outputConfigs,
        inputConfigs
    )
}

fun FlowDrivenArchitecture(setup: FlowArchitectureBuilder.() -> Unit) = FlowArchitectureBuilder().apply(setup).build()
