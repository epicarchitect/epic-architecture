package epicarchitect.arch.android.app

import android.app.Application
import epicarchitect.arch.android.app.architecture.FlowArchitecture
import epicarchitect.arch.android.app.output.TaskContentFlowProvider
import epicarchitect.arch.android.app.output.TaskIdsFlowProvider
import epicarchitect.arch.android.app.output.TasksRepository
import epicarchitect.arch.android.app.output.TaskTitleFlowProvider
import epicarchitect.arch.android.app.repository.FakeTasksRepository


class App : Application() {
    companion object {
        val tasksRepository: TasksRepository by lazy {
            FakeTasksRepository()
        }

        val architecture by lazy {
            FlowArchitecture {
                output { TaskTitleFlowProvider(tasksRepository) }
                output { TaskContentFlowProvider(tasksRepository) }
                output { TaskIdsFlowProvider(tasksRepository) }
            }
        }
    }
}