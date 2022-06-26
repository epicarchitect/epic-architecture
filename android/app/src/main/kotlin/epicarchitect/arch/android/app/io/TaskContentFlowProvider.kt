package epicarchitect.arch.android.app.io

import epicarchitect.arch.android.app.architecture.FlowArchitecture
import kotlinx.coroutines.flow.map

class TaskContentFlowProvider(
    private val tasksRepository: TasksRepository
) : FlowArchitecture.OutputProvider<TaskId, TaskContent?> {

    override fun provide(key: TaskId) = tasksRepository.taskFlow(key).map { it?.content }

}