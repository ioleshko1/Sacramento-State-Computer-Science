package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {
	
	// Declare the GameWorld object
	private GameWorld gw;
	
	// Constructor
	public MapView (GameWorld gw) {
		
		// Set the border of the MapView object to a red line with a 5 px width
		this.getAllStyles().setBorder(Border.createLineBorder(5, ColorUtil.rgb(255, 0, 0)));
		
		// Initialize the GameWorld object
		this.gw = gw;
		
	}
	
	
	@Override
	public void update(Observable observable, Object data) {
		
		// Call the displayCurrentMap() method using the GameWorld object
		gw.displayCurrentMap();
		
		// Update the screen
		this.revalidate();
		
	}

}
