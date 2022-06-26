package epicarchitect.arch.console.io

import epicarchitect.arch.io.TaskContent
import epicarchitect.arch.io.TaskId
import epicarchitect.arch.io.TaskTitle

data class TaskIdWrapper(val value: Int) : TaskId
data class TaskTitleWrapper(val value: String) : TaskTitle
data class TaskContentWrapper(val value: String) : TaskContent

fun TaskId.impl() = this as TaskIdWrapper
fun TaskTitle.impl() = this as TaskTitleWrapper
fun TaskContent.impl() = this as TaskContentWrapper