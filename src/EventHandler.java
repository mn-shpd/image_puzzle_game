import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EventHandler implements ActionListener {

    private String m_scene_name;
    private JFrame m_window;

    public EventHandler(String m_scene_name, JFrame m_window) {
        this.m_scene_name = m_scene_name;
        this.m_window = m_window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch(m_scene_name) {

            case "main_menu":

                if(((JButton)e.getSource()).getName().equals("load_image")) {

                    //Handles image choosing.
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    int result = fileChooser.showOpenDialog(m_window);

                    //When image has loaded successfully.
                    if(result == JFileChooser.APPROVE_OPTION) {

                        File file = fileChooser.getSelectedFile();

                        //Converting file to the img format which allows cropping.
                        BufferedImage img = null;

                        try {
                            img = ImageIO.read(file);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }

                        //Used to store user's screen dimension for image scaling.
                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                        //TODO

                        //Creating new window.
                        JFrame startedGame = new JFrame("Puzzle Game - Started");

                        startedGame.setSize(img.getWidth(), img.getHeight());

                        //Buttons initialization.
                        List<JButton> buttons = new ArrayList<>();

                        for(int j = 0; j < 4; j++) {
                            for (int i = 0; i < 4; i++) {
                                JButton tempButton = new JButton(Integer.toString(i));
                                tempButton.setName(Integer.toString(i));
                                tempButton.setBounds(i * img.getWidth() / 4, j * img.getHeight() / 4, img.getWidth() / 4, img.getHeight() / 4);
                                BufferedImage croppedImg = img.getSubimage(i * img.getWidth() / 4, j * img.getHeight() / 4, img.getWidth() / 4, img.getHeight() / 4);
                                tempButton.setIcon(new ImageIcon(croppedImg));
                                buttons.add(tempButton);
                                //startedGame.add(tempButton);
                            }
                        }


                        startedGame.setLayout(null);

                        SceneHandler.addScene(new Scene("started_game", startedGame, buttons));
                        SceneHandler.changeScene("started_game");
                    }
                }
                else if(((JButton)e.getSource()).getName().equals("exit")) {
                    m_window.dispose();
                }
                break;
            case "started_game":
                //TODO
                break;
        }
    }
}
