package com.mycompany.a3;

import java.util.Observable;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class GameWorld extends Observable {
	
	// Timer is the amount of time that it takes for a user to complete the game
	private int timer = 0;
	
	// The amount of lives than an ant has
	private int lives = 3;
	
	// Sound is a boolean variable which captures whether the sound is on or off
	private boolean sound = false;
	
	// Pause is a boolean variable which captures whether the game is paused or not
	private boolean paused = false;
	
	// Position is a boolean variable which captures whether the game allows the objects to be moved or not
	private boolean position = false;
	
	// The GameObjectCollection objects contains all of the Game Object that have been created
	private GameObjectCollection gameObjects;
	
	// The width of the gameWorld object
	private int width;
	
	// The height of the gameWorld object
	private int height;

	// Declare several sound variable which will be initialized in the createSound() method
	private Sound spiderSound;
	private Sound foodSound;
	private Sound flagSound;
	private BGSound backgroundSound;
	
	
	
	// Create the world
	public GameWorld() {}
	
	// Initialize the variables in the world
	public void init() {
		
		// Initialize the private variables to the necessary values
		gameObjects = new GameObjectCollection();
		
		// Initial location
		Point initial_location = new Point(200, 200);
		
		// Create 4 flags, set the location, and add them to the gameObjects GameObjectCollection
		gameObjects.add(new Flag(1, initial_location, this));
		gameObjects.add(new Flag(2, new Point(200, 800), this));
		gameObjects.add(new Flag(3, new Point(700, 800), this));
		gameObjects.add(new Flag(4, new Point(900, 400), this));
		
		// Call the getAnt() method on the Ant object which will determine if an ant exists or not
		gameObjects.add(Ant.getAnt(initial_location, this));
		
		// Create 2 spiders and add them to the gameObjects GameObjectCollection
		gameObjects.add(new Spider(width, height, this));
		gameObjects.add(new Spider(width, height, this));
		
		// Create 2 food stations and add them to the gameObjects GameObjectCollection
		gameObjects.add(new FoodStation(width, height, this));
		gameObjects.add(new FoodStation(width, height, this));
		
		
		// Set the changed and notify the observers about the changes
		this.setChanged();
		this.notifyObservers(this);
		
	}
	
	
	
	// Accelerate the ant
	public void accelerate() {
		
		// Create an iterator to keep track of the objects
		IIterator iterator = gameObjects.getIterator();
		
		// Here we check to see if the iterator that we are using has a next object
		while(iterator.hasNext()) {
			
			// If so then we create a new temporary objects and call getNext() which passes in the next object so we can store it
			GameObject tempObject = iterator.getNext();
			
			// Here we check to see if that object is an instance of the Ant object
			if(tempObject instanceof Ant) {
				
				// If so then we can call the accelerate method using the Ant object
				((Ant) tempObject).accelerate();
				
			}
			
		}
		
		// Set the changed and notify the observers about the changes
		this.setChanged();
		this.notifyObservers(this);
		
	}

	// Brake the ant
	public void brake() {
		
		// Create an iterator to keep track of the objects
		IIterator iterator = gameObjects.getIterator();
		
		// Here we check to see if the iterator that we are using has a next object
		while(iterator.hasNext()) {
			
			// If so then we create a new temporary objects and call getNext() which passes in the next object so we can store it
			GameObject tempObject = iterator.getNext();
			
			// Here we check to see if that object is an instance of the Ant object
			if(tempObject instanceof Ant) {
				
				// If so then we can call the brake method using the Ant object
				((Ant) tempObject).brake();
				
			}
			
		}
		
		// Set the changed and notify the observers about the changes
		this.setChanged();
		this.notifyObservers(this);
		
	}

	// Make the ant turn left
	public void left() {
		
		// Create an iterator to keep track of the objects
		IIterator iterator = gameObjects.getIterator();
		
		// Here we check to see if the iterator that we are using has a next object
		while(iterator.hasNext()) {
			
			// If so then we create a new temporary objects and call getNext() which passes in the next object so we can store it
			GameObject tempObject = iterator.getNext();
			
			// Here we check to see if that object is an instance of the Ant object
			if(tempObject instanceof Ant) {
				
				// Display a message header
				System.out.println("--------------------");
				System.out.println("Ant is turning left.");
				System.out.println("--------------------");
				
				// Call the left method of the ant object which has been casted
				((Ant) tempObject).steer(-5);
				
			}
			
		}
		
		// Set the changed and notify the observers about the changes
		this.setChanged();
		this.notifyObservers(this);
		
	}

	// Make the ant turn right
	public void right() {		
		
		// Create an iterator to keep track of the objects
		IIterator iterator = gameObjects.getIterator();
		
		// Here we check to see if the iterator that we are using has a next object
		while(iterator.hasNext()) {
			
			// If so then we create a new temporary objects and call getNext() which passes in the next object so we can store it
			GameObject tempObject = iterator.getNext();
			
			// Here we check to see if that object is an instance of the Ant object
			if(tempObject instanceof Ant) {
				
				// Display a message header
				System.out.println("---------------------");
				System.out.println("Ant is turning right.");
				System.out.println("---------------------");
				
				// Call the left method of the ant object which has been casted
				((Ant) tempObject).steer(5);
				
			}
			
		}
		
		// Set the changed and notify the observers about the changes
		this.setChanged();
		this.notifyObservers(this);
		
	}

	
	// Check the collision that occurred between the ant and a flag
	public void collisionFlag(int sequenceNumber) {
		
		// Create an iterator to keep track of the objects
		IIterator iterator = gameObjects.getIterator();
		
		// Here we check to see if the iterator that we are using has a next object
		while(iterator.hasNext()) {
			
			// If so then we create a new temporary objects and call getNext() which passes in the next object so we can store it
			GameObject tempObject = iterator.getNext();
			
			// Here we check to see if that object is an instance of the Ant object
			if(tempObject instanceof Ant) {
				
				// Call the checkFlagCollision method of the ant object which has been casted
				boolean advanced = ((Ant) tempObject).checkFlagCollision(sequenceNumber);
				
				// If the sound is on, play the flagSound (only if the flag advanced)
				if (getSound() && advanced) {
					flagSound.play();
				}
				
			}
			
		}
		
		// Set the changed and notify the observers about the changes
		this.setChanged();
		this.notifyObservers(this);
		
	}
	
	
	// Check the collision that occurred between the ant and a foodStation
	public void collisionFoodStation(FoodStation food) {
		
		// Display a message header
		System.out.println("-------------------------------------");
		System.out.println("Ant has collided with a food station.");
		System.out.println("-------------------------------------");
		
		
		// Create an iterator to keep track of the objects
		IIterator iterator = gameObjects.getIterator();
				
		// Here we check to see if the iterator that we are using has a next object
		while(iterator.hasNext()) {
					
			// If so then we create a new temporary objects and call getNext() which passes in the next object so we can store it
			GameObject tempObject = iterator.getNext();
					
			// Here we check to see if that object is an instance of the Ant object
			if(tempObject instanceof Ant) {
				
				// Capture the ant object
				Ant ant = (Ant) tempObject;
				
				// Update the foodLevel of the ant
				ant.setFoodLevel(ant.getFoodLevel() + food.getCapacity());
				
				// Set the capacity of the foodStation to 0
				food.setCapacity(0);
				
				// Change the color of the foodStation to a lighter green
				food.setColor(ColorUtil.rgb(0, 150, 0));
				
				// Add a new foodStation
				gameObjects.add(new FoodStation(width, height, this));
				
				// If the sound is on, play the foodSound
				if (getSound()) {
					foodSound.play();
				}
				
			}
			
		}
		
		// Set the changed and notify the observers about the changes
		this.setChanged();
		this.notifyObservers(this);
		
	}

	
	// Check the collision that occurred between the ant and a spider
	public void collisionSpider() {
		
		// Create an iterator to keep track of the objects
		IIterator iterator = gameObjects.getIterator();
		
		// Here we check to see if the iterator that we are using has a next object
		while(iterator.hasNext()) {
			
			// If so then we create a new temporary objects and call getNext() which passes in the next object so we can store it
			GameObject tempObject = iterator.getNext();
			
			// Here we check to see if that object is an instance of the Ant object
			if(tempObject instanceof Ant) {
				
				// Call the collisionSpider method of the ant object which has been casted
				((Ant) tempObject).collisionSpider();
				
				// If the sound is on, play the spiderSound
				if (getSound()) {
					spiderSound.play();	
				}
				
			}
			
		}
		
		// Set the changed and notify the observers about the changes
		this.setChanged();
		this.notifyObservers(this);
		
	}

	
	// Tick the game clock, modify the foodLevel, and check to see if it has reached 0
	public void gameClockTick() {
		
		//if(timer>0.02 && timer <0.70){createSounds();}
		
		// Display a message header
		System.out.println("-----------------");
		System.out.println("Clock has ticked.");
		System.out.println("-----------------");
		
		// Update the Timer
		setTimer(getTimer() + 1);
		
		// Create an iterator to keep track of the objects
		IIterator iterator = gameObjects.getIterator();
		
		// Here we check to see if the iterator that we are using has a next object
		while(iterator.hasNext()) {
			
			// If so then we create a new temporary objects and call getNext() which passes in the next object so we can store it
			GameObject tempObject = iterator.getNext();
			
			// Here we check to see if that object is an instance of the Ant object
			if(tempObject instanceof Ant) {
				
				// Capture the ant object
				Ant ant = (Ant) tempObject;
				
				// Call the checkFoodLevel method of the ant object which has been casted
				ant.modifyFoodLevel(getWidth(), getHeight(), 20);
				
				// Check to see if the foodLevel of the ant has reached 0 or if the healthLevel has reached 0
				if (ant.getFoodLevel() <= 0 || ant.getHealthLevel() <= 0) {
					
					// Reset the GameObjectCollection
					resetGameObjectCollection();
					
				}
			
			}
			
			// Here we check to see if that object is an instance of the Spider object
			if(tempObject instanceof Spider) {
				
				// Call the controlSpider method of the spider object which has been casted
				((Spider) tempObject).controlSpider(getWidth(), getHeight(), 20);
				
			}
			
			// Here we check to see if that object is an instance of the ISelectable interface
			if (tempObject instanceof ISelectable) {
				
				// Set the selected value to false
				((ISelectable) tempObject).setSelected(false);
				
			}
			
		}
		
		// Check to see if there were any collisions
		checkCollisions();
		
		// Check to see if the sound is on
		if (getSound()) {
			
			// If so, play the background music
			backgroundSound.play();
			
		} 
		
		// Set the changed and notify the observers about the changes
		this.setChanged();
		this.notifyObservers(this);
		
	}
	
	
	// Check to see if there was a collision between the ant and any other items
	public void checkCollisions() {
		
		// Create an iterator to keep track of the objects
		IIterator iterator1 = gameObjects.getIterator();
		
		// Here we check to see if the iterator that we are using has a next object
		while(iterator1.hasNext()) {
			
			// If so then we create a new temporary objects and call getNext() which passes in the next object so we can store it
			GameObject tempObject = iterator1.getNext();
			
			// Check to see if the first object was an Ant
			if (tempObject instanceof Ant) {
				
				// Create another iterator to keep track of the objects
				IIterator iterator2 = gameObjects.getIterator();
				
				// Here we check to see if the iterator that we are using has a next object
				while(iterator2.hasNext()) {
					
					// If so then we create a new temporary objects and call getNext() which passes in the next object so we can store it
					GameObject tempObject2 = iterator2.getNext();
					
					// Check to see if both temporary variables are referring to the same object and if they collide
					if ((tempObject != tempObject2) && (tempObject.collidesWith(tempObject2))) {
						
						// Handle the collision
						tempObject.handleCollision(tempObject2);
						
					} 
					
					// Otherwise the objects are not colliding so we can make sure that they are not in the collision list
					else {
						
						// Otherwise they no longer collide so we remove the object (if the object is in the list)
						tempObject.removeObject(tempObject2);
						
					}
						
				}
					
			}
		
		}	
			
	}
	
	
	
	
	
	
	
	
	

	
	// Display all of the current important values
	public void displayCurrentValues() {
		
		// Display a message header
		System.out.println("------------------------------------------------------------------");
		System.out.println("The current important values of the game has been displayed below.");
		System.out.println("------------------------------------------------------------------");
		
		// Create an iterator to keep track of the objects
		IIterator iterator = gameObjects.getIterator();
		
		// Here we check to see if the iterator that we are using has a next object
		while(iterator.hasNext()) {
			
			// If so then we create a new temporary objects and call getNext() which passes in the next object so we can store it
			GameObject tempObject = iterator.getNext();
			
			// Here we check to see if that object is an instance of the Ant object
			if(tempObject instanceof Ant) {
								
				// Print the number of lives left
				System.out.println("Number of lives left=" + getLives());
				
				// Print the current clock value (elapsed time)
				System.out.println("Current clock value=" + getTimer());
				
				// Print the highest flag number that the ant has reached sequentially so far
				System.out.println("Highest flag number reached=" + ((Ant) tempObject).getLastFlagReached());
				
				// Print the ant's current fool level
				System.out.println("Ant's current food level=" + ((Ant) tempObject).getFoodLevel());
				
				// Print the ant's health level
				System.out.println("Ant's health level=" + ((Ant) tempObject).getHealthLevel());
				
			}
			
		}
		
	}

	
	// Display all of the current map information
	public void displayCurrentMap() {
		
		// Display a message header
		System.out.println("-----------------------------------------------------");
		System.out.println("The current map of the game has been displayed below.");
		System.out.println("-----------------------------------------------------");
		
		// Create an iterator to keep track of the objects
		IIterator iterator = gameObjects.getIterator();
		
		// Here we check to see if the iterator that we are using has a next object
		while(iterator.hasNext()) {
			
			// If so then we create a new temporary objects and call getNext() which passes in the next object so we can store it
			GameObject tempObject = iterator.getNext();
			
			// Using the .toString() method that we overwrote in each class, we can print the details of the game objects
			System.out.println(tempObject.toString());
			
		}
		
	} 


	// Decrease the amount of lives, check if we have 0 lives left, and reinitialize the GameObjectCollection
	public void resetGameObjectCollection() {
		
		// Display a message header
		System.out.println("------------------------");
		System.out.println("The ant has lost 1 life.");
		System.out.println("------------------------");
		
		// Remove a life
		setLives(getLives() - 1);
		
		// Check to see if there are 0 lives left
		if (getLives() == 0) {
			
			// Display a message header
			System.out.println("----------------------------------");
			System.out.println("Game over, you have lost the game.");
			System.out.println("----------------------------------");
			
			// Exit the game
			exit();
			
		}
		
		// Destroy the ant object so a new one can be created in the init
		Ant.destroyAnt();
		
		// Reinitialize the GameObjectCollection
		init();
		
	}
	
	
	// This method processes the initialization of the private sound variables
	public void createSounds() {

		// Initialize the spiderSound variable to a sound for when the spider and ant collide
		spiderSound = new Sound("spider.wav");

		// Initialize the foodSound variable to a sound for when the food station and ant collide
		foodSound = new Sound("food.wav");

		// Initialize the flagSound variable to a sound for when the flag and ant collide
		flagSound = new Sound("flag.wav");

		// Initialize the bgSound variable which plays the background sound
		backgroundSound = new BGSound("background.wav");

	}
	

	// This method allows clients to get the lastFlagReached without accessing the Ant object
	public int getLastFlagReached() {
		
		// Create an iterator to keep track of the objects
		IIterator iterator = gameObjects.getIterator();
		
		// Here we check to see if the iterator that we are using has a next object
		while(iterator.hasNext()) {
			
			// If so then we create a new temporary objects and call getNext() which passes in the next object so we can store it
			GameObject tempObject = iterator.getNext();
			
			// Here we check to see if that object is an instance of the Ant object
			if(tempObject instanceof Ant) {
				
				// Return the last flag that was reached using the ant object that we got using the iterator
				return ((Ant) tempObject).getLastFlagReached();
			
			}
		
		}
		
		// Return the base value for the lastFlagReached if there is no value found
		return 1;
		
	}

	
	// This method allows clients to get the foodLevel without accessing the Ant object
	public int getFoodLevel() {
		
		// Create an iterator to keep track of the objects
		IIterator iterator = gameObjects.getIterator();
						
		// Here we check to see if the iterator that we are using has a next object
		while(iterator.hasNext()) {
			
			// If so then we create a new temporary objects and call getNext() which passes in the next object so we can store it
			GameObject tempObject = iterator.getNext();
			
			// Here we check to see if that object is an instance of the Ant object
			if(tempObject instanceof Ant) {
				
				// Return the current food level of the ant object that we got using the iterator
				return ((Ant) tempObject).getFoodLevel();
				
			}
		
		}
		
		// Return the base value for the foodLevel if there is no value found
		return 10;
		
	}

	
	// This method allows clients to get the healthLevel without accessing the Ant object
	public int getHealthLevel() {

		// Create an iterator to keep track of the objects
		IIterator iterator = gameObjects.getIterator();
		
		// Here we check to see if the iterator that we are using has a next object
		while(iterator.hasNext()) {
			
			// If so then we create a new temporary objects and call getNext() which passes in the next object so we can store it
			GameObject tempObject = iterator.getNext();
			
			// Here we check to see if that object is an instance of the Ant object
			if(tempObject instanceof Ant) {
			
				// Return the current health level of the ant object that we got using the iterator
				return ((Ant) tempObject).getHealthLevel();
				
			}
		
		}
		
		// Return the base value for the healthLevel if there is no value found
		return 50;
		
	}
	
	
	
	
	
	
	
	
	
	// Allow the ability to get the lives private variable using a method
	public int getLives() {
		return lives;
	}

	// Allow the ability to set the lives private variable using a method
	public void setLives(int lives) {
		this.lives = lives;
	}
	
	// Allow the ability to get the timer private variable using a method
	public int getTimer() {
		return timer;
	}
	
	// Allow the ability to set the timer private variable using a method
	public void setTimer(int timer) {
		this.timer = timer;
	}

	// Allow the ability to get the sound private variable using a method
	public boolean getSound() {
		return sound;
	}
	
	// Allow the ability to set the sound private variable using a method
	public void setSound(boolean sound) {
		this.sound = sound;
		
		// Set the changed and notify the observers about the changes
		this.setChanged();
		this.notifyObservers(this);
	}

	// Allow the ability to get the width private variable using a method
	public int getWidth() {
		return width;
	}

	// Allow the ability to set the width private variable using a method
	public void setWidth(int width) {
		this.width = width;
	}

	// Allow the ability to get the height private variable using a method
	public int getHeight() {
		return height;
	}
	
	// Allow the ability to set the height private variable using a method
	public void setHeight(int height) {
		this.height = height;
	}
	
	// Allow the ability to get the paused private variable using a method
	public boolean getPaused() {
		return paused;
	}

	// Allow the ability to set the paused private variable using a method
	public void setPaused(boolean paused) {
		this.paused = paused;
	}
	
	// Pause the background sound
	public void pauseSound() {
		
		// Check to see if the sound is on
		if(sound) {
			
			// If so then we will pause the background music
			backgroundSound.pause();
			
		}
		
	}
	
	// Pause the background sound
	public void playSound() {
		
		// Check to see if the sound is on
		if (sound) {
			
			// If so then we will play the background music
			backgroundSound.play();
			
		}
		
	}
	
	// This method sets the position variable to true if the position is not currently set to true
	public void revertPosition() {
		
		// Check to see if the position is currently set to true
		if (position == true) {
			
			// Update it to be false (not allow the object to be moved)
			position = false;
			
		} else {
			
			// Otherwise, we just allow the object to be moved
			position = true;
			
		}
		
	}
	
	// This method returns the position variable
	public boolean getPosition() {
		return position;
	}
	
	// This method set the position variable
	public void setPosition(boolean position) {
		this.position = position;
	}
	
	// This method gives an iterator for other classes to use
	public IIterator getIterator() {
		
		// Create an iterator to keep track of the objects
		IIterator iterator = gameObjects.getIterator();
		return iterator;
		
	}
	
	// Exit the game using System.exit(0)
	public void exit() {
		System.exit(0);
	}

	
}
