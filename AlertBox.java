package cs2012final;
/* 
 * Description: This is the AlertBox Class where messages are sent to the Player(User).
 * Other Comments: Potential Hazards, Winning, and Losing screens are also displayed.
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
	
	public static void display(String message) {
		
		
		Stage window =new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		
		window.setTitle("POTENTIAL HAZARDS ALERTS");
		
		
		MakeText mk = new MakeText(message);
		//label.setText(message);
		
		MakeButton exit = new MakeButton("CLOSE");
		exit.setOnAction(e -> {
			window.close();
		});
		
		VBox vb = new VBox(10);
		vb.getChildren().addAll(mk, exit);
		vb.setAlignment(Pos.CENTER);
		vb.setStyle("-fx-background-color: RED; -fx-font-size: 2em; -fx-text-fill: #0000ff");	
		
			
		Scene scene = new Scene(vb, 900, 200);
		window.setScene(scene);
		window.showAndWait();
	}
	public static void youWin(Stage gameStage) {
		gameStage.close();
		Stage window =new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		VBox vb = new VBox(20);
		vb.setAlignment(Pos.CENTER);
		
		MakeText mm = new MakeText("You Killed the Dragon!");
		MakeText bb = new MakeText("Congradulations, You Win!!!");
		
		MakeButton tryAgain = new MakeButton("Play AGAIN");
		tryAgain.setOnAction(e -> {
			window.close();
			GameSetUp gameStart = new GameSetUp();
			Stage newWindow = new Stage();
			newWindow = gameStart.getStage();
			newWindow.show();	
		});
		
		MakeButton exit = new MakeButton("EXIT");
		exit.setOnAction(e -> {
			//gameStage.close();
			window.close();
		});
		vb.setStyle("-fx-background-color: RED; -fx-font-size: 2em; -fx-text-fill: #0000ff");	
		
		vb.getChildren().addAll(mm, bb, tryAgain, exit);
		
		Scene scene = new Scene(vb, 800, 400);
		window.setScene(scene);
		window.showAndWait();
			
	}
	public static void youLose(Stage gameStage) {
		gameStage.close();
		Stage window =new Stage();
		
		window.initModality(Modality.APPLICATION_MODAL);
		VBox vb = new VBox(20);
		vb.setAlignment(Pos.CENTER);
		
		MakeText mm = new MakeText("Sorry, You Lost!");
		
		MakeButton tryAgain = new MakeButton("TRY AGAIN");
		tryAgain.setOnAction(e -> {
			window.close();
			GameSetUp gameStart = new GameSetUp();
			Stage newWindow = new Stage();
			newWindow = gameStart.getStage();
			newWindow.show();	
		});
		
		MakeButton exit = new MakeButton("EXIT");
		exit.setOnAction(e -> {
			//gameStage.close();
			window.close();
		});
		vb.setStyle("-fx-background-color: RED; -fx-font-size: 2em; -fx-text-fill: #0000ff");	
		
		vb.getChildren().addAll(mm, tryAgain, exit);
		
		Scene scene = new Scene(vb, 800, 400);
		window.setScene(scene);
		window.showAndWait();
			
	}
	public static void displayHelpMenu(String title) {
		
		Stage window =new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		
		window.setTitle(title);
		
		
		MakeText up = new MakeText("PRESS UP KEY TO MOVE UP!");
		MakeText down = new MakeText("PRESS DOWN KEY TO MOVE DOWN!");	
		MakeText left = new MakeText("PRESS LEFT KEY MOVE TO THE LEFT!");	
		MakeText right = new MakeText("PRESS RIGHT MOVE TO THE RIGHT!");
		MakeText shootUp = new MakeText("PRESS a TO SHOOT UPWARD!");
		MakeText shootDown = new MakeText("PRESS s TO SHOOT DOWNARD!");
		MakeText shootLeft = new MakeText("PRESS d TO SHOOT TO THE LEFT!");
		MakeText shootRight = new MakeText("PRESS f TO SHOOT TO THE RIGHT!");
		MakeText remind = new MakeText("press letters lowercased!");
			
		
		MakeButton gotIt = new MakeButton("GOT IT");
		gotIt.setOnAction(e -> {
			window.close();
		});
		
		VBox vb = new VBox(10);
		vb.getChildren().addAll(up, down, left, right, shootUp, shootDown, shootLeft, shootRight,remind, gotIt);
		vb.setAlignment(Pos.CENTER);
		vb.setStyle("-fx-background-color: Aquamarine; -fx-font-size: 2em; -fx-text-fill: #0000ff");	
		

		
		Scene scene = new Scene(vb, 1000, 700);
		window.setScene(scene);
		window.showAndWait();
	}
		
		
	}


