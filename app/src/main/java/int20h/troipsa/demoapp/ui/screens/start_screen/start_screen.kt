package int20h.troipsa.demoapp.ui.screens.start_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(
    options1: List<String>,
    options2: List<String>,
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier,
    logo: ImageBitmap,
    appName: String
    ) {
        var expanded1 by remember { mutableStateOf(false) }
        var expanded2 by remember { mutableStateOf(false) }
        var query1 by remember { mutableStateOf("") }
        var query2 by remember { mutableStateOf("") }
        var selectedOption1 by remember { mutableStateOf(options1.firstOrNull()) }
        var selectedOption2 by remember { mutableStateOf(options2.firstOrNull()) }

        Column(modifier = modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = appName)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(bitmap = logo, contentDescription = "Logo")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.weight(1f)) {
                ExposedDropdownMenuBox(
                    expanded = expanded1,
                    onExpandedChange = {
                        expanded1 = !expanded1
                    }
                ) {

                    TextField(
                        value = query1,
                        onValueChange = { query1 = it },
                        label = { Text(text = "Label") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expanded1
                            )
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors()
                    )

                    val filteringOptions =
                        options1.filter { it.contains(query1, ignoreCase = true) }

                    if (options1.isNotEmpty()) {

                        ExposedDropdownMenu(
                            expanded = expanded1,
                            onDismissRequest = { expanded1 = false }
                        ) {
                            options1.forEach { selectionOption ->
                                DropdownMenuItem(
                                    text = { Text(text = selectionOption) },
                                    onClick = {
                                        query1 = selectionOption
                                        expanded1 = false
                                    }
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.weight(1f)) {
                OutlinedTextField(
                    value = query2,
                    onValueChange = {
                        query2 = it
                    },
                    label = { Text("Search 2") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth()
                )
                DropdownMenu(
                    expanded = expanded2,
                    onDismissRequest = { expanded2 = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    options2.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(text = option) },
                            onClick = {
                                selectedOption2 = option
                                expanded2 = false
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { onStartClick() },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Start")
            }
        }
    }

@Preview
@Composable
private fun Preview() {
    StartScreen(
        options1 = listOf("Option 1", "Option 2", "Option 3"),
        options2 = listOf("Option 1", "Option 2", "Option 3"),
        onStartClick = {},
        logo = ImageBitmap(100, 100),
        appName = "App name"
    )
}

