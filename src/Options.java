import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

public class Options extends Composite implements PropertyChangeListener {

    //NumberOfBlocks
    private int number_of_blocks;
    private JFormattedTextField number_of_blocks_field;
    private NumberFormat number_of_blocks_format;
    //Scale
    private String scale;
    private JComboBox scale_combo_box;
    //Message
    private String message;
    //Return button
    private JButton return_button;
    //Start button
    private JButton start_button;

    public Options(String name, JFrame window, Resolution window_size) {
        super(name, window, window_size);
        this.number_of_blocks =  6;
        this.scale = "medium";
        this.message = null;
        this.return_button = new JButton("Return");
        this.start_button = new JButton("Start");

        this.setLayout(new BorderLayout(5, 5));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
    }

    public void loadSections() {

        this.loadTitleSection();
        this.loadOptionsSection();
        this.loadButtonsSection();
    }

    private void loadTitleSection() {

        Section title_section = new Section("TITLE");
        title_section.setBorder(BorderFactory.createEmptyBorder(45, 0, 19, 0));

        JLabel title = new JLabel("Game Options");
        Font title_font = new Font("Arial", Font.BOLD, 29);
        title.setForeground(Color.BLACK);
        title.setFont(title_font);

        title_section.add(title);

        this.addSection(title_section, BorderLayout.NORTH);
    }

    private void loadOptionsSection() {

        //Options section
        Section options_section = new Section("OPTIONS");
        options_section.setLayout(new BoxLayout(options_section, BoxLayout.PAGE_AXIS));
        options_section.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 10));

        //Font for option labels
        Font options_font = new Font("Arial", Font.BOLD, 14);

        this.setFormatsForFields();

        //Number of blocks option
        JLabel number_of_blocks_label = new JLabel("Amount of blocks:");
        number_of_blocks_label.setLabelFor(this.number_of_blocks_field);
        number_of_blocks_label.setForeground(Color.BLACK);
        number_of_blocks_label.setFont(options_font);

        this.number_of_blocks_field = new JFormattedTextField(number_of_blocks_format);
        this.number_of_blocks_field.addPropertyChangeListener("value", this);
        this.number_of_blocks_field.setAlignmentX(LEFT_ALIGNMENT);
        this.number_of_blocks_field.setMaximumSize(new Dimension(75, 25));

        //Scale option
        JLabel scale_label = new JLabel("Scale:");
        scale_label.setLabelFor(this.scale_combo_box);
        scale_label.setForeground(Color.BLACK);
        scale_label.setFont(options_font);

        //combo box
        String values[] = { "Small", "Medium", "Large" };
        this.scale_combo_box = new JComboBox(values);
        this.scale_combo_box.setAlignmentX(LEFT_ALIGNMENT);
        this.scale_combo_box.setMaximumSize(new Dimension(75, 25));

        //Adding components to the panel
        options_section.add(number_of_blocks_label);
        options_section.add(Box.createRigidArea(new Dimension(0, 5)));
        options_section.add(this.number_of_blocks_field);
        options_section.add(Box.createRigidArea(new Dimension(0, 10)));
        options_section.add(scale_label);
        options_section.add(Box.createRigidArea(new Dimension(0, 5)));
        options_section.add(this.scale_combo_box);

        this.addSection(options_section, BorderLayout.CENTER);
    }

    private void loadButtonsSection() {

        Section buttons_section = new Section("BUTTONS");
        buttons_section.setLayout(new BoxLayout(buttons_section, BoxLayout.LINE_AXIS));
        buttons_section.setBorder(BorderFactory.createEmptyBorder(0, 20, 10, 20));

        this.return_button.addActionListener(this);
        this.start_button.addActionListener(this);

        buttons_section.add(this.return_button);
        buttons_section.add(Box.createHorizontalGlue());
        buttons_section.add(this.start_button);

        this.add(buttons_section, BorderLayout.SOUTH);
    }

    private void setFormatsForFields() {

        this.number_of_blocks_format = NumberFormat.getNumberInstance();
        this.number_of_blocks_format.setParseIntegerOnly(true);
        number_of_blocks_format.setMinimumIntegerDigits(1);
        number_of_blocks_format.setMaximumIntegerDigits(3);
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        Object source = e.getSource();
        if(source == this.number_of_blocks_field) {
            System.out.println("ZMIENILO SIE");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == this.return_button) {
            System.out.println("WRACAM");
        }
    }
}