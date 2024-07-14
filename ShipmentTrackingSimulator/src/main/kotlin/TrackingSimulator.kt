import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.io.InputStream

object TrackingSimulator {
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

        File ("./src/main/resources/simulationItems.txt").reader().useLines{
            lines ->lines.forEach {
                listOfLines.add (it)
                println(it)
                delay(3000)
            }
        }
    }
}