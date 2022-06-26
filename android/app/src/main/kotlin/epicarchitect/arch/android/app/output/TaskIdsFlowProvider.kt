package epicarchitect.arch.android.app.output

import epicarchitect.arch.android.app.architecture.FlowArchitecture
import epicarchitect.arch.android.app.data.TaskId
import epicarchitect.arch.android.app.repository.FakeTasksRepository
import kotlinx.coroutines.flow.map

class TaskIdsFlowProvider(
    private val tasksRepository: TasksRepository
) : FlowArchitecture.OutputProvider<Unit, List<TaskId>> {

    override fun provide(key: Unit) = tasksRepository.tasksFlow().map { it.map { it.id } }

}