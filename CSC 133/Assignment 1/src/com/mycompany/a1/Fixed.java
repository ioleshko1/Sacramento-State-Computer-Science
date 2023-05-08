package com.mycompany.a1;

import com.codename1.charts.models.Point;

public abstract class Fixed extends GameObject {
	
	// Constructor for the Fixed class which passes some information to the parent constructor
	public Fixed(int color, int size, Point location) {
		
		// Pass the following information to the parent of this class which is GameObject
		super(color, size, location);
		
	}
	
	
	// Remove the ability to set the location because this is a fixed object
	public void setLocation(float x_coordinate, float y_coordinate) {}
	
}
