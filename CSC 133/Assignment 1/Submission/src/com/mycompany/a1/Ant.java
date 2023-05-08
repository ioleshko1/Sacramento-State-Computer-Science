package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Ant extends Movable implements ISteerable {
	
	// The maximum speed that an ant can go.  Attempts to accelerate the ant beyond this will be ignored
	private int maximumSpeed = 20;
	
	// Determines how hungry the ant is.  If the ant food level is zero, it cannot move
	private int foodLevel = 50;
	
	// Determines how much food the ant needs to consume each time the clock ticks
	private int foodConsumptionRate = 2;
	
	// Determines what the health of an ant is.  Looses health when the ant collides with a spider
	private int healthLevel = 10;
	
	// Indicates the sequence number of the last flag that the ant has reached in the increasing order.
	private int lastFlagReached = 1;
	
	
	
	// This is the Ant constructor which has been passed a location and helps assign values to all of the fields
	public Ant(Point location) {
		
		// Set the color to red, the size to 20, and pass the location up to the constructor of the parent class
		super(ColorUtil.rgb(255, 0, 0), 20, location, 0, 10);
		
	}

	
	
	// This toString() method displays the important values of this class
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

	// Method which is called to modify the speed to accelerate the ant
	public void accelerate() {
		
		// Get the speed of the ant
		int speed = getSpeed();
				
		// Increase the speed of the ant if speed < maximumSpeed
		if (speed < getMaximumSpeed()) {
			
			// Display a message header
			System.out.println("--------------------");
			System.out.println("Ant is accelerating.");
			System.out.println("--------------------");
			
			// Update the maximumSpeed by adding 1
			setSpeed(speed + 1);
			
		} else {
			
			// Display a message header
			System.out.println("--------------------------");
			System.out.println("Ant has reached max speed.");
			System.out.println("--------------------------");
			
		}
		
	}

	// Method which is called to modify the speed to brake the ant
	public void brake() {
		
		// Get the speed of the ant
		int speed = getSpeed();
		
		// Decrease the speed of the ant if speed > 0
		if (speed > 0) {
			
			// Display a message header
			System.out.println("---------------");
			System.out.println("Ant is braking.");
			System.out.println("---------------");
			
			setSpeed(speed - 1);
			
		} else {
			
			// Display a message header
			System.out.println("--------------------------");
			System.out.println("Ant has reached min speed.");
			System.out.println("--------------------------");
			
		}
		
	}
	
	// Method which is called steer to modify the heading of the ant by a given amount
	@Override
	public void steer(int amount) {

		// Update the heading by amount
		setHeading(getHeading() + amount);
		
	}
	
	// Check the collision that occurred between the ant and a spider
	public void collisionSpider() {

		// Display a message header
		System.out.println("-------------------------------");
		System.out.println("Ant has collided with a spider.");
		System.out.println("-------------------------------");
		
		
		// Update the healthLevel to subtract 1
		setHealthLevel(getHealthLevel() - 1);
		
		//Decrease the maximumSpeed by 2 every time a spider collides with the ant (since health is 10 and maxSpeed is 20)
		setMaximumSpeed(getMaximumSpeed() - 2);
		
		// Update the color of the ant to make it a lighter red color
		setColor(ColorUtil.rgb(ColorUtil.red(getColor()) - 5, 0, 0));
		
		// Check to see if the current speed of the ant is more than the maximumSpeed that it should be allowed to reach
		if (getSpeed() > getMaximumSpeed()) {
		
			// Set the speed to the max speed
			setSpeed(getMaximumSpeed());				
		
		}		
		
	}
	
	// Check the collision that occurred between the ant and a flag
	public void checkFlagCollision(int sequenceNum) {
		
		// Get the last flag reached
		int last_flag = getLastFlagReached();
		
		// Check to see if the flag that the ant collided with is one greater than the last flag that the ant has reached
		if (last_flag + 1 == sequenceNum) {
			
			// Display a message header
			System.out.println("-----------------------------");
			System.out.println("Ant has collided with flag " + sequenceNum + ".");
			System.out.println("-----------------------------");
			
			// Update the last flag reached
			setLastFlagReached(sequenceNum);
			
		} else {
			
			// Display a message header
			System.out.println("---------------------------------------------");
			System.out.println("Please reach the flags in a sequential order.");
			System.out.println("---------------------------------------------");
			
		}
		
	}
	
	// Check to see what should happen with the ant according to the foodLevel
	public void modifyFoodLevel() {
	
		// Create two local variables to help keep track of the foodLevel and consumptionRate
		int foodLevel = getFoodLevel();
		int consumptionRate = getFoodConsumptionRate();
		
		// Check to see if the foodLevel is above 0
		if (foodLevel > 0) {
			
			// Lessen the foodLevel by the consumption amount
			setFoodLevel(foodLevel - consumptionRate);
			
			// Move the ant
			move();
			
		}
		
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
