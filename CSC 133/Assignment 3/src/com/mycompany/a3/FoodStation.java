package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class FoodStation extends Fixed {
	
	// Create a private variable to hold the capacity of the FoodStation
	private int capacity;
	
	
	
	// Constructor for the FoodStation class which passes some information to the parent constructor and sets the capacity to the size of the object
	public FoodStation(int width, int height, GameWorld gw) {
		
		// Set the color to green and randomly select an integer between 25 and 50 to set the size
		super(ColorUtil.GREEN, random.nextInt(50 - 25) + 25, randomLocation(width, height), gw);
		
		// Set the local private capacity variable to the value of size using getSize()
		capacity = getSize();
		
	}
	
	
	
	
	// This toString() method displays the important values of this class - DONE
	@Override
	public String toString() {
		
		// Create the returnStatement by combining the correct values using get methods
		String returnStatement = "FoodStation: loc=" + Math.round(getX() * 10.0) / 10.0 + "," + Math.round(getY() * 10.0) / 10.0 + 
								 " color=" + colorToString() + 
								 " size=" + getSize() + 
								 " capacity=" + getCapacity();
		
		// Return the concatenated values
		return returnStatement;
	}
	
	
	
	// This method is used to draw the food station
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {

		// Store the size so we can use it to draw the square
		int objectSize = this.getSize();
		
		// Using this we can get the coordinates of the object while using pCmpRelPrnt we can get the coordinates of the parent container
		int objectX = (int) (this.getX() + pCmpRelPrnt.getX() - objectSize/2);
		int objectY = (int) (this.getY() + pCmpRelPrnt.getY() - objectSize/2);
		
		
		// Change the color of the object to that of the object that is being drawn
		g.setColor(super.getColor());
		
		// Check to see if the object is selected
		if (isSelected()) {
			
			// Draw an unfilled rectangle using the x-coordinate, y-coordinate, and the sizes of the objects
			g.drawRect(objectX, objectY, objectSize, objectSize);
			
		} else {
			
			// Draw the filled rectangle using the x-coordinate, y-coordinate, and the sizes of the objects
			g.fillRect(objectX, objectY, objectSize, objectSize);
			
		}
		
		
		// Change the color to BLACK
		g.setColor(ColorUtil.BLACK);
						
		// Draw the string using the capacity number
		g.drawString("" + capacity, objectX + 5, objectY + 5);
		
	}
	
	
	
	
	
	
	// Public method to have the ability to get the capacity of the FoodStation object
	public int getCapacity() {
		return capacity;
	}
	
	// Public method to have the ability to set the capacity of the FoodStation object
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
