import java.util.ArrayList;
import java.util.List;

public class SceneHandler {

    private List<Scene> m_scenes;

    public SceneHandler() {
        this.m_scenes = new ArrayList<>();
    }

    public void addScene(Scene scene) {
        m_scenes.add(scene);
    }
}
