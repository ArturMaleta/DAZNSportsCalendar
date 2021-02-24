package mal.art.daznsportscalendar.splash.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.reactivex.Single
import mal.art.daznsportscalendar.R
import mal.art.daznsportscalendar.dashboard.view.activity.DashboardActivity
import mal.art.daznsportscalendar.util.base.BaseValues
import mal.art.daznsportscalendar.util.extensions.startActivityWithFinish
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startApp()
    }

    private fun startApp() = Single.timer(BaseValues.TimedEvents.SPLASH_TIME_IN_MILLIS, TimeUnit.MILLISECONDS)
        .map { openDashboard() }
        .subscribe()

    private fun openDashboard() = startActivityWithFinish(DashboardActivity.intent(this))
}

