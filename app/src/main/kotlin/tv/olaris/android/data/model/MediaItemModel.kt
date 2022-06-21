package tv.olaris.android.data.model

sealed class MediaItem(
    open val uuid: String,
    open val posterUrl: String,
    open val posterPath: String,
    open val title: String,
    open val serverId: Int?,
    open val runtime: Double = 0.0,
    open val subTitle: String = "",
    open val fileUuid: String = "",
    open val playtime: Double = 0.0,
    open val finished: Boolean = false,
    open val baseImageUrl: String = "https://image.tmdb.org/t/p/",
)
