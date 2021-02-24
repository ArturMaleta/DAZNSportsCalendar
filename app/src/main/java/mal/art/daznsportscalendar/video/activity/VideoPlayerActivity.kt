package mal.art.daznsportscalendar.video.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import mal.art.daznsportscalendar.R
import mal.art.daznsportscalendar.util.base.BaseValues
import mal.art.daznsportscalendar.util.extensions.openFragment
import mal.art.daznsportscalendar.util.extensions.setVisible
import mal.art.daznsportscalendar.video.fragments.VideoFragment

class VideoPlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val videoUrl = intent.getStringExtra(BaseValues.Message.VIDEO_URL_MESSAGE)
        if (videoUrl != null) {
            openFragment(
                supportFragmentManager, R.id.video_fragments_container, VideoFragment(videoUrl)
            )
        } else {
            val noVideoUrlTv: TextView = findViewById(R.id.no_url_tv)
            noVideoUrlTv.setVisible()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    companion object {
        fun intent(context: Context) = Intent(context, VideoPlayerActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }
    }
}