package epicarchitect.arch.android.app.architecture

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import epicarchitect.architecture.FlowDrivenArchitecture

@Composable
inline fun <reified KEY : Any?, reified VALUE : Any?> FlowDrivenArchitecture.outputAsStateBy(
    key: KEY
) = output<KEY, VALUE>(key).collectAsState(initial = null)