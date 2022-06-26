package epicarchitect.arch.io

data class Task(
    val id: TaskId,
    val title: TaskTitle,
    val content: TaskContent
)

interface TaskId
interface TaskTitle
interface TaskContent
