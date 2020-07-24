import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainMenu extends Composite {

    private Resolution window_size;

    public MainMenu(JFrame window) {
        super(window);
    }

    public void computeWindowSize() {

    }

    //Loads GUI sections
    public void loadSections() {

        //Title
        Section title_section = new Section("TITLE_SECTION");

        JLabel title = new JLabel("Puzzle Game");
        Font font = new Font("Arial", Font.BOLD,15);
        title.setForeground(Color.BLACK);
        title.setFont(font);
//        title.setHorizontalAlignment(JLabel.CENTER);

        title_section.setBackground(Color.BLACK);
        title_section.add(title);

        //Buttons
        Section buttons_section = new Section("BUTTONS_SECTION");
        buttons_section.setLayout(new GridLayout(0, 1));
        buttons_section.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));

        JButton load_image_button = new JButton("Load Image");
        load_image_button.setName("load_image");
        JButton exit_button = new JButton("Exit");
        exit_button.setName("exit");

        buttons_section.addButton(load_image_button);
        buttons_section.addButton(exit_button);

        this.addSection(title_section, BorderLayout.NORTH);
        this.addSection(buttons_section, BorderLayout.CENTER);
    }
}
