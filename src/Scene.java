import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Scene implements ActionListener {

    //Scene name
    private String m_name;
    //Scene window
    private JFrame m_window;
    //List of window's buttons
    private List<JButton> m_buttons;

    public Scene(String name, JFrame window, List<JButton> buttons) {
        this.m_name = name;
        this.m_window = window;
        this.m_buttons = buttons;

        for(JButton button : m_buttons) {
            button.addActionListener(this);
            m_window.add(button);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(m_name) {
            case "main_menu":
                if(((JButton)e.getSource()).getName().equals("load_image")) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    int result = fileChooser.showOpenDialog(m_window);

                    //When img file loaded successfully.
                    if(result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        //System.out.println(selectedFile.getAbsolutePath());

                        //******TESTING*********
                        JFrame startedGame = new JFrame("Puzzle Game - Started");
                        startedGame.setSize(600, 600);

                        //Buttons initialization.
                        List<JButton> buttons = new ArrayList<>();

                        for(int j = 0; j < 3; j++) {
                            for (int i = 0; i < 3; i++) {
                                JButton tempButton = new JButton(Integer.toString(i));
                                tempButton.setName(Integer.toString(i));
                                tempButton.setBounds(i * 200, j * 200, 200, 200);
                                buttons.add(tempButton);
                            }
                        }


                        startedGame.setLayout(null);

                        //m_sceneHandler.addScene(new Scene("main_menu", startedGame, buttons));

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

    public String getName() {
        return m_name;
    }

    public void setName(String name) {
        this.m_name = name;
    }

    public JFrame getWindow() {
        return m_window;
    }

    public void setWindow(JFrame window) {
        this.m_window = window;
    }
}
