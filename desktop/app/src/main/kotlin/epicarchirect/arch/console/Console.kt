package epicarchirect.arch.console

import epicarchirect.arch.console.architecture.IoDrivenArchitecture
import epicarchirect.arch.console.architecture.input
import epicarchirect.arch.console.architecture.output
import epicarchirect.arch.console.io.CreateTaskProvider
import epicarchirect.arch.console.io.DeleteTaskProvider
import epicarchirect.arch.console.io.TaskContentProvider
import epicarchirect.arch.console.io.TaskIdsProvider
import epicarchirect.arch.console.io.TaskTitleProvider
import epicarchirect.arch.console.io.data.CreateNewTask
import epicarchirect.arch.console.io.data.DeleteTask
import epicarchirect.arch.console.io.data.TaskContent
import epicarchirect.arch.console.io.data.TaskId
import epicarchirect.arch.console.io.data.TaskTitle
import epicarchirect.arch.console.repository.FakeTasksRepository

fun main() {
    val tasksRepository = FakeTasksRepository()

    val architecture = IoDrivenArchitecture {
        output { TaskIdsProvider(tasksRepository) }
        output { TaskContentProvider(tasksRepository) }
        output { TaskTitleProvider(tasksRepository) }
        input { CreateTaskProvider(tasksRepository) }
        input { DeleteTaskProvider(tasksRepository) }
    }

    while (true) {
        try {
            print("command: ")

            val args = readln().split(" ")

            when (args[0]) {
                "createTask" -> {
                    architecture.input(
                        CreateNewTask(
                            title = TaskTitle(args[1]),
                            content = TaskContent(args[2])
                        )
                    )
                }
                "deleteTask" -> {
                    val taskId = TaskId(args[1].toInt())
                    architecture.input(DeleteTask(taskId))
                }
                "getTaskContent" -> {
                    val taskId = TaskId(args[1].toInt())
                    val content: TaskContent? = architecture.output(taskId)
                    println("Task ${taskId.id} content: ${content?.content}")
                }
                "getTaskTitle" -> {
                    val taskId = TaskId(args[1].toInt())
                    val title: TaskTitle? = architecture.output(taskId)
                    println("Task ${taskId.id} title: ${title?.title}")
                }
                "getTaskIds" -> {
                    val taskIds: List<TaskId> = architecture.output(key = Unit)
                    println(taskIds.map { it.id })
                }
                "exit" -> break
                else -> throw IllegalArgumentException()
            }
        } catch (e: Exception) {
            println("write something nice, okay?")
        }
    }
}