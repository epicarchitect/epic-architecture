package epicarchirect.arch.console.repository

import epicarchirect.arch.console.io.TasksRepository
import epicarchirect.arch.console.io.data.Task
import epicarchirect.arch.console.io.data.TaskContent
import epicarchirect.arch.console.io.data.TaskId
import epicarchirect.arch.console.io.data.TaskTitle

class FakeTasksRepository : TasksRepository {

    private var lastId = 0
    private val tasks = mutableListOf<Task>()

    override fun insert(title: TaskTitle, content: TaskContent) {
        lastId++
        tasks.add(
            Task(
                id = TaskId(lastId),
                title = title,
                content = content
            )
        )
    }

    override fun delete(taskId: TaskId) {
        tasks.removeIf { it.id.id == taskId.id }
    }

    override fun getTasks() = tasks

    override fun getTask(taskId: TaskId) = tasks.find { it.id.id == taskId.id }

}