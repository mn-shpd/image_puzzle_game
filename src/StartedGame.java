import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class StartedGame extends Composite {

    public StartedGame(String name, JFrame window, GuiHandler gui_handler, Resolution window_size) {
        super(name, window, gui_handler, window_size);
        this.setLayout(new BorderLayout(5, 5));
    }

    private void initStartedGameComposite(String scale, int number_of_blocks) {

        if(scale == null) {
            System.out.println("Scale parameter was not passed.");
            return;
        }
        else if(scale.isEmpty()) {
            System.out.println("Scale parameter is empty.");
            return;
        }
        if(number_of_blocks <= 1) {
            System.out.println("Invalid value for parameter number_of_blocks.");
            return;
        }

        Section left_bar_section = new Section("LEFT_BAR");
        int left_bar_horizontal_border = (int) (this.window_size.getWidth() * 0.15);
//        int left_bar_bottom_border = (int) (this.window_size.getHeight() * 0.05);
//        title_section.setBorder(BorderFactory.createEmptyBorder(title_top_border, 0, title_bottom_border, 0));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //TODO
    }
}
