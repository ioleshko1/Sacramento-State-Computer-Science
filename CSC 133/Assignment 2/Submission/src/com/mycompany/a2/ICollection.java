package com.mycompany.a2;

public interface ICollection {

	 // This method will add the object that was passed into the needed structure
	 void add(GameObject object);
	  
	 // This method will create a new iterator so the client can use it
	 IIterator getIterator();
	
}
