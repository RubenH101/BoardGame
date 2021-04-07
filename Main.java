package cs2012final;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

	@Override
	public void start(Stage window) throws Exception {
		
		GameSetUp gameStart = new GameSetUp();
		window = gameStart.getStage();
		window.show();	
		
	}
	public static void main(String[] args) {
		Application.launch(args);

	}
}
