package epicarchitect.arch.console

import epicarchitect.arch.console.io.TaskIdWrapper
import epicarchitect.domain.Task
import epicarchitect.domain.TaskId
import epicarchitect.domain.TaskCreationParameters
import epicarchitect.domain.TaskDeletionParameters

class FakeTasksRepository {

    private var lastId = 0
    private val tasks = mutableListOf<Task>()

    fun delete(parameters: TaskDeletionParameters) {
        tasks.removeIf { it.id == parameters.taskId }
    }

    fun getTasks() = tasks

    fun getTask(taskId: TaskId) = tasks.find { it.id == taskId }

    fun createTask(parameters: TaskCreationParameters) {
        lastId++
        tasks.add(
            Task(
                id = TaskIdWrapper(lastId),
                title = parameters.title,
                content = parameters.content
            )
        )
    }

}