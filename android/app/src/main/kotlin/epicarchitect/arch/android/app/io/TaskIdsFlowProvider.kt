package epicarchitect.arch.android.app.io

import epicarchitect.arch.android.app.architecture.FlowArchitecture
import kotlinx.coroutines.flow.map

class TaskIdsFlowProvider(
    private val tasksRepository: TasksRepository
) : FlowArchitecture.OutputProvider<Unit, List<TaskId>> {

    override fun provide(key: Unit) = tasksRepository.tasksFlow().map { it.map { it.id } }

}