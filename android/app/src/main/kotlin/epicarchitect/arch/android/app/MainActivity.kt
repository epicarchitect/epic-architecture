package epicarchitect.arch.android.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.runtime.collectAsState
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
import epicarchitect.arch.android.app.data.TaskContent
import epicarchitect.arch.android.app.data.TaskId
import epicarchitect.arch.android.app.data.TaskTitle

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArchTheme {
                Tasks()
            }
        }
    }
}

@Composable
fun Tasks() {
    val taskIds by taskRepository.taskIds().collectAsState(initial = emptyList())

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

        items(taskIds, key = { it.id }) {
            Task(it)
        }

        if (taskIds.size > 7) {
            item(key = Int.MAX_VALUE) {
                CreateTaskButton()
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
            taskRepository.insert(
                title = "Item",
                content = "Content"
            )
        },
        content = {
            Text(
                text = "Create task"
            )
        },
    )
}

@Composable
inline fun <reified DATA : Any?, reified ARGS> Architecture.stateBy(args: ARGS, initial: DATA? = null) =
    outputFlow<DATA, ARGS>(args).collectAsState(initial = initial).value

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.Task(taskId: TaskId) {
    val title: TaskTitle? = App.architecture.stateBy(taskId)
    val content: TaskContent? = App.architecture.stateBy(taskId)

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
                    text = title?.title ?: "",
                    style = MaterialTheme.typography.h6
                )

                Text(
                    text = content?.content ?: "",
                    style = MaterialTheme.typography.body1
                )
            }

            Button(
                modifier = Modifier
                    .padding(end = 12.dp, bottom = 8.dp)
                    .align(Alignment.BottomEnd),
                onClick = {
                    taskRepository.delete(taskId)
                },
                content = {
                    Text(
                        text = "Delete item ${taskId.id}"
                    )
                },
            )
        }
    }
}