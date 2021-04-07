package cs2012final;
/*
 * Description: In TheGame Class all the game elements are created.
 * Other Comments: The player is represented as a blue rectangle and they have to traverse the caves and kill
 * the dragon while avoiding monsters that steal your ammo and deadly traps that kill you automatically.
 * If the player believes they can kill the enemy the may chooses to shoot, if they miss they lose ammo, but if they
 * aim correctly, they kill the dragon and win.
 * Also, at every direction the user is alerted for any potential hazards even right before they start the game.
 */

import java.util.Random;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class TheGame {
	private final int windowX = 800;
	private final int windowY = 700;
	
	private Stage stage = new Stage();
	private Stage menuStage;
	private Scene scene;
	private GridPane grid = new GridPane();
	private Rectangle gridCells;
	private People[][] thePeople;
	
	private Random number = new Random();
	private int xSize;
	private int ySize;
	private int countClick = 2;
	private int rows;
	private int column;
	private int xPlayerLocation;
	private int yPlayerLocation;
	
	private MakeButton debug = new MakeButton("DEBUG");
	private MakeButton help = new MakeButton("HELP");
	private MakeButton exit = new MakeButton("EXIT");
	private MakeButton back = new MakeButton("BACK");
	
	private MakeText ammoLabel;
	private int ammo = 3;

	private People user;
	private People dragon;
	private Traps makeTrap;
	private People makeMonsters;
	private Traps ammoPickUp;
	private int ammoLblrow;
	private int ammoLblcol;
	
	public TheGame(Stage firstStage, int width, int height) {
		this.menuStage = firstStage;
		menuStage.hide();
		
		makeGrid(width, height);	
		
		grid.setAlignment(Pos.CENTER);
		
		this.rows = height - 1;
		this.column = width - 1;
		makeAmmoLabel(ammo);
		grid.setStyle("-fx-background-color: LightBlue; -fx-font-size: 2em; -fx-text-fill: #0000ff");	
		
		//2D array that holds all of the Monsters, Players, Traps, and Dragon.
		thePeople = new People[height][width];
		
		makePlayers();
		getPlayerLocation();
		
		scene = new Scene(grid, 1100, 775);
		
		showGameElements(thePeople);
		createButtons();
		
		//Checks for the Dangers nearby
		checkTheDanger(thePeople[yPlayerLocation][xPlayerLocation]);
		
		//Allows the player to move up, down, left, and eight with the arrow keys.
		playerMovement(scene, thePeople[yPlayerLocation][xPlayerLocation]);
			
		stage.setScene(scene);
		stage.show();
	}
	
	
	//The Game elements are made here. Such as: players, traps, monsters, and dragon.
	private void makePlayers() {
		for (int i = 0; i < 11; i++) {
			if(i < 4) {
			int p = 0;
			while(p == 0) {
				int xCord = number.nextInt(column+1);
				int yCord = number.nextInt(rows+1);
				if(thePeople[yCord][xCord] == null) {
					int xx= yCord; int yy = xCord;
					makeMonsters = new People(40, 40, xx, yy, "Monster", Color.HOTPINK);
					makeMonsters.setTranslateX(xSize/3);
					makeMonsters.setVisible(false);
					thePeople[yCord][xCord] = makeMonsters;
					p++;
				}
			}		
		}
			if(i == 4) {
				//Make the player here
				int p = 0;
				while(p == 0) {
					int xCord = number.nextInt(column+1);
					int yCord = number.nextInt(rows+1);
					
					if(thePeople[yCord][xCord] == null) {	
						int xxxx= yCord; int yyyy = xCord;
						user = new People(45, 45, xxxx, yyyy, "User", Color.BLUE);	
						user.setTranslateX(xSize/3);
						thePeople[yCord][xCord] = user;
						p++;	
					}
				}		
			}
			if(i > 4 && i < 8) {
				int p = 0;
				while(p == 0) {	
					int xCord = number.nextInt(column+1);
					int yCord = number.nextInt(rows+1);	
					if(thePeople[yCord][xCord] == null) {
						int xx= yCord; int yy = xCord;
						makeTrap = new Traps(25, 25, xx, yy, "Trap", Color.GREEN);
						makeTrap.setTranslateX(xSize/3);
						makeTrap.setVisible(false);
						thePeople[yCord][xCord] = makeTrap;
						p++;
					}
				}		
				}
			if(i == 8) {
				int p = 0;
				while(p == 0) {	
					int xCord = number.nextInt(column+1);
					int yCord = number.nextInt(rows+1);	
					if(thePeople[yCord][xCord] == null) {
						int xx= yCord; int yy = xCord;
						dragon = new People(50, 50, xx, yy, "Dragon", Color.RED);
						dragon.setTranslateX(xSize/3);
						dragon.setVisible(false);
						thePeople[yCord][xCord] = dragon;
						p++;
					}
				}		
				}
			if(i > 8 && i < 11) {
				int p = 0;
				while(p == 0) {	
					int xCord = number.nextInt(column+1);
					int yCord = number.nextInt(rows+1);	
					if(thePeople[yCord][xCord] == null) {
						int xx= yCord; int yy = xCord;
						ammoPickUp = new Traps(20, 20, xx, yy, "Ammo", Color.ORANGE);
						ammoPickUp.setTranslateX(xSize/3);
						ammoPickUp.setVisible(false);
						thePeople[yCord][xCord] = ammoPickUp;
						p++;
					}
				}			
			}
			}
		}
	
	//The Game elements are shown here
	private void showGameElements(People[][] array) {	
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if(array[i][j] != null) {
					grid.add(array[i][j], j, i);
				}
				
			}	
		}	
	}
	//This methods checks if their is danger nearby, by checking above the tile, below 
	//the tile, left of the tile, and to the right of the tile
	private void checkTheDanger(People people) {
		int xSpot = people.getYLocation();
		int ySpot = people.getXLocation();
	
		if(xSpot!=0) {
			if(thePeople[ySpot][xSpot-1] != null) {
				playerMessages(thePeople[ySpot][xSpot-1]);	
			}
		}
		if(xSpot!=column) {
			if(thePeople[ySpot][xSpot+1] != null) {
				playerMessages(thePeople[ySpot][xSpot+1]);	
			}
		}
		
		if(ySpot != 0) {
			if(thePeople[ySpot - 1][xSpot] != null) {
				playerMessages(thePeople[ySpot - 1][xSpot]);	
			}
		}
		if(ySpot != rows) {
			if(thePeople[ySpot + 1][xSpot] != null) {
				playerMessages(thePeople[ySpot + 1][xSpot]);
			}
		}
		//if element is 0
		if(xSpot == 0) {
			if(thePeople[ySpot][xSpot+1] != null) {
				playerMessages(thePeople[ySpot][xSpot+1]);
			}
			if(ySpot == 0) {
				if(thePeople[ySpot][xSpot + 1] != null) {
					playerMessages(thePeople[ySpot][xSpot + 1]);
					
				}
			}
		
			//checks the left bottom corner
			if(ySpot == rows) {
				if(thePeople[ySpot][xSpot + 1] != null) {
					playerMessages(thePeople[ySpot][xSpot + 1]);
				
				}
				if(thePeople[ySpot - 1][xSpot] != null) {
					playerMessages(thePeople[ySpot - 1][xSpot]);
				}
			}	
		}
		//right side
		if(xSpot == column) {
			if(ySpot == 0) {
				//the upper right side
				if(thePeople[ySpot][xSpot - 1] != null) {
					playerMessages(thePeople[ySpot][xSpot - 1]);
					
				}
				if(thePeople[ySpot+1][xSpot] != null) {
					playerMessages(thePeople[ySpot+1][xSpot]);	
				}
			}
			//the right bottom corner
			if(ySpot == rows) {
				if(thePeople[ySpot][xSpot-1] != null) {
					playerMessages(thePeople[ySpot][xSpot-1]);
				}
				if(thePeople[ySpot-1][xSpot] != null) {
					playerMessages(thePeople[ySpot-1][xSpot]);
				}
			}
		}
		if(ySpot == rows) {
			if(xSpot != 0) {
				if(thePeople[ySpot][xSpot - 1] != null) {
					playerMessages(thePeople[ySpot][xSpot - 1]);
				}
			}
			if(xSpot != column) {
				if(thePeople[ySpot][xSpot + 1] != null) {
					playerMessages(thePeople[ySpot][xSpot + 1]);	
				}	
			}
			if(thePeople[ySpot-1][xSpot] != null) {
				playerMessages(thePeople[ySpot-1][xSpot]);	
			}
		}
		if(ySpot == 0) {
			if(thePeople[ySpot+1][xSpot] != null) {
				playerMessages(thePeople[ySpot+1][xSpot]);	
			}
			if(xSpot!=0) {
				if(thePeople[ySpot][xSpot - 1] != null) {
					playerMessages(thePeople[ySpot][xSpot - 1]);
					
				}
				if(xSpot != column) {
					if(thePeople[ySpot][xSpot + 1] != null) {
						playerMessages(thePeople[ySpot][xSpot + 1]);
						
					}
				}
			}
		}
	}
		
	//Returns the player x and y coordinates initially 	
	private void getPlayerLocation() {
		for (int i = 0; i < thePeople.length; i++) {
			for (int j = 0; j < thePeople[i].length; j++) {
				if(thePeople[i][j] != null && thePeople[i][j].getType().equals("User")) {
					xPlayerLocation = j;
					yPlayerLocation = i;
				}
				
			}
		}
			
	}
	//Gets the stage 
	public Stage getStage() {
		return stage;
	}
	
	//This method shows the hazard notifications
	private void playerMessages(People people) {
		if(people.getType().equals("Trap")) {
			AlertBox.display("I Feel something springy");
		}
		if(people.getType().equals("Dragon")) {
			AlertBox.display("The Devil may be near");
		}
		if(people.getType().equals("Monster")) {
			AlertBox.display("Don't lose anything!");
		}
		if(people.getType().equals("Ammo")) {
			AlertBox.display("A sweet treat can be nearby");
		}
			
	}
	
	//Checks the state of ammo of the player
	private void checkAmmo(int num) {
		if(ammo == 0) {
			AlertBox.youLose(stage);
		}
		
	}
	
	//In this Method Player Movement is performed
	//Also shooting system is implemented here using the keys (a,s,d,f)
	//a = shoot upward
	//s = shoot downward
	//d = shoot to the left
	//f = shoot to the right
	private void playerMovement(Scene scene, People thePeople) {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		      @Override public void handle(KeyEvent event) {
		        switch (event.getCode()) {
		          case RIGHT:  
		        	  moveToRight(thePeople);
		          	break;
		          case UP: 
		        	  moveUp(thePeople);
		        	  break;
		          case DOWN:  
		        	  moveDown(thePeople);
		        	  break;
		          case LEFT:  
		        	  moveToLeft(thePeople);
		        	  break;
		          case A:
		        	 shoot(thePeople, 1);
		        	 checkAmmo(ammo);
		        	  break;
		          case S:
		        	 shoot(thePeople, 2);
		        	 checkAmmo(ammo);
		        	  break;
		          case D:
		        	  shoot(thePeople, 3);
		        	  checkAmmo(ammo);
		        	  break;
		          case F:
		        	  shoot(thePeople, 4);
		        	  checkAmmo(ammo);
		        	  break;
				default:
					break;
		        	  
		        }
		      }
		    });
	}

	//The player can shoot with the provided key.
	private void shoot(People people, int num) {
		int xSpot = people.getYLocation();
		int ySpot = people.getXLocation();
		
		//Checks for the user shooting up
		if(ySpot != 0 && num == 1) {
			if(thePeople[ySpot - 1][xSpot] != null) {
				if(thePeople[ySpot - 1][xSpot] == thePeople[dragon.getXLocation()][dragon.getYLocation()]) {
					AlertBox.youWin(stage);
					}
			}
			if((thePeople[ySpot - 1][xSpot] != thePeople[dragon.getXLocation()][dragon.getYLocation()]) || (thePeople[ySpot - 1][xSpot] == null)) {
					ammo--;
					grid.getChildren().remove(ammoLabel);
					makeAmmoLabel(ammo);
				}
		}
		if(ySpot == 0 && num == 1) {
			ammo--;
			grid.getChildren().remove(ammoLabel);
			makeAmmoLabel(ammo);	
		}
			
		//Checks for the user shooting down
		if(ySpot != rows && num == 2) {
			if(thePeople[ySpot + 1][xSpot] != null) {
				if(thePeople[ySpot + 1][xSpot] == thePeople[dragon.getXLocation()][dragon.getYLocation()]) {
					AlertBox.youWin(stage);
				}	
			}
				if((thePeople[ySpot + 1][xSpot] != thePeople[dragon.getXLocation()][dragon.getYLocation()]) || thePeople[ySpot + 1][xSpot] == null) {
					ammo--;
					grid.getChildren().remove(ammoLabel);
					makeAmmoLabel(ammo);
				}		
		}
		if(ySpot == rows && num == 2) {
			ammo--;
			grid.getChildren().remove(ammoLabel);
			makeAmmoLabel(ammo);	
		}
		
		//Checks for the user shooting towards the left
		if(xSpot != 0 && num == 3) {
			if(thePeople[ySpot][xSpot - 1] != null) {
				if(thePeople[ySpot][xSpot - 1] == thePeople[dragon.getXLocation()][dragon.getYLocation()]) {
					AlertBox.youWin(stage);
					}	
				}
				if((thePeople[ySpot][xSpot - 1] != thePeople[dragon.getXLocation()][dragon.getYLocation()]) || thePeople[ySpot][xSpot - 1] == null) {
					ammo--;
					grid.getChildren().remove(ammoLabel);
					makeAmmoLabel(ammo);
				}		
		}
		if(xSpot == 0 && num == 3) {
			ammo--;
			grid.getChildren().remove(ammoLabel);
			makeAmmoLabel(ammo);	
		}	
		//Checks when the user shoots toward the right
		if(xSpot != rows && num == 4) {
			if(xSpot != 0) {
				if(thePeople[ySpot][xSpot + 1 ] != null) {
					if(thePeople[ySpot][xSpot + 1] == thePeople[dragon.getXLocation()][dragon.getYLocation()]) {
						AlertBox.youWin(stage);
					}	
					if(thePeople[ySpot][xSpot + 1] != thePeople[dragon.getXLocation()][dragon.getYLocation()] || thePeople[ySpot][xSpot + 1] == null) {
						ammo--;
						grid.getChildren().remove(ammoLabel);
						makeAmmoLabel(ammo);
					}
				}	
			}
		}
		if(xSpot == rows && num == 4) {
			ammo--;
			grid.getChildren().remove(ammoLabel);
			makeAmmoLabel(ammo);	
		}
	}
	//This method checks if the player walks into a room with ammo.
	private void isItAmmo(People people, int num) {
		int xSpot = people.getYLocation();
		int ySpot = people.getXLocation();
		

		if(ySpot != 0 && num == 1) {
			if(thePeople[ySpot - 1][xSpot] != null) {
				if(thePeople[ySpot - 1][xSpot].getType().equals("Ammo")) {
					grid.getChildren().remove(thePeople[ySpot - 1][xSpot]);
					ammo++;
					grid.getChildren().remove(ammoLabel);
					makeAmmoLabel(ammo);	
				}
			}
		}
		if(ySpot != rows && num == 2) {
			if(thePeople[ySpot + 1][xSpot] != null) {
				if(thePeople[ySpot + 1][xSpot].getType().equals("Ammo")) {
					grid.getChildren().remove(thePeople[ySpot + 1][xSpot]);
					ammo++;
					grid.getChildren().remove(ammoLabel);
					makeAmmoLabel(ammo);
				}	
			}		
		}
		if(xSpot != 0 && num == 3) {
			if(thePeople[ySpot][xSpot - 1] != null) {
				if(thePeople[ySpot][xSpot - 1].getType().equals("Ammo")) {
					grid.getChildren().remove(thePeople[ySpot][xSpot - 1]);
					ammo++;
					grid.getChildren().remove(ammoLabel);
					makeAmmoLabel(ammo);	
				}	
			}		
		}
		if(xSpot != rows && num == 4) {
			if(xSpot != 0) {
				if(thePeople[ySpot][xSpot + 1 ] != null) {
					if(thePeople[ySpot][xSpot + 1].getType().equals("Ammo")) {
						grid.getChildren().remove(thePeople[ySpot][xSpot + 1]);
						ammo++;
						grid.getChildren().remove(ammoLabel);
						makeAmmoLabel(ammo);	
					}	
					}				
				}
			}			
	}
	// This method will check if the player walks into a room with a monster, if they do
	// walk in the room with a monster they loose one ammo.
	private void isMonster(People people, int num) {
		int xSpot = people.getYLocation();
		int ySpot = people.getXLocation();
		

		if(ySpot != 0 && num == 1) {
			if(thePeople[ySpot - 1][xSpot] != null) {
				if(thePeople[ySpot - 1][xSpot].getType().equals("Monster")) {
					ammo--;
					grid.getChildren().remove(ammoLabel);
					makeAmmoLabel(ammo);	
				}
			}
		}
		if(ySpot != rows && num == 2) {
			
			if(thePeople[ySpot + 1][xSpot] != null) {
				if(thePeople[ySpot + 1][xSpot].getType().equals("Monster")) {
					ammo--;
					grid.getChildren().remove(ammoLabel);
					makeAmmoLabel(ammo);	
				}	
			}		
		}
		if(xSpot != 0 && num == 3) {
			if(thePeople[ySpot][xSpot - 1] != null) {
				if(thePeople[ySpot][xSpot - 1].getType().equals("Monster")) {
					ammo--;
					grid.getChildren().remove(ammoLabel);
					makeAmmoLabel(ammo);	
				}	
			}		
		}
		if(xSpot != rows && num == 4) {
			if(xSpot != 0) {
				if(thePeople[ySpot][xSpot + 1 ] != null) {
					if(thePeople[ySpot][xSpot + 1].getType().equals("Monster")) {
						ammo--;
						grid.getChildren().remove(ammoLabel);
						makeAmmoLabel(ammo);	
					}	
				}				
			}
			}		
	}
	
	// This Method Checks if their a Dragon Nearby
	// If the player walks into a room with the dragon they automatically die.
	private void isDragon(People people, int num) {
		int xSpot = people.getYLocation();
		int ySpot = people.getXLocation();
		

		if(ySpot != 0 && num == 1) {
			if(thePeople[ySpot - 1][xSpot] != null) {
				if(thePeople[ySpot - 1][xSpot] == thePeople[dragon.getXLocation()][dragon.getYLocation()]) {
					AlertBox.youLose(stage);
			}
			}
		}
		if(ySpot != rows && num == 2) {
			
			if(thePeople[ySpot + 1][xSpot] != null) {
				if(thePeople[ySpot + 1][xSpot] == thePeople[dragon.getXLocation()][dragon.getYLocation()]) {
					AlertBox.youLose(stage);
				}	
			}		
		}
		if(xSpot != 0 && num == 3) {
			if(thePeople[ySpot][xSpot - 1] != null) {
				if(thePeople[ySpot][xSpot - 1] == thePeople[dragon.getXLocation()][dragon.getYLocation()]) {
					AlertBox.youLose(stage);
				}	
			}		
		}
		if(xSpot != rows && num == 4) {
			if(xSpot != 0) {
				if(thePeople[ySpot][xSpot + 1 ] != null) {
					if(thePeople[ySpot][xSpot + 1] == thePeople[dragon.getXLocation()][dragon.getYLocation()]) {
						AlertBox.youLose(stage);
					}	
				}				
			}
		}		
	}
	//This method checks if the user walks into a room with a trap, if they do they automatically die.
	private void isTrap(People people, int num) {
		int xSpot = people.getYLocation();
		int ySpot = people.getXLocation();
		
		
		if(ySpot != 0 && num == 1) {
			if(thePeople[ySpot - 1][xSpot] != null) {
			
				if(thePeople[ySpot - 1][xSpot].getType().equals("Trap")) {
				AlertBox.youLose(stage);	
			}
		}
		}
		if(ySpot != rows && num == 2) {
			
			if(thePeople[ySpot + 1][xSpot] != null) {
				if(thePeople[ySpot + 1][xSpot].getType().equals("Trap")) {
					AlertBox.youLose(stage);
				}	
			}		
		}
		if(xSpot != 0 && num == 3) {
			if(thePeople[ySpot][xSpot - 1 ] != null) {
				if(thePeople[ySpot][xSpot - 1].getType().equals("Trap")) {
					AlertBox.youLose(stage);
				}	
			}			
		}
		if(xSpot != rows && num == 4) {
			if(xSpot != 0) {
				if(thePeople[ySpot][xSpot + 1 ] != null) {
					if(thePeople[ySpot][xSpot + 1].getType().equals("Trap")) {
						AlertBox.youLose(stage);	
					}	
				}				
			}
		}	
	}
	
	//Player Movement to the right.
	private void moveToRight(People people) {
		int z = people.getXLocation(); int r = people.getYLocation();
		if (people.getYLocation() == column) {
			
		}
		else {	
		isTrap(people, 4);
		isDragon(people, 4);
		isMonster(people, 4);
		isItAmmo(people, 4);
		grid.getChildren().remove(people);
		thePeople[z][r] = null;
		thePeople[z][r+1]= people;
		people.setYLocation(r+1);
		grid.add(people, r+1  , z);
		yPlayerLocation = r+1;
		checkTheDanger(people);
		checkAmmo(ammo);
		
		
		}
	}
	//Player Movement to the left.
	private void moveToLeft(People people) {
		int z = people.getXLocation(); int r = people.getYLocation();
		if (people.getYLocation() == 0) {
			
		}	
		else {
			isTrap(people, 3);
			isDragon(people, 3);
			isMonster(people, 3);
			isItAmmo(people, 3);
			grid.getChildren().remove(people);
			thePeople[z][r] = null;
			thePeople[z][r-1]= people;
			people.setYLocation(r-1);
			grid.add(people, r-1  , z);
			yPlayerLocation = r-1;
			checkTheDanger(people);
			checkAmmo(ammo);
		}
	}
	//Player Movement up.
	private void moveUp(People people) {
		int z = people.getXLocation(); int r = people.getYLocation();
		if(people.getXLocation() == 0) {
			
		}
		else {
			isTrap(people, 1);
			isDragon(people, 1);
			isMonster(people, 1);
			isItAmmo(people, 1);
			
			grid.getChildren().remove(people);
			thePeople[z][r] = null;
			thePeople[z-1][r]= people;
			people.setXLocation(z-1);
			grid.add(people, r  , z-1);
			
			xPlayerLocation = z-1;
			checkTheDanger(people);
			checkAmmo(ammo);
		}
	}
	//Player Movement down.
	private void moveDown(People people) {
		int z = people.getXLocation(); int r = people.getYLocation();
		if(people.getXLocation() == rows) {
			
		}
		else {
			isTrap(people, 2);
			isDragon(people, 2);
			isMonster(people, 2);
			isItAmmo(people, 2);
			grid.getChildren().remove(people);
			thePeople[z][r] = null;
			thePeople[z+1][r]= people;
			people.setXLocation(z+1);
			grid.add(people, r  , z+1);
			xPlayerLocation = z+1;
			checkTheDanger(people);
			checkAmmo(ammo);
		}
	}
		
	//Buttons are created here.
	private void createButtons() {
		exitButton();
		helpButton();
		debugModeButton();
		createBackButton();
	}
	//The AmmoLabel will show the amount of ammo the user has.
	private void makeAmmoLabel(int theAmmo) {
		ammoLabel = new MakeText("AMMO: " + theAmmo);
		ammoLabel.setTranslateX(xSize/3);
		if(rows == 4) {
			ammoLblcol = column + 1;
			ammoLblrow = rows-(rows - 0);
			grid.add(ammoLabel, ammoLblcol, ammoLblrow);
		}
		else if(rows == 6) {
			ammoLblcol = column + 1;
			ammoLblrow = rows-(rows - 1);
		grid.add(ammoLabel, ammoLblcol, ammoLblrow);
		}
		else {
			ammoLblcol = column + 10;
			ammoLblrow = rows-(rows - 1);
			grid.add(ammoLabel, ammoLblcol, ammoLblrow);
		}	
	}
	//In the Exit Button, the program terminates if chosen.
	private void exitButton() {
		exit.setFocusTraversable(false);
		exit.setTranslateX(xSize/6);
		exit.setOnAction(e -> {
			stage.close();
		});
		if(rows == 4) {
			grid.add(exit, column + 1, rows-(rows -4));
		}
		else if(rows == 6) {
		grid.add(exit, column + 1, rows-(rows -4));
		}
		else {
			grid.add(exit, column+ 4, rows-(rows -4));
		}
		
	}
	//If the back button is pressed the user is returned to the grid selector menu
	private void createBackButton() {
		back.setFocusTraversable(false);
		back.setTranslateX(xSize/6);
		back.setOnAction(e -> {
			
			stage.close();
			this.menuStage.show();
			
		});
		if(rows == 4) {
			grid.add(back, column + 1, rows-(rows -3));
		}
		else if(rows == 6) {
		grid.add(back, column + 1, rows-(rows -5));
		}
		else {
			grid.add(back, column+ 4, rows-(rows -5));
		}
	}
	//The Degub Mode is a button that when pressed the game elements are revealed.
	//When the button is then pressed gain the elements hide again.
	private void debugModeButton() {
		debug.setFocusTraversable(false);
		debug.setTranslateX(xSize/6);
		debug.setOnAction(e -> {
			if(countClick % 2 == 0 ) {	
			for (int i = 0; i < thePeople.length; i++) {
				for (int j = 0; j < thePeople[i].length; j++) {
					if(thePeople[i][j] != null) {
						if(thePeople[i][j].getType().equals("Monster")) {
						thePeople[i][j].setVisible(true);
					}
						if(thePeople[i][j].getType().equals("Trap")) {
							thePeople[i][j].setVisible(true);
						}
						if(thePeople[i][j].getType().equals("Dragon")) {
							thePeople[i][j].setVisible(true);
						}
						if(thePeople[i][j].getType().equals("Ammo")) {
							thePeople[i][j].setVisible(true);
						}
						}
						}
						}
						countClick++;
					}
			else {
				for (int i = 0; i < thePeople.length; i++) {
					for (int j = 0; j < thePeople[i].length; j++) {
						if(thePeople[i][j] != null) {
							if(thePeople[i][j].getType().equals("Monster")) {
							thePeople[i][j].setVisible(false);
							}	
							if(thePeople[i][j].getType().equals("Trap")) {
								thePeople[i][j].setVisible(false);
							}
							if(thePeople[i][j].getType().equals("Dragon")) {
								thePeople[i][j].setVisible(false);
							}
							if(thePeople[i][j].getType().equals("Ammo")) {
								thePeople[i][j].setVisible(false);
							}
							}
							}
							}	
							countClick++;	
							}	
					});	
		if(rows == 4) {
			grid.add(debug, column + 1, rows-(rows - 1));
		}
		else if(rows == 6) {
		grid.add(debug, column + 1, rows-(rows -3));
		}
		else {
			grid.add(debug, column+ 4, rows-(rows -3));
		}
	}
	private void helpButton() {
		help.setFocusTraversable(false);
		help.setTranslateX(xSize/6);
		help.setOnAction(e -> {	
			AlertBox.displayHelpMenu("Help");
		});
		if(rows == 4) {
			grid.add(help, column + 1, rows-(rows -2));
		}
		else if(rows == 6){
		grid.add(help, column + 1, rows-(rows -6));
		}
		else {
			grid.add(help, column+ 4, rows-(rows -6));
		}
	}
		
	
	
	//Creates the Grid given the size x and y
	private void makeGrid(int x, int y) {
		xSize = windowX / x;
		ySize = windowY / y;
		
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				gridCells = new Rectangle(xSize, ySize);
				gridCells.setFill(Color.LIGHTBLUE.darker());
				gridCells.setStroke(Color.BLACK);
				grid.add(gridCells, i, j);
				
			}
		}
	}
}

