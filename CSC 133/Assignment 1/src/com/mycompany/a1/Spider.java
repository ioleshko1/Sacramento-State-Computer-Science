package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class Spider extends Movable {
	
	// Constructor for the Spider class which passes some information to the parent constructor and sets the speed and heading
	public Spider () {
		
		// Set the color to black and randomly select an integer between 10 and 50 to set the size
		super(ColorUtil.BLACK, random.nextInt(50 - 10) + 10, randomLocation(), random.nextInt(360), random.nextInt(10 - 5) + 5);
		
	}
	
	
	
	// This toString() method displays the important values of this class
	@Override
	public String toString() {
		
		// Create the returnStatement by combining the correct values using get methods
		String returnStatement = "Spider: loc=" + Math.round(getX() * 10.0) / 10.0 + "," + Math.round(getY() * 10.0) / 10.0 + 
								 " color=" + colorToString() + 
								 " size=" + getSize() + 
								 " heading=" + getHeading() + 
								 " speed=" + getSpeed();
		
		// Return the concatenated values
		return returnStatement;
		
	}
	
	

	// Checks to see if the spider has went out of bounds
	public void checkBoundary() {		
		
		// Check to see if the spider has went too far in the right/top direction
		if(getX() > 1000 || getY() < 0) {
			
			// Update the Heading to turn positive 180 degrees
			setHeading(getHeading() + 180);
			
			// Move the spider to make sure that it is not outside the border
			move();
			
		}
		
		// Check to see if the spider has went too far in the left/bottom direction
		if(getY() > 1000 || getX() < 0) {
			
			// Update the Heading to turn negative 180 degrees
			setHeading(getHeading() - 180);
			
			// Move the spider to make sure that it is not outside the border
			move();
			
		}
				
	}
	
	// Method which controls the movement of the spider including modifying the heading, moving the spider, and checking the boundary
	public void controlSpider() {
		
		// Randomize a value to help pick a number between -5 and 5 and update the heading of the spider
		setHeading(getHeading() + (random.nextInt(5 + 5) - 5));
		
		// Move the spider
		move();
		
		// Check the boundary to see if the spider has crossed it yet
		checkBoundary();
		
	}

	
	
	// Do not allow changes to the color of the Spider
	public void setColor(int color) {}
	
}
