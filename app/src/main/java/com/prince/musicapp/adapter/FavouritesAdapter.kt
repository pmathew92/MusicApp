package com.prince.musicapp.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.prince.musicapp.GlideApp
import com.prince.musicapp.R
import com.prince.musicapp.model.Result
import com.prince.musicapp.ui.FavouritesActivity
import com.prince.musicapp.ui.PlayerActivity
import kotlinx.android.synthetic.main.fragment_item.view.*

class FavouritesAdapter(private val mValues: MutableList<Result>,
                        private val mContext: Context) :
        RecyclerView.Adapter<FavouritesAdapter.ViewHolder>() {

    private val listener: ItemListener = mContext as FavouritesActivity
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.bindData(item)
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        private val mTrackName = mView.tv_track_name!!
        private val mCardView = mView.layout_track!!
        private val mTrackImage = mView.iv_track_image!!
        private val mArtist = mView.tv_artist_name!!
        private val mAlbum = mView.tv_album_name!!

        fun bindData(item: Result) {
            mTrackName.text = item.getTrackName()
            mArtist.text = item.getArtistName()
            mAlbum.text = item.getCollectionName()
            GlideApp.with(mContext)
                    .load(item.getArtworkUrl100())
                    .dontAnimate()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.placeholder)
                    .centerCrop()
                    .into(mTrackImage)
            mCardView.setOnClickListener {
                val intent = Intent(mContext, PlayerActivity::class.java)
                intent.putExtra("audio_track", item)
                mContext.startActivity(intent)
            }
        }
    }

    fun removeItem(position: Int) {
        listener.removeItem(mValues[position])
        mValues.removeAt(position)
        notifyItemRemoved(position)
    }

    interface ItemListener {
        fun removeItem(item: Result)
    }
}
