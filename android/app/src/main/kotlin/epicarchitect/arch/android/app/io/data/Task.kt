package epicarchitect.arch.android.app.io.data

data class Task(
    val id: TaskId,
    val title: TaskTitle,
    val content: TaskContent
)

class TaskId(val id: Int)
class TaskTitle(val title: String)
class TaskContent(val content: String)
