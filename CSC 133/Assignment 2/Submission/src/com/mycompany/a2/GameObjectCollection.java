package com.mycompany.a2;

import java.util.ArrayList;

public class GameObjectCollection implements ICollection {

	// Private gameObjects array list that will keep track of the objects
	private ArrayList<GameObject> gameObjects;
	
	
	// Constructor
	public GameObjectCollection() {
		
		// Create an arrayList of Game Objects so that we can keep track of them without the client knowing the details
		gameObjects = new ArrayList<GameObject>();
		
	}


	// This method will allow clients to add objects to the data structure without knowing what the data structure is
	@Override
	public void add(GameObject object) {

		// Add the given object to the ArrayList
		gameObjects.add(object);
		
	}


	// This method will provide the client with an iterator which they can use to loop through the objects
	@Override
	public IIterator getIterator() {

		// Create a new GameObjectCollectionIterator() object when a client requests an iterator
		return new GameObjectCollectionIterator();
		
	}
	
	
	// Private class which will be created when the client asks for an iterator
	private class GameObjectCollectionIterator implements IIterator {
		
		// Create a private index variable so we can keep track of where we are in the Iterator
		private int currElementIndex;
		
		// Constructor
		public GameObjectCollectionIterator() {
			
			// Set the initial index to -1 because we have no objects added yet
			currElementIndex = -1;

		}
		
		
		// This method will provide the client with a boolean as to whether the data structure has another object or not
		@Override
		public boolean hasNext() {
			
			// Check to see if the size of the gameObjects array list is 0 or if the index is equal to the size of the array list - 1
			if(gameObjects.size() <= 0 || currElementIndex == gameObjects.size() - 1) {
				
				// Return false if that is correct (which means that there are no next objects)
				return false;
				
			} else { 
				
				// Return true if that is not correct (which means that there are next objects)
				return true;
				
			}
			
		}
		
		
		// This method will provide the client with the next object
		@Override
		public GameObject getNext() {
			
			// Increment the index
			currElementIndex += 1;
			
			// Return the object that is given at the current index
			return gameObjects.get(currElementIndex);
			
		}
		
	}
	
}
