package com.mycompany.a1;

import java.util.ArrayList;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class GameWorld {
	
	// Timer is the amount of time that it takes for a user to complete the game
	private int timer = 0;
	
	// The amount of lives than an ant has
	private int lives = 3;
	
	// The gameObjects ArrayList contains all of the gameObjects that have been created
	private ArrayList<GameObject> gameObjects;
	
	
	
	// Create the world
	public GameWorld() {}
	
	// Initialize the variables in the world
	public void init() {
		
		// Initialize the private variables to the necessary values
		gameObjects = new ArrayList<GameObject>();
		
		// Initial location
		Point initial_location = new Point(200, 200);

		// Create 4 flags, set the location, and add it to the gameObjects arrayList
		gameObjects.add(new Flag(1, initial_location));
		gameObjects.add(new Flag(2, new Point(200, 800)));
		gameObjects.add(new Flag(3, new Point(700, 800)));
		gameObjects.add(new Flag(4, new Point(900, 400)));

		// Create an ant, set the location to the same as Flag 1, and add it to the gameObjects arrayList
		gameObjects.add(new Ant(initial_location));
		
		// Create 2 spiders and add it to the gameObjects arrayList
		gameObjects.add(new Spider());
		gameObjects.add(new Spider());
		
		// Create 2 food stations and add it to the gameObjects arrayList
		gameObjects.add(new FoodStation());
		gameObjects.add(new FoodStation());	
	
	}
	
	
	
	// Accelerate the ant
	public void accelerate() {
		
		// Loop through all of the objects in the gameObjects arrayList
		for (int i=0; i < gameObjects.size(); i++) {
			
			// Filter it down to just the ant object
			if (gameObjects.get(i) instanceof Ant) {
				
				// Call the accelerate method of the ant object which has been casted
				((Ant) gameObjects.get(i)).accelerate();
				
			}
			
		}		
		
	}

	// Brake the ant
	public void brake() {
		
		// Loop through all of the objects in the gameObjects arrayList
		for (int i=0; i < gameObjects.size(); i++) {
			
			// Filter it down to just the ant object
			if (gameObjects.get(i) instanceof Ant) {
		
				// Call the brake method of the ant object which has been casted
				((Ant) gameObjects.get(i)).brake();
						
			}
					
		}
		
	}

	// Make the ant turn left
	public void left() {

		// Loop through all of the objects in the gameObjects arrayList
		for (int i=0; i < gameObjects.size(); i++) {
			
			// Filter it down to just the ant object
			if (gameObjects.get(i) instanceof Ant) {
				
				// Display a message header
				System.out.println("--------------------");
				System.out.println("Ant is turning left.");
				System.out.println("--------------------");
		
				// Call the left method of the ant object which has been casted
				((Ant) gameObjects.get(i)).steer(-5);
				
			}
					
		}		
		
	}

	// Make the ant turn right
	public void right() {		
		
		// Loop through all of the objects in the gameObjects arrayList
		for (int i=0; i < gameObjects.size(); i++) {
					
			// Filter it down to just the ant object
			if (gameObjects.get(i) instanceof Ant) {
				
				// Display a message header
				System.out.println("---------------------");
				System.out.println("Ant is turning right.");
				System.out.println("---------------------");
				
				// Call the right method of the ant object which has been casted
				((Ant) gameObjects.get(i)).steer(5);
				
			}
			
		}		
		
	}

	
	// Check the collision that occurred between the ant and a flag
	public void collisionFlag(int sequenceNumber) {
		
		// Loop through all of the objects in the gameObjects arrayList
		for (int i=0; i < gameObjects.size(); i++) {
							
			// Filter it down to just the ant object
			if (gameObjects.get(i) instanceof Ant) {
				
				// Call the checkFlagCollision method of the ant object which has been casted
				((Ant) gameObjects.get(i)).checkFlagCollision(sequenceNumber);
				
				// Check to see if the ant has reached the last flag
				if (((Ant) gameObjects.get(i)).getLastFlagReached() == 4) {
					
					// Print the congratulations message
					System.out.println("------------------------------------");
					System.out.println("Game over, you win!  Total time: #" + getTimer());
					System.out.println("------------------------------------");
					
					// Exit the game
					exit();
					
				}
				
			}
					
		}	
		
	}
	
	
	// Check the collision that occurred between the ant and a foodStation
	public void collisionFoodStation() {

		// Display a message header
		System.out.println("-------------------------------------");
		System.out.println("Ant has collided with a food station.");
		System.out.println("-------------------------------------");
		
		
		// Create an ArrayList to keep track of all the non-zero FoodStations
		ArrayList<FoodStation> foodStations = new ArrayList<FoodStation>();
		
		
		// Loop through all of the objects in the gameObjects arrayList
		for (int k=0; k < gameObjects.size(); k++) {
			
			// Filter it down to just the ant object
			if (gameObjects.get(k) instanceof FoodStation) {
				
				// Capture the FoodStation object
				FoodStation food = (FoodStation) gameObjects.get(k);
				
				// Check to see if the capacity of the foodStation is not 0
				if (food.getCapacity() != 0) {
				
					// Add the FoodStation to the ArrayList
					foodStations.add(food);
				
				}
	
			}
		
		}
		
		// Loop through all of the objects in the gameObjects arrayList
		for (int i=0; i < gameObjects.size(); i++) {
		
			// Filter it down to just the ant object
			if (gameObjects.get(i) instanceof Ant) {
				
				// Create a random object
				Random random = new Random();
				
				// Pick a random number to serve as the index for the foodStations ArrayList
				int rand = random.nextInt(foodStations.size());
				
				// Capture both of the objects
				Ant ant = (Ant) gameObjects.get(i);
				FoodStation food = foodStations.get(rand);
				
				// Update the foodLevel of the ant
				ant.setFoodLevel(ant.getFoodLevel() + food.getCapacity());
				
				// Set the capacity of the foodStation to 0
				food.setCapacity(0);
				
				// Change the color of the foodStation to a lighter green
				food.setColor(ColorUtil.rgb(0, 150, 0));
				
				// Add a new foodStation
				gameObjects.add(new FoodStation());
		
			}
		
		}
	
	}

	
	// Check the collision that occurred between the ant and a spider - COMPLETED
	public void collisionSpider() {
		
		// Loop through all of the objects in the gameObjects arrayList
		for (int i=0; i < gameObjects.size(); i++) {
							
			// Filter it down to just the ant object
			if (gameObjects.get(i) instanceof Ant) {
				
				// Call the collisionSpider method of the ant object which has been casted
				((Ant) gameObjects.get(i)).collisionSpider();
				
				// Check to see if the healthLevel has reached 0
				if (((Ant) gameObjects.get(i)).getHealthLevel() == 0) {
					
					// Reset the gameObjects
					resetGameObjects();
					
				} 
				
			}
			
		}	
		
	}

	
	// Tick the game clock, modify the foodLevel, and check to see if it has reached 0
	public void gameClockTick() {

		// Display a message header
		System.out.println("-----------------");
		System.out.println("Clock has ticked.");
		System.out.println("-----------------");
		
		// Update the Timer
		setTimer(getTimer() + 1);
		
		// Loop through all of the objects in the gameObjects arrayList
		for (int i=0; i < gameObjects.size(); i++) {
					
			// Filter it down to just the ant object
			if (gameObjects.get(i) instanceof Ant) {
				
				// Call the checkFoodLevel method of the ant object which has been casted
				((Ant) gameObjects.get(i)).modifyFoodLevel();

				// Check to see if the foodLevel of the ant has reached 0
				if (((Ant) gameObjects.get(i)).getFoodLevel() == 0) {
					
					// Reset the gameObjects
					resetGameObjects();
										
				}
				
			}
			
			// Filter it down to just the spider object
			if (gameObjects.get(i) instanceof Spider) {
				
				// Call the controlSpider method of the spider object which has been casted
				((Spider) gameObjects.get(i)).controlSpider();
				
			}
				
		}		
		
	}

	
	// Display all of the current important values
	public void displayCurrentValues() {

		// Display a message header
		System.out.println("------------------------------------------------------------------");
		System.out.println("The current important values of the game has been displayed below.");
		System.out.println("------------------------------------------------------------------");

		// Loop through all of the objects in the gameObjects arrayList
		for (int i=0; i < gameObjects.size(); i++) {
					
			// Filter it down to just the ant object
			if (gameObjects.get(i) instanceof Ant) {
				
				// Print the number of lives left
				System.out.println("Number of lives left=" + getLives());
				
				// Print the current clock value (elapsed time)
				System.out.println("Current clock value=" + getTimer());
				
				// Print the highest flag number that the ant has reached sequentially so far
				System.out.println("Highest flag number reached=" + ((Ant) gameObjects.get(i)).getLastFlagReached());
				
				// Print the ant's current fool level
				System.out.println("Ant's current food level=" + ((Ant) gameObjects.get(i)).getFoodLevel());
				
				// Print the ant's health level
				System.out.println("Ant's health level=" + ((Ant) gameObjects.get(i)).getHealthLevel());
				
			}
		
		}
		
	}

	
	// Display all of the current map information
	public void displayCurrentMap() {

		// Display a message header
		System.out.println("-----------------------------------------------------");
		System.out.println("The current map of the game has been displayed below.");
		System.out.println("-----------------------------------------------------");

		// Using a for loop we can loop through all of the objects in gameObjects so we can display their information
		for(int i=0; i < gameObjects.size(); i++) {
			
			// Using the .toString() method that we overwrote in each class, we can print the details of the game objects
			System.out.println(gameObjects.get(i).toString());
			
		}
		
	} 
	
	
	// Exit the game using .exit(0)
	public void exit() {
		System.exit(0);
	}
	

	// Decrease the amount of lives, check if we have 0 lives left, and reinitialize the gameObjects
	public void resetGameObjects() {
		
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
		
		// Reinitialize the GameObjects
		init();
		
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
	
}
