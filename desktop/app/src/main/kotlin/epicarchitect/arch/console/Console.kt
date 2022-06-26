package epicarchitect.arch.console

import epicarchitect.arch.console.architecture.IoDrivenArchitecture
import epicarchitect.arch.console.architecture.input
import epicarchitect.arch.console.architecture.output
import epicarchitect.arch.console.io.CreateTaskProvider
import epicarchitect.arch.console.io.DeleteTaskProvider
import epicarchitect.arch.console.io.TaskContentProvider
import epicarchitect.arch.console.io.TaskContentWrapper
import epicarchitect.arch.console.io.TaskIdWrapper
import epicarchitect.arch.console.io.TaskIdsProvider
import epicarchitect.arch.console.io.TaskTitleProvider
import epicarchitect.arch.console.io.TaskTitleWrapper
import epicarchitect.arch.console.io.impl
import epicarchitect.arch.console.repository.FakeTasksRepository
import epicarchitect.arch.io.CreateNewTask
import epicarchitect.arch.io.DeleteTask
import epicarchitect.arch.io.TaskContent
import epicarchitect.arch.io.TaskId
import epicarchitect.arch.io.TaskTitle

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
                            title = TaskTitleWrapper(args[1]),
                            content = TaskContentWrapper(args[2])
                        )
                    )
                }
                "deleteTask" -> {
                    val taskId = TaskIdWrapper(args[1].toInt())
                    architecture.input(DeleteTask(taskId))
                }
                "getTaskContent" -> {
                    val taskId = TaskIdWrapper(args[1].toInt())
                    val content = architecture.output<TaskId, TaskContent?>(taskId)?.impl()
                    println("Task ${taskId.value} content: ${content?.value}")
                }
                "getTaskTitle" -> {
                    val taskId = TaskIdWrapper(args[1].toInt())
                    val title = architecture.output<TaskId, TaskTitle?>(taskId)?.impl()
                    println("Task ${taskId.value} title: ${title?.value}")
                }
                "getTaskIds" -> {
                    val taskIds: List<TaskId> = architecture.output(key = Unit)
                    println(taskIds.map { it.impl().value })
                }
                "exit" -> break
                else -> throw IllegalArgumentException()
            }
        } catch (e: Exception) {
            println("write something nice, okay?")
        }
    }
}