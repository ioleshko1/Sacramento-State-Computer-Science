package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class CommandAbout extends Command {
	
	// Constructor
    public CommandAbout() {
    	
    	// Pass the overridden text to the parent constructor
        super("About");
        
    }
    
    
    // This action will be performed when the command is pushed
    @Override
    public void actionPerformed(ActionEvent e) {
    	 
    	// Display the dialog which just shows some details about the project
        Dialog.show("About", "Welcome to Ants and Spiders\n Professor Pinar Muyan Ocelik\n Author: Igor Oleshko\n Assignment #2", "OK", null);
         
    }
    
}
