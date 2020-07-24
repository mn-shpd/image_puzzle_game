import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Composite extends JPanel {

    private JFrame window;
    private List<Section> sections;

    public Composite(JFrame window) {
        this.window = window;
        this.sections = new ArrayList<Section>();
    }

    public boolean addSection(Section section) {
        return this.addSection(section, null);
    }

    public boolean addSection(Section section, String position) {
        if(section == null) {
            return false;
        }
        else {
            if(section == null) {
                this.add(section);
            }
            else {
                this.add(section, position);
            }
            this.sections.add(section);
            return true;
        }
    }
}
