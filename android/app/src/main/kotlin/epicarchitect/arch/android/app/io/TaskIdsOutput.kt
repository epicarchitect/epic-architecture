package epicarchitect.arch.android.app.io

import epicarchitect.architecture.flow.FlowDrivenArchitecture
import epicarchitect.domain.TaskId
import kotlinx.coroutines.flow.map

class TaskIdsOutput(
    private val tasksRepository: TasksRepository
) : FlowDrivenArchitecture.Output<Unit, List<TaskId>> {

    override fun invoke(key: Unit) = tasksRepository.tasksFlow().map { it.map { it.id } }

}