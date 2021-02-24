package mal.art.daznsportscalendar.dashboard.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import mal.art.daznsportscalendar.R
import mal.art.daznsportscalendar.dashboard.view.fragments.EventsFragment
import mal.art.daznsportscalendar.dashboard.view.fragments.ScheduleFragment
import mal.art.daznsportscalendar.util.extensions.openFragment

class DashboardActivity : AppCompatActivity() {
  private lateinit var bottomNavigationBar: BottomNavigationView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_dashboard)

    bottomNavigationBar = findViewById(R.id.bottom_navigation)
    setupBottomNavigationBar()

    openFragment(supportFragmentManager, R.id.dashboard_fragments_container, EventsFragment())
  }

  private fun setupBottomNavigationBar() =
    bottomNavigationBar.setOnNavigationItemSelectedListener {
      when (it.itemId) {
        R.id.events_nav_bar -> openFragment(supportFragmentManager, R.id.dashboard_fragments_container, EventsFragment())
        R.id.schedule_nav_bar ->openFragment(supportFragmentManager, R.id.dashboard_fragments_container, ScheduleFragment())
      }
      true
    }


  companion object {
    fun intent(context: Context) = Intent(context, DashboardActivity::class.java).apply { flags = Intent.FLAG_ACTIVITY_SINGLE_TOP }
  }
}