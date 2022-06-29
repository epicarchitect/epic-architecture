package epicarchitect.arch.console.io

import epicarchitect.architecture.io.IoDrivenArchitecture
import epicarchitect.arch.io.TaskId

class TaskIdsOutput(
    private val tasksRepository: TasksRepository
) : IoDrivenArchitecture.Output<Unit, List<TaskId>> {

    override fun invoke(key: Unit) = tasksRepository.getTasks().map {
        it.id
    }

}