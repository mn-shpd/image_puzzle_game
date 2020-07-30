import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game {

    public static String version = "1.0";

    private JFrame window;
    private static Resolution desktop_resolution;
    private ArrayList<Composite> composites;
    ///////
    private static WindowHandler m_windowHandler;
    private static List<Integer> m_order_list;
    private static int m_number_of_blocks;
    private static int m_last_button_id;
    private static int m_number_of_moves;
    private static JLabel moves;

    public Game() {
        this.composites = new ArrayList<Composite>();
    }

    //Computes resolution and creates game window.
    public void init() {
        this.computeResolution();
        this.createWindow();
        this.initMainMenuComposite();
        this.initOptionsComposite();
    }

    private void computeResolution() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width = gd.getDisplayMode().getWidth();
        int height = gd.getDisplayMode().getHeight();
        this.desktop_resolution = new Resolution(width, height);
    }

    private void createWindow() {
        this.window = new JFrame("Puzzle Game");
        this.window.setLayout(new CardLayout());
        this.window.setVisible(true);
        this.window.setResizable(false);
    }

    private void initMainMenuComposite() {

        //Compute window size for Main Menu composite.
        Resolution window_size = new Resolution(this.desktop_resolution.getWidth() / 4, this.desktop_resolution.getHeight() / 2);
        MainMenu main_menu = new MainMenu("MAIN_MENU", this.window, window_size);

        //Load labels, buttons etc.
        main_menu.loadSections();

        //Set visibility to false because it is only initialization.
        main_menu.setVisible(false);

        this.composites.add(main_menu);
        this.window.add(main_menu);
    }

    private void initOptionsComposite() {

        Resolution window_size = new Resolution(342, 384);
        Options options = new Options("OPTIONS", this.window, window_size);

        options.loadSections();

        options.setVisible(false);

        this.composites.add(options);
        this.window.add(options);
    }

    public void start() {
        this.showComposite("MAIN_MENU");
    }

    private void showComposite(String name) {
//        CardLayout interfaces = (CardLayout) this.window.getLayout();
//        interfaces.show(this.window, name);

        for(Composite composite : this.composites) {
            if(composite.getName().equals(name)) {
                this.window.pack();
                composite.setVisible(true);
                this.window.setSize(composite.getWindowSize().getWidth(), composite.getWindowSize().getHeight());
            }
            else {
                composite.setVisible(false);
            }
        }
    }

//    public void hideComposite(String name) {
//        for(Composite composite : this.composites) {
//            if(composite.getName().equals(name)) {
//                composite.setVisible(false);
//            }
//        }
//    }

    public static void loadGame(String scale, int numberOfBlocks1) throws IOException {

        m_number_of_moves = 0;
        //FOR TESTING
        m_number_of_blocks = 25;
        //Left bar width
        int barWidth = 150;

        //File file = new File("C:\\Users\\thewo\\Desktop\\neon-sunset-4k-eh-1366x768.jpg");
        File file = chooseFile();
        BufferedImage img = adjustFile(file, "", m_number_of_blocks);

        JFrame game = new JFrame("Puzzle Game - Started");
        game.setSize(barWidth + img.getWidth(), img.getHeight());
        game.setLayout(null);
        game.setUndecorated (true);
        game.setLocationRelativeTo(null);

        //Labels initialization.
        List<JLabel> labels = new ArrayList<>();

        moves = new JLabel("Moves: " + getNumberOfMoves());
        moves.setBounds(0, 0, barWidth, 50);
        Font f = new Font("Arial", Font.BOLD,15);
        moves.setForeground(Color.BLACK);
        moves.setFont(f);
        moves.setHorizontalAlignment(JLabel.CENTER);
        labels.add(moves);

        ImageIcon backgroundImg = new ImageIcon("background.png");
        JLabel background = new JLabel("", backgroundImg, JLabel.LEFT);
        background.setBounds(0, img.getHeight() - backgroundImg.getIconHeight(), barWidth, backgroundImg.getIconHeight());
        labels.add(background);

        //Buttons initialization.
        List<JButton> buttons = new ArrayList<>();

        //Left bar buttons.


        int id_iterator = 0;
        m_order_list = new ArrayList<>();
        int blockSize = img.getHeight() / m_number_of_blocks;

        for(int i = 0; i < m_number_of_blocks; i++) {
            for (int j = 0; j < m_number_of_blocks; j++) {

                JButton tempButton = new JButton();
                tempButton.setName(Integer.toString(id_iterator));
                m_order_list.add(id_iterator++);

                int currentBlockPosX = j * img.getWidth() / m_number_of_blocks;
                int currentBlockPosY = i * img.getHeight() / m_number_of_blocks;

                tempButton.setBounds(barWidth + currentBlockPosX, currentBlockPosY, blockSize, blockSize);
                BufferedImage croppedImg = img.getSubimage(currentBlockPosX, currentBlockPosY, blockSize, blockSize);
                tempButton.setIcon(new ImageIcon(croppedImg));

                buttons.add(tempButton);
            }
        }

        WindowHandler.addScene(new Window(Window.STARTED_GAME, game, labels, buttons));
        m_last_button_id = -1;
        m_number_of_moves = 0;
        WindowHandler.changeScene(Window.STARTED_GAME);
    }

    public static File chooseFile() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(m_windowHandler.getCurrentScene().getWindow());

        //When image has loaded successfully.
        if(result == JFileChooser.APPROVE_OPTION) {

            File file = fileChooser.getSelectedFile();

            //Converting file to the img format which allows cropping.
//            BufferedImage img = null;
//
//            try {
//                img = ImageIO.read(file);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
            return file;
        }
        else {
            //TODO (ERROR)
        }

        return null;
    }

    public static BufferedImage adjustFile(File file, String scale, int numberOfBlocks) throws IOException {

        //Used to store user's screen dimension for image scaling.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Used to store file as an image and process it.
        BufferedImage img = null;
        //Used to resize image.
        Graphics2D g2d = null;
        //Reads input file and stores it as an image.
        img = ImageIO.read(file);
        //Temporary object used to process the image.
        BufferedImage scaledImage;

        //If image is bigger than screen resolution it is resized.
        for(int i = 99; img.getHeight() > screenSize.getHeight(); i--) {

            int scaledWidth = img.getWidth() * i / 100;
            int scaledHeight = img.getHeight() * i / 100;

            scaledImage = new BufferedImage(scaledWidth, scaledHeight, img.getType());
            g2d = scaledImage.createGraphics();
            g2d.drawImage(img, 0, 0, scaledWidth, scaledHeight, null);

        }

        int scaledSize = img.getHeight();

        switch(scale) {
            case "large":
                if(img.getHeight() > screenSize.getHeight() * 85 / 100) {
                    scaledSize = img.getHeight() * 85 / 100;
                }
                break;
            case "medium":
                if(img.getHeight() > screenSize.getHeight() * 75 / 100) {
                    scaledSize = img.getHeight() * 75 / 100;
                }
                break;
            case "small":
                if(img.getHeight() > screenSize.getHeight() * 65 / 100) {
                    scaledSize = img.getHeight() * 65 / 100;
                }
                break;
        }

        scaledImage = new BufferedImage(scaledSize, scaledSize, img.getType());
        g2d = scaledImage.createGraphics();
        g2d.drawImage(img, 0, 0, scaledSize, scaledSize, null);
        g2d.dispose();

        //Crops the image if it gives remainder when dividing its size by number of blocks.
        if(scaledSize % numberOfBlocks != 0) {
            int sizeOfBlock = scaledSize / numberOfBlocks;
            int croppingSize = sizeOfBlock * numberOfBlocks;
            img = img.getSubimage(0, 0, croppingSize, croppingSize);
        }

        return img;
    }

    public static int getNumberOfBlocks() {
        return m_number_of_blocks;
    }

    public static void updateMoves() {
        moves.setText("Moves: " + m_number_of_moves);
    }

    public static List<Integer> getOrderList() {
        return m_order_list;
    }

    public static int getLastButtonId() {
        return m_last_button_id;
    }

    public static void setLastButtonId(int last_button_id) {
        Game.m_last_button_id = last_button_id;
    }

    public static int getNumberOfMoves() {
        return m_number_of_moves;
    }

    public static void setNumberOfMoves(int number_of_moves) {
        Game.m_number_of_moves = number_of_moves;
    }

    public static void incrementNumberOfMoves() {
        Game.m_number_of_moves++;
    }
}
