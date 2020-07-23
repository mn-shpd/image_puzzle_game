import java.util.ArrayList;
import java.util.List;

public class WindowHandler {

    private static List<Window> m_windows;
    //Index of current scene.
    private static int m_current_scene;

    public WindowHandler() {
        this.m_windows = new ArrayList<>();
        this.m_current_scene = -1;
    }

    public Window getCurrentScene() {
        return m_windows.get(m_current_scene);
    }

    public Window getSceneByName(int name) {

        for(Window window : m_windows) {
            if(window.getName() == name) {
                return window;
            }
        }
        return null;
    }

    public static void addScene(Window window) {
        m_windows.add(window);
    }

    public static void changeScene(int targetName) {

        for(int i = 0; i < m_windows.size(); i++) {

            Window tempWindow = m_windows.get(i);

            if(tempWindow.getName() == targetName) {

                //When there is not any scene displayed.
                if(m_current_scene == -1) {
                    tempWindow.getWindow().setVisible(true);
                }
                else {
                    m_windows.get(m_current_scene).getWindow().dispose();
                    tempWindow.getWindow().setVisible(true);
                }
                m_current_scene = i;
            }
        }
    }
}
