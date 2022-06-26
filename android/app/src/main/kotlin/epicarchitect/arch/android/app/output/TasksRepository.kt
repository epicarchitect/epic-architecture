package epicarchitect.arch.android.app.output

import epicarchitect.arch.android.app.data.Task
import epicarchitect.arch.android.app.data.TaskContent
import epicarchitect.arch.android.app.data.TaskId
import epicarchitect.arch.android.app.data.TaskTitle
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    suspend fun insert(title: TaskTitle, content: TaskContent)

    suspend fun delete(taskId: TaskId)

    fun tasksFlow(): Flow<List<Task>>

    fun taskFlow(taskId: TaskId): Flow<Task?>

}