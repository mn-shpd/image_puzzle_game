import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EventHandler implements ActionListener {

    private Window m_current_window;

    public EventHandler(Window window) {
        this.m_current_window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch(m_current_window.getName()) {

            case Window.MAIN_MENU:

                if(((JButton)e.getSource()).getName().equals("load_image")) {

                    try {
                        Game.loadGame("", 9999);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
                else if(((JButton)e.getSource()).getName().equals("exit")) {
                    m_current_window.getWindow().dispose();
                }
                break;
            case Window.STARTED_GAME:

                if(((JButton)e.getSource()).getName().equals("restart")) {
                    //TODO}
                }
                else if(((JButton)e.getSource()).getName().equals("exit")) {
                    //TODO
                }
                else {

                    int currentButtonId = Integer.parseInt(((JButton) e.getSource()).getName());

                    if(Game.getLastButtonId() == -1) {
                        Game.setLastButtonId(currentButtonId);
                    }
                    else {
                        if((Math.abs(Game.getLastButtonId() - currentButtonId) == 1) || (Math.abs(Game.getLastButtonId() - currentButtonId) == Game.getNumberOfBlocks())) {
                            Icon tempIcon = m_current_window.getButtons().get(Game.getLastButtonId()).getIcon();
                            m_current_window.getButtons().get(Game.getLastButtonId()).setIcon(m_current_window.getButtons().get(currentButtonId).getIcon());
                            m_current_window.getButtons().get(currentButtonId).setIcon(tempIcon);
                            int tempIndexLast = Game.getOrderList().indexOf(Game.getLastButtonId());
                            int tempIndexCurrent = Game.getOrderList().indexOf(currentButtonId);
                            Game.getOrderList().set(tempIndexLast, currentButtonId);
                            Game.getOrderList().set(tempIndexCurrent, Game.getLastButtonId());
                            Game.incrementNumberOfMoves();
                            Game.updateMoves();
                        }
                        Game.setLastButtonId(-1);

                        for(int i = 0; i < Game.getOrderList().size(); i++) {
                            if(i != Game.getOrderList().get(i)) {
                                System.out.println("ZLE");
                                break;
                            }
                        }
                    }
                }
                break;
        }
    }
}
