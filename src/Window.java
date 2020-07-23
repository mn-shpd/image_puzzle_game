import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Window {

    public static final int MAIN_MENU = 0;
    public static final int STARTED_GAME = 1;

    //Window name
    private int m_name;
    //Window
    private JFrame m_window;
    //List of window's labels
    private List<JLabel> m_labels;
    //List of window's buttons
    private List<JButton> m_buttons;

    public Window(int name, JFrame window, List<JLabel> labels, List<JButton> buttons) {
        this.m_name = name;
        this.m_window = window;
        this.m_buttons = buttons;
        this.m_labels = labels;

        for(JLabel label : labels) {
            m_window.add(label);
        }

        for(JButton button : m_buttons) {
            button.addActionListener(new EventHandler(this));
            m_window.add(button);
        }
    }

    public Window(int name, JFrame window, List<JButton> buttons) {
        this.m_name = name;
        this.m_window = window;
        this.m_buttons = buttons;

        for(JButton button : m_buttons) {
            button.addActionListener(new EventHandler(this));
            m_window.add(button);
        }
    }

    public int getName() {
        return m_name;
    }

    public void setName(int name) {
        this.m_name = name;
    }

    public JFrame getWindow() {
        return m_window;
    }

    public void setWindow(JFrame window) {
        this.m_window = window;
    }

    public List<JButton> getButtons() {
        return m_buttons;
    }

    public void setButtons(List<JButton> m_buttons) {
        this.m_buttons = m_buttons;
    }

    public List<JLabel> getLabels() {
        return m_labels;
    }

    public void setLabels(List<JLabel> labels) {
        this.m_labels = labels;
    }
}
