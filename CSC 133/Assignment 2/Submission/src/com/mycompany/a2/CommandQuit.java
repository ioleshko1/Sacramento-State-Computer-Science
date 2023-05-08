package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

public class CommandQuit extends Command {
	
	// Constructor
    public CommandQuit() {
    	
    	// Pass the overridden text to the parent constructor
        super("Exit");
        
    }
    
    
    // This action will be performed when the command is pushed
    @Override
    public void actionPerformed(ActionEvent e) {

    	// Display the dialog along with the give options
        boolean dialog = Dialog.show("Confirm exit", "Are you sure you want to exit?", "Yes", "No");
        
        // Checks to see if the result of the dialog was that the user wanted to quit
        if (dialog) {
        	
        	// If so then we exit the game
        	Display.getInstance().exitApplication();
        	
        }
        	
    }
    
}
