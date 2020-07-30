import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public abstract class Composite extends JPanel implements ActionListener {

    protected JFrame window;
    protected Resolution window_size;
    private List<Section> sections;

    public Composite(String name, JFrame window, Resolution window_size) {
        super.setName(name);
        this.window = window;
        this.window_size = window_size;
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

//    public String getName() {
//        return super.getName();
//    }

    public Resolution getWindowSize() {
        return window_size;
    }
}
