package epicarchitect.arch.console.io

import epicarchitect.arch.console.architecture.IoDrivenArchitecture
import epicarchitect.arch.io.TaskId

class TaskIdsProvider(
    private val tasksRepository: TasksRepository
) : IoDrivenArchitecture.OutputProvider<Unit, List<TaskId>> {

    override fun provide(key: Unit) = tasksRepository.getTasks().map {
        it.id
    }

}