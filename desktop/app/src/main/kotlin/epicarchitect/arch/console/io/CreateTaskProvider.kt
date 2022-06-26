package epicarchitect.arch.console.io

import epicarchitect.arch.console.architecture.IoDrivenArchitecture
import epicarchitect.arch.io.CreateNewTask

class CreateTaskProvider(
    private val tasksRepository: TasksRepository
) : IoDrivenArchitecture.InputProvider<CreateNewTask> {

    override fun provide(value: CreateNewTask) {
        tasksRepository.insert(
            value.title,
            value.content
        )
    }
}