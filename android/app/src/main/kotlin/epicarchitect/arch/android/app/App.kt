package epicarchitect.arch.android.app

import android.app.Application
import epicarchitect.architecture.flow.FlowDrivenArchitecture
import epicarchitect.arch.android.app.io.CreateNewTaskInput
import epicarchitect.arch.android.app.io.DeleteTaskInput
import epicarchitect.arch.android.app.io.TaskContentOutput
import epicarchitect.arch.android.app.io.TaskIdsOutput
import epicarchitect.arch.android.app.io.TaskTitleOutput
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
                output { TaskTitleOutput(tasksRepository) }
                output { TaskContentOutput(tasksRepository) }
                output { TaskIdsOutput(tasksRepository) }
                input { CreateNewTaskInput(tasksRepository, coroutineScope) }
                input { DeleteTaskInput(tasksRepository, coroutineScope) }
            }
        }
    }
}