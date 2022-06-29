package epicarchitect.arch.android.app.io

import epicarchitect.architecture.FlowDrivenArchitecture
import epicarchitect.domain.TaskDeletionParameters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DeleteTaskInput(
    private val tasksRepository: TasksRepository,
    private val coroutineScope: CoroutineScope
) : FlowDrivenArchitecture.Input<TaskDeletionParameters> {

    override fun invoke(value: TaskDeletionParameters) {
        coroutineScope.launch {
            tasksRepository.delete(value.taskId)
        }
    }
}