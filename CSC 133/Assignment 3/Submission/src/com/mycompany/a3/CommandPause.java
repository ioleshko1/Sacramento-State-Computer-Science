package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class CommandPause extends Command {
	
	// Create a private Game object so we can manipulate the one that is given in the constructor
	private Game game;
	
	
	// Constructor
	public CommandPause(Game game) {
		
		// Pass the overridden text to the parent constructor
		super("Pause");
		
		// Set the local Game (game) object to the one that was given in the constructor
		this.game = game;
		
	}
	
	// This action will be performed when the command is pushed
	@Override 
	public void actionPerformed(ActionEvent e) {
		
		// Call the gamePausePlay() method in the Game class to update the buttons
		game.gamePausePlay();
		
	}
	
}
