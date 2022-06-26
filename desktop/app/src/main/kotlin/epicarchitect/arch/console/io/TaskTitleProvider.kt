package epicarchitect.arch.console.io

import epicarchitect.arch.console.architecture.IoDrivenArchitecture
import epicarchitect.arch.io.TaskId
import epicarchitect.arch.io.TaskTitle

class TaskTitleProvider(
    private val tasksRepository: TasksRepository
) : IoDrivenArchitecture.OutputProvider<TaskId, TaskTitle?> {

    override fun provide(key: TaskId) = tasksRepository.getTask(key)?.title
}