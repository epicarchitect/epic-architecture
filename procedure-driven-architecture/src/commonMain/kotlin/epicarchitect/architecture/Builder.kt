package epicarchitect.architecture

class IoDrivenArchitectureBuilder {
    val outputs = mutableListOf<ProcedureDrivenArchitecture.Output<*, *>>()
    val inputs = mutableListOf<ProcedureDrivenArchitecture.Input<*>>()
    val outputConfigs = mutableListOf<EpicArchitecture.OutputConfig>()
    val inputConfigs = mutableListOf<EpicArchitecture.InputConfig>()

    inline fun <reified KEY : Any?, reified VALUE : Any?> output(crossinline process: (KEY) -> VALUE) {
        val output = ProcedureDrivenArchitecture.Output<KEY, VALUE> { process(it) }
        outputs.add(output)
        outputConfigs.add(
            EpicArchitecture.OutputConfig(
                KEY::class,
                VALUE::class,
                output::class
            )
        )
    }


    inline fun <reified VALUE : Any?> input(crossinline process: (VALUE) -> Unit) {
        val input = ProcedureDrivenArchitecture.Input<VALUE> { process(it) }
        inputs.add(input)
        inputConfigs.add(
            EpicArchitecture.InputConfig(
                VALUE::class,
                input::class
            )
        )
    }

    fun build() = ProcedureDrivenArchitecture(
        outputs,
        inputs,
        outputConfigs,
        inputConfigs
    )
}

fun ProcedureDrivenArchitecture(setup: IoDrivenArchitectureBuilder.() -> Unit) = IoDrivenArchitectureBuilder().apply(setup).build()
