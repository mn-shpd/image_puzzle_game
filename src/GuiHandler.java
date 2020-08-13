import javax.swing.*;
import java.awt.*;

public class GuiHandler {

    private JFrame window;
    private Resolution desktop_resolution;
    private JPanel main_container;

    public GuiHandler() {
        this.main_container = new JPanel(new CardLayout());
    }

    public void init() {
        this.createWindow();
        this.computeResolution();
        this.initMainMenuComposite();
        this.initOptionsComposite();
    }

    private void createWindow() {
        this.window = new JFrame("Puzzle Game");
        this.window.setVisible(true);
        this.window.setResizable(false);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.add(this.main_container);
    }

    private void computeResolution() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        this.desktop_resolution = new Resolution(width, height);
    }

    private void initMainMenuComposite() {
        Resolution window_size = new Resolution(342, 384);
        MainMenu main_menu = new MainMenu("MAIN_MENU", this.window, this, window_size);

        //Load labels, buttons etc.
        main_menu.loadSections();

        this.addComposite(main_menu, main_menu.getName());
    }

    private void initOptionsComposite() {
        Resolution window_size = new Resolution(342, 384);
        Options options = new Options("OPTIONS", this.window, this, window_size);

        //Load labels, buttons etc.
        options.loadSections();

        this.addComposite(options, options.getName());
    }

    public boolean addComposite(Composite composite, String name) {
        if(composite == null || name == null) {
            return false;
        }
        else {
            this.main_container.add(composite, name);
            return true;
        }
    }

    public boolean deleteComposite(String name) {
        for(Component component : this.main_container.getComponents()) {
            if (component.getName().equals(name)) {
                this.main_container.remove(component);
                return true;
            }
        }
        return false;
    }

    public boolean showComposite(String name) {
        if(name == null) {
            return false;
        }
        else {
            CardLayout interfaces = (CardLayout) this.main_container.getLayout();
            interfaces.show(this.main_container, name);

            for (Component component : this.main_container.getComponents()) {
                if (component.getName().equals(name)) {
                    if (component.getName().equals("STARTED_GAME")) {
                        this.window.pack();
                    } else {
                        Composite composite = (Composite) component;
                        this.window.setSize(composite.getWindowSize().getWidth(), composite.getWindowSize().getHeight());
                    }
                }
            }

            return true;
        }
    }

    //Returns resolution of available surface for window rendering (without taskbar).
    public Resolution getMaximumWindowBounds() {
        Rectangle rect = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        return new Resolution(rect.width, rect.height);
    }

    //Returns the height of OS's taskbar.
    public int getOsTaskBarHeight() {
        return this.desktop_resolution.getHeight() - this.getMaximumWindowBounds().getHeight();
    }
}
