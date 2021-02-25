package mal.art.daznsportscalendar.video.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mal.art.daznsportscalendar.R
import mal.art.daznsportscalendar.databinding.ActivityVideoPlayerBinding
import mal.art.daznsportscalendar.util.base.BaseValues
import mal.art.daznsportscalendar.util.extensions.openFragment
import mal.art.daznsportscalendar.util.extensions.setVisible
import mal.art.daznsportscalendar.video.fragments.VideoFragment

class VideoPlayerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val videoUrl = intent.getStringExtra(BaseValues.Message.VIDEO_URL_MESSAGE)
        if (videoUrl != null) {
            openFragment(
                supportFragmentManager, R.id.video_fragments_container, VideoFragment(videoUrl)
            )
        } else {
            binding.noUrlTv.setVisible()
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