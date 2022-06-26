package epicarchitect.arch.console.io

import epicarchitect.arch.console.architecture.IoDrivenArchitecture
import epicarchitect.arch.io.DeleteTask

class DeleteTaskProvider(
    private val tasksRepository: TasksRepository
) : IoDrivenArchitecture.InputProvider<DeleteTask> {

    override fun provide(value: DeleteTask) {
        tasksRepository.delete(value.taskId)
    }
}