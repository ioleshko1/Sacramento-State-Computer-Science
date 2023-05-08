package com.mycompany.a2;

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
	
	
	
	// Changes the heading of the spider by a random number generated between -5 and 5
	public void changeHeading() {
		
		// Randomize a value to help pick a number between -5 and 5 and update the heading of the spider
		setHeading(getHeading() + (random.nextInt(5 + 5) - 5));
		
	}
	
	
	// Method which controls the movement of the spider including modifying the heading, moving the spider, and checking the boundary
	public void controlSpider(int width, int height) {
		
		// Change the heading of the spider
		changeHeading();
		
		// Move the spider
		move();
		
		// Check the boundary to see if the spider has crossed it yet
		checkBoundary(width, height);
		
	}

	
	
	// Do not allow changes to the color of the Spider
	public void setColor(int color) {}
	
}
