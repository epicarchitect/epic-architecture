package epicarchitect.arch.android.app.io

import epicarchitect.arch.android.app.architecture.FlowDrivenArchitecture
import epicarchitect.arch.io.TaskId
import epicarchitect.arch.io.TaskTitle
import kotlinx.coroutines.flow.map

class TaskTitleFlowProvider(
    private val tasksRepository: TasksRepository
) : FlowDrivenArchitecture.OutputProvider<TaskId, TaskTitle?> {

    override fun provide(key: TaskId) = tasksRepository.taskFlow(key).map { it?.title }
}