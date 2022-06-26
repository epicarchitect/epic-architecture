package epicarchitect.arch.console.io

import epicarchitect.arch.io.Task
import epicarchitect.arch.io.TaskContent
import epicarchitect.arch.io.TaskId
import epicarchitect.arch.io.TaskTitle

interface TasksRepository {

    fun insert(title: TaskTitle, content: TaskContent)

    fun delete(taskId: TaskId)

    fun getTasks(): List<Task>

    fun getTask(taskId: TaskId): Task?

}