package epicarchitect.arch.android.app.provider

import epicarchitect.arch.android.app.FlowArchitecture
import epicarchitect.arch.android.app.data.TaskContent
import epicarchitect.arch.android.app.data.TaskId
import epicarchitect.arch.android.app.repository.TaskRepository
import kotlinx.coroutines.flow.map

class TaskContentFlowProvider(
    private val taskRepository: TaskRepository
) : FlowArchitecture.OutputProvider<TaskId, TaskContent?> {

    override fun provide(key: TaskId) = taskRepository.taskFlow(key).map { it?.content }

}