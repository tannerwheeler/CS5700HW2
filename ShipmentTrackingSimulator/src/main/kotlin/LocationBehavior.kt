class LocationBehavior(
    data: MutableList<String>
) : SimulatorActionBehavior(data) {
    override var dataLength: Int = 4

    override fun performAction() {
        if (data.size < dataLength) {
            return
        }
        TrackingSimulator.findShipment(data[1])?.currentLocation = data[3]
        TrackingSimulator.findShipment(data[1])?.notifyObservers(null, null)
    }
}