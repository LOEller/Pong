package src.game;
import javafx.scene.text.Text;
import javafx.scene.text.Font;


public class Score {
    Text text;

    public Score(Integer xPosition) {
        this.text = new Text();
        this.text.setFont(new Font(45)); 
        this.text.setX(xPosition); 
        this.text.setY(50); 
        this.text.setText("0");
    }

    public void incrementScore() {
        Integer newScore = Integer.parseInt(text.getText()) + 1;
        text.setText(Integer.toString(newScore));
    }
}
