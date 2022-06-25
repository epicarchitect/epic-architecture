package epicarchitect.arch.android.app.repository

import epicarchitect.arch.android.app.data.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class TaskRepository {

    private var lastId = 0
    private val tasks = MutableStateFlow<List<Task>>(emptyList())

    fun insert(title: String, content: String) {
        lastId++
        tasks.value += Task(
            id = lastId,
            title = "$title $lastId",
            content = "$content $lastId"
        )
    }

    fun delete(taskId: Int) {
        tasks.update { it.filter { it.id != taskId } }
    }

    fun taskIds() = tasks.map { it.map { it.id } }

    fun taskTitleFlow(taskId: Int) = taskFlow(taskId).map { it?.title }

    fun taskContentFlow(taskId: Int) = taskFlow(taskId).map { it?.content }

    private fun taskFlow(taskId: Int) = tasks.map {
        it.find { it.id == taskId }
    }
}