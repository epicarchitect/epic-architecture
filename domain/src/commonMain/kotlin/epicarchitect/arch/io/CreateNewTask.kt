package epicarchitect.arch.io

data class CreateNewTask(
    val title: TaskTitle,
    val content: TaskContent
)