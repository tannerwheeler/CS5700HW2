class CreateBehavior(
    data: MutableList<String>
) : SimulatorActionBehavior(data) {
    override var dataLength: Int = 3

    override fun performAction() {
        TrackingSimulator.addShipment(Shipment(data[0], data[1], null, null))
    }
}