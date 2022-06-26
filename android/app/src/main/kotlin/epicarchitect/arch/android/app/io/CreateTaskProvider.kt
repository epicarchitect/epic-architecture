package epicarchitect.arch.android.app.io

import epicarchitect.arch.android.app.architecture.FlowDrivenArchitecture
import epicarchitect.arch.android.app.io.data.CreateNewTask
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CreateTaskProvider(
    private val tasksRepository: TasksRepository,
    private val coroutineScope: CoroutineScope
) : FlowDrivenArchitecture.InputProvider<CreateNewTask> {

    override fun provide(value: CreateNewTask) {
        coroutineScope.launch {
            tasksRepository.insert(
                value.title,
                value.content
            )
        }
    }
}