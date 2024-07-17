class LocationBehavior(
    data: MutableList<String>
) : SimulatorActionBehavior(data) {
    override var dataLength: Int = 4

    init {
        require(data.size == dataLength) {
            "CreateBehavior data parameter must be of size 4"
        }
    }

    override fun performAction() {
        val shipment = TrackingSimulator.findShipment(data[1])
        require(shipment != null) { "Shipment not found during Location Behavior" }

        shipment.currentLocation = data[3]
        shipment.notifyObservers(null, null)
    }
}