abstract class SimulatorActionBehavior(
    var data: MutableList<String>
) {
    abstract var dataLength: Int
    abstract fun performAction()
}