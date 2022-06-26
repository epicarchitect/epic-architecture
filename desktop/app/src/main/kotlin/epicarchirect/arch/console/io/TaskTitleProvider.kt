package epicarchirect.arch.console.io

import epicarchirect.arch.console.architecture.IoDrivenArchitecture
import epicarchirect.arch.console.io.data.TaskId
import epicarchirect.arch.console.io.data.TaskTitle

class TaskTitleProvider(
    private val tasksRepository: TasksRepository
) : IoDrivenArchitecture.OutputProvider<TaskId, TaskTitle?> {

    override fun provide(key: TaskId) = tasksRepository.getTask(key)?.title
}