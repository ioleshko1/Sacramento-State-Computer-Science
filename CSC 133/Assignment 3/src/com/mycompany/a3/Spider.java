package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Spider extends Movable {
	
	// Constructor for the Spider class which passes some information to the parent constructor and sets the speed and heading
	public Spider (int width, int height, GameWorld gw) {
		
		// Set the color to black and randomly select an integer between 10 and 50 to set the size
		super(ColorUtil.BLACK, random.nextInt(50 - 10) + 10, randomLocation(width, height), random.nextInt(360), random.nextInt(150 - 100) + 100, gw);
		
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
	public void controlSpider(int width, int height, int tickRate) {
		
		// Change the heading of the spider
		changeHeading();
		
		// Move the spider
		move(tickRate);
		
		// Check the boundary to see if the spider has crossed it yet
		checkBoundary(width, height, tickRate);
		
	}
	
	
	
	// This method is used to draw the spider
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {

		// Store half of the size so we can use it to draw the triangle
		int objectSize = this.getSize();
		
		// Using this we can get the coordinates of the object while using pCmpRelPrnt we can get the coordinates of the parent container
		int objectX = (int) (this.getX() + pCmpRelPrnt.getX() - objectSize/2) ;
		int objectY = (int) (this.getY() + pCmpRelPrnt.getY() - objectSize/2);
		
		
		// Change the color of the object to that of the object that is being drawn
		g.setColor(super.getColor());
		
		// Use an array of ints to store the coordinates of each of the corners
		int[] objectXPoints = {objectX, objectX + objectSize, objectX + objectSize/2};
		int[] objectYPoints = {objectY, objectY, objectY + objectSize};
		
		// Draw an filled triangle using the objectXPoints, objectYPoints, and the number of points
		g.drawPolygon(objectXPoints, objectYPoints, 3);
		
	}
	

	
	
	// Do not allow changes to the color of the Spider
	public void setColor(int color) {}
	
}
