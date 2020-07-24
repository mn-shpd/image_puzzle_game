import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenu extends Composite {

    public MainMenu(String name, JFrame window, Resolution window_size) {
        super(name, window, window_size);
    }

    //Loads GUI sections
    public void loadSections() {

        //Title
        Section title_section = new Section("TITLE_SECTION");
        int title_top_border = (int) (this.window_size.getWidth() * 0.15);
        int title_bottom_border = (int) (this.window_size.getHeight() * 0.05);
        title_section.setBorder(BorderFactory.createEmptyBorder(title_top_border, 0, title_bottom_border, 0));

        JLabel title = new JLabel("Puzzle Game");
        Font title_font = new Font("Arial", Font.BOLD,30);
        title.setForeground(Color.BLACK);
        title.setFont(title_font);

        title_section.add(title);

        //Buttons
        Section buttons_section = new Section("BUTTONS_SECTION");
        int v_gap = (int) (this.window_size.getHeight() * 0.02);
        GridLayout layout = new GridLayout(0, 1);
        layout.setVgap(v_gap);
        buttons_section.setLayout(layout);

        int buttons_h_border = (int) (this.window_size.getWidth() * 0.27);
        int buttons_bottom_border = (int) (this.window_size.getHeight() * 0.25);
        buttons_section.setBorder(BorderFactory.createEmptyBorder(0, buttons_h_border, buttons_bottom_border, buttons_h_border));

        JButton load_image_button = new JButton("Load Image");
        load_image_button.setName("load_image");
        JButton exit_button = new JButton("Exit");
        exit_button.setName("exit");
        exit_button.addActionListener(this);

        buttons_section.addButton(load_image_button);
        buttons_section.addButton(exit_button);

        //Footer
        Section footer_section = new Section("FOOTER_SECTION");
        footer_section.setLayout(new FlowLayout(FlowLayout.LEFT));
        footer_section.setBackground(Color.BLACK);

        JLabel footer = new JLabel("Version: 1.0");
        int footer_size = (int) (this.window_size.getHeight() * 0.04);
        Font footer_font = new Font("Arial", Font.BOLD | Font.ITALIC, footer_size);
        footer.setForeground(Color.WHITE);
        footer.setFont(footer_font);

        footer_section.add(footer);

        this.addSection(title_section, BorderLayout.NORTH);
        this.addSection(buttons_section, BorderLayout.CENTER);
        this.addSection(footer_section, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String src_component_name = ((JButton)e.getSource()).getName();

        switch(src_component_name) {
            case "load_game":

            case "exit":
                this.window.dispose();
                break;
        }
    }
}
