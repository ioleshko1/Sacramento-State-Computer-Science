package com.mycompany.a1;

import com.codename1.charts.models.Point;

public abstract class Movable extends GameObject {
	
	// Heading keeps track of which way the movable object should be moving
	private int heading;
	
	// Speed keeps track of how fast the movable object should be moving
	private int speed;
	
	// Constructor for the Movable class which passes some information to the parent constructor
	public Movable(int color, int size, Point location, int heading, int speed) {
		
		// Pass these fields to the constructor in the parent class
		super(color, size, location);
		
		// Update the heading of the movable object
		this.heading = heading;
		
		// Update the speed of the movable object
		this.speed = speed;		
		
	}
	
	
	
	
	// Public method to allow to control the movement of each of the movable objects
	public void move () {		
		
		// Convert the heading to radians
		double radians = Math.toRadians(90 - getHeading());
		
		// Create two variables to capture the changes that will be occurring to the coordinates
		float deltaX = (float) (Math.cos(radians) * getSpeed());
		float deltaY = (float) (Math.sin(radians) * getSpeed());
		
		// Update the current location by adding the additional coordinates
		setLocation(getX() + deltaX, getY() + deltaY);
		
	}
	
	
	
	
	// Allow the ability to get the heading private variable using a method
	public int getHeading() {
		return heading;
	}
	
	// Allow the ability to set the heading private variable using a method
	public void setHeading(int heading) {
		this.heading = heading;
	}

	// Allow the ability to get the speed private variable using a method
	public int getSpeed() {
		return speed;
	}

	// Allow the ability to set the speed private variable using a method
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}
