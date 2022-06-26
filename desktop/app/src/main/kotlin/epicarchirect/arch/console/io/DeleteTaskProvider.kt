package epicarchirect.arch.console.io

import epicarchirect.arch.console.architecture.IoDrivenArchitecture
import epicarchirect.arch.console.io.data.DeleteTask

class DeleteTaskProvider(
    private val tasksRepository: TasksRepository
) : IoDrivenArchitecture.InputProvider<DeleteTask> {

    override fun provide(value: DeleteTask) {
        tasksRepository.delete(value.taskId)
    }
}