public interface IMouseListener {

    // expects 1 as left click, 2 as right click, 3 as middle-mouse click

    // these methods return true to "consume" an event,
    // so that it will not be passed to other listeners
    boolean mousePressed(int button);

    boolean mouseReleased(int button);

    boolean mouseWheel(int amount);

}
