package epicarchitect.domain

data class TaskCreationParameters(
    val title: TaskTitle,
    val content: TaskContent
)