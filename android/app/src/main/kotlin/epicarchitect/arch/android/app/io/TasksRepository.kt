package epicarchitect.arch.android.app.io

import epicarchitect.domain.Task
import epicarchitect.domain.TaskContent
import epicarchitect.domain.TaskId
import epicarchitect.domain.TaskTitle
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    suspend fun insert(title: TaskTitle, content: TaskContent)

    suspend fun delete(taskId: TaskId)

    fun tasksFlow(): Flow<List<Task>>

    fun taskFlow(taskId: TaskId): Flow<Task?>

}