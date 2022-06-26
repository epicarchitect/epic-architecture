package epicarchitect.arch.android.app.architecture

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState

@Composable
inline fun <reified KEY : Any?, reified VALUE : Any?> FlowArchitecture.stateBy(
    key: KEY
) = output<KEY, VALUE>(key).collectAsState(initial = null)