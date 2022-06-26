package epicarchitect.arch.android.app.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import epicarchitect.arch.android.app.compose.theme.ArchTheme
import epicarchitect.arch.android.app.compose.screen.TasksScreen

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArchTheme {
                TasksScreen()
            }
        }
    }
}