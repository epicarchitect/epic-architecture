package epicarchitect.arch.android.app.output

import epicarchitect.arch.android.app.architecture.FlowArchitecture
import epicarchitect.arch.android.app.data.TaskId
import epicarchitect.arch.android.app.data.TaskTitle
import kotlinx.coroutines.flow.map

class TaskTitleFlowProvider(
    private val tasksRepository: TasksRepository
) : FlowArchitecture.OutputProvider<TaskId, TaskTitle?> {

    override fun provide(key: TaskId) = tasksRepository.taskFlow(key).map { it?.title }
}