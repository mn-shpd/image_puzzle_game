import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

public class Options extends Composite implements PropertyChangeListener {

    public static final int additional_gap = 50;

    //NumberOfBlocks
    private Integer number_of_blocks;
    private JTextField number_of_blocks_field;
    private NumberFormat number_of_blocks_format;
    //Scale
    private String scale;
    private JComboBox scale_combo_box;
    //Message
    private JLabel message;
    //Return button
    private JButton return_button;
    //Start button
    private JButton start_button;

    public Options(String name, JFrame window, GuiHandler gui_handler, Resolution window_size) {
        super(name, window, gui_handler, window_size);
        this.number_of_blocks = null;
        this.scale = "maximum";
        this.message = null;
        this.return_button = new JButton("Return");
        this.start_button = new JButton("Start");

        this.setLayout(new BorderLayout(5, 5));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    }

    //Loads GUI sections of this composite.
    public void loadSections() {
        this.loadTitleSection();
        this.loadCenterSection();
        this.loadButtonsSection();
    }

    //Loads "Game Options" label and adds it to the composite.
    private void loadTitleSection() {
        JPanel title_section = new JPanel();
        title_section.setName("TITLE");
        title_section.setBorder(BorderFactory.createEmptyBorder(45, 0, 19, 0));

        JLabel title = new JLabel("Game Options");
        Font title_font = new Font("Arial", Font.BOLD, 29);
        title.setForeground(Color.BLACK);
        title.setFont(title_font);

        title_section.add(title);

        this.add(title_section, BorderLayout.NORTH);
    }

    //Loads labels, fields, combo-box and adds them to the composite.
    private void loadCenterSection() {
        //Options section
        JPanel center_section = new JPanel();
        center_section.setName("OPTIONS");
        center_section.setLayout(new BoxLayout(center_section, BoxLayout.PAGE_AXIS));
        center_section.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 10));

        //Font for option labels
        Font options_font = new Font("Arial", Font.BOLD, 14);

        //Number of blocks option
        JLabel number_of_blocks_label = new JLabel("Amount of blocks:");
        number_of_blocks_label.setLabelFor(this.number_of_blocks_field);
        number_of_blocks_label.setForeground(Color.BLACK);
        number_of_blocks_label.setFont(options_font);

        this.number_of_blocks_field = new JTextField();
        this.number_of_blocks_field.addPropertyChangeListener(this);
        this.number_of_blocks_field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent doc_event) {
                numberOfBlocksChange(doc_event);
            }

            @Override
            public void removeUpdate(DocumentEvent doc_event) {
                numberOfBlocksChange(doc_event);
            }

            @Override
            public void changedUpdate(DocumentEvent doc_event) {
                numberOfBlocksChange(doc_event);
            }
        });
        this.number_of_blocks_field.setAlignmentX(LEFT_ALIGNMENT);
        this.number_of_blocks_field.setMaximumSize(new Dimension(90, 25));

        //Scale option label
        JLabel scale_label = new JLabel("Scale:");
        scale_label.setLabelFor(this.scale_combo_box);
        scale_label.setForeground(Color.BLACK);
        scale_label.setFont(options_font);

        //Combo box
        String values[] = { "Maximum", "Small", "Medium", "Large" };
        this.scale_combo_box = new JComboBox(values);
        this.scale_combo_box.setAlignmentX(LEFT_ALIGNMENT);
        this.scale_combo_box.setMaximumSize(new Dimension(90, 25));

        //Message
        this.message = new JLabel();
        this.message.setForeground(Color.red);
        this.message.setAlignmentX(LEFT_ALIGNMENT);

        //Adding components to the section.
        center_section.add(number_of_blocks_label);
        center_section.add(Box.createRigidArea(new Dimension(0, 5)));
        center_section.add(this.number_of_blocks_field);
        center_section.add(Box.createRigidArea(new Dimension(0, 10)));
        center_section.add(scale_label);
        center_section.add(Box.createRigidArea(new Dimension(0, 5)));
        center_section.add(this.scale_combo_box);
        center_section.add(Box.createRigidArea(new Dimension(0, 30)));
        center_section.add(this.message);

        this.add(center_section, BorderLayout.CENTER);
    }

    //Loads bottom buttons and adds them to the composite.
    private void loadButtonsSection() {
        JPanel buttons_section = new JPanel();
        buttons_section.setName("BUTTONS");
        buttons_section.setLayout(new BoxLayout(buttons_section, BoxLayout.LINE_AXIS));
        buttons_section.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));

        this.return_button.addActionListener(this);
        this.start_button.addActionListener(this);

        buttons_section.add(this.return_button);
        buttons_section.add(Box.createHorizontalGlue());
        buttons_section.add(this.start_button);

        this.add(buttons_section, BorderLayout.SOUTH);
    }

    //Initializes a new game with options provided by the user and starts it.
    private void startGame() {
        if(this.number_of_blocks == null) {
            this.message.setText("No value for number of blocks...");
        }
        else if(this.scale.isEmpty()) {
            this.message.setText("Scale was not set...");
        }
        else {
            BufferedImage adjusted_img = this.adjustFile(Game.img);
            StartedGame started_game = new StartedGame("STARTED_GAME", this.window, this.gui_handler, adjusted_img, this.number_of_blocks);

            started_game.loadSections();

            gui_handler.addComposite(started_game, started_game.getName());
            this.gui_handler.showComposite("STARTED_GAME");
        }
    }

    //Adjusts the image loaded by the user.
    //It is scaled to square form.
    //If it is bigger than OS's windows area it is resized.
    //It is scaled along the user's criteria.
    //It is cropped if it gives remainder when dividing it's length by number of blocks choosen by the user.
    private BufferedImage adjustFile(File file) {
        //Used to store file as an image and process it.
        BufferedImage img = null;
        //Reads input file and stores it as an image.
        try {
            img = ImageIO.read(new File("C:\\Users\\thewo\\Desktop\\neon-sunset-4k-eh-1366x768.jpg"));
        } catch (IOException e) {
            //TODO
        }

        img = this.resizeToFitImgSection(img);
        img = this.userCriteriaScale(img, this.scale);
        img = this.cropImageIfGivesRemainder(img, this.number_of_blocks);

        return img;
    }

    //Scales image to square and resizes it if it's bigger than tiles section's dimensions.
    private BufferedImage resizeToFitImgSection(BufferedImage img) {

        //Tiles section's resolution is set to the maximum of OS's windows area.
        Resolution tiles_section_size = gui_handler.getMaximumWindowBounds();
        tiles_section_size.setWidth(tiles_section_size.getWidth() - StartedGame.LEFT_BAR_WIDTH);

        //Checks if the image's dimensions are beyond tiles sections' resolution.
        int scale_width = (img.getWidth() > tiles_section_size.getWidth()) ? tiles_section_size.getWidth() : img.getWidth();
        int scale_height = (img.getHeight() > tiles_section_size.getHeight()) ? tiles_section_size.getHeight() : img.getHeight();
        int scale_length;

        if(scale_width < scale_height) {
            scale_length = scale_width;
        }
        else {
            scale_length = scale_height;
        }

        //Dimensions of image are corrected by taskbar's height to fit into OS's windows area.
        if(this.checkIfBiggerThanOsWindowsArea(scale_length, scale_length)) {
            scale_length -= gui_handler.getOsTaskBarHeight() * 2;
        }

        //Used to resize image.
        BufferedImage scaled_image = new BufferedImage(scale_length, scale_length, img.getType());
        Graphics2D g2d = scaled_image.createGraphics();
        g2d.drawImage(img, 0, 0, scale_length, scale_length, null);

        return scaled_image;
    }

    //Checks if resolution passed as parameters is bigger than the screen resolution without taskbar.
    private boolean checkIfBiggerThanOsWindowsArea(int width, int height) {
        Resolution windows_area = this.gui_handler.getMaximumWindowBounds();

        if(width >= windows_area.getWidth()) {
            return true;
        }
        if(height >= windows_area.getHeight()) {
            return true;
        }

        return false;
    }

    //Scales image along user criteria.
    private BufferedImage userCriteriaScale(BufferedImage img, String scale) {
        if(!scale.toLowerCase().equals("maximum")) {
            int scaled_length = img.getHeight();

            switch (scale.toLowerCase()) {
                case "large":
                    scaled_length = scaled_length * 85 / 100;
                    break;
                case "medium":
                    scaled_length = img.getHeight() * 75 / 100;
                    break;
                case "small":
                    scaled_length = img.getHeight() * 65 / 100;
                    break;
            }

            BufferedImage scaled_image = new BufferedImage(scaled_length, scaled_length, img.getType());
            Graphics2D g2d = scaled_image.createGraphics();
            g2d.drawImage(img, 0, 0, scaled_length, scaled_length, null);
            g2d.dispose();

            img = scaled_image;
        }

        return img;
    }

    //Crops the square image if it gives remainder when dividing it's size by number of blocks.
    private BufferedImage cropImageIfGivesRemainder(BufferedImage img, int number_of_blocks) {
        if(img.getHeight() % number_of_blocks != 0) {
            int size_of_block = img.getHeight() / number_of_blocks;
            int cropping_size = size_of_block * number_of_blocks;
            img = img.getSubimage(0, 0, cropping_size, cropping_size);
        }

        return img;
    }

    //Handles changes of options.

    private void numberOfBlocksChange(DocumentEvent doc_event) {
        this.message.setText("");
        this.number_of_blocks = null;

        Document document = doc_event.getDocument();
        String text = null;
        try {
            text = document.getText(0, document.getLength());
            if(!text.isEmpty()) {
                this.number_of_blocks = Integer.parseInt(text);
            }
        } catch (BadLocationException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            this.message.setText("Wrong value for number of blocks...");
        }
    }

    //TO DELETE?
    @Override
    public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        if(source == this.number_of_blocks_field) {
            //TODO
        }
    }

    //Handles events generated by buttons.
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == this.return_button) {
            this.gui_handler.showComposite("MAIN_MENU");
        }
        else if(source == this.start_button) {
            this.startGame();
        }
    }
}
