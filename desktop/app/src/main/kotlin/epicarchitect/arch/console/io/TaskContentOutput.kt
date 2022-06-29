package epicarchitect.arch.console.io

import epicarchitect.architecture.io.IoDrivenArchitecture
import epicarchitect.arch.io.TaskContent
import epicarchitect.arch.io.TaskId

class TaskContentOutput(
    private val tasksRepository: TasksRepository
) : IoDrivenArchitecture.Output<TaskId, TaskContent?> {

    override fun invoke(key: TaskId) = tasksRepository.getTask(key)?.content

}