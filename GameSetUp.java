package cs2012final;
/*
 * Description: This is GameSetup page is the menu where the user is directed towards the game.
 * Other Comments: If the Player chooses play, they then choose their grid and begin their game.
 * If the player chooses help they get a help menu appear with advice.
 * If the player chooses exit they exit the game.
 */

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameSetUp {
	
	private Scene scene;
	private Stage menuStage;
	private Pane pane;
	
	private MenuSubScene play;
	private MenuSubScene help;
	private MenuSubScene hide;
	private MenuSubScene gridChoose;
	private MenuSubScene helpChoose;
	
	private final int WINDOW_WIDTH = 1400;
	private final int WINDOW_HEIGHT = 750;
	
	ArrayList<MakeButton> buttons;
	
	//Default Constructor that creates the game set up
	public GameSetUp() {
		buttons = new ArrayList<>();
		pane = new Pane();
		
		makeTitle();
		theBackground();	
		
		scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);
		menuStage = new Stage();
		menuStage.setScene(scene);
		
		makeButtons();
		makeSubScene();
		
	}
	private void makeTitle() {
		MakeText mk = new MakeText("Welcome to the Final Game by Ruben Heredia");
		mk.setLayoutX(225);
		mk.setLayoutY(142);
		pane.getChildren().add(mk);
	}
		
	//Creates the sub scenes and adds them to the pane
	private void makeSubScene() {
		play = new MenuSubScene();
		pane.getChildren().add(play);
		
		help = new MenuSubScene();
		pane.getChildren().add(help);
		
		
		gridSelector();	
		helpOption();
	}
	// Shows if their is a subscene in the window
		private void showSubScene(MenuSubScene menu) {
			if (hide != null) {
				hide.moveOptions();
			}
			menu.moveOptions();
			hide = menu;
		}
	
	
	private void gridSelector() {	
		gridChoose = new MenuSubScene();
		pane.getChildren().add(gridChoose);
		MakeText mk= new MakeText("Choose Your Grid");	
		mk.setLayoutX(150);
		mk.setLayoutY(75);
		gridChoose.getPane().getChildren().addAll(mk, chooseGrid());		
		
	}
	private void helpOption() {
		helpChoose = new MenuSubScene();
		pane.getChildren().add(helpChoose);
		MakeText mk= new MakeText("NEED HELP?");
		MakeText startHelp = new MakeText("Press play to start!");
		MakeText exitHelp = new MakeText("Press exit to quit!");
		mk.setLayoutX(100);
		mk.setLayoutY(70);
		startHelp.setLayoutX(100);
		startHelp.setLayoutY(130);
		exitHelp.setLayoutX(100);
		exitHelp.setLayoutY(190);
		MakeButton inGame = new MakeButton("GAMEPLAY");
		inGame.setLayoutX(275);
		inGame.setLayoutY(250);
		inGame.setOnAction(e -> {
			AlertBox.displayHelpMenu("Help");
		});
		
		
		helpChoose.getPane().getChildren().addAll(mk, startHelp, exitHelp, inGame);			
	}
	
	public Stage getStage() {
		return menuStage;
	}
	
	//creates the buttons for the menu
	private void makeButtons() {
		createPlayButton();
		createHelpButton();
		createExitButton();
		
	}
	
	//adds a button
	private void addButton(MakeButton button) {		
		buttons.add(button);
		pane.getChildren().add(button);
	}
	//makes the play button
	private void createPlayButton() {
		MakeButton playButton = new MakeButton("PLAY");
		playButton.setLayoutX(500);
		playButton.setLayoutY(190);
		playButton.setMaxWidth(100);
		playButton.setMaxHeight(200);
		addButton(playButton);
		
		playButton.setOnAction(e -> {
			showSubScene(gridChoose);
					
		});	
	}
	//makes the exit button
	private void createExitButton() {
		MakeButton exitButton = new MakeButton("EXIT");
		exitButton.setLayoutX(900);
		exitButton.setLayoutY(193);
		exitButton.setMaxWidth(100);
		exitButton.setMaxHeight(200);
		addButton(exitButton);

		exitButton.setOnAction(e -> {
			menuStage.close();
		});
	}
	//makes the help button
	private void createHelpButton() {
		MakeButton helpButton = new MakeButton("HELP");
		helpButton.setLayoutX(700);
		helpButton.setLayoutY(193);
		helpButton.setMaxWidth(100);
		helpButton.setMaxHeight(200);
		addButton(helpButton);	
			
		helpButton.setOnAction(e -> {
			showSubScene(helpChoose);
			});
		}
	

	//Grid for choosing the Grid Size
	private HBox chooseGrid() {
		HBox horizontalBox = new HBox();
		horizontalBox.setSpacing(25);
		MakeButton op1 = new MakeButton("5 x 5");
		
		op1.setOnAction(e -> {
			TheGame grid  = new TheGame(menuStage, 5, 5);

		});
		
	
		MakeButton op2 = new MakeButton("7 x 7");
		op2.setOnAction(e -> {
			TheGame grid  = new TheGame(menuStage, 7, 7);

		});
			
		MakeButton op3 = new MakeButton("10 x 7");
		op3.setOnAction(e -> {
			TheGame grid  = new TheGame(menuStage, 10, 7);

		});

	
		horizontalBox.setLayoutX(55);
		horizontalBox.setLayoutY(120);
		
		horizontalBox.getChildren().addAll(op1, op2, op3);	
		return horizontalBox;	
	}
	
	private void theBackground() {
		Image imageBack = new Image("cs2012final_resources/grey_panel.png", 50, 48, false, true);
		BackgroundImage background = new BackgroundImage(imageBack, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		pane.setBackground(new Background(background));
		
		
		
	}
	

}