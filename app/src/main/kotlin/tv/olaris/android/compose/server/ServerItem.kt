package tv.olaris.android.compose.server

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tv.olaris.android.R

@ExperimentalMaterial3Api
@Composable
fun ServerItem(serverName: String, serverVersion: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .padding(8.dp),
        ) {
            Column(
                modifier = Modifier
                    .weight(10f),
            ) {
                Text(
                    text = serverName,
                    fontSize = 22.sp,
                )
                Text(
                    text = serverVersion,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = 12.sp,
                )
            }
            Column(
                modifier = Modifier
                    .width(48.dp)
                    .weight(1f),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.outline_edit_24),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentDescription = null,
                )
            }
            Spacer(
                modifier = Modifier
                    .width(16.dp)
            )
            Column(
                modifier = Modifier
                    .width(48.dp)
                    .weight(1f),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.outline_delete_24),
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    contentDescription = null,
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview("ServerItem")
@Composable
fun PreviewServerItem() {
    ServerItem(
        serverName = "Test Server",
        serverVersion = "Best Version",
    )
}
