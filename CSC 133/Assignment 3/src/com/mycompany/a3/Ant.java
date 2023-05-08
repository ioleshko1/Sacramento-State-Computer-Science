package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Ant extends Movable implements ISteerable {
	
	// The maximum speed that an ant can go.  Attempts to accelerate the ant beyond this will be ignored
	private int maximumSpeed = 150;
	
	// Determines how hungry the ant is.  If the ant food level is zero, it cannot move
	private int foodLevel = 5000;
	
	// Determines how much food the ant needs to consume each time the clock ticks
	private int foodConsumptionRate = 1;
	
	// Determines what the health of an ant is.  Looses health when the ant collides with a spider
	private int healthLevel = 10;
	
	// Indicates the sequence number of the last flag that the ant has reached in the increasing order.
	private int lastFlagReached = 1;
	
	// Create a private gw object to be able to call methods from
	private GameWorld gw;
	
	// Create an instance of the Ant object for the Singleton design pattern
	private static Ant ant;
	
	
	
	// This is the private Ant constructor which has been passed a location and helps assign values to all of the fields
	private Ant(Point location, GameWorld gw) {
		
		// Create the ant by passing the information to the parent class
		super(ColorUtil.rgb(255, 0, 0), 50, location, 0, 100, gw);
		
		// Initialize the gw object
		this.gw = gw;
		
	}
	
	
	// This method allows the client to create a new ant if no ant currently exists but does not create more ants if one already exists
	public static Ant getAnt(Point location, GameWorld gw) {
		
		// Check to see if an ant object already exists
		if(ant == null) {
			
			// In not then we can create the ant object using the private constructor and the location that was passed
			ant = new Ant(location, gw);
			
		}
		
		// Return either the new ant or the existing ant
		return ant;
		
	}
	
	
	// This method allows the client to destroy the current ant object so a new one can be created
	public static void destroyAnt() {
		
		// Set the value of the ant to null
		ant = null;
		
	}


	
	// This toString() method displays the important values of this class - DONE
	@Override
	public String toString() {
		
		// Create the returnStatement by combining the correct values using get methods
		String returnStatement = "Ant: loc=" + Math.round(getX() * 10.0) / 10.0 + "," + Math.round(getY() * 10.0) / 10.0 + 
								 " color=" + colorToString() + 
								 " heading=" + getHeading() + 
								 " speed=" + getSpeed() + 								 
								 " size=" + getSize() + 
								 " maxSpeed=" + getMaximumSpeed() +
								 " foodConsumptionRate=" + getFoodConsumptionRate();
		
		// Return the concatenated values
		return returnStatement;
		
	}

	// Method which is called to modify the speed to accelerate the ant - DONE
	public void accelerate() {
		
		// Get the speed of the ant
		int speed = getSpeed();
				
		// Increase the speed of the ant if speed < maximumSpeed
		if (speed < maximumSpeed) {
			
			// Display a message header
			System.out.println("--------------------");
			System.out.println("Ant is accelerating.");
			System.out.println("--------------------");
			
			// Update the maximumSpeed by adding 5
			setSpeed(speed + 5);
			
		} else {
			
			// Display a message header
			System.out.println("--------------------------");
			System.out.println("Ant has reached max speed.");
			System.out.println("--------------------------");
			
		}
		
	}

	// Method which is called to modify the speed to brake the ant - DONE
	public void brake() {
		
		// Get the speed of the ant
		int speed = getSpeed();
		
		// Decrease the speed of the ant if speed > 0
		if (speed > 0) {
			
			// Display a message header
			System.out.println("---------------");
			System.out.println("Ant is braking.");
			System.out.println("---------------");
			
			// Update the maximumSpeed by subtracting 5
			setSpeed(speed - 5);
			
		} else {
			
			// Display a message header
			System.out.println("--------------------------");
			System.out.println("Ant has reached min speed.");
			System.out.println("--------------------------");
			
		}
		
	}
	
	// Method which is called steer to modify the heading of the ant by a given amount - DONE
	@Override
	public void steer(int amount) {

		// Update the heading by amount
		setHeading(getHeading() + amount);
		
	}
	
	// Check the collision that occurred between the ant and a spider - DONE
	public void collisionSpider() {

		// Display a message header
		System.out.println("-------------------------------");
		System.out.println("Ant has collided with a spider.");
		System.out.println("-------------------------------");
		
		
		// Update the healthLevel to subtract 1
		healthLevel -= 1;
		
		//Decrease the maximumSpeed by 5 every time a spider collides with the ant
		maximumSpeed -= 5;
		
		// Update the color of the ant to make it a lighter red color
		setColor(ColorUtil.rgb(ColorUtil.red(getColor()) - 5, 0, 0));
		
		// Check to see if the current speed of the ant is more than the maximumSpeed that it should be allowed to reach
		if (getSpeed() > maximumSpeed) {
		
			// Set the speed to the max speed
			setSpeed(maximumSpeed);				
		
		}		
		
	}
	
	// Check the collision that occurred between the ant and a flag - DONE
	public boolean checkFlagCollision(int sequenceNum) {
		
		// Check to see if the flag that the ant collided with is one greater than the last flag that the ant has reached
		if (lastFlagReached + 1 == sequenceNum) {
			
			// Display a message header
			System.out.println("-----------------------------");
			System.out.println("Ant has collided with flag " + sequenceNum + ".");
			System.out.println("-----------------------------");
			
			// Update the last flag reached
			setLastFlagReached(sequenceNum);
			
			// Check to see if the ant has reached the last flag
			if (getLastFlagReached() == 4) {
				
				// Print the congratulations message
				System.out.println("------------------------------------");
				System.out.println("Game over, you win!  Total time: #" + gw.getTimer());
				System.out.println("------------------------------------");
				
				// Exit the game
				System.exit(0);
				
			}
			
			// Return that the flag was update
			return true;
			
		} else {
			
			// Display a message header
			System.out.println("---------------------------------------------");
			System.out.println("Please reach the flags in a sequential order.");
			System.out.println("---------------------------------------------");
			
			// Return that the flag was not update
			return false;
			
		}
		
	}
	
	// Check to see what should happen with the ant according to the foodLevel - DONE
	public void modifyFoodLevel(int width, int height, int tickRate) {
		
		// Check to see if the foodLevel is above 0
		if (foodLevel > 0) {
			
			// Lessen the foodLevel by the consumption amount
			setFoodLevel(foodLevel - foodConsumptionRate);
			
			// Move the ant
			move(tickRate);
			
			// Check the boundary to see if the ant has crossed it yet
			checkBoundary(width, height, tickRate);
			
		}
		
	}
	
	
	
	// This method is used to draw the ant
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		// Store the size so we can use it to draw the circle
		int objectSize = this.getSize();
		
		// Using this we can get the coordinates of the object while using pCmpRelPrnt we can get the coordinates of the parent container
		int objectX = (int) (this.getX() + pCmpRelPrnt.getX() - objectSize/2);
		int objectY = (int) (this.getY() + pCmpRelPrnt.getY() - objectSize/2);
		
		
		// Change the color of the object to that of the object that is being drawn
		g.setColor(super.getColor());
		
		// Draw the filled circle using the x-coordinate, y-coordinate, and the sizes of the objects
		g.fillArc(objectX, objectY, objectSize, objectSize, 0, 360);
		
	}
	
	
	
	
	
		

	// Allow the ability to get the maximumSpeed private variable using a method
	public int getMaximumSpeed() {
		return maximumSpeed;
	}
	
	// Allow the ability to set the maximumSpeed private variable using a method
	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}
	
	// Allow the ability to get the foodLevel private variable using a method
	public int getFoodLevel() {
		return foodLevel;
	}
	
	// Allow the ability to set the foodLevel private variable using a method
	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}
	
	// Allow the ability to get the foodConsumptionRate private variable using a method
	public int getFoodConsumptionRate() {
		return foodConsumptionRate;
	}

	// Allow the ability to set the foodConsumptionRate private variable using a method
	public void setFoodConsumptionRate(int foodConsumptionRate) {
		this.foodConsumptionRate = foodConsumptionRate;
	}

	// Allow the ability to get the healthLevel private variable using a method
	public int getHealthLevel() {
		return healthLevel;
	}

	// Allow the ability to set the healthLevel private variable using a method
	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
	}

	// Allow the ability to get the lastFlagReached private variable using a method
	public int getLastFlagReached() {
		return lastFlagReached;
	}

	// Allow the ability to set the lastFlagReached private variable using a method
	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}
	
}
