package epicarchitect.arch.console.io

import epicarchitect.architecture.io.IoDrivenArchitecture
import epicarchitect.arch.io.TaskId
import epicarchitect.arch.io.TaskTitle

class TaskTitleOutput(
    private val tasksRepository: TasksRepository
) : IoDrivenArchitecture.Output<TaskId, TaskTitle?> {

    override fun invoke(key: TaskId) = tasksRepository.getTask(key)?.title
}