package epicarchitect.arch.android.app.repository

import epicarchitect.arch.android.app.io.TaskContentWrapper
import epicarchitect.arch.android.app.io.TaskIdWrapper
import epicarchitect.arch.android.app.io.TaskTitleWrapper
import epicarchitect.arch.android.app.io.TasksRepository
import epicarchitect.arch.android.app.io.impl
import epicarchitect.domain.Task
import epicarchitect.domain.TaskContent
import epicarchitect.domain.TaskId
import epicarchitect.domain.TaskTitle
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
            id = TaskIdWrapper(lastId),
            title = TaskTitleWrapper("${title.impl()} $lastId"),
            content = TaskContentWrapper("${content.impl()} $lastId")
        )
    }

    override suspend fun delete(taskId: TaskId) {
        tasks.update { it.filter { it.id != taskId } }
    }

    override fun tasksFlow(): Flow<List<Task>> = tasks

    override fun taskFlow(taskId: TaskId) = tasks.map {
        it.find { it.id == taskId }
    }
}