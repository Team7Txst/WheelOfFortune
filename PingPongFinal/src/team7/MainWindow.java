package team7;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import java.util.Random;

/**
 * Class for the single player game logic and javafx
 */
public class MainWindow {
    //variables
    static final int width = 800;
    static final int height = 600;
    private static final int PLAYER_HEIGHT = 100;
    private static final int PLAYER_WIDTH = 15;
    private static final double BALL_R = 15;
    private boolean gameStarted;
    private int ballYSpeed = 1;
    private int ballXSpeed = 1;
    private double ballXPos = width / 2;
    private double ballYPos = height / 2;
    private double playerOneYPos = height / 2;
    private double playerTwoYPos = height / 2;
    private int playerOneXPos = 0;
    private double playerTwoXPos = width - PLAYER_WIDTH;
    private int scoreP1 = 0;
    private int scoreP2 = 0;

    /**
     *Runs the JavaFX application and sets some aesthetic params
     * @param gc
     */
    public void run(GraphicsContext gc) {
        //set graphics
        //set background color
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);

        //set text
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font(25));

        if (gameStarted){
            //set ball movement
            ballXPos += ballXSpeed;
            ballYPos += ballYSpeed;

            //simple computer opponent who is following the ball
            if(ballXPos < width - width  / 4) {
                playerTwoYPos = ballYPos - PLAYER_HEIGHT / 2;
            }  else {
                playerTwoYPos =  ballYPos > playerTwoYPos + PLAYER_HEIGHT / 2 ?playerTwoYPos += 1: playerTwoYPos - 1;
            }

            //draw the ball
            gc.fillOval(ballXPos, ballYPos, BALL_R, BALL_R);

        } else {
            //set the start text
            gc.setStroke(Color.WHITE);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.strokeText("Click", width / 2, height / 2);

            //reset the ball start position
            ballXPos = width / 2;
            ballYPos = height / 2;

            //reset the ball speed and the direction
            ballXSpeed = new Random().nextInt(2) == 0 ? 1: -1;
            ballYSpeed = new Random().nextInt(2) == 0 ? 1: -1;
        }

        //makes sure the ball stays in the canvas
        if(ballYPos > height || ballYPos < 0) ballYSpeed *=-1;

        //if you miss the ball, computer gets a point
        if(ballXPos < playerOneXPos - PLAYER_WIDTH) {
            scoreP2++;
            gameStarted= false;
        }

        //if the computer misses the ball, you get a point
        if(ballXPos > playerTwoXPos + PLAYER_WIDTH) {
            scoreP1++;
            gameStarted= false;
        }

        //increase the speed after the ball hits the player
        if(((ballXPos + BALL_R > playerTwoXPos) && ballYPos >= playerTwoYPos && ballYPos <= playerTwoYPos + PLAYER_HEIGHT) ||
                ((ballXPos < playerOneXPos + PLAYER_WIDTH) && ballYPos >= playerOneYPos && ballYPos <= playerOneYPos + PLAYER_HEIGHT))
        {
            ballYSpeed += 1 * Math.signum(ballYSpeed);
            ballXSpeed += 1 * Math.signum(ballXSpeed);
            ballXSpeed *= -1;
            ballYSpeed *= -1;
        }

        //draw score
        gc.fillText(scoreP1 + "\t\t\t\t\t\t\t\t" + scoreP2, width / 2, 100);

        //draw player 1 & 2
        gc.fillRect(playerTwoXPos, playerTwoYPos, PLAYER_WIDTH, PLAYER_HEIGHT);
        gc.fillRect(playerOneXPos, playerOneYPos, PLAYER_WIDTH, PLAYER_HEIGHT);
    }

    /**
     * getter
     * @return
     */
    public boolean getGameStarted(){
        return gameStarted;
    }

    /**
     * setter
     * @param gameStarted
     */
    public void setGameStarted(boolean gameStarted){
        this.gameStarted= gameStarted;
    }

    /**
     * getter
     * @return
     */
    public double getPlayerOneYPos(){
        return playerOneYPos;
    }

    /**
     * setter
     * @param playerOneYPos
     */
    public void setPlayerOneYPos(double playerOneYPos){
        this.playerOneYPos= playerOneYPos;
    }
}