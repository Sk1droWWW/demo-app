package int20h.troipsa.demoapp.ui.screens.map

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.*
import com.google.maps.android.compose.*
import int20h.troipsa.demoapp.R
import int20h.troipsa.demoapp.ui.base.ui.PseudoScaffold
import int20h.troipsa.demoapp.ui.screens.suggest_location.SuggestLocationDialog
import kotlinx.coroutines.launch

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun MapScreenContent(
    showBottomSheet: (Boolean) -> Unit,
) {
    PseudoScaffold(
//        modifier = Modifier.navigationBarsPadding(),
    ) {
        val viewModel = hiltViewModel<Screen1ViewModel>()

        val context = LocalContext.current

        val allSamples = viewModel.allSample.collectAsState()
        val scope = rememberCoroutineScope()
        var sheetVisible by remember { mutableStateOf(false) }
        val buildingPoints = remember { buildingPolygons }

        var pointSelected by remember {
            mutableStateOf(
                Pair(LatLng(0.0, 0.0), false)
            )
        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            val default = LatLng(50.449211, 30.457419)
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(default, 15.5f)
            }
            val strokeColor = MaterialTheme.colorScheme.secondary
            val fillColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                uiSettings  = MapUiSettings(
//                    zoomControlsEnabled = false,
                ),
                onMapClick = {
//                    pointSelected = Pair(it, !pointSelected.second)
                    Log.i("DemoTag", "MapScreenContent: $it")
                    pointSelected = Pair(it, true)
                }
            ) {
                MapEffect(Unit) { map ->
//                    map.setMapStyle(
//                        MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style)
//                    )

                    map.isBuildingsEnabled = true
                    map.isIndoorEnabled = true

                    map.addPolygon(
                        PolygonOptions()
                            .addAll(buildingPoints)
                            .strokeWidth(2f)
                            .strokeColor(strokeColor.toArgb())
                            .fillColor(fillColor.toArgb())
                            .clickable(true)
                    )

                }
                if (pointSelected.second) {
                    Marker(
                        state = MarkerState(position = pointSelected.first),
                        draggable = true,
//                        icon = LocalContext.current
//                            .getBitmapDescriptorFromResource(R.drawable.ic_map_marker),
                    )
                }
            }

            AnimatedVisibility(
                visible = sheetVisible,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(durationMillis = 400)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { it },
                    animationSpec = tween(durationMillis = 400)
                )
            ) {
                SuggestLocationDialog(
                    pointSelected = pointSelected,
                    onDismiss = {
                        showBottomSheet(true)
                        sheetVisible = false
                    },
                )
            }

            SuggestLocationFab(
                visible = !sheetVisible && pointSelected.second,
                onClick = {
                    scope.launch {
                        sheetVisible = true
                        showBottomSheet(false)
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
            )
        }

    }

}

@Composable
private fun SuggestLocationFab(
    onClick: () -> Unit,
    visible: Boolean,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
    ) {
        ExtendedFloatingActionButton(
            text = {
                Text(
                    text = "Suggest Location",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.labelLarge,
                )
            },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_suggest_location),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                    contentDescription = null,
                )
            },
            onClick = onClick,
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            shape = RoundedCornerShape(16.dp),
//            modifier = modifier
        )
    }
}



fun Context.getBitmapDescriptorFromResource(
    @DrawableRes resourceId: Int
): BitmapDescriptor {
    val drawable = ContextCompat.getDrawable(this, resourceId)
    return drawable?.let {
        val bitmap = if (it is BitmapDrawable) {
            it.bitmap
        } else {
            val width = it.intrinsicWidth.takeIf { width -> width > 0 } ?: 1
            val height = it.intrinsicHeight.takeIf { height -> height > 0 } ?: 1
            Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).also { bitmap ->
                Canvas(bitmap).apply {
                    it.setBounds(0, 0, width, height)
                    it.draw(this)
                }
            }
        }
        BitmapDescriptorFactory.fromBitmap(bitmap)
    } ?: BitmapDescriptorFactory.defaultMarker()
}

@Preview
@Composable
private fun Preview() {
//    Screen1(
//        navigateToScreen2 = {},
//    )
}

