package tv.olaris.android.compose.movie.library

import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import org.koin.androidx.compose.viewModel
import tv.olaris.android.data.model.Movie
import tv.olaris.android.compose.media.grid.MediaGrid
import tv.olaris.android.viewModel.movie.library.MovieLibraryViewModel

@ExperimentalMaterial3Api
@Composable
fun MovieLibrary(

) {
    val vm by viewModel<MovieLibraryViewModel>()
    val movieLibraryState = rememberLazyGridState()
    val mediaList = listOf<Movie>()

    MediaGrid(
        mediaList = mediaList,
        state = movieLibraryState,
    )
    
    LaunchedEffect(movieLibraryState) {
        TODO("load movies from repo")
    }
}
