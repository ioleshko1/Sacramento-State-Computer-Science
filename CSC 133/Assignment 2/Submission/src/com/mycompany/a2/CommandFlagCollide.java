package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class CommandFlagCollide extends Command {
	
	// Create a private GameWorld object so we can manipulate the one that is given in the constructor
	private GameWorld gw;
	
	// Constructor
    public CommandFlagCollide(GameWorld gw) {
    	
    	// Pass the overridden text to the parent constructor
        super("Collide with Flag");
        
        // Set the local GameWorld (gw) object to the one that was given in the constructor
        this.gw = gw;
        
    }
    
    
    // This action will be performed when the command is pushed
    @Override
    public void actionPerformed(ActionEvent e) {
       
        // This try will catch the error if the user inserted a value that is not a number
        try {
        	
        	// Create a textField (userInput) which will keep track of the user input
        	TextField userInput = new TextField();
        	
        	// Using the Dialog feature, capture the flag sequence number that the user is entering
            Dialog.show("Enter the flag sequence number:", userInput, new Command("OK"));
        	
        	// Convert the user input into an integer which we can use to compare to other number
            int intInput = Integer.parseInt(userInput.getText().toString());
        	
        	// Check to see if the input of the user is between 0 (exclusive) and 5 (exclusive)
        	if (intInput > 0 && intInput < 5) {
        		
        		// Call the collisionFlag() method in the game world and pass in the user input flag number
        		gw.collisionFlag(intInput);
        		
        	} else {

        		// If the user inputed a number that is not between 1 and 4 we display an error
        		Dialog.show("Error (invalid number)", "Please enter a valid flag sequence number between 1 and 4." , "OK", null);
        		
        	}
        	
        } catch (NumberFormatException e1) {
        	
        	// If the user inputed a NON-number, we also display an error
        	Dialog.show("Error (letter)", "Please enter a valid flag sequence number between 1 and 4.", "OK", null);
        	
        }
        
    }
	
}
