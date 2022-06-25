package epicarchitect.arch.android.app

import android.app.Application
import epicarchitect.arch.android.app.factorystore.set
import epicarchitect.arch.android.app.repository.TaskRepository

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        architecture.factoryStore.set {
            TaskRepository()
        }
    }

    companion object {
        val architecture by lazy {
            Architecture()
        }
    }
}