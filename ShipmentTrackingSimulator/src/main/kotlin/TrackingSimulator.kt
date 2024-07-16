import kotlinx.coroutines.delay
import java.io.File

object TrackingSimulator {
    private var shipments = mutableListOf<Shipment>()

    fun findShipment(id: String?) : Shipment? {
        shipments.forEach {
            if(it.id == id) {
                return it
            }
        }
        return null
    }

    fun addShipment(shipment: Shipment) {
        shipments.add(shipment)
    }

    suspend fun runSimulation() {
        val listOfLines = mutableListOf<String>()

        File ("./src/main/resources/test.txt").reader().useLines{
            lines ->lines.forEach {
                listOfLines.add (it)
            }
        }

        listOfLines.forEach {
            val shipmentAction = it.split(",")
            println(shipmentAction)
            ActionFactory(shipmentAction.toMutableList()).callAction()
            delay(1000)
        }
    }
}