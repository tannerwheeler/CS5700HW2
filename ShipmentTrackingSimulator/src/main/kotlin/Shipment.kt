class Shipment(
    var status: String,
    var id: String,
    var expectedDeliveryDateTimestamp: Long?,
    var currentLocation: String?
): ShipmentSubject() {
    private var notes = mutableListOf<String>()
    private var updateHistory = mutableListOf<ShippingUpdate>()

    fun addNote(note: String) {
        this.notes.add(note)
        this.notifyObservers(note,null)
    }

    fun addUpdate(update: ShippingUpdate) {
        updateHistory.add(update)
        this.status = update.newStatus
        this.notifyObservers(null,
            "Shipment went from ${update.previousStatus} " +
                    "to ${this.status} " +
                    "on ${update.timestamp}")
    }

    override fun notifyObservers(note: String?, history: String?) {
        observers.forEach {
            it.notify(note,
                history,
                this.expectedDeliveryDateTimestamp,
                this.status,
                this.currentLocation)
        }
    }
}