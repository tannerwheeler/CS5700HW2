import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.io.InputStream

object TrackingSimulator {
    enum class ShipmentAction {
        created,
        shipped,
        location,
        delayed,
        noteadded,
        lost,
        canceled,
        delivered,
    }

    var shipments = mutableListOf<Shipment>()

    fun findShipment(id: String?) : Shipment? {
        shipments.forEach {
            if(it.id == id) {
                return it
            }
        }
        return null
    }

    fun addShipment(shipment: Shipment) {
        TODO()
    }

    suspend fun runSimulation() {
        val listOfLines = mutableListOf<String>()

        File ("./src/main/resources/test.txt").reader().useLines{
            lines ->lines.forEach {
                listOfLines.add (it)
                println(it)
            }
        }

        listOfLines.forEach {
            var shipmentAction = it.split(",")



            delay(1000)
        }
    }
}