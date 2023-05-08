package com.mycompany.a2;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;

public class CommandSound extends Command {
	
	// Create a private GameWorld object so we can manipulate the one that is given in the constructor
	private GameWorld gw;
	
	// Create a private ToolBar object so we can close it when the user clicks on the checkbox
	Toolbar toolBar;
	
	
	
	// Constructor
    public CommandSound(GameWorld gw, Toolbar toolBar) {
    	
    	// Pass the overridden text to the parent constructor
        super("Sound");
        
        // Set the local GameWorld (gw) object to the one that was given in the constructor
        this.gw = gw;
        
        // Set the local ToolBar (toolBar) object to the one that was given in the constructor
        this.toolBar = toolBar;
        
    }
    
    
    // This action will be performed when the command is pushed
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	// Check to see if the e component was selected
    	if (((CheckBox) e.getComponent()).isSelected()) {
    		
    		// If so then we set the sound to true
    		gw.setSound(true);
    		
    	} else {
    		
    		// Otherwise, we set the sound to false
    		gw.setSound(false);
    		
    	}
    	
    	// Close the toolbar
    	toolBar.closeSideMenu();
        
    }
    
}

