package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandRightTurn extends Command {
	
	// Create a private GameWorld object so we can manipulate the one that is given in the constructor
	private GameWorld gw;
	
	// Constructor
    public CommandRightTurn(GameWorld gw) {
    	
    	// Pass the overridden text to the parent constructor
        super("Right");
        
        // Set the local GameWorld (gw) object to the one that was given in the constructor
        this.gw = gw;
        
    }
    
    
    // This action will be performed when the command is pushed
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	// Call the right() method on the GameWorld (gw) object
        gw.right();
        
    }
    
}