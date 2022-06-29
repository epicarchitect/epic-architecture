package epicarchitect.arch.android.app.io

import epicarchitect.domain.TaskCreationParameters
import epicarchitect.architecture.FlowDrivenArchitecture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CreateNewTaskInput(
    private val tasksRepository: TasksRepository,
    private val coroutineScope: CoroutineScope
) : FlowDrivenArchitecture.Input<TaskCreationParameters> {

    override fun invoke(value: TaskCreationParameters) {
        coroutineScope.launch {
            tasksRepository.insert(
                value.title,
                value.content
            )
        }
    }
}