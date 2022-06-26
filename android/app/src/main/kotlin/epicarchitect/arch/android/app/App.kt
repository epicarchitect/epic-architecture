package epicarchitect.arch.android.app

import android.app.Application
import epicarchitect.arch.android.app.data.TaskContent
import epicarchitect.arch.android.app.data.TaskId
import epicarchitect.arch.android.app.data.TaskTitle
import epicarchitect.arch.android.app.provider.TaskContentFlowProvider
import epicarchitect.arch.android.app.provider.TaskIdsFlowProvider
import epicarchitect.arch.android.app.provider.TaskTitleFlowProvider
import epicarchitect.arch.android.app.repository.TaskRepository


class App : Application() {
    companion object {
        val taskRepository by lazy {
            TaskRepository()
        }

        val architecture by lazy {
            FlowArchitecture {
                output { TaskTitleFlowProvider(taskRepository) }
                output { TaskContentFlowProvider(taskRepository) }
                output { TaskIdsFlowProvider(taskRepository) }
            }
        }
    }
}