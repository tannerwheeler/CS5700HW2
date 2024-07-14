class Shipment(
    val id: String
): ShipmentSubject() {
    var status = String
    val notes = mutableListOf<String>()
    val updateHistory = mutableListOf<ShippingUpdate>()
    val expectedDeliveryDateTimestamp = Long
    val currentLocation = String

    fun addNote(note: String) {
        notes.add(note)
    }

    fun addUpdate(update: ShippingUpdate) {
        updateHistory.add(update)
    }

    override fun notifyObservers() {
        TODO("not implemented")
    }
}