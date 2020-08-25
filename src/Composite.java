import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class Composite extends JPanel implements ActionListener {

    protected JFrame window;
    protected GuiHandler gui_handler;
    protected Resolution window_size;

    public Composite(String name, JFrame window, GuiHandler gui_handler) {
        this(name, window, gui_handler, null);
    }

    public Composite(String name, JFrame window, GuiHandler gui_handler, Resolution window_size) {
        super.setName(name);
        this.window = window;
        this.gui_handler = gui_handler;
        this.window_size = window_size;
    }

    public void removeSection(String name) {
        for(Component component : this.getComponents()) {
            if(component.getName().equals(name)) {
                this.remove(component);
            }
        }
    }

    public Resolution getWindowSize() {
        return window_size;
    }
}
