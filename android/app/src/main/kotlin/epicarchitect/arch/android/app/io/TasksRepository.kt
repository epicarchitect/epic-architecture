package epicarchitect.arch.android.app.io

import epicarchitect.arch.android.app.io.data.Task
import epicarchitect.arch.android.app.io.data.TaskContent
import epicarchitect.arch.android.app.io.data.TaskId
import epicarchitect.arch.android.app.io.data.TaskTitle
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    suspend fun insert(title: TaskTitle, content: TaskContent)

    suspend fun delete(taskId: TaskId)

    fun tasksFlow(): Flow<List<Task>>

    fun taskFlow(taskId: TaskId): Flow<Task?>

}