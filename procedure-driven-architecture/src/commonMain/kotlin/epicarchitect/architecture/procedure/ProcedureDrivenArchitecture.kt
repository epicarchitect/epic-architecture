@file:Suppress("UNCHECKED_CAST")

package epicarchitect.architecture.procedure

import epicarchitect.architecture.core.EpicArchitecture

class ProcedureDrivenArchitecture(
    outputs: Iterable<Output<*, *>>,
    inputs: Iterable<Input<*>>,
    outputConfigs: Iterable<OutputConfig>,
    inputConfigs: Iterable<InputConfig>
) : EpicArchitecture(
    outputs,
    inputs,
    outputConfigs,
    inputConfigs
) {

    inline fun <reified KEY, reified VALUE> output(key: KEY): VALUE {
        val config = getOutputConfig(KEY::class, VALUE::class)
        val provider = getOutput(config.outputClass) as Output<KEY, VALUE>
        return provider(key)
    }

    inline fun <reified VALUE> input(value: VALUE) {
        val config = getInputConfig(VALUE::class)
        val provider = getInput(config.inputClass) as Input<VALUE>
        return provider(value)
    }

    fun interface Output<KEY, VALUE : Any?> : EpicArchitecture.Output, (KEY) -> VALUE
    fun interface Input<VALUE : Any?> : EpicArchitecture.Input, (VALUE) -> Unit
}

