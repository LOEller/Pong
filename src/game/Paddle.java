package src.game;
import javafx.scene.shape.Rectangle;
import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;
import javafx.scene.input.KeyCode;


public class Paddle {
    Rectangle rectangle;
    private Integer yDelta;
    private KeyCode upKey;
    private KeyCode downKey;
    private Integer sceneHeight;

    public Paddle(Integer sceneHeight, Integer x, KeyCode upKey, KeyCode downKey) {
        this.upKey = upKey;
        this.downKey = downKey;
        this.sceneHeight = sceneHeight;
        this.rectangle = new Rectangle(10, 50);
        this.yDelta = 0;
        rectangle.relocate(x, sceneHeight / 2 - 25);
        paddleTimer.start();
    }

    private AnimationTimer paddleTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            Bounds bounds = rectangle.getBoundsInParent();

            // keep paddles within bounds of the game
            if (yDelta < 0 && bounds.getMinY() > 2) {
                rectangle.setLayoutY(rectangle.getLayoutY() + yDelta);
            } 
            if (yDelta > 0 && bounds.getMaxY() < sceneHeight - 2) {
                rectangle.setLayoutY(rectangle.getLayoutY() + yDelta);
            }  
        }
    };

    public void keyReleased(KeyCode keyCode) {
        if (keyCode == upKey) {
            yDelta = 0;
        } else if (keyCode == downKey) {
            yDelta = 0;
        }
    }

    public void keyPressed(KeyCode keyCode) {
        if (keyCode == upKey) {
            yDelta = -6;
        } else if (keyCode == downKey) {
            yDelta = 6;
        }
    }
}
