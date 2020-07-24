import javax.swing.*;
import java.util.ArrayList;

public class Section extends JPanel {

    private String name;
    private ArrayList<JLabel> labels;
    private ArrayList<JButton> buttons;

    public Section(String name) {
        this.name = name;
        this.labels = new ArrayList<JLabel>();
        this.buttons = new ArrayList<JButton>();
    }

    public boolean addButton(JButton button) {
        return this.addButton(button, null);
    }

    public boolean addButton(JButton button, String position) {
        if(button == null) {
            return false;
        }
        else {
            if(position == null) {
                this.add(button);
            }
            else {
                this.add(button, position);
            }
            this.buttons.add(button);
            return true;
        }
    }

    public boolean addLabel(JLabel label) {
        return this.addLabel(label, null);
    }

    public boolean addLabel(JLabel label, String position) {
        if(label == null) {
            return false;
        }
        else {
            if(position == null) {
                this.add(label);
            }
            else
            {
                this.add(label, position);
            }
            this.labels.add(label);
            return true;
        }
    }
}
