import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Game {

    private SceneHandler m_sceneHandler;
    private Scene currentScene;

    public Game() {
        m_sceneHandler = new SceneHandler();
    }

    public void init() {

        //Initializing main_menu scene.
        JFrame mainMenuWindow = new JFrame("Puzzle Game");
        mainMenuWindow.setSize(300, 450);

        //Buttons initialization.
        List<JButton> buttons = new ArrayList<>();

        JButton loadImageButton = new JButton("Load Image");
        loadImageButton.setName("load_image");
        loadImageButton.setBounds(100, 200, 100, 25);
        buttons.add(loadImageButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setName("exit");
        exitButton.setBounds(100, 235, 100, 25);
        buttons.add(exitButton);

        mainMenuWindow.setLayout(null);
        //Renders scene on the screen.
        mainMenuWindow.setVisible(true);

        m_sceneHandler.addScene(new Scene("main_menu", mainMenuWindow, buttons));

    }
}
