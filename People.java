package finalGame;
/*
 * Description: This is the Players Class where the Players are created.
 * Other Comments: Friendly Users, Dragon, Monsters are made here.
 * A color can be choose and they size can also be modified.
 * They rows and columns are saved in the x and y locations.
 */

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class People extends Rectangle{
	public String type;
	private int xLocation;
	private int yLocation;
	
	
	//The yLocation will represent the row for the 2d array.
	//The xLocation will represent the column for the 2d array.
	public People(int x, int y, int xLocation, int yLocation,  String type, Color color) {
		super(x, y, color);
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		this.type = type;
		
	}
	//This method will get the type of player they are. If they are User, Dragon, or a Monster.
	public String getType() {
		return type;
	}
	
	//These are getters and setters for the x and y locations.
	public int getXLocation() {
		return xLocation;
	}
	public int getYLocation() {
		return yLocation;
	}
	public void setXLocation(int num) {
		this.xLocation = num;
	}
	public void setYLocation(int num) {
		this.yLocation = num;
	}

}
