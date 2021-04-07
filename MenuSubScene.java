package finalGame;
/* 
 * Description: This is the MenuSubScene class where a secondary a sub scene is created.
 * Other Comments: The subscene animated in the stage and shows the corresponding information.
 */

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class MenuSubScene extends SubScene{
	private final static String Background_Picture = "game_resources/blue_panel.png";
	//private MakeText text;
	private boolean isHidden;
	
	public MenuSubScene() {
		
		super(new Pane(), 750, 400);
		super.prefHeight(400);
		super.prefWidth(700);
		isHidden = true;
		
		super.setLayoutX(350);
		super.setLayoutY(750);
		Pane pane = (Pane) this.getRoot();
		//pane.setStyle("-fx-background-color: PaleTurquoise; -fx-font-size: 2em; -fx-text-fill: #0000ff");
		
		BackgroundImage image = new BackgroundImage(new Image(Background_Picture, 50, 5, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		pane.setBackground(new Background(image));
	}
	
	public MenuSubScene(int x) {
		super(new Pane(), 200, 200);
		super.prefHeight(400);
		super.prefWidth(700);
		isHidden = true;
		
		super.setLayoutX(350);
		super.setLayoutY(750);
		Pane pane = (Pane) this.getRoot();
		//pane.setStyle("-fx-background-color: PaleTurquoise; -fx-font-size: 2em; -fx-text-fill: #0000ff");
		
		BackgroundImage image = new BackgroundImage(new Image(Background_Picture, 50, 5, false, true), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		pane.setBackground(new Background(image));
	}
	public void moveOptions() {
		TranslateTransition tr = new TranslateTransition();
		tr.setDuration(Duration.seconds(1.0));
		tr.setNode(this);
		
		if(isHidden) {
		tr.setToY(-450);
		isHidden = false;
			}
		else {
			
			tr.setToY(0);
			isHidden = true;
		}
		tr.play();
		
	}
	
	//Returns the Pane
	public Pane getPane() {
		return (Pane) this.getRoot();
	}


	
}
	

