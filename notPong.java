package notPong;

import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration; 
import javafx.application.Application;
import javafx.stage.Stage;

public class Pong extends Application {
	
	//Variables and Decelerations
	private static final int width = 800;
	private static final int height = 600;
	private static final int PLAYER_HEIGHT = 100;
	private static final int PLAYER_WIDTH = 15;
	private static final double BALL_R = 15;
	private int ballYSpeed = 1;
	private int ballXSpeed = 1;
	private double playerOneYPos = height / 2;
	private double playerTwoYPos = height / 2;
	private double ballXPos = width / 2;
	private double ballYPos = width / 2;
	private int scoreP1 = 0;
	private int scoreP2 = 0;
	private boolean gameStarted;
	private int playerOneXPos = 0;
	private double playerTwoXPos = width - PLAYER_WIDTH;
	

	public void start(Stage stage) throws Exception {
		stage.setTitle("NOT PONG 4 CW PURPOSES");
		Canvas canvas = new Canvas(width, height);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		Timeline t1 = new Timeline(new KeyFrame(Duration.millis(10), e ->run(gc)));
		t1.setCycleCount(Timeline.INDEFINITE);
		
		//Mouse Control
		canvas.setOnMouseMoved(e -> playerOneYPos = e.getY());
		canvas.setOnMouseClicked(e -> gameStarted = true);
		stage.setScene(new Scene(new StackPane(canvas)));
		stage.show();
		t1.play();
	}
	
	private void run(GraphicsContext gc) {
		//setting background color
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, width, height);
		
		//setting text color
		gc.setFill(Color.WHITE);
		gc.setFont(Font.font(25));
		
	}
	
	public static void main (String[]args) {
		launch(args);
	}

}