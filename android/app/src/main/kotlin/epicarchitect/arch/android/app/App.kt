package epicarchitect.arch.android.app

import android.app.Application
import epicarchitect.arch.android.app.architecture.FlowDrivenArchitecture
import epicarchitect.arch.android.app.io.CreateTaskProvider
import epicarchitect.arch.android.app.io.DeleteTaskProvider
import epicarchitect.arch.android.app.io.TaskContentFlowProvider
import epicarchitect.arch.android.app.io.TaskIdsFlowProvider
import epicarchitect.arch.android.app.io.TaskTitleFlowProvider
import epicarchitect.arch.android.app.io.TasksRepository
import epicarchitect.arch.android.app.repository.FakeTasksRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class App : Application() {
    companion object {
        private val coroutineScope by lazy {
            CoroutineScope(Dispatchers.IO)
        }

        private val tasksRepository: TasksRepository by lazy {
            FakeTasksRepository()
        }

        val architecture by lazy {
            FlowDrivenArchitecture {
                output { TaskTitleFlowProvider(tasksRepository) }
                output { TaskContentFlowProvider(tasksRepository) }
                output { TaskIdsFlowProvider(tasksRepository) }
                input { CreateTaskProvider(tasksRepository, coroutineScope) }
                input { DeleteTaskProvider(tasksRepository, coroutineScope) }
            }
        }
    }
}