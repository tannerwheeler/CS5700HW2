import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@Composable
@Preview
fun App() {
    var shipmentIdFieldContent by remember { mutableStateOf("") }
    val shipments = remember { mutableListOf<TrackerViewHelper>() }
    val coroutineScope = rememberCoroutineScope()

    coroutineScope.launch {
        TrackingSimulator.runSimulation()
    }

    MaterialTheme {
        Column {
            Row {
                TextField(shipmentIdFieldContent, onValueChange = {
                    shipmentIdFieldContent = it
                })

                Button(onClick = {
                    val viewHelper = TrackerViewHelper()
                    TrackerViewHelper().trackShipment(shipmentIdFieldContent)
                    shipments.add(viewHelper)
                    shipmentIdFieldContent = ""
                }) {
                    Text("Track")
                }
            }

            LazyColumn {
                items(shipments, key = {it}) {
                    Row (
                        modifier = Modifier
                            .padding(8.dp)
                            .border(1.dp, Color.Blue, RoundedCornerShape(4.dp))
                            .padding(4.dp)
                    ) {
                        if (it.shipment != null) {
                            Column {
                                Text("Tracking Shipment: ${it.shipmentId}", modifier = Modifier
                                    .size(10.dp))
                                Text("Status: ${it.shipmentStatus}")
                                Text("Location: ${it.shipmentLocation}")
                                Text("Expected Delivery: ${it.expectedShipmentDeliveryDate}")
                                Text("")
                                Text("Status Updates:")
                                for (s in it.shipmentUpdateHistory) {
                                    Text(s)
                                }
                                Text("")
                                Text("Notes:")
                                for (s in it.shipmentNotes) {
                                    Text(s)
                                }
                            }
                        }
                        else {
                            Column {
                                Text("Shipment with id = ?", modifier = Modifier
                                    .size(4.dp))
                            }
                            Column {
                                Button(onClick = {
                                    shipments.remove(it)
                                    it.stopTracking()
                                }) {
                                    Text("X")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        App()
    }
}
