package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class CommandHelp extends Command {
	
	// Constructor
	public CommandHelp() {
		
		// Pass the overridden text to the parent constructor
        super("Help");
        
    }
	
	
	// This action will be performed when the command is pushed
    @Override
    public void actionPerformed(ActionEvent e) {
        
    	// Display the dialog which just shows all of the key bindings and what they do
        Dialog.show("Help", "A: Accelerate\n B: Brake\n L: Left Turn\n R: Right Turn\n F: Collision with Food Station\n G: Collision with Spider\n T: Tick\n", "OK", null);
        
    }
}
