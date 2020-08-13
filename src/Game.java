import java.io.File;

public class Game {

    public static String version = "1.0";
    public static File img;

    private GuiHandler gui_handler;

    public Game() {
        this.gui_handler = new GuiHandler();
    }

    public void init() {
        this.gui_handler.init();
    }

    public void start() {
        this.gui_handler.showComposite("OPTIONS");
    }
}
