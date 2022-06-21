package tv.olaris.android.data.model

import tv.olaris.android.fragment.MovieBase
import tv.olaris.android.fragment.PlaystateBase

data class Movie(
    override val uuid: String,
    override val title: String,
    override val posterUrl: String,
    override val posterPath: String,
    override val serverId: Int? = null,
    override val runtime: Double = 0.0,
    override val subTitle: String = "",
    override val fileUuid: String = "",
    override val playtime: Double = 0.0,
    override val finished: Boolean = false,
    override val baseImageUrl: String = "https://image.tmdb.org/t/p/",
    val backdropPath: String,
    val year: Int,
    val overview: String,
    val movieBase: MovieBase,
) : MediaItem(
    uuid = uuid,
    posterUrl = posterUrl,
    posterPath = posterPath,
    title = title,
    serverId = serverId,
    runtime = runtime,
    subTitle = subTitle,
    fileUuid = fileUuid,
    playtime = playtime,
    finished = finished,
    baseImageUrl = baseImageUrl,
)

val testMovieModel = Movie(
    uuid = "",
    title = "The Shawshank Redemption",
    subTitle = "Two imprisoned",
    posterUrl = "",
    posterPath = "",
    backdropPath = "",
    year = 1994,
    overview = "Two imprisoned",
    movieBase = MovieBase(
        name = "",
        title = "The Shawshank Redemption",
        year = "1994",
        overview = "Two imprisoned",
        imdbID = "",
        tmdbID = 1,
        backdropPath = "",
        posterPath = "",
        posterURL = "",
        uuid = "",
        files = listOf(),
        playState = MovieBase.PlayState(
            __typename = "",
            playstateBase = PlaystateBase(
                finished = false,
                playtime = 0.0,
                uuid = "",
            )
        )
    )
)
