package epicarchitect.arch.console.io

import epicarchitect.arch.console.architecture.IoDrivenArchitecture
import epicarchitect.arch.io.TaskContent
import epicarchitect.arch.io.TaskId

class TaskContentProvider(
    private val tasksRepository: TasksRepository
) : IoDrivenArchitecture.OutputProvider<TaskId, TaskContent?> {

    override fun provide(key: TaskId) = tasksRepository.getTask(key)?.content

}