package epicarchitect.arch.android.app.compose.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import epicarchitect.arch.android.app.App
import epicarchitect.arch.android.app.R
import epicarchitect.arch.android.app.architecture.input
import epicarchitect.arch.android.app.architecture.outputAsStateBy
import epicarchitect.arch.android.app.io.TaskContentWrapper
import epicarchitect.arch.android.app.io.TaskTitleWrapper
import epicarchitect.arch.android.app.io.impl
import epicarchitect.arch.io.CreateNewTask
import epicarchitect.arch.io.DeleteTask
import epicarchitect.arch.io.TaskContent
import epicarchitect.arch.io.TaskId
import epicarchitect.arch.io.TaskTitle

@Composable
fun TasksScreen() {
    val taskIds: List<TaskId>? by App.architecture.outputAsStateBy(key = Unit)
    taskIds?.let { taskIds ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = rememberInsetsPaddingValues(
                insets = LocalWindowInsets.current.systemBars,
                additionalTop = 16.dp,
                additionalBottom = 16.dp,
                additionalStart = 16.dp,
                additionalEnd = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            item(key = Int.MIN_VALUE) {
                Logo()
            }

            item(key = Int.MIN_VALUE + 1) {
                CreateTaskButton()
            }

            items(taskIds, key = { it.impl() }) {
                Task(it)
            }

            if (taskIds.size > 7) {
                item(key = Int.MAX_VALUE) {
                    CreateTaskButton()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalUnitApi::class)
@Composable
fun LazyItemScope.Logo() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .animateItemPlacement(),
        contentAlignment = Alignment.Center
    ) {
        Box {
            Image(
                modifier = Modifier.size(56.dp),
                painter = painterResource(R.drawable.app_icon),
                contentDescription = stringResource(R.string.app_name)
            )

            Text(
                modifier = Modifier
                    .padding(start = 42.dp, bottom = 11.2.dp)
                    .align(Alignment.BottomStart),
                text = stringResource(R.string.app_name)
                    .drop(1),
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.primary,
                letterSpacing = TextUnit(10f, TextUnitType.Sp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.CreateTaskButton() {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .animateItemPlacement(),
        onClick = {
            App.architecture.input(
                CreateNewTask(
                    title = TaskTitleWrapper("Item"),
                    content = TaskContentWrapper("Content")
                )
            )
        },
        content = {
            Text(
                text = "Create task"
            )
        },
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.Task(taskId: TaskId) {
    val title: TaskTitle? by App.architecture.outputAsStateBy(taskId)
    val content: TaskContent? by App.architecture.outputAsStateBy(taskId)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateItemPlacement(),
    ) {
        Box {
            Column(
                modifier = Modifier.padding(16.dp),
            ) {
                Text(
                    text = title?.impl() ?: "",
                    style = MaterialTheme.typography.h6
                )

                Text(
                    text = content?.impl() ?: "",
                    style = MaterialTheme.typography.body1
                )
            }

            Button(
                modifier = Modifier
                    .padding(end = 12.dp, bottom = 8.dp)
                    .align(Alignment.BottomEnd),
                onClick = {
                    App.architecture.input(DeleteTask(taskId))
                },
                content = {
                    Text(
                        text = "Delete item ${taskId.impl()}"
                    )
                },
            )
        }
    }
}