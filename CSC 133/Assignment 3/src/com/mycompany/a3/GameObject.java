package com.mycompany.a3;

// Imports
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

import java.util.ArrayList;
import java.util.Random;

// GameObject class
public abstract class GameObject implements IDrawable, ICollider {

	// Size will keep track of the length of the bounding square
	private int size;
	
	// Location will keep track of where the object is located on the map
	private Point location;
	
	// Color will keep track of which color is being used for the object
	private int color;
	
	// Create a private gw object to be able to call methods from
	private GameWorld gw;
	
	// The collisionList ArrayList contains all of the gameObjects that have collided with the Ant
	private ArrayList<GameObject> collisionList;
	
	// Initiate a new random object
	static Random random = new Random();

	
	// Constructor which initializes all of the private variables with the values that were passed
	public GameObject(int color, int size, Point location, GameWorld gw) {

		// Set the private variables to that of what has been passed
		this.color = color;
		this.size = size;
		this.location = location;
		
		// Initialize the gw object
		this.gw = gw;
		
		// Initialize the private variables to the necessary values
		collisionList = new ArrayList<GameObject>();
		
	}
	
	

	// Creates a random point
	public static Point randomLocation(int width, int height) {
		
		// Returns a random point
		return new Point(random.nextFloat() * width, random.nextFloat() * height);
		
	}
	
	
	
	
	// Method checks to see if an object (this) collides with another object (obj)
	@Override
	public boolean collidesWith(GameObject obj) {
		
		// Capture the x and y coordinates of the this and obj GameObjects
		double thisCenterX = this.getX(); // find centers
		double thisCenterY = this.getY();
		double otherCenterX = obj.getX();
		double otherCenterY = obj.getY();
		
		// Determine what the difference in x and y of each centers
		double dx = thisCenterX - otherCenterX;
		double dy = thisCenterY - otherCenterY;
		
		// Create a variable to keep track of the distance between the two object centers
		double distBetweenCentersSqr = (dx*dx + dy*dy);
		
		// Create two variable to keep track of the radii for both objects
		int thisRadius = this.getSize()/2;
		int otherRadius = obj.getSize()/2;
		
		// Find square of sum of the radii
		int radiiSqr = (thisRadius*thisRadius + 2*thisRadius*otherRadius + otherRadius*otherRadius);
		
		// Check to see if the distance between the centers is less than or equal to the square of sum of the radii
		if (distBetweenCentersSqr <= radiiSqr) {
			
			// If so then we return true meaning that there was a collision
			return true ;
			
		}
		
		// Otherwise we return false meaning that there was no collisions
		return false ;
		
	}
	
	
	
	// This method handles the collisions between the Ant and other objects
	@Override
	public void handleCollision(GameObject otherObject) {	
		
		// Check to see if the otherObject is in the collisionList
		if (!collisionList.contains(otherObject)) {
			
			// Check to see if the otherObject was a FoodStation
			if (this instanceof Ant && otherObject instanceof FoodStation) {
				
				// Capture the FoodStation object
				FoodStation food = (FoodStation) otherObject;
					
				// Check to see if the FoodStation is empty
				if (food.getCapacity() != 0) {
				
					// Call the method to collide the ant with the FoodStation
					gw.collisionFoodStation(food);
						
				}
						
			}
				
			// Check to see if the otherObject was a Flag
			else if (this instanceof Ant && otherObject instanceof Flag) {
				
				// Capture the Flag object
				Flag flag = (Flag) otherObject;
				
				// Call the method to collide the ant with the Flag
				gw.collisionFlag(flag.getSequenceNumber());
				
			}
			
			// Check to see if the otherObject was a Spider
			else if (this instanceof Ant && otherObject instanceof Spider) {
				
				// Call the method to collide the ant with the Spider
				gw.collisionSpider();
				
			}
			
			// Add the object to the arrayList of GameObjects that have collided with the Ant right now
			collisionList.add(otherObject);
			
		}
		
	}
	
	
	// This method allows us to remove an object from the collisionList
	public void removeObject (GameObject otherObject) {
		
		// Check to see if the otherObject is in the collisionList
		if (collisionList.contains(otherObject)) {
		
			// Remove the otherObject from the collision list
			collisionList.remove(otherObject);
			
		}
			
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
