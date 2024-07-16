class Shipment(
    var status: String,
    var id: String,
    var expectedDeliveryDateTimestamp: Long?,
    var currentLocation: String?
): ShipmentSubject() {
    private var notes = mutableListOf<String>()
    private var updateHistory = mutableListOf<ShippingUpdate>()

    fun addNote(note: String) {
        notes.add(note)
        notifyObservers()
    }

    fun addUpdate(update: ShippingUpdate) {
        updateHistory.add(update)
        status = update.newStatus
        notifyObservers()
    }

    override fun notifyObservers() {
        TODO("not implemented")
    }
}