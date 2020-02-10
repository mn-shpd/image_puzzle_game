import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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

                    if(result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        System.out.println(selectedFile.getAbsolutePath());
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
