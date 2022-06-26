package epicarchitect.arch.android.app.io

import epicarchitect.arch.android.app.architecture.FlowArchitecture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CreateTaskProvider(
    private val tasksRepository: TasksRepository,
    private val coroutineScope: CoroutineScope
) : FlowArchitecture.InputProvider<CreateNewTask> {

    override fun provide(value: CreateNewTask) {
        coroutineScope.launch {
            tasksRepository.insert(
                value.title,
                value.content
            )
        }
    }
}

data class CreateNewTask(
    val title: TaskTitle,
    val content: TaskContent
)