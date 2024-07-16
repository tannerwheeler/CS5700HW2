class GiveEndStatusBehavior(
    data: MutableList<String>
) : SimulatorActionBehavior(data) {
    override var dataLength: Int = 3

    override fun performAction() {
        if (data.size < dataLength) {
            return
        }
        val shipment = TrackingSimulator.findShipment(data[1])
        if (shipment != null) {
            val update = ShippingUpdate(shipment.status, data[0], data[2].toLong())
            shipment.addUpdate(update)
        }
    }
}