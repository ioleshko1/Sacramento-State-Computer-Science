package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

public class Flag extends Fixed {
	
	// Create a private variable to hold the sequenceNumber
	private int sequenceNumber;
	
	
	// Constructor for the Flag class which passes some information to the parent constructor and sets the sequenceNumber
	public Flag(int sequenceNumber, Point location, GameWorld gw) {
		
		// Set the color to green and the size to 50
		super(ColorUtil.BLUE, 75, location, gw);
		
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


	
	// This method is used to draw the flag
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		// Store the size so we can use it to draw the triangle
		int objectSize = this.getSize();
		
		// Using this we can get the coordinates of the object while using pCmpRelPrnt we can get the coordinates of the parent container
		int objectX = (int) (this.getX() + pCmpRelPrnt.getX() - objectSize/2) ;
		int objectY = (int) (this.getY() + pCmpRelPrnt.getY() - objectSize/2);
		
		// Use an array of ints to store the coordinates of each of the corners
		int[] objectXPoints = {objectX, objectX + objectSize, objectX + objectSize/2};
		int[] objectYPoints = {objectY, objectY, objectY + objectSize};	
		
		
		// Change the color of the object to that of the object that is being drawn
		g.setColor(super.getColor());
		
		// Check to see if the object is selected
		if (isSelected()) {
			
			// Draw an unfilled triangle using the objectXPoints, objectYPoints, and the number of points
			g.drawPolygon(objectXPoints, objectYPoints, 3);
			
		} else {
			
			// Draw an filled triangle using the objectXPoints, objectYPoints, and the number of points
			g.fillPolygon(objectXPoints, objectYPoints, 3);
			
		}
		
		
		// Change the color to BLACK
		g.setColor(ColorUtil.BLACK);
				
		// Draw the string using the sequence number
		g.drawString("" + sequenceNumber, objectX + 25, objectY + 10);
		
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
