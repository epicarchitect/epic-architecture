package epicarchitect.arch.android.app.io

import epicarchitect.arch.android.app.architecture.FlowArchitecture
import kotlinx.coroutines.flow.map

class TaskTitleFlowProvider(
    private val tasksRepository: TasksRepository
) : FlowArchitecture.OutputProvider<TaskId, TaskTitle?> {

    override fun provide(key: TaskId) = tasksRepository.taskFlow(key).map { it?.title }
}