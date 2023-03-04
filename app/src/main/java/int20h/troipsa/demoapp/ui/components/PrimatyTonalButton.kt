package int20h.troipsa.demoapp.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import int20h.troipsa.demoapp.ui.theme.DemoAppTheme

@Composable
fun PrimaryTonalButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    uppercase: Boolean = true,
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 10.dp,
        ),
        shape = RoundedCornerShape(50),
    ) {
        Text(
            text = if (uppercase) text.uppercase() else text,
            style = MaterialTheme.typography.labelLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun PrimaryTonalButtonPreview() {
    DemoAppTheme {
        PrimaryTonalButton(
            text = "Primary Button",
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}