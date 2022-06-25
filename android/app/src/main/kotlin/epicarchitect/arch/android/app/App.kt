package epicarchitect.arch.android.app

import android.app.Application
import epicarchitect.arch.android.app.data.TaskContent
import epicarchitect.arch.android.app.data.TaskId
import epicarchitect.arch.android.app.data.TaskTitle
import epicarchitect.arch.android.app.factorystore.factoryStore
import epicarchitect.arch.android.app.factorystore.set
import epicarchitect.arch.android.app.repository.TaskRepository
import kotlinx.coroutines.flow.Flow


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        architecture.dataArgsFeatureList += listOf(
            Triple(
                TaskContent::class,
                TaskId::class,
                TaskContentFeature::class
            ),
            Triple(
                TaskTitle::class,
                TaskId::class,
                TaskTitleFeature::class
            )
        )
    }

    companion object {
        val architecture by lazy {
            Architecture(
                factoryStore = factoryStore {
                    set { TaskContentFeature() }
                    set { TaskTitleFeature() }
                }
            )
        }
    }
}

val taskRepository by lazy {
    TaskRepository()
}

interface Feature<DATA, ARGS> {
    fun getFlow(args: ARGS): Flow<DATA>
}

class TaskContentFeature : Feature<TaskContent?, TaskId> {
    override fun getFlow(taskId: TaskId) = taskRepository.taskContentFlow(taskId)
}

class TaskTitleFeature : Feature<TaskTitle?, TaskId> {
    override fun getFlow(taskId: TaskId) = taskRepository.taskTitleFlow(taskId)
}