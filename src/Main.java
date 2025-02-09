import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Tower Defense");


        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); // add gamePanel to the window
        window.pack(); // causes this Window to be sized to fit the preferred size and layouts of its subcomponents(gamePanel)


        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();


    }
}