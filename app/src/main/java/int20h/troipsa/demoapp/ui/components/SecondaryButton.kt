package int20h.troipsa.demoapp.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import int20h.troipsa.demoapp.ui.theme.DemoAppTheme

@Composable
fun SecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    uppercase: Boolean = true,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        contentPadding = PaddingValues(
            horizontal = 24.dp,
            vertical = 10.dp,
        ),
        border = ButtonDefaults.outlinedButtonBorder,
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
private fun SecondaryButtonPreview() {
    DemoAppTheme {
        SecondaryButton(
            text = "Primary Button",
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}