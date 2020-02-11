import java.util.ArrayList;
import java.util.List;

public class SceneHandler {

    private List<Scene> m_scenes;
    //Index of current scene.
    private int m_current_scene;

    public SceneHandler() {
        this.m_scenes = new ArrayList<>();
        this.m_current_scene = -1;
    }

    public void addScene(Scene scene) {
        m_scenes.add(scene);
    }

    public void changeScene(String targetName) {

        for(int i = 0; i < m_scenes.size(); i++) {

            Scene tempScene = m_scenes.get(i);

            if(tempScene.getName().equals(targetName)) {

                //When there is not any scene displayed.
                if(m_current_scene == -1) {
                    tempScene.getWindow().setVisible(true);
                }
                else {
                    m_scenes.get(m_current_scene).getWindow().dispose();
                    tempScene.getWindow().setVisible(true);
                }
                m_current_scene = i;
            }
        }
    }
}
