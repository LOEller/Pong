package src.game;
import javafx.application.Application; 
import javafx.stage.Stage;
import javafx.scene.Group; 
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Line;


public class Pong extends Application {  
    @Override     
    public void start(Stage primaryStage) throws Exception { 

      Integer sceneWidth = 1000;
      Integer sceneHeight = 600;

      Score playerOneScore = new Score(sceneWidth / 4);
      Score playerTwoScore = new Score(3*sceneWidth / 4);

      Paddle leftPaddle = new Paddle(sceneHeight, 20, KeyCode.W, KeyCode.S);
      Paddle rightPaddle = new Paddle(sceneHeight, sceneWidth - 30, KeyCode.UP, KeyCode.DOWN);
      Ball ball = new Ball(sceneWidth, sceneHeight, leftPaddle, rightPaddle, playerOneScore, playerTwoScore);

      Line line = new Line(sceneWidth / 2, 0, sceneWidth / 2, sceneHeight);
      line.setStrokeWidth(3.0);

      Group root = new Group(
        ball.circle, leftPaddle.rectangle, rightPaddle.rectangle, line, playerOneScore.text, playerTwoScore.text
      );
      Scene scene = new Scene(root, sceneWidth, sceneHeight);

      scene.setOnKeyPressed((KeyEvent event) -> {
        rightPaddle.keyPressed(event.getCode());
        leftPaddle.keyPressed(event.getCode());
      });

      scene.setOnKeyReleased((KeyEvent event) -> {
        rightPaddle.keyReleased(event.getCode());
        leftPaddle.keyReleased(event.getCode());
      });
      
      primaryStage.setTitle("P O N G");      
      primaryStage.setScene(scene);      
      primaryStage.show();
    }         
    public static void main(String args[]){           
       launch(args);      
    } 
 } 