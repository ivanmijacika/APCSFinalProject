import java.util.*;

public class UIManager {

    // LinkedHashSet to maintain order & quick add/remove
    private Collection<UIElement> elements = new LinkedHashSet<UIElement>();
    private Game game;

    public UIManager(Game game) {
        this.game = game;
    }

    public void draw() {
        for (UIElement element : elements) {
            element.draw();
        }
    }

    public void addElement(UIElement element) {
        elements.add(element);
    }

    public void removeElement(UIElement element) {
        elements.remove(element);
    }

}
