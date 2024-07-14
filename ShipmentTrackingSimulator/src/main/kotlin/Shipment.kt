class Shipment(
    var status: String,
    var id: String,
    var expectedDeliveryDateTimestamp: Long,
    var currentLocation: String
): ShipmentSubject() {
    var notes = mutableListOf<String>()
    var updateHistory = mutableListOf<ShippingUpdate>()
        private set

    fun addNote(note: String) {
        notes.add(note)
    }

    fun addUpdate(update: ShippingUpdate) {
        updateHistory.add(update)
        notifyObservers()
    }

    override fun notifyObservers() {
        TODO("not implemented")
    }
}