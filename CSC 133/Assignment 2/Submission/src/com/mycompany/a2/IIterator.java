package com.mycompany.a2;

public interface IIterator {

	// This method will help check to see if there are more objects in the iterator
	boolean hasNext();
		
	// This method will get the next object in the iterator
	GameObject getNext();
	
}
