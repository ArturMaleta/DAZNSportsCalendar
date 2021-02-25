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
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        openDashboard()
    }

    private fun openDashboard() = startActivityWithFinish(DashboardActivity.intent(this))
}

