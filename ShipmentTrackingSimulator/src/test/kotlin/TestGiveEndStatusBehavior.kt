import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotEquals

class TestGiveEndStatusBehavior {
    val s1 = CreateBehavior(mutableListOf<String>("created", "s10001", "10002928849"))
    val s2 = CreateBehavior(mutableListOf<String>("created", "s10002", "10002928849"))
    val s3 = CreateBehavior(mutableListOf<String>("created", "tandlleslldd", "10002928849"))

    @Test
    fun testBasicGiveEndStatusBehavior() {
        s1.performAction()
        s2.performAction()
        s3.performAction()

        val shipment = TrackingSimulator.findShipment("s10001")
        val lost = GiveEndStatusBehavior(mutableListOf<String>("lost", "s10001", "10002928849"))
        val delayed = GiveEndStatusBehavior(mutableListOf<String>("delayed", "s10001", "10002928849"))
        val delivered = GiveEndStatusBehavior(mutableListOf<String>("delivered", "s10001", "10002928849"))

        lost.performAction()
        assertNotEquals(null, shipment)
        assertEquals("s10001", shipment?.id)
        assertEquals("lost", shipment?.status)
        assertEquals(null, shipment?.currentLocation)
        assertEquals(1, shipment?.updateHistory?.size)

        delayed.performAction()
        assertNotEquals(null, shipment)
        assertEquals("s10001", shipment?.id)
        assertEquals("delayed", shipment?.status)
        assertEquals(null, shipment?.currentLocation)
        assertEquals(2, shipment?.updateHistory?.size)


        delivered.performAction()
        assertNotEquals(null, shipment)
        assertEquals("s10001", shipment?.id)
        assertEquals("delivered", shipment?.status)
        assertEquals(null, shipment?.currentLocation)
        assertEquals(3, shipment?.updateHistory?.size)
    }

    @Test
    fun testWeirdIDGiveEndStatusBehavior() {
        s3.performAction()

        val lost = GiveEndStatusBehavior(mutableListOf<String>("lost", "tandlleslldd", "10002928849"))
        lost.performAction()
        val shipment = TrackingSimulator.findShipment("tandlleslldd")
        assertNotEquals(null, shipment)
        assertEquals("tandlleslldd", shipment?.id)
        assertEquals("lost", shipment?.status)
        assertEquals(null, shipment?.currentLocation)
        assertEquals(1, shipment?.updateHistory?.size)
    }

    @Test
    fun testTooFewParametersIDGiveEndStatusBehavior() {
        val block : () -> Unit = { GiveEndStatusBehavior(mutableListOf<String>("lost", "tandlleslldd")) }
        assertFailsWith<IllegalArgumentException> { block() }
    }

    @Test
    fun testTooManyParametersIDGiveEndStatusBehavior() {
        val block : () -> Unit = { GiveEndStatusBehavior(mutableListOf<String>("lost", "tandlleslldd", "10002928849", "Los Angeles, CA")) }
        assertFailsWith<IllegalArgumentException> { block() }
    }
}