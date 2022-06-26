package epicarchitect.arch.android.app.output

import epicarchitect.arch.android.app.architecture.FlowArchitecture
import epicarchitect.arch.android.app.data.TaskContent
import epicarchitect.arch.android.app.data.TaskId
import kotlinx.coroutines.flow.map

class TaskContentFlowProvider(
    private val tasksRepository: TasksRepository
) : FlowArchitecture.OutputProvider<TaskId, TaskContent?> {

    override fun provide(key: TaskId) = tasksRepository.taskFlow(key).map { it?.content }

}