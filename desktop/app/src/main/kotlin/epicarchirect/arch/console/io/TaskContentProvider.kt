package epicarchirect.arch.console.io

import epicarchirect.arch.console.architecture.IoDrivenArchitecture
import epicarchirect.arch.console.io.data.TaskContent
import epicarchirect.arch.console.io.data.TaskId

class TaskContentProvider(
    private val tasksRepository: TasksRepository
) : IoDrivenArchitecture.OutputProvider<TaskId, TaskContent?> {

    override fun provide(key: TaskId) = tasksRepository.getTask(key)?.content

}