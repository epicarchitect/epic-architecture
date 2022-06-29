package epicarchitect.arch.console.io

import epicarchitect.architecture.io.IoDrivenArchitecture
import epicarchitect.arch.io.DeleteTask

class DeleteTaskInput(
    private val tasksRepository: TasksRepository
) : IoDrivenArchitecture.Input<DeleteTask> {

    override fun invoke(value: DeleteTask) {
        tasksRepository.delete(value.taskId)
    }
}