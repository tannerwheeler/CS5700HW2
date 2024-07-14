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

    fun runSimulation() {

    }
}