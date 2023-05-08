package src.game;
import javafx.scene.shape.Circle;
import javafx.animation.AnimationTimer;
import javafx.geometry.Bounds;


public class Ball {
    Circle circle;
    private Integer yDirection;
    private Integer xDirection;
    private Integer sceneWidth;
    private Integer sceneHeight;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Score playerOneScore;
    private Score playerTwoScore;

    public Ball(Integer sceneWidth, Integer sceneHeight, Paddle leftPaddle, Paddle rightPaddle, Score playerOneScore, Score playerTwoScore) {
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.sceneHeight = sceneHeight;
        this.sceneWidth = sceneWidth;
        this.playerOneScore = playerOneScore;
        this.playerTwoScore = playerTwoScore;
        this.circle = new Circle(15);
        resetBall();
        ballTimer.start();
    }

    private void resetBall() {
        circle.relocate(sceneWidth / 2, sceneHeight / 2);
        // give ball random initial direction
        yDirection = Math.random() > 0.5 ? -1 : 1;
        xDirection = Math.random() > 0.5 ? -1 : 1;
    }

    private AnimationTimer ballTimer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            Bounds circleBounds = circle.getBoundsInParent();
            Bounds rightPaddleBounds = rightPaddle.rectangle.getBoundsInParent();
            Bounds leftPaddleBounds = leftPaddle.rectangle.getBoundsInParent();

            // detect collision with floor and ceiling
            if (circleBounds.getMinY() < 0 || circleBounds.getMaxY() > sceneHeight) {
                yDirection *= -1;
            } 
            // detect collision with the right wall
            if (circleBounds.getMinX() > sceneWidth) {
                playerOneScore.incrementScore();
                resetBall();
            } 
            // detect collision with the left wall
            if (circleBounds.getMaxX() < 0) {
                playerTwoScore.incrementScore();
                resetBall();
            } 
            // detect collision with right paddle
            if (circleBounds.intersects(rightPaddleBounds)) {
                circle.setLayoutX(rightPaddleBounds.getMinX() - circle.getRadius());
                xDirection = -1;
            } 
            // detect collision with left paddle
            if (circleBounds.intersects(leftPaddleBounds)) {
                circle.setLayoutX(leftPaddleBounds.getMaxX() + circle.getRadius());
                xDirection = 1;
            } 

            circle.setLayoutX(circle.getLayoutX() + 5*xDirection);
            circle.setLayoutY(circle.getLayoutY() + 5*yDirection);
        }
    };
}
