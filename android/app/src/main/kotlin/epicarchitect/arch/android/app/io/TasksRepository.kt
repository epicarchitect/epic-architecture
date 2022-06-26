package epicarchitect.arch.android.app.io

import epicarchitect.arch.io.Task
import epicarchitect.arch.io.TaskContent
import epicarchitect.arch.io.TaskId
import epicarchitect.arch.io.TaskTitle
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    suspend fun insert(title: TaskTitle, content: TaskContent)

    suspend fun delete(taskId: TaskId)

    fun tasksFlow(): Flow<List<Task>>

    fun taskFlow(taskId: TaskId): Flow<Task?>

}