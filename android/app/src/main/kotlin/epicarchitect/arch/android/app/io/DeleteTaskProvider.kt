package epicarchitect.arch.android.app.io

import epicarchitect.arch.android.app.architecture.FlowDrivenArchitecture
import epicarchitect.arch.android.app.io.data.DeleteTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DeleteTaskProvider(
    private val tasksRepository: TasksRepository,
    private val coroutineScope: CoroutineScope
) : FlowDrivenArchitecture.InputProvider<DeleteTask> {

    override fun provide(value: DeleteTask) {
        coroutineScope.launch {
            tasksRepository.delete(value.taskId)
        }
    }
}