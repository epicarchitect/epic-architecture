package epicarchitect.arch.console.io

import epicarchitect.architecture.io.IoDrivenArchitecture
import epicarchitect.arch.io.CreateNewTask

class CreateNewTaskInput(
    private val tasksRepository: TasksRepository
) : IoDrivenArchitecture.Input<CreateNewTask> {

    override fun invoke(value: CreateNewTask) {
        tasksRepository.insert(
            value.title,
            value.content
        )
    }
}