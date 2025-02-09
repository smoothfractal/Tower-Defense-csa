import javax.swing.*;
import java.awt.*;

// GamePanel in basic words just an extension of the class JPanel
// it means that GamePanel has all the properties of the JPanel and additional properties as well
public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16; //16 by 16 tile
    final int scale = 3;
    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768
    final int screenHeight = tileSize * maxScreenRow; //576
    int fps = 60;


    KeyHandler keyH = new KeyHandler(); // instantiate the key-handler





    // game time
    Thread gameThread;
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // size of the class GamePanel
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start(); // call run method

    }
    @Override
    public void run() {
        // creation of the game loop
        double drawInterval = 1000000000/fps; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){ // as long as the game Thread exists it will run
            long currentTime = System.nanoTime();


            // UPDATE: position
            update();
            // DRAW: Screen
            repaint();



            try{
                double remainingTime = nextDrawTime - currentTime;
                remainingTime = remainingTime/1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }
                Thread.sleep((long)remainingTime);
                nextDrawTime += drawInterval;

            }catch (InterruptedException e){
                e.printStackTrace();
            }


        }
    }
    public void update(){
        if(keyH.upPressed){
            playerY -= playerSpeed;
        }
        else if(keyH.downPressed){
            playerY += playerSpeed;
        }
        else if(keyH.leftPressed){
            playerX -= playerSpeed;
        }
        else if(keyH.rightPressed){
            playerX += playerSpeed;
        }

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);

        g2.fillRect(playerX, playerY, tileSize, tileSize); // draws the rectangle

        g2.dispose();



    }

}
