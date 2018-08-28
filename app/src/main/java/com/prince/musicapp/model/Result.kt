package com.prince.musicapp.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Result() :Parcelable {
    @SerializedName("wrapperType")
    @Expose
    private var wrapperType: String? = null
    @SerializedName("kind")
    @Expose
    private var kind: String? = null
    @SerializedName("collectionId")
    @Expose
    private var collectionId: Int = 0
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
    @SerializedName("collectionCensoredName")
    @Expose
    private var collectionCensoredName: String? = null
    @SerializedName("trackCensoredName")
    @Expose
    private var trackCensoredName: String? = null
    @SerializedName("collectionArtistId")
    @Expose
    private var collectionArtistId: Int = 0
    @SerializedName("collectionArtistViewUrl")
    @Expose
    private var collectionArtistViewUrl: String? = null
    @SerializedName("collectionViewUrl")
    @Expose
    private var collectionViewUrl: String? = null
    @SerializedName("trackViewUrl")
    @Expose
    private var trackViewUrl: String? = null
    @SerializedName("previewUrl")
    @Expose
    private var previewUrl: String? = null
    @SerializedName("artworkUrl30")
    @Expose
    private var artworkUrl30: String? = null
    @SerializedName("artworkUrl60")
    @Expose
    private var artworkUrl60: String? = null
    @SerializedName("artworkUrl100")
    @Expose
    private var artworkUrl100: String? = null
    @SerializedName("collectionPrice")
    @Expose
    private var collectionPrice: Double = 0.toDouble()
    @SerializedName("trackPrice")
    @Expose
    private var trackPrice: Double = 0.toDouble()
    @SerializedName("trackRentalPrice")
    @Expose
    private var trackRentalPrice: Double = 0.toDouble()
    @SerializedName("collectionHdPrice")
    @Expose
    private var collectionHdPrice: Double = 0.toDouble()
    @SerializedName("trackHdPrice")
    @Expose
    private var trackHdPrice: Double = 0.toDouble()
    @SerializedName("trackHdRentalPrice")
    @Expose
    private var trackHdRentalPrice: Double = 0.toDouble()
    @SerializedName("releaseDate")
    @Expose
    private var releaseDate: String? = null
    @SerializedName("collectionExplicitness")
    @Expose
    private var collectionExplicitness: String? = null
    @SerializedName("trackExplicitness")
    @Expose
    private var trackExplicitness: String? = null
    @SerializedName("discCount")
    @Expose
    private var discCount: Int = 0
    @SerializedName("discNumber")
    @Expose
    private var discNumber: Int = 0
    @SerializedName("trackCount")
    @Expose
    private var trackCount: Int = 0
    @SerializedName("trackNumber")
    @Expose
    private var trackNumber: Int = 0
    @SerializedName("trackTimeMillis")
    @Expose
    private var trackTimeMillis: Int = 0
    @SerializedName("country")
    @Expose
    private var country: String? = null
    @SerializedName("currency")
    @Expose
    private var currency: String? = null
    @SerializedName("primaryGenreName")
    @Expose
    private var primaryGenreName: String? = null
    @SerializedName("contentAdvisoryRating")
    @Expose
    private var contentAdvisoryRating: String? = null
    @SerializedName("shortDescription")
    @Expose
    private var shortDescription: String? = null
    @SerializedName("longDescription")
    @Expose
    private var longDescription: String? = null
    @SerializedName("hasITunesExtras")
    @Expose
    private var hasITunesExtras: Boolean = false
    @SerializedName("artistId")
    @Expose
    private var artistId: Int = 0
    @SerializedName("amgArtistId")
    @Expose
    private var amgArtistId: Int = 0
    @SerializedName("artistViewUrl")
    @Expose
    private var artistViewUrl: String? = null
    @SerializedName("copyright")
    @Expose
    private var copyright: String? = null
    @SerializedName("description")
    @Expose
    private var description: String? = null

    constructor(parcel: Parcel) : this() {
        wrapperType = parcel.readString()
        kind = parcel.readString()
        collectionId = parcel.readInt()
        trackId = parcel.readInt()
        artistName = parcel.readString()
        collectionName = parcel.readString()
        trackName = parcel.readString()
        collectionCensoredName = parcel.readString()
        trackCensoredName = parcel.readString()
        collectionArtistId = parcel.readInt()
        collectionArtistViewUrl = parcel.readString()
        collectionViewUrl = parcel.readString()
        trackViewUrl = parcel.readString()
        previewUrl = parcel.readString()
        artworkUrl30 = parcel.readString()
        artworkUrl60 = parcel.readString()
        artworkUrl100 = parcel.readString()
        collectionPrice = parcel.readDouble()
        trackPrice = parcel.readDouble()
        trackRentalPrice = parcel.readDouble()
        collectionHdPrice = parcel.readDouble()
        trackHdPrice = parcel.readDouble()
        trackHdRentalPrice = parcel.readDouble()
        releaseDate = parcel.readString()
        collectionExplicitness = parcel.readString()
        trackExplicitness = parcel.readString()
        discCount = parcel.readInt()
        discNumber = parcel.readInt()
        trackCount = parcel.readInt()
        trackNumber = parcel.readInt()
        trackTimeMillis = parcel.readInt()
        country = parcel.readString()
        currency = parcel.readString()
        primaryGenreName = parcel.readString()
        contentAdvisoryRating = parcel.readString()
        shortDescription = parcel.readString()
        longDescription = parcel.readString()
        hasITunesExtras = parcel.readByte() != 0.toByte()
        artistId = parcel.readInt()
        amgArtistId = parcel.readInt()
        artistViewUrl = parcel.readString()
        copyright = parcel.readString()
        description = parcel.readString()
    }

    fun getWrapperType(): String? {
        return wrapperType
    }

    fun setWrapperType(wrapperType: String) {
        this.wrapperType = wrapperType
    }

    fun getKind(): String? {
        return kind
    }

    fun setKind(kind: String) {
        this.kind = kind
    }

    fun getCollectionId(): Int {
        return collectionId
    }

    fun setCollectionId(collectionId: Int) {
        this.collectionId = collectionId
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

    fun setCollectionName(collectionName: String) {
        this.collectionName = collectionName
    }

    fun getTrackName(): String? {
        return trackName
    }

    fun setTrackName(trackName: String) {
        this.trackName = trackName
    }

    fun getCollectionCensoredName(): String? {
        return collectionCensoredName
    }

    fun setCollectionCensoredName(collectionCensoredName: String) {
        this.collectionCensoredName = collectionCensoredName
    }

    fun getTrackCensoredName(): String? {
        return trackCensoredName
    }

    fun setTrackCensoredName(trackCensoredName: String) {
        this.trackCensoredName = trackCensoredName
    }

    fun getCollectionArtistId(): Int {
        return collectionArtistId
    }

    fun setCollectionArtistId(collectionArtistId: Int) {
        this.collectionArtistId = collectionArtistId
    }

    fun getCollectionArtistViewUrl(): String? {
        return collectionArtistViewUrl
    }

    fun setCollectionArtistViewUrl(collectionArtistViewUrl: String) {
        this.collectionArtistViewUrl = collectionArtistViewUrl
    }

    fun getCollectionViewUrl(): String? {
        return collectionViewUrl
    }

    fun setCollectionViewUrl(collectionViewUrl: String) {
        this.collectionViewUrl = collectionViewUrl
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

    fun getArtworkUrl30(): String? {
        return artworkUrl30
    }

    fun setArtworkUrl30(artworkUrl30: String) {
        this.artworkUrl30 = artworkUrl30
    }

    fun getArtworkUrl60(): String? {
        return artworkUrl60
    }

    fun setArtworkUrl60(artworkUrl60: String) {
        this.artworkUrl60 = artworkUrl60
    }

    fun getArtworkUrl100(): String? {
        return artworkUrl100
    }

    fun setArtworkUrl100(artworkUrl100: String) {
        this.artworkUrl100 = artworkUrl100
    }

    fun getCollectionPrice(): Double {
        return collectionPrice
    }

    fun setCollectionPrice(collectionPrice: Double) {
        this.collectionPrice = collectionPrice
    }

    fun getTrackPrice(): Double {
        return trackPrice
    }

    fun setTrackPrice(trackPrice: Double) {
        this.trackPrice = trackPrice
    }

    fun getTrackRentalPrice(): Double {
        return trackRentalPrice
    }

    fun setTrackRentalPrice(trackRentalPrice: Double) {
        this.trackRentalPrice = trackRentalPrice
    }

    fun getCollectionHdPrice(): Double {
        return collectionHdPrice
    }

    fun setCollectionHdPrice(collectionHdPrice: Double) {
        this.collectionHdPrice = collectionHdPrice
    }

    fun getTrackHdPrice(): Double {
        return trackHdPrice
    }

    fun setTrackHdPrice(trackHdPrice: Double) {
        this.trackHdPrice = trackHdPrice
    }

    fun getTrackHdRentalPrice(): Double {
        return trackHdRentalPrice
    }

    fun setTrackHdRentalPrice(trackHdRentalPrice: Double) {
        this.trackHdRentalPrice = trackHdRentalPrice
    }

    fun getReleaseDate(): String? {
        return releaseDate
    }

    fun setReleaseDate(releaseDate: String) {
        this.releaseDate = releaseDate
    }

    fun getCollectionExplicitness(): String? {
        return collectionExplicitness
    }

    fun setCollectionExplicitness(collectionExplicitness: String) {
        this.collectionExplicitness = collectionExplicitness
    }

    fun getTrackExplicitness(): String? {
        return trackExplicitness
    }

    fun setTrackExplicitness(trackExplicitness: String) {
        this.trackExplicitness = trackExplicitness
    }

    fun getDiscCount(): Int {
        return discCount
    }

    fun setDiscCount(discCount: Int) {
        this.discCount = discCount
    }

    fun getDiscNumber(): Int {
        return discNumber
    }

    fun setDiscNumber(discNumber: Int) {
        this.discNumber = discNumber
    }

    fun getTrackCount(): Int {
        return trackCount
    }

    fun setTrackCount(trackCount: Int) {
        this.trackCount = trackCount
    }

    fun getTrackNumber(): Int {
        return trackNumber
    }

    fun setTrackNumber(trackNumber: Int) {
        this.trackNumber = trackNumber
    }

    fun getTrackTimeMillis(): Int {
        return trackTimeMillis
    }

    fun setTrackTimeMillis(trackTimeMillis: Int) {
        this.trackTimeMillis = trackTimeMillis
    }

    fun getCountry(): String? {
        return country
    }

    fun setCountry(country: String) {
        this.country = country
    }

    fun getCurrency(): String? {
        return currency
    }

    fun setCurrency(currency: String) {
        this.currency = currency
    }

    fun getPrimaryGenreName(): String? {
        return primaryGenreName
    }

    fun setPrimaryGenreName(primaryGenreName: String) {
        this.primaryGenreName = primaryGenreName
    }

    fun getContentAdvisoryRating(): String? {
        return contentAdvisoryRating
    }

    fun setContentAdvisoryRating(contentAdvisoryRating: String) {
        this.contentAdvisoryRating = contentAdvisoryRating
    }

    fun getShortDescription(): String? {
        return shortDescription
    }

    fun setShortDescription(shortDescription: String) {
        this.shortDescription = shortDescription
    }

    fun getLongDescription(): String? {
        return longDescription
    }

    fun setLongDescription(longDescription: String) {
        this.longDescription = longDescription
    }

    fun isHasITunesExtras(): Boolean {
        return hasITunesExtras
    }

    fun setHasITunesExtras(hasITunesExtras: Boolean) {
        this.hasITunesExtras = hasITunesExtras
    }

    fun getArtistId(): Int {
        return artistId
    }

    fun setArtistId(artistId: Int) {
        this.artistId = artistId
    }

    fun getAmgArtistId(): Int {
        return amgArtistId
    }

    fun setAmgArtistId(amgArtistId: Int) {
        this.amgArtistId = amgArtistId
    }

    fun getArtistViewUrl(): String? {
        return artistViewUrl
    }

    fun setArtistViewUrl(artistViewUrl: String) {
        this.artistViewUrl = artistViewUrl
    }

    fun getCopyright(): String? {
        return copyright
    }

    fun setCopyright(copyright: String) {
        this.copyright = copyright
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String) {
        this.description = description
    }

    override fun describeContents(): Int {
        return hashCode()
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(wrapperType)
        dest?.writeString(kind)
        dest?.writeInt(collectionId)
        dest?.writeInt(trackId)
        dest?.writeString(artistName)
        dest?.writeString(collectionName)
        dest?.writeString(trackName)
        dest?.writeString(collectionCensoredName)
        dest?.writeString(trackCensoredName)
        dest?.writeInt(collectionArtistId)
        dest?.writeString(collectionArtistViewUrl)
        dest?.writeString(collectionViewUrl)
        dest?.writeString(trackViewUrl)
        dest?.writeString(previewUrl)
        dest?.writeString(artworkUrl30)
        dest?.writeString(artworkUrl60)
        dest?.writeString(artworkUrl100)
        dest?.writeDouble(collectionPrice)
        dest?.writeDouble(trackPrice)
        dest?.writeDouble(trackRentalPrice)
        dest?.writeDouble(collectionHdPrice)
        dest?.writeDouble(trackHdPrice)
        dest?.writeDouble(trackHdRentalPrice)
        dest?.writeString(releaseDate)
        dest?.writeString(collectionExplicitness)
        dest?.writeString(trackExplicitness)
        dest?.writeInt(discCount)
        dest?.writeInt(discNumber)
        dest?.writeInt(trackCount)
        dest?.writeInt(trackNumber)
        dest?.writeInt(trackTimeMillis)
        dest?.writeString(country)
        dest?.writeString(currency)
        dest?.writeString(primaryGenreName)
        dest?.writeString(contentAdvisoryRating)
        dest?.writeString(shortDescription)
        dest?.writeString(longDescription)
        dest?.writeByte(if (hasITunesExtras) 1 else 0)
        dest?.writeInt(artistId)
        dest?.writeInt(amgArtistId)
        dest?.writeString(artistViewUrl)
        dest?.writeString(copyright)
        dest?.writeString(description)
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