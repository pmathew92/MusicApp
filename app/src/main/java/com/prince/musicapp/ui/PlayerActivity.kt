package com.prince.musicapp.ui

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.prince.musicapp.GlideApp
import com.prince.musicapp.R
import com.prince.musicapp.model.Result
import com.prince.musicapp.repository.datasource.FavouriteDataSource
import kotlinx.android.synthetic.main.activity_player.*
import java.util.concurrent.TimeUnit


class PlayerActivity : AppCompatActivity(), Player.EventListener, PlayerContract.PlayerView {

    override lateinit var presenter: PlayerContract.PlayerPresenter

    private var player: SimpleExoPlayer? = null
    private var playWhenReady: Boolean = true
    private var currentWindow: Int = 0
    private var playbackPosition: Long = 0
    private var isPlaying = true

    private lateinit var item: Result

    private var isFavourite: Boolean = false

    override fun isFavourite() {
        isFavourite = true
        iv_add_favourites.setImageResource(R.drawable.aded_favourites)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        item = intent.getParcelableExtra("audio_track")

        PlayerPresenterImpl(this, FavouriteDataSource(this))
        presenter.checkIfAlreadyFavourite(item)

        setData()

        layout_play_pause.setOnClickListener {
            if (isPlaying) setPause()
            else setPlay()
        }

        iv_list.setOnClickListener {
            finish()
        }

        seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                player?.seekTo(progress.toLong())
                seek_bar.progress = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })


        iv_add_favourites.setOnClickListener {
            if (isFavourite) Toast.makeText(applicationContext, "Already Added", Toast.LENGTH_SHORT).show()
            else {
                presenter.addToFavourite(item)
                iv_add_favourites.setImageResource(R.drawable.aded_favourites)
                Toast.makeText(applicationContext, "Added to favourites", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setPlay() {
        isPlaying = true
        player?.playWhenReady = isPlaying
        iv_play_pause.setImageResource(R.drawable.pause)
    }

    private fun setPause() {
        isPlaying = false
        player?.playWhenReady = isPlaying
        iv_play_pause.setImageResource(R.drawable.play)
    }


    private fun setData() {
        tv_track.text = item.getTrackName()
        tv_artist_.text = item.getArtistName()
        tv_album.text = item.getCollectionName()
        GlideApp.with(this)
                .load(item.getArtworkUrl100())
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.placeholder)
                .centerCrop()
                .into(iv_image)
    }

    private val updateSeekBar = object : Runnable {

        override fun run() {
            val progress: Int = (player?.currentPosition!!).toInt()
            seek_bar.progress = progress
            tv_current_duration.text = String.format("%d:%02d",
                    player?.currentPosition?.let { TimeUnit.MILLISECONDS.toMinutes(it) },
                    player?.currentPosition?.let { TimeUnit.MILLISECONDS.toSeconds(it) }?.minus(TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(player?.currentPosition!!)))
            )

            if (player?.currentPosition!! >= player?.duration!!) {
                setPause()
            }
            seek_bar.postDelayed(this, 1000)
        }
    }

    private fun initializePlayer() {
        player = ExoPlayerFactory.newSimpleInstance(
                DefaultRenderersFactory(this),
                DefaultTrackSelector(), DefaultLoadControl())

//        audio_player.player = player


        player?.playWhenReady = playWhenReady
        player?.seekTo(currentWindow, playbackPosition)

        val uri = Uri.parse(item.getPreviewUrl() ?: "")
        val mediaSource = buildMediaSource(uri)
        player?.prepare(mediaSource, true, false)
        player?.addListener(this)
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        return ExtractorMediaSource.Factory(
                DefaultHttpDataSourceFactory("exoplayer-codelab")).createMediaSource(uri)
    }

    public override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer()
        }
    }

    public override fun onResume() {
        super.onResume()
        if (Util.SDK_INT <= 23 || player == null) {
            initializePlayer()
        }
    }

    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
//        audio_player.systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
//                or View.SYSTEM_UI_FLAG_FULLSCREEN
//                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    override fun onPlaybackParametersChanged(playbackParameters: PlaybackParameters?) {
    }

    override fun onSeekProcessed() {
    }

    override fun onTracksChanged(trackGroups: TrackGroupArray?, trackSelections: TrackSelectionArray?) {
    }

    override fun onPlayerError(error: ExoPlaybackException?) {
        Toast.makeText(this, "Error playing this track", Toast.LENGTH_SHORT).show()
    }

    override fun onLoadingChanged(isLoading: Boolean) {
    }

    override fun onPositionDiscontinuity(reason: Int) {
    }

    override fun onRepeatModeChanged(repeatMode: Int) {
    }

    override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
    }

    override fun onTimelineChanged(timeline: Timeline?, manifest: Any?) {
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        if (playbackState == Player.STATE_READY) initializeTrackInfo()

    }

    private fun initializeTrackInfo() {
        tv_total_duration.text = String.format("%d:%d",
                player?.duration?.let { TimeUnit.MILLISECONDS.toMinutes(it) },
                player?.duration?.let { TimeUnit.MILLISECONDS.toSeconds(it) }?.minus(TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(player?.duration!!)))
        )
        seek_bar.max = (player?.duration)?.toInt()!!
        seek_bar.postDelayed(updateSeekBar, 1000)
    }


    public override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) {
            releasePlayer()
        }
    }

    public override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) {
            releasePlayer()
        }
    }

    private fun releasePlayer() {
        if (player != null) {
            playbackPosition = player?.currentPosition!!
            currentWindow = player?.currentWindowIndex!!
            playWhenReady = player?.playWhenReady!!
            seek_bar.removeCallbacks(updateSeekBar)
            player?.release()
            player = null
        }
    }
}
