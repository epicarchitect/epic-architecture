package epicarchitect.arch.console

import epicarchitect.arch.console.io.TaskContentWrapper
import epicarchitect.arch.console.io.TaskIdWrapper
import epicarchitect.arch.console.io.TaskTitleWrapper
import epicarchitect.arch.console.io.impl
import epicarchitect.architecture.procedure.ProcedureDrivenArchitecture
import epicarchitect.domain.TaskContent
import epicarchitect.domain.TaskCreationParameters
import epicarchitect.domain.TaskDeletionParameters
import epicarchitect.domain.TaskId
import epicarchitect.domain.TaskTitle

fun main() {
    val tasksRepository = FakeTasksRepository()

    val architecture = ProcedureDrivenArchitecture {
        output { _: Unit ->
            tasksRepository.getTasks().map { it.id }
        }
        output { key: TaskId ->
            tasksRepository.getTask(key)?.content
        }
        output { key: TaskId ->
            tasksRepository.getTask(key)?.title
        }
        input { parameters: TaskCreationParameters ->
            tasksRepository.createTask(parameters)
        }
        input { parameters: TaskDeletionParameters ->
            tasksRepository.delete(parameters)
        }
    }

    while (true) {
        try {
            print("command: ")

            val args = readln().split(" ")

            when (args[0]) {
                "createTask" -> {
                    architecture.input(
                        TaskCreationParameters(
                            title = TaskTitleWrapper(args[1]),
                            content = TaskContentWrapper(args[2])
                        )
                    )
                }
                "deleteTask" -> {
                    val taskId = TaskIdWrapper(args[1].toInt())
                    architecture.input(TaskDeletionParameters(taskId))
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