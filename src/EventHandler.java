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

    private Scene m_current_scene;
    private static int m_last_button_id;
    private static List<Integer> m_order_list;
    private static int m_number_of_moves;

    public EventHandler(Scene scene) {
        this.m_current_scene = scene;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch(m_current_scene.getName()) {

            case "main_menu":

                if(((JButton)e.getSource()).getName().equals("load_image")) {

                    //Handles image choosing.
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    int result = fileChooser.showOpenDialog(m_current_scene.getWindow());

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

                        int numberOfBlocks = 4;
                        int id_iterator = 0;
                        m_order_list = new ArrayList<>();

                        for(int j = 0; j < numberOfBlocks; j++) {
                            for (int i = 0; i < numberOfBlocks; i++) {
                                JButton tempButton = new JButton(Integer.toString(i));
                                tempButton.setName(Integer.toString(id_iterator));
                                m_order_list.add(id_iterator++);
                                tempButton.setBounds(i * img.getWidth() / 4, j * img.getHeight() / 4, img.getWidth() / 4, img.getHeight() / 4);
                                BufferedImage croppedImg = img.getSubimage(i * img.getWidth() / 4, j * img.getHeight() / 4, img.getWidth() / 4, img.getHeight() / 4);
                                tempButton.setIcon(new ImageIcon(croppedImg));
                                buttons.add(tempButton);
                                //startedGame.add(tempButton);
                            }
                        }


                        startedGame.setLayout(null);

                        SceneHandler.addScene(new Scene("started_game", startedGame, buttons));
                        m_last_button_id = -1;
                        m_number_of_moves = 0;
                        SceneHandler.changeScene("started_game");
                    }
                }
                else if(((JButton)e.getSource()).getName().equals("exit")) {
                    m_current_scene.getWindow().dispose();
                }
                break;
            case "started_game":

                if(((JButton)e.getSource()).getName().equals("restart")) {
                    //TODO}
                }
                else if(((JButton)e.getSource()).getName().equals("exit")) {
                    //TODO
                }
                else {

                    int currentButtonId = Integer.parseInt(((JButton) e.getSource()).getName());

                    if(m_last_button_id == -1) {
                        m_last_button_id = currentButtonId;
                    }
                    else {
                        if((Math.abs(m_last_button_id - currentButtonId) == 1) || (Math.abs(m_last_button_id - currentButtonId) == 4)) {
                            Icon tempIcon = m_current_scene.getButtons().get(m_last_button_id).getIcon();
                            m_current_scene.getButtons().get(m_last_button_id).setIcon(m_current_scene.getButtons().get(currentButtonId).getIcon());
                            m_current_scene.getButtons().get(currentButtonId).setIcon(tempIcon);
                            int tempIndexLast = m_order_list.indexOf(m_last_button_id);
                            int tempIndexCurrent = m_order_list.indexOf(currentButtonId);
                            m_order_list.set(tempIndexLast, currentButtonId);
                            m_order_list.set(tempIndexCurrent, m_last_button_id);
                        }
                        m_last_button_id = -1;

                        for(int i = 0; i < m_order_list.size(); i++) {
                            if(i != m_order_list.get(i)) {
                                System.out.println("ZLE");
                                break;
                            }
                        }
                    }
                }
                break;
        }
    }
}
