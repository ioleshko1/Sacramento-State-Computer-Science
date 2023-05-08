package com.mycompany.a3;

import com.codename1.charts.models.Point;

public abstract class Fixed extends GameObject implements ISelectable {
	
	// Create a private variable to hold whether the fixed object is selected or not
	private boolean selected;
	
	
	// Constructor for the Fixed class which passes some information to the parent constructor
	public Fixed(int color, int size, Point location, GameWorld gw) {
		
		// Pass the following information to the parent of this class which is GameObject
		super(color, size, location, gw);
		
	}
	
	
	
	
	// This method will check to see if the Point that was clicked is within an object
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
		
		// Capture the size of the object that we will be checking against
		int objectSize = getSize();
		
		// Capture the pointer's location relative to the parent's origin
		float pointerX = pPtrRelPrnt.getX();
		float pointerY = pPtrRelPrnt.getY();
		
		// Capture the shape location relative to parent's origin
		float objectX = this.getX() + pCmpRelPrnt.getX() - objectSize/2;
		float objectY = this.getY() + pCmpRelPrnt.getY() - objectSize/2;
		
		
		// pointerX >= objectX:  This checks to see if the pointer location (x) is greater than or equal to that of the object (left side)
		boolean leftSide = pointerX >= objectX;
		
		// pointerX <= objectX + objectSize:  This checks to see if the pointer location (x) is less than or equal to that of the object (right side)
		boolean rightSide = pointerX <= objectX + objectSize;
		
		// pointerY >= objectY:  This checks to see if the pointer location (y) is greater than or equal to that of the object (top side)
		boolean topSide = pointerY >= objectY;
		
		// pointerY <= objectY + objectSize:  This checks to see if the pointer location (y) is less than or equal to that of the object (bottom side)
		boolean bottomSize = pointerY <= objectY + objectSize;
		
		
		// Here we combine all of the booleans to see if the pointer was between all of those sides
		if (leftSide && rightSide && topSide && bottomSize) {
			
			// If so then we return true (the object contains the pointer)
			return true;
			
		} else {
			
			// Otherwise we return false (the object does not contain the pointer)
			return false;
			
		}
		
	}
	
	
	
	
	
	// Allow the ability to get the height private variable using a method	
	@Override
	public boolean isSelected() {
		return selected;
	}
		

	// Allow the ability to set the height private variable using a method
	@Override
	public void setSelected(boolean y) {
		selected = y;		
	}
	
}
