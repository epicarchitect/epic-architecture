package epicarchitect.arch.android.app.io

import epicarchitect.arch.android.app.architecture.FlowDrivenArchitecture
import epicarchitect.arch.android.app.io.data.TaskId
import epicarchitect.arch.android.app.io.data.TaskTitle
import kotlinx.coroutines.flow.map

class TaskTitleFlowProvider(
    private val tasksRepository: TasksRepository
) : FlowDrivenArchitecture.OutputProvider<TaskId, TaskTitle?> {

    override fun provide(key: TaskId) = tasksRepository.taskFlow(key).map { it?.title }
}