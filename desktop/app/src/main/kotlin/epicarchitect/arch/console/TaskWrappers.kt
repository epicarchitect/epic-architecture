package epicarchitect.arch.console.io

import epicarchitect.domain.TaskContent
import epicarchitect.domain.TaskId
import epicarchitect.domain.TaskTitle

data class TaskIdWrapper(val value: Int) : TaskId
data class TaskTitleWrapper(val value: String) : TaskTitle
data class TaskContentWrapper(val value: String) : TaskContent

fun TaskId.impl() = this as TaskIdWrapper
fun TaskTitle.impl() = this as TaskTitleWrapper
fun TaskContent.impl() = this as TaskContentWrapper