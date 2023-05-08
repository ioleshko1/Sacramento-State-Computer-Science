package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	
	// Declare the GameWorld object
	private GameWorld gw;
	
	
	// Constructor
	public MapView (GameWorld gw) {
		
		// Set the border of the MapView object to a red line with a 5 px width
		this.getAllStyles().setBorder(Border.createLineBorder(5, ColorUtil.rgb(255, 0, 0)));
		this.getAllStyles().setBgColor(ColorUtil.WHITE);
		this.getAllStyles().setBgTransparency(255);
		
		// Initialize the GameWorld object
		this.gw = gw;
		
	}
	
	
	// This method is used to update the view
	@Override
	public void update(Observable observable, Object data) {
		
		// Repaint the MapView
		this.repaint();
		
	}
	
	
	// This method is used to draw all of the drawable objects
	@Override
	public void paint(Graphics g) {
		
		// Call the parent paint method and pass in the graphics object
		super.paint(g);
		
		// Create a new point using the x and y coordinates of the MapView relative to the screen
		Point pCmpRelPrnt = new Point(getX(), getY());
		
		// Create an iterator to keep track of the objects
		IIterator iterator = gw.getIterator();
								
		// Here we check to see if the iterator that we are using has a next object
		while(iterator.hasNext()) {
			
			// If so then we create a new temporary objects and call getNext() which passes in the next object so we can store it
			GameObject tempObject = iterator.getNext();
			
			// Here we check to see if that object is an instance of the IDrawable object
			if (tempObject instanceof IDrawable) {
				
				// If so then we can call the draw method using the IDrawable object
				((IDrawable) tempObject).draw(g, pCmpRelPrnt);
				
			}
			
		}
		
	}
	
	
	// This method will be called when a pointer is pressed
	@Override
	public void pointerPressed(int xPointer, int yPointer) {		
		
		// Check to see if the game is currently paused
		if(gw.getPaused()) {
			
			// Create the x and y coordinates of the point where the user has clicked
			int xCoordinate = xPointer - getParent().getAbsoluteX();
			int yCoordinate = yPointer - getParent().getAbsoluteY();
			
			// Create a point to keep track where the user has clicked
			Point pPtrRelPrnt = new Point(xCoordinate, yCoordinate);
			
			// Create a point to keep track of the object
			Point pCmpRelPrnt = new Point(getX(), getY());
			
			
			// Create an iterator to keep track of the objects
			IIterator iterator = gw.getIterator();
			
			// Here we check to see if the iterator that we are using has a next object
			while(iterator.hasNext()) {
				
				// If so then we create a new temporary objects and call getNext() which passes in the next object so we can store it
				GameObject tempObject = iterator.getNext();
				
				// Check to see if the first object was Fixed
				if(tempObject instanceof Fixed) {
					
					// If so then we capture that object as a Fixed object
					Fixed fixedObject = ((Fixed) tempObject);
					
					
					// Check to see if the fixed item contains the 
					if(fixedObject.contains(pPtrRelPrnt, pCmpRelPrnt)) {
						
						// Select the object
						fixedObject.setSelected(true);
						
						// Update the position variable to be false (we cant move an item)
						gw.setPosition(false);
						
					}
					
					// Check to see if the fixed object is selected
					else if(fixedObject.isSelected()) {
						
						// Check to see if we are allowed to change the position of the object
						if(gw.getPosition() == true) {
							
							// Create new x and y coordinates for the existing object relative to the parent container
							float xCoordinateNew = pPtrRelPrnt.getX() - pCmpRelPrnt.getX();
							float yCoordinateNew = pPtrRelPrnt.getY() - pCmpRelPrnt.getY();
								
							// Set the location of the fixed object to that of the new one
							fixedObject.setLocation(xCoordinateNew, yCoordinateNew);
							
						}
						
						// Update the position variable to be false (we cant move an item)
						gw.setPosition(false);
							
						// Unselect the object
						fixedObject.setSelected(false);
						
					}
					
				}
				
			}
			
			// Update the view on the screen
			revalidate();
			
		}
		
	}

}
 