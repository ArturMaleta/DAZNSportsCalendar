package mal.art.daznsportscalendar

import android.app.Application
import mal.art.daznsportscalendar.util.koin.repositoryModule
import mal.art.daznsportscalendar.util.koin.restModule
import mal.art.daznsportscalendar.util.koin.timeHelper
import mal.art.daznsportscalendar.util.koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DAZNApp: Application() {
    override fun onCreate() {
        super.onCreate()
        initializeDI()
    }

    private fun initializeDI() {
        startKoin {
            androidContext(this@DAZNApp)
            modules(modules)
        }
    }

    companion object {
        val modules = listOf(
            restModule,
            repositoryModule,
            viewModelModule,
            timeHelper
        )
    }
}