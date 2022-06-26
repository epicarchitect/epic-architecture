package epicarchirect.arch.console.io

import epicarchirect.arch.console.architecture.IoDrivenArchitecture
import epicarchirect.arch.console.io.data.CreateNewTask

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