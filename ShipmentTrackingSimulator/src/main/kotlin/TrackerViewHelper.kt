import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class TrackerViewHelper(
) : ShipmentObserver() {
    var shipment by mutableStateOf<Shipment?>(null)
        private set
    var shipmentId by mutableStateOf<String?>(null)
        private set
    var shipmentUpdateHistory by mutableStateOf(mutableListOf<String>())
        private set
    var expectedShipmentDeliveryDate by mutableStateOf(mutableListOf<String>())
        private set
    var shipmentStatus by mutableStateOf<String?>(null)
        private set

    override fun getUpdate(update: ShippingUpdate) {
        TODO("Not yet implemented")
    }

    fun trackShipment(id: String) {
        shipment = TrackingSimulator.findShipment(id)
        shipment?.subscribe(this)
    }

    fun stopTracking() {
        shipment?.unsubscribe(this)
    }
}