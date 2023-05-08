package com.mycompany.a1;

import com.codename1.charts.util.ColorUtil;

public class FoodStation extends Fixed {
	
	// Create a private variable to hold the capacity of the FoodStation
	private int capacity;
	
	
	
	// Constructor for the FoodStation class which passes some information to the parent constructor and sets the capacity to the size of the object
	public FoodStation() {
		
		// Set the color to green and randomly select an integer between 10 and 50 to set the size
		super(ColorUtil.GREEN, random.nextInt(50 - 10) + 10, randomLocation());
		
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
	
	
	
	// Public method to have the ability to get the capacity of the FoodStation object
	public int getCapacity() {
		return capacity;
	}
	
	// Public method to have the ability to set the capacity of the FoodStation object
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
