package epicarchitect.arch.android.app.provider

import epicarchitect.arch.android.app.FlowArchitecture
import epicarchitect.arch.android.app.data.TaskId
import epicarchitect.arch.android.app.data.TaskTitle
import epicarchitect.arch.android.app.repository.TaskRepository
import kotlinx.coroutines.flow.map

class TaskTitleFlowProvider(
    private val taskRepository: TaskRepository
) : FlowArchitecture.OutputProvider<TaskId, TaskTitle?> {

    override fun provide(key: TaskId) = taskRepository.taskFlow(key).map { it?.title }
}