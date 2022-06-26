package epicarchirect.arch.console.io

import epicarchirect.arch.console.io.data.Task
import epicarchirect.arch.console.io.data.TaskContent
import epicarchirect.arch.console.io.data.TaskId
import epicarchirect.arch.console.io.data.TaskTitle

interface TasksRepository {

    fun insert(title: TaskTitle, content: TaskContent)

    fun delete(taskId: TaskId)

    fun getTasks(): List<Task>

    fun getTask(taskId: TaskId): Task?

}