package tv.olaris.android.compose.media.item

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import tv.olaris.android.R
import tv.olaris.android.data.model.MediaItem
import tv.olaris.android.data.model.testMovieModel
import tv.olaris.android.compose.theme.OlarisTheme

@ExperimentalMaterial3Api
@Composable
fun MediaItem(
    item: MediaItem,
//    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
    ) {
        Card(
//            onClick = onClick,
            elevation = CardDefaults.cardElevation(2.dp),
            modifier = Modifier
                .aspectRatio(1f / 1.5f)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.baseImageUrl)
                    .crossfade(true)
                    .build(),
                fallback = painterResource(R.drawable.placeholder_coverart),
                placeholder = painterResource(R.drawable.placeholder_coverart),
                contentDescription = "",
            )
            AnimatedVisibility(visible = item.playtime > 0f) {
                LinearProgressIndicator(
                    progress = item.playtime.toFloat(),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

        Text(
            text = item.title,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier
                .padding(top = 8.dp),
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 12.sp,
                letterSpacing = 0.1.sp,
            )
        )

        Text(
            text = item.subTitle,
            style = TextStyle(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 10.sp,
            )
        )
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OlarisTheme {
        MediaItem(
            testMovieModel,
//            onClick = { },
        )
    }
}
