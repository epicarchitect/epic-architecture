package epicarchitect.arch.android.app.io

import epicarchitect.architecture.flow.FlowDrivenArchitecture
import epicarchitect.arch.io.TaskId
import epicarchitect.arch.io.TaskTitle
import kotlinx.coroutines.flow.map

class TaskTitleOutput(
    private val tasksRepository: TasksRepository
) : FlowDrivenArchitecture.Output<TaskId, TaskTitle?> {

    override fun invoke(key: TaskId) = tasksRepository.taskFlow(key).map { it?.title }

}