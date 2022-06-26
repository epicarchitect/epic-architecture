package epicarchitect.arch.console.repository

import epicarchitect.arch.console.io.TaskIdWrapper
import epicarchitect.arch.console.io.TasksRepository
import epicarchitect.arch.io.Task
import epicarchitect.arch.io.TaskContent
import epicarchitect.arch.io.TaskId
import epicarchitect.arch.io.TaskTitle

class FakeTasksRepository : TasksRepository {

    private var lastId = 0
    private val tasks = mutableListOf<Task>()

    override fun insert(title: TaskTitle, content: TaskContent) {
        lastId++
        tasks.add(
            Task(
                id = TaskIdWrapper(lastId),
                title = title,
                content = content
            )
        )
    }

    override fun delete(taskId: TaskId) {
        tasks.removeIf { it.id == taskId }
    }

    override fun getTasks() = tasks

    override fun getTask(taskId: TaskId) = tasks.find { it.id == taskId }

}