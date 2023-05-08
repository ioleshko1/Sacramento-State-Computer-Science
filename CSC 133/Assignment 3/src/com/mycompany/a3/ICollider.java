package com.mycompany.a3;

public interface ICollider {
	
	// This method will check to see if an object collides with another object
	boolean collidesWith(GameObject otherObject);
	
	// This method will handle the collision (call the necessary methods)
	void handleCollision(GameObject otherObject);

}
