package epicarchitect.arch.android.app.io

import epicarchitect.arch.android.app.architecture.FlowArchitecture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DeleteTaskProvider(
    private val tasksRepository: TasksRepository,
    private val coroutineScope: CoroutineScope
) : FlowArchitecture.InputProvider<DeleteTask> {

    override fun provide(value: DeleteTask) {
        coroutineScope.launch {
            tasksRepository.delete(value.taskId)
        }
    }
}

data class DeleteTask(val taskId: TaskId)