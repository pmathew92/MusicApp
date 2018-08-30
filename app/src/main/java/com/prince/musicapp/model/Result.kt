package com.prince.musicapp.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "favourites")
class Result() : Parcelable {
    override fun describeContents(): Int {
        return 0
    }

    @PrimaryKey
    @SerializedName("trackId")
    @Expose
    private var trackId: Int = 0
    @SerializedName("artistName")
    @Expose
    private var artistName: String? = null
    @SerializedName("collectionName")
    @Expose
    private var collectionName: String? = null
    @SerializedName("trackName")
    @Expose
    private var trackName: String? = null
    @SerializedName("trackViewUrl")
    @Expose
    private var trackViewUrl: String? = null
    @SerializedName("previewUrl")
    @Expose
    private var previewUrl: String? = null
    @SerializedName("artworkUrl100")
    @Expose
    private var artworkUrl100: String? = null
    @Ignore
    private var selected = false

    constructor(parcel: Parcel) : this() {
        trackId = parcel.readInt()
        artistName = parcel.readString()
        collectionName = parcel.readString()
        trackName = parcel.readString()
        trackViewUrl = parcel.readString()
        previewUrl = parcel.readString()
        artworkUrl100 = parcel.readString()
        selected = parcel.readInt() != 0
    }


    fun getTrackId(): Int {
        return trackId
    }

    fun setTrackId(trackId: Int) {
        this.trackId = trackId
    }

    fun getArtistName(): String? {
        return artistName
    }

    fun setArtistName(artistName: String) {
        this.artistName = artistName
    }

    fun getCollectionName(): String? {
        return collectionName
    }

    fun getSelected(): Boolean {
        return selected
    }

    fun setCollectionName(collectionName: String) {
        this.collectionName = collectionName
    }

    fun getTrackName(): String? {
        return trackName
    }

    fun setTrackName(trackName: String) {
        this.trackName = trackName
    }

    fun setSelected(selected: Boolean) {
        this.selected = selected
    }


    fun getTrackViewUrl(): String? {
        return trackViewUrl
    }

    fun setTrackViewUrl(trackViewUrl: String) {
        this.trackViewUrl = trackViewUrl
    }

    fun getPreviewUrl(): String? {
        return previewUrl
    }

    fun setPreviewUrl(previewUrl: String) {
        this.previewUrl = previewUrl
    }

    fun getArtworkUrl100(): String? {
        return artworkUrl100
    }

    fun setArtworkUrl100(artworkUrl100: String) {
        this.artworkUrl100 = artworkUrl100
    }


    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(trackId)
        dest?.writeString(artistName)
        dest?.writeString(collectionName)
        dest?.writeString(trackName)
        dest?.writeString(trackViewUrl)
        dest?.writeString(previewUrl)
        dest?.writeString(artworkUrl100)
        dest?.writeInt(if (selected) 1 else 0)
    }


    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }
}