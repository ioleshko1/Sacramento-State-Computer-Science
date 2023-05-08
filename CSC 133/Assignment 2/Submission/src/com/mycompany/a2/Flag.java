package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Flag extends Fixed {
	
	// Create a private variable to hold the sequenceNumber
	private int sequenceNumber;
	
	
	// Constructor for the Flag class which passes some information to the parent constructor and sets the sequenceNumber
	public Flag(int sequenceNumber, Point location) {
		
		// Set the color to green and the size to 10
		super(ColorUtil.BLUE, 10, location);
		
		// Set the local private variable to the given variable
		this.sequenceNumber = sequenceNumber;
		
	}
	
	
	// This toString() method displays the important values of this class - DONE
	@Override
	public String toString() {
		
		// Create the returnStatement by combining the correct values using get methods
		String returnStatement = "Flag: loc=" + Math.round(getX() * 10.0) / 10.0 + "," + Math.round(getY() * 10.0) / 10.0 + 
								 " color=" + colorToString() + 
								 " size=" + getSize() + 
								 " seqNum=" + getSequenceNumber();
		
		// Return the concatenated values
		return returnStatement;
		
	}


	
	
	
	// Public method to have the ability to get the sequenceNumber of the Flag object
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	// Public method to have the ability to set the sequenceNumber of the Flag object
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
	// Do not allow changes to color for the Flag objects
	public void setColor(int color) {}
	
}
