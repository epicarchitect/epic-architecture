package epicarchitect.arch.android.app.io

import epicarchitect.architecture.FlowDrivenArchitecture
import epicarchitect.domain.TaskId
import epicarchitect.domain.TaskTitle
import kotlinx.coroutines.flow.map

class TaskTitleOutput(
    private val tasksRepository: TasksRepository
) : FlowDrivenArchitecture.Output<TaskId, TaskTitle?> {

    override fun invoke(key: TaskId) = tasksRepository.taskFlow(key).map { it?.title }

}