package mal.art.daznsportscalendar.dashboard.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mal.art.daznsportscalendar.R
import mal.art.daznsportscalendar.dashboard.view.fragments.EventsFragment
import mal.art.daznsportscalendar.dashboard.view.fragments.ScheduleFragment
import mal.art.daznsportscalendar.databinding.ActivityDashboardBinding
import mal.art.daznsportscalendar.util.extensions.openFragment

class DashboardActivity : AppCompatActivity() {
  private lateinit var binding: ActivityDashboardBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityDashboardBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setupBottomNavigationBar()

    openFragment(supportFragmentManager, R.id.dashboard_fragments_container, EventsFragment())
  }

  private fun setupBottomNavigationBar() =
    binding.bottomNavigation.setOnNavigationItemSelectedListener {
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