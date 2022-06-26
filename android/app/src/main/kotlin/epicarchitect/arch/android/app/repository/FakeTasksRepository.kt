package epicarchitect.arch.android.app.repository

import epicarchitect.arch.android.app.io.Task
import epicarchitect.arch.android.app.io.TaskContent
import epicarchitect.arch.android.app.io.TaskId
import epicarchitect.arch.android.app.io.TaskTitle
import epicarchitect.arch.android.app.io.TasksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class FakeTasksRepository : TasksRepository {

    private var lastId = 0
    private val tasks = MutableStateFlow<List<Task>>(emptyList())

    override suspend fun insert(title: TaskTitle, content: TaskContent) {
        lastId++
        tasks.value += Task(
            id = TaskId(lastId),
            title = TaskTitle("${title.title} $lastId"),
            content = TaskContent("${content.content} $lastId")
        )
    }

    override suspend fun delete(taskId: TaskId) {
        tasks.update { it.filter { it.id.id != taskId.id } }
    }

    override fun tasksFlow(): Flow<List<Task>> = tasks

    override fun taskFlow(taskId: TaskId) = tasks.map {
        it.find { it.id.id == taskId.id }
    }
}