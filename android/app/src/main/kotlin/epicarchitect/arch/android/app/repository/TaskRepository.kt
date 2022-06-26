package epicarchitect.arch.android.app.repository

import epicarchitect.arch.android.app.data.Task
import epicarchitect.arch.android.app.data.TaskContent
import epicarchitect.arch.android.app.data.TaskId
import epicarchitect.arch.android.app.data.TaskTitle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class TaskRepository {

    private var lastId = 0
    private val tasks = MutableStateFlow<List<Task>>(emptyList())

    fun insert(title: String, content: String) {
        lastId++
        tasks.value += Task(
            id = TaskId(lastId),
            title = TaskTitle("$title $lastId"),
            content = TaskContent("$content $lastId")
        )
    }

    fun delete(taskId: TaskId) {
        tasks.update { it.filter { it.id.id != taskId.id } }
    }

    fun tasksFlow(): Flow<List<Task>> = tasks

    fun taskFlow(taskId: TaskId) = tasks.map {
        it.find { it.id.id == taskId.id }
    }
}