import org.junit.jupiter.api.Test

class TestCreateBehavior {
    @Test
    fun testCreateBehavior() {
        val create = CreateBehavior(mutableListOf<String>("create", "tandlleslldd", "10002928849"))
        create.performAction()
        val shipment = TrackingSimulator.findShipment("tandlleslldd")
        assert(shipment != null)
        assert(shipment?.id == "tandlleslldd")
    }
}