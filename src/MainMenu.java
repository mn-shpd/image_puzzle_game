import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainMenu extends Composite {

    private JButton load_image_button;
    private JButton exit_button;

    public MainMenu(String name, JFrame window, Resolution window_size) {
        super(name, window, window_size);
        this.setLayout(new BorderLayout(5, 5));
        this.load_image_button = null;
        this.exit_button = null;
    }

    //Loads GUI sections
    public void loadSections() {

        //Title
        Section title_section = new Section("TITLE");
        int title_top_border = (int) (this.window_size.getWidth() * 0.15);
        int title_bottom_border = (int) (this.window_size.getHeight() * 0.05);
        title_section.setBorder(BorderFactory.createEmptyBorder(title_top_border, 0, title_bottom_border, 0));

        JLabel title = new JLabel("Puzzle Game");
        int title_font_size = (int) (this.window_size.getHeight() * 0.075);
        Font title_font = new Font("Arial", Font.BOLD, title_font_size);
        title.setForeground(Color.BLACK);
        title.setFont(title_font);

        title_section.add(title);

        //Buttons
        Section buttons_section = new Section("BUTTONS");
        int v_gap = (int) (this.window_size.getHeight() * 0.02);
        GridLayout layout = new GridLayout(0, 1);
        layout.setVgap(v_gap);
        buttons_section.setLayout(layout);

        int buttons_h_border = (int) (this.window_size.getWidth() * 0.27);
        int buttons_bottom_border = (int) (this.window_size.getHeight() * 0.25);
        buttons_section.setBorder(BorderFactory.createEmptyBorder(0, buttons_h_border, buttons_bottom_border, buttons_h_border));

        this.load_image_button = new JButton("Load Image");
        this.exit_button = new JButton("Exit");
        this.load_image_button.addActionListener(this);
        this.exit_button.addActionListener(this);

        buttons_section.addButton(this.load_image_button);
        buttons_section.addButton(this.exit_button);

        //Footer
        Section footer_section = new Section("FOOTER");
        footer_section.setLayout(new FlowLayout(FlowLayout.LEFT));
        footer_section.setBackground(Color.BLACK);

        JLabel footer = new JLabel("Version: " + Game.version);
        int footer_font_size = (int) (this.window_size.getHeight() * 0.04);
        Font footer_font = new Font("Arial", Font.BOLD | Font.ITALIC, footer_font_size);
        footer.setForeground(Color.WHITE);
        footer.setFont(footer_font);

        footer_section.add(footer);

        this.addSection(title_section, BorderLayout.NORTH);
        this.addSection(buttons_section, BorderLayout.CENTER);
        this.addSection(footer_section, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if(source == this.load_image_button) {
            //TODO
        }
        else if(source == this.exit_button) {
            this.window.dispose();
        }
    }
}
