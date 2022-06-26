package epicarchitect.arch.android.app.io

import epicarchitect.arch.android.app.architecture.FlowDrivenArchitecture
import epicarchitect.arch.android.app.io.data.TaskId
import kotlinx.coroutines.flow.map

class TaskIdsFlowProvider(
    private val tasksRepository: TasksRepository
) : FlowDrivenArchitecture.OutputProvider<Unit, List<TaskId>> {

    override fun provide(key: Unit) = tasksRepository.tasksFlow().map { it.map { it.id } }

}