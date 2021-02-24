package mal.art.daznsportscalendar.util.extensions

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun Activity.startActivityWithFinish(intent: Intent) {
    startActivity(intent)
    finish()
}

fun Activity.openFragment(sfm: FragmentManager, container: Int, fragment: Fragment) {
    val transaction = sfm.beginTransaction()
    transaction.replace(container, fragment)
    transaction.addToBackStack(null)
    transaction.commit()
}