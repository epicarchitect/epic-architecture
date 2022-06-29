package epicarchitect.arch.android.app.io

import epicarchitect.architecture.flow.FlowDrivenArchitecture
import epicarchitect.arch.io.CreateNewTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CreateNewTaskInput(
    private val tasksRepository: TasksRepository,
    private val coroutineScope: CoroutineScope
) : FlowDrivenArchitecture.Input<CreateNewTask> {

    override fun invoke(value: CreateNewTask) {
        coroutineScope.launch {
            tasksRepository.insert(
                value.title,
                value.content
            )
        }
    }
}