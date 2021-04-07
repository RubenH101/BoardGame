package finalGame;
/*
 * Description: This is theMakeButton Class where a custom button is made.
 * Other Comments: The Button implements a Button font, Button Pressed and Free Style.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class MakeButton extends Button{

	//Save these in a package the src folder to access these resources.
	private final String Font_Path = "src/game_resources/Kenney Mini Square.ttf";
	private final String Button_Pressed_Style = "-fx-background-color: transparent; -fx-background-image: url('/game_resources/blue_button04.png');";
	private final String Button_Free_Style = "-fx-background-color: transparent; -fx-background-image: url('/game_resources/blue_button00.png');";
	
	public MakeButton(String text) {
		
		super.setText(text);
		setButtonFont();
		super.setPrefHeight(49);
		super.setPrefWidth(190);
		super.setStyle(Button_Free_Style);
		initialButtonListen();
		
			
	}
	private void setButtonFont() {
		try {
			super.setFont(Font.loadFont(new FileInputStream(Font_Path), 23));
		} catch (FileNotFoundException e) {
			super.setFont(Font.font("Verdana, 23"));
		}
		
		
		
	}
	
	private void setButtonPressedStyle() {
		super.setStyle(Button_Pressed_Style);
		super.prefHeight(45);
		super.setLayoutX(getLayoutX());
	
	}
	private void setButtonReleasedStyle() {
		super.setStyle(Button_Free_Style);
		super.setPrefHeight(49);
		super.setLayoutY(getLayoutY());
		
		
	}
	private void initialButtonListen() {
		super.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonPressedStyle();
				}
				
			}	
		});
		super.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonReleasedStyle();
				}
				
			}	
		});
		super.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow());
			}
		});
		
		super.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
			}
		});
		
		
		
	}
	
	
}
