package epicarchirect.arch.console.io

import epicarchirect.arch.console.architecture.IoDrivenArchitecture
import epicarchirect.arch.console.io.data.TaskId

class TaskIdsProvider(
    private val tasksRepository: TasksRepository
) : IoDrivenArchitecture.OutputProvider<Unit, List<TaskId>> {

    override fun provide(key: Unit) = tasksRepository.getTasks().map { it.id }

}