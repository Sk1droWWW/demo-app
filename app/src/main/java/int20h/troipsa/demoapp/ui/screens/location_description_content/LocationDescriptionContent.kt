package int20h.troipsa.demoapp.ui.screens.location_description_content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import int20h.troipsa.demoapp.R
import int20h.troipsa.demoapp.utils.extension.medium

@Composable
fun Window(
    name: String,
    detail: String,
    image: Painter = painterResource(id = R.drawable.img_legal)
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Location",
            style = MaterialTheme.typography.headlineSmall.medium(),
            color = MaterialTheme.colorScheme.onBackground,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
                .fillMaxWidth()
        )

        Text(
            text = name,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 0.dp
                )
                .fillMaxWidth()
        )
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f/2f)
                .clip(RoundedCornerShape(8.dp))
                .padding(top = 4.dp, bottom = 16.dp)
                .background(MaterialTheme.colorScheme.onSecondary),
            contentScale = ContentScale.Crop
        )

        Text(
            text = "Details",
            style = MaterialTheme.typography.headlineSmall.medium(),
            color = MaterialTheme.colorScheme.onBackground,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 0.dp
                )
                .fillMaxWidth()
        )

        Text(
            text = detail,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp,
                    vertical = 4.dp
                )
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun PreviewWindow() {
    val image: Painter = painterResource(id = R.drawable.img_legal)
    Window(
        name = "HuiHuiniaHuische HuiHuiniaHuische HuiHuiniaHuische HuiHuiniaHuische HuiHuiniaHuische",
        detail = "HuiHuiniaHuische HuiHuiniaHuische HuiHuiniaHuische HuiHuiniaHuische HuiHuiniaHuische HuiHuiniaHuische"
    )
}