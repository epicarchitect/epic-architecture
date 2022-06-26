package epicarchitect.arch.android.app.io

import epicarchitect.arch.android.app.architecture.FlowDrivenArchitecture
import epicarchitect.arch.io.TaskContent
import epicarchitect.arch.io.TaskId
import kotlinx.coroutines.flow.map

class TaskContentFlowProvider(
    private val tasksRepository: TasksRepository
) : FlowDrivenArchitecture.OutputProvider<TaskId, TaskContent?> {

    override fun provide(key: TaskId) = tasksRepository.taskFlow(key).map { it?.content }

}