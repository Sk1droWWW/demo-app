package int20h.troipsa.demoapp.ui.screens.start_screen

import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.view.ViewTreeObserver
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import int20h.troipsa.demoapp.BuildConfig
import int20h.troipsa.demoapp.R
import int20h.troipsa.demoapp.ui.components.PrimaryButton
import int20h.troipsa.demoapp.ui.theme.DemoAppTheme
import int20h.troipsa.demoapp.utils.extension.bold
import int20h.troipsa.demoapp.utils.extension.medium

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    list_university: List<String> = listOf("KPI"),
    list_group: List<String>,
    onStartClick: () -> Unit
) {
    var expanded_university by remember { mutableStateOf(false) }
    var expanded_group by remember { mutableStateOf(false) }

    var SelectedText_university by remember { mutableStateOf("") }
    var TextFieldSize_university by remember { mutableStateOf(Size.Zero) }

    var SelectedText_group by remember { mutableStateOf("") }
    var TextFieldSize_group by remember { mutableStateOf(Size.Zero) }

    val icon_university = if (expanded_university)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    val icon_group = if (expanded_group)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    val isKeyboardOpen by keyboardAsState()

    DemoAppTheme {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .statusBarsPadding()
                .imePadding()
                .padding(horizontal = 20.dp)
        ) {

            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.displayLarge.bold(),
                color = if (isKeyboardOpen == Keyboard.Closed)
                    MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.surface,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxWidth()
            )

            Image(
                painter = painterResource(id = R.drawable.img_legal),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f/2f)
                    .clip(RoundedCornerShape(8.dp))
                    .padding(top = 25.dp, bottom = 16.dp)
                    .background(MaterialTheme.colorScheme.onSecondary),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.weight(1f))

            OutlinedTextField(
                value = SelectedText_university,
                onValueChange = { SelectedText_university = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        TextFieldSize_university = coordinates.size.toSize()
                    },
                label = { Text(stringResource(R.string.select_university_label)) },
                trailingIcon = {
                    Icon(icon_university, "contentDescription",
                        Modifier.clickable { expanded_university = !expanded_university })
                }
            )

            DropdownMenu(
                expanded = expanded_university,
                onDismissRequest = { expanded_university = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { TextFieldSize_university.width.toDp() })
            ) {
                list_university.forEach { university ->
                    DropdownMenuItem(
                        text = { Text(text = university) },
                        onClick = {
                            SelectedText_university = university
                            expanded_university = false
                        })
                }
            }

            Spacer(modifier = Modifier.padding(vertical = 2.dp))

            OutlinedTextField(
                value = SelectedText_group,
                onValueChange = { SelectedText_group = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        TextFieldSize_group = coordinates.size.toSize()
                    },
                label = { Text(stringResource(R.string.select_group_label)) },
                trailingIcon = {
                    Icon(icon_group, "contentDescription",
                        Modifier.clickable { expanded_group = !expanded_group })
                }
            )

            DropdownMenu(
                expanded = expanded_group,
                onDismissRequest = { expanded_group = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { TextFieldSize_group.width.toDp() })
            ) {
                list_group.forEach { group ->
                    DropdownMenuItem(
                        text = { Text(text = group) },
                        onClick = {
                            SelectedText_group = group
                            expanded_group = false
                        })
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            PrimaryButton(
                text = stringResource(R.string.start_button),
                onClick = { onStartClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 8.dp
                    )
            )
        }
    }
}

enum class Keyboard {
    Opened, Closed
}

@Composable
fun keyboardAsState(): State<Keyboard> {
    val keyboardState = remember { mutableStateOf(Keyboard.Closed) }
    val view = LocalView.current
    DisposableEffect(view) {
        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            keyboardState.value = if (keypadHeight > screenHeight * 0.15) {
                Keyboard.Opened
            } else {
                Keyboard.Closed
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
        }
    }

    return keyboardState
}

@Preview
@Composable
private fun Preview() {
    StartScreen(
        list_group = listOf("Option 1", "Option 2", "Option 3", "Option 2", "Option 3", "Option 2", "Option 3", "Option 2", "Option 3", "Option 2", "Option 3", "Option 2", "Option 3", "Option 2", "Option 3", "Option 2", "Option 3"),
        onStartClick = {},
    )
}

