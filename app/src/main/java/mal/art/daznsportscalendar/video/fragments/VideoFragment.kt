package mal.art.daznsportscalendar.video.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import mal.art.daznsportscalendar.databinding.VideoFragmentLayoutBinding

class VideoFragment(private val videoUrl: String): Fragment() {
    private var playWhenReady = true
    private var currentWindow = 0
    private var playbackPosition: Long = 0
    private var _binding: VideoFragmentLayoutBinding? = null
    private val binding get() = _binding!!

    private lateinit var player: ExoPlayer

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = VideoFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun initializePlayer() {
        player = SimpleExoPlayer.Builder(requireContext()).build()
        binding.playerView.player = player

        val mediaItem = MediaItem.fromUri(videoUrl)
        player.setMediaItem(mediaItem)

        player.playWhenReady = playWhenReady
        player.seekTo(currentWindow, playbackPosition)
        player.prepare()
    }

    private fun releasePlayer() {
        playWhenReady = player.playWhenReady
        playbackPosition = player.currentPosition
        currentWindow = player.currentWindowIndex
        player.release()
    }

    override fun onResume() {
        super.onResume()
        initializePlayer()
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}