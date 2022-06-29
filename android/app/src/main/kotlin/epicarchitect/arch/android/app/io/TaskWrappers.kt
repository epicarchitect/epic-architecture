package epicarchitect.arch.android.app.io

import epicarchitect.domain.TaskContent
import epicarchitect.domain.TaskId
import epicarchitect.domain.TaskTitle

data class TaskIdWrapper(val value: Int) : TaskId
data class TaskTitleWrapper(val value: String) : TaskTitle
data class TaskContentWrapper(val value: String) : TaskContent

fun TaskId.impl() = (this as TaskIdWrapper).value
fun TaskTitle.impl() = (this as TaskTitleWrapper).value
fun TaskContent.impl() = (this as TaskContentWrapper).value