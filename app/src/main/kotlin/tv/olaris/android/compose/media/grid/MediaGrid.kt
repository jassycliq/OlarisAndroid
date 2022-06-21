package tv.olaris.android.compose.media.grid

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tv.olaris.android.compose.media.item.MediaItem
import tv.olaris.android.data.model.MediaItem
import tv.olaris.android.data.model.testMovieModel

@ExperimentalMaterial3Api
@Composable
fun MediaGrid(
    state: LazyGridState,
    mediaList: List<MediaItem>,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 96.dp),
        contentPadding = PaddingValues(16.dp),
        state = state,
    ) {
        items(
            items = mediaList,
            key = { it.uuid },
        ) {
            MediaItem(item = it)
        }
    }
}

@ExperimentalMaterial3Api
@Preview("MediaGrid")
@Composable
fun PreviewMediaGrid() {
    val list = mutableListOf<MediaItem>()
    val mediaState = rememberLazyGridState()

    for (num in 0..100) {
        list.add(testMovieModel.copy(uuid = num.toString()))
    }

    MediaGrid(
        mediaList = list,
        state = mediaState,
    )
}
