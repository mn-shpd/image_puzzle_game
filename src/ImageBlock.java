//This class represents one block of image in the game.

import javax.swing.*;

public class ImageBlock {

    private int id;
    private JButton block;

    public ImageBlock(int id, ImageIcon imagePath) {
        this.id = id;
        this.block = new JButton();
        this.block.setIcon(imagePath);
    }
}
