package epicarchitect.arch.android.app.io

import epicarchitect.architecture.FlowDrivenArchitecture
import epicarchitect.domain.*
import kotlinx.coroutines.flow.map

class TaskContentOutput(
    private val tasksRepository: TasksRepository
) : FlowDrivenArchitecture.Output<TaskId, TaskContent?> {

    override fun invoke(key: TaskId) = tasksRepository.taskFlow(key).map { it?.content }

}