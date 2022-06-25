package epicarchitect.arch.android.app

import android.util.Log
import epicarchitect.arch.android.app.factorystore.FactoryStore
import epicarchitect.arch.android.app.factorystore.factoryStore
import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KClass


@Suppress("UNCHECKED_CAST")
class Architecture(
    val factoryStore: FactoryStore = factoryStore()
) {

    val dataArgsFeatureList = mutableListOf<Triple<KClass<*>, KClass<*>, KClass<out Feature<*, *>>>>()

    inline fun <reified DATA, reified ARGS> outputFlow(args: ARGS): Flow<DATA> {
        val bundle = dataArgsFeatureList.first {
            it.first == DATA::class && it.second == ARGS::class
        }

        return (factoryStore[bundle.third]!!.invoke() as Feature<DATA, ARGS>).getFlow(args)
    }

}