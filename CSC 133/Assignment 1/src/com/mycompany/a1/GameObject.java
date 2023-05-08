// Package
package com.mycompany.a1;

// Imports
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;

// GameObject class
public abstract class GameObject {

	// Size will keep track of the length of the bounding square
	private int size;
	
	// Location will keep track of where the object is located on the map
	private Point location;
	
	// Color will keep track of which color is being used for the object
	private int color;
	
	// Initiate a new random object
	static Random random = new Random();

	
	// Constructor which initializes all of the private variables with the values that were passed
	public GameObject(int color, int size, Point location) {

		// Set the private variables to that of what has been passed
		this.color = color;
		this.size = size;
		this.location = location;
		
	}
	
	

	// Creates a random point
	public static Point randomLocation() {
		
		// Returns a random point
		return new Point(random.nextFloat() * 1000, random.nextFloat() * 1000);
		
	}
	
	

	// Allow the ability to get the size of the GameObject
	public int getSize() {
		return size;
	}
	
	
	// Allow the ability to get each of the individual coordinates of the GameObject
	public float getX() {
		return location.getX();
	}

	public float getY() {
		return location.getY();
	}
	
	// Public method to have the ability to set the entire location to a new location
	public void setLocation(float x_coordinate, float y_coordinate) {
		location = new Point(x_coordinate, y_coordinate);
	}
	
	
	// Public method to have the ability to get the color of the GameObject
	public int getColor() {
		return color;
	}

	// Public method to have the ability to set the color of the GameObject
	public void setColor(int color) {
		this.color = color;
	}

	// Public method to have the ability to display the color of the GameObject
	public String colorToString() {

		// Return a string containing the specific amount of red, green, and blue in the color of the GameObject
		return "[" + ColorUtil.red(color) + ", " + ColorUtil.green(color) + ", " + ColorUtil.blue(color) + ']';

	}

}
