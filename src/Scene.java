import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scene {

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
            button.addActionListener(new EventHandler(m_name, m_window));
            m_window.add(button);
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
