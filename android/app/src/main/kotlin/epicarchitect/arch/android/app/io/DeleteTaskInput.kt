package epicarchitect.arch.android.app.io

import epicarchitect.architecture.flow.FlowDrivenArchitecture
import epicarchitect.arch.io.DeleteTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DeleteTaskInput(
    private val tasksRepository: TasksRepository,
    private val coroutineScope: CoroutineScope
) : FlowDrivenArchitecture.Input<DeleteTask> {

    override fun invoke(value: DeleteTask) {
        coroutineScope.launch {
            tasksRepository.delete(value.taskId)
        }
    }
}