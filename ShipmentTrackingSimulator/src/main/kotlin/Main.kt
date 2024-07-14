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
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.launch

@Composable
@Preview
fun App() {
    var shipmentIdFieldContent by remember { mutableStateOf("") }
    var shipments = remember { mutableListOf<String>() }
    val coroutineScope = rememberCoroutineScope()

    coroutineScope.launch {
        TrackingSimulator.runSimulation()
    }

    MaterialTheme {
        Column {
            TextField(shipmentIdFieldContent, onValueChange = {
                shipmentIdFieldContent = it
            })

            Button(onClick = {
                shipments.add(shipmentIdFieldContent)
                shipmentIdFieldContent = ""
            }) {
                Text("Track Shipment")
            }

            Button(onClick = {
                shipments.remove(shipmentIdFieldContent)
                shipmentIdFieldContent = ""
            }) {
                Text("Stop Tracking Shipment")
            }

            LazyColumn {
                items(shipments, key = {it}) {
                    Row (
                        modifier = Modifier
                            .padding(8.dp)
                            .border(1.dp, Color.Blue, RoundedCornerShape(4.dp))
                            .padding(4.dp)
                    ) {
                        Text(it)
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
