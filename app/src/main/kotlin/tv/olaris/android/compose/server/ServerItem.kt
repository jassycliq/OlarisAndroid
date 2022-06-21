package tv.olaris.android.compose.server

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import tv.olaris.android.R
import tv.olaris.android.data.model.Server

@ExperimentalMaterial3Api
@Composable
fun ServerItem(server: Server) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { },
    ) {
        Row {
            Column(
                modifier = Modifier
                    .weight(10f),
            ) {
                Text(
                    text = server.name,
                )
                Text(
                    text = server.version,
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f),
            ) {
                Image(
                    painter = painterResource(id = R.drawable.outline_edit_24),
                    contentDescription = null,
                )
                Image(
                    painter = painterResource(id = R.drawable.outline_delete_24),
                    contentDescription = null,
                )
            }
        }

    }
}
