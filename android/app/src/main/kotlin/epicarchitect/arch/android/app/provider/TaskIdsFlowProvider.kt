package epicarchitect.arch.android.app.provider

import epicarchitect.arch.android.app.FlowArchitecture
import epicarchitect.arch.android.app.data.TaskContent
import epicarchitect.arch.android.app.data.TaskId
import epicarchitect.arch.android.app.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskIdsFlowProvider(
    private val taskRepository: TaskRepository
) : FlowArchitecture.OutputProvider<Unit, List<TaskId>> {

    override fun provide(key: Unit) = taskRepository.tasksFlow().map { it.map { it.id } }

}