package int20h.troipsa.demoapp.ui.screens.suggest_location

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolygonOptions
import com.google.maps.android.compose.*
import int20h.troipsa.demoapp.R
import int20h.troipsa.demoapp.ui.base.ui.PseudoScaffold
import int20h.troipsa.demoapp.ui.components.PrimaryButton
import int20h.troipsa.demoapp.ui.screens.map.Screen1ViewModel
import int20h.troipsa.demoapp.ui.screens.map.buildingPolygons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuggestLocationDialog(
    onDismiss: () -> Unit,
    pointSelected: Pair<LatLng, Boolean>,
) {
    BackHandler {
        onDismiss()
    }

    PseudoScaffold(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .navigationBarsPadding(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Suggest location",
                        color = MaterialTheme.colorScheme.onPrimary,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onDismiss,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = null,
                        )
                    }
                },
                modifier = Modifier.background(color = MaterialTheme.colorScheme.primary),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                )
            )
        },
    ) {
        val viewModel = hiltViewModel<Screen1ViewModel>()

        val allSamples = viewModel.allSample.collectAsState()
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(top = 105.dp)
            ) {
                Text(
                    text = "Location name:",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp)
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = { Text("Write Name...") },
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = "Details:",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp)
                )
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    minLines = 4,
                    label = { Text("Write Some Details..") },
                    modifier = Modifier
                        .padding(vertical = 4.dp)
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                )

                Text(
                    text = "Map:",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .padding(horizontal = 16.dp)
                )

                val strokeColor = MaterialTheme.colorScheme.secondary
                val fillColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)
                GoogleMap(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    cameraPositionState = rememberCameraPositionState {
                        position = CameraPosition.fromLatLngZoom(pointSelected.first, 15.5f)
                    },
                    uiSettings = MapUiSettings(
                        zoomControlsEnabled = false,
                    ),

                    ) {
                    MapEffect(Unit) { map ->
//                    map.setMapStyle(
//                        MapStyleOptions.loadRawResourceStyle(context, R.raw.map_style)
//                    )

                        map.isBuildingsEnabled = true
                        map.isIndoorEnabled = true

                        map.addPolygon(
                            PolygonOptions()
                                .addAll(buildingPolygons)
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
            }

            PrimaryButton(
                text = "Send Suggestion",
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}
