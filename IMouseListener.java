public interface IMouseListener {

    // these methods return true to "consume" an event,
    // so that it will not be passed to other listeners

    // this defaults to false so that listeners that don't
    // need a particular method would not be forced to implement it

    default boolean mousePressed(int button) {
        return false;
    }

    default boolean mouseReleased(int button) {
        return false;
    }

    default boolean mouseWheel(int amount) {
        return false;
    }

}
