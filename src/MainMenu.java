import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class MainMenu extends Composite {

    private JButton load_image_button;
    private JButton exit_button;
    private JLabel message;

    public MainMenu(String name, JFrame window, GuiHandler gui_handler, Resolution window_size) {
        super(name, window, gui_handler, window_size);
        this.load_image_button = null;
        this.exit_button = null;

        this.setLayout(new BorderLayout(5, 5));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    }

    //Loads GUI sections
    public void loadSections() {

        this.loadTitleSection();
        this.loadCenterSection();
        this.loadFooterSection();
    }

    private void loadTitleSection() {

        //Title
        Section title_section = new Section("TITLE");
        int title_top_border = (int) (this.window_size.getWidth() * 0.15);
        int title_bottom_border = (int) (this.window_size.getHeight() * 0.05);
        title_section.setBorder(BorderFactory.createEmptyBorder(51, 0, 19, 0));

        JLabel title = new JLabel("Puzzle Game");
        int title_font_size = (int) (this.window_size.getHeight() * 0.075);
        Font title_font = new Font("Arial", Font.BOLD, title_font_size);
        title.setForeground(Color.BLACK);
        title.setFont(title_font);

        title_section.add(title);

        this.addSection(title_section, BorderLayout.NORTH);
    }

    private void loadCenterSection() {

        //Buttons
        Section center_section = new Section("BUTTONS");
        center_section.setLayout(new BoxLayout(center_section, BoxLayout.PAGE_AXIS));
        center_section.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Buttons
        this.load_image_button = new JButton("Load Image");
        this.exit_button = new JButton("Exit");
        this.load_image_button.setAlignmentX(CENTER_ALIGNMENT);
        this.exit_button.setAlignmentX(CENTER_ALIGNMENT);
        this.load_image_button.setMaximumSize(new Dimension(125, 40));
        this.exit_button.setMaximumSize(new Dimension(125, 40));
        this.load_image_button.addActionListener(this);
        this.exit_button.addActionListener(this);

        //Message
        this.message = new JLabel();
        this.message.setForeground(Color.red);
        this.message.setAlignmentX(CENTER_ALIGNMENT);

        center_section.add(this.load_image_button);
        center_section.add(Box.createRigidArea(new Dimension(0, 5)));
        center_section.add(this.exit_button);
        center_section.add(Box.createRigidArea(new Dimension(0, 30)));
        center_section.add(this.message);

        this.addSection(center_section, BorderLayout.CENTER);
    }

    private void loadFooterSection() {

        //Footer
        Section footer_section = new Section("FOOTER");
        footer_section.setLayout(new BoxLayout(footer_section, BoxLayout.LINE_AXIS));
        footer_section.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        footer_section.setBackground(Color.BLACK);

        JLabel footer = new JLabel("Version: " + Game.version);
        Font footer_font = new Font("Arial", Font.BOLD | Font.ITALIC, 15);
        footer.setForeground(Color.WHITE);
        footer.setFont(footer_font);
        footer.setAlignmentX(LEFT_ALIGNMENT);

        footer_section.add(footer);

        this.addSection(footer_section, BorderLayout.SOUTH);
    }

    private File chooseFile() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this.window);

        //When image has loaded successfully.
        if(result == JFileChooser.APPROVE_OPTION) {

            Game.img = fileChooser.getSelectedFile();

            //Converting file to the img format which allows cropping.
//            BufferedImage img = null;
//
//            try {
//                img = ImageIO.read(file);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
            this.gui_handler.showComposite("OPTIONS");
        }
        else {
            this.message.setText("Image was not loaded...");
        }

        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if(source == this.load_image_button) {
            this.chooseFile();
        }
        else if(source == this.exit_button) {
            this.window.dispose();
        }
    }
}
