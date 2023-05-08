package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;

public class Game extends Form {

	// Private GameWorld variable to ensure that we cannot directly access the information from outside the class
	private GameWorld gw;
	
	// Create a flag to keep track if the previous input was X due to needing to confirm whether a player wants to exit or not
	private boolean xKey = false;

	
	
	// Game constructor
	public Game() {
		
		// The Game constructor instantiates a GameWorld
		gw = new GameWorld();

		// Calls a GameWorld method init() to set the initial state of the game
		gw.init();

		// Start the game by calling a Game method play()
		play();
		
	}

	
	
	private void play() {
		
		Label myLabel = new Label("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField = new TextField();
		this.addComponent(myTextField);
		this.show();
		
		
		myTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				
				if (sCommand.length() != 0) {
					
					// Checks to see if the input is invalid after the x command has been entered
					if (xKey && (sCommand.charAt(0) != 'n' && sCommand.charAt(0) != 'y')) {
						
						// Display a message header
						System.out.println("--------------------------------------------");
						System.out.println("Please enter either y or n after pressing x.");
						System.out.println("--------------------------------------------");
						
						// Set the xKey to false
						xKey = false;
						
						// Don't continue into the switch statement
						return;
					}
					
					switch (sCommand.charAt(0)) {
					
					// Ant has accelerated by a small amount
					case 'a':
						gw.accelerate();
						break;
						
						
					// Ant has braked by a small amount
					case 'b':
						gw.brake();
						break;
					
						
					// Ant has turned left by 5 degrees
					case 'l':
						gw.left();
						break;
					
						
					// Ant has turned right by 5 degrees
					case 'r':
						gw.right();
						break;	
						
						
					// Ant has collided with the first flag
					case '1':
						gw.collisionFlag(1);
						break;
						
					
					// Ant has collided with the second flag
					case '2':
						gw.collisionFlag(2);
						break;
						
					
					// Ant has collided with the third flag
					case '3':
						gw.collisionFlag(3);
						break;
						
						
					// Ant has collided with the fourth flag
					case '4':
						gw.collisionFlag(4);
						break;
							
						
					// Ant has collided with a food station
					case 'f':
						gw.collisionFoodStation();
						break;
						
						
					// Spider has gotten to the same location and collided with the ant
					case 'g':
						gw.collisionSpider();
						break;
					
						
					// Tell the game that the "game clock" has ticked
					case 't':
						gw.gameClockTick();
						break;
						
						
					// Generate a display by outputting lines of text on the console describing the current game/ant state values
					case 'd':
						gw.displayCurrentValues();
						break;
						
						
					// Tell the game world to output a "map" showing the current world
					case 'm':
						gw.displayCurrentMap();
						break;
						
						
					// Exit, by calling the method exit() to terminate the program.  Need to confirm using 'y' or 'n'
					case 'x':
						
						// Set the xKey private variable to true
						xKey = true;
						
						// Display a message header
						System.out.println("----------------------------------------");
						System.out.println("Confirm that you want to leave the game.");
						System.out.println("----------------------------------------");
						
						break;
						
					
					// User has confirmed the exit by saying yes
					case 'y':
						
						// Check to see if the previous input that was entered was X
						if(xKey) {
							
							// Display a message header
							System.out.println("-----------------");
							System.out.println("Leaving the game.");
							System.out.println("-----------------");
							
							// Exit the game world by calling the exit method
							gw.exit();
							
						} else {
							
							// Display a message header
							System.out.println("-------------------------------");
							System.out.println("Please enter a valid character.");
							System.out.println("-------------------------------");
							
						}
						
						// Set the xKey back to false
						xKey = false;
						
						break;
						

					// User has not confirmed the exit by saying no
					case 'n':
						
						// Check to see if the previous input that was entered was X
						if(xKey) {
							
							// Display a message header
							System.out.println("------------------");
							System.out.println("Continue the game.");
							System.out.println("------------------");
							
						} else {
							
							// Display a message header
							System.out.println("-------------------------------");
							System.out.println("Please enter a valid character.");
							System.out.println("-------------------------------");
							
						}
						
						// Set the xKey back to false
						xKey = false;
						
						break;
					
						
					// Display the default response to when a character has been entered that doesn't match up with any of the commands
					default:
						
						// Display a message header
						System.out.println("-------------------------------");
						System.out.println("Please enter a valid character.");
						System.out.println("-------------------------------");
						
						break;
						
						
						
					} // End of the switch statement
				}
				
			} // actionPerformed
			
		} // new ActionListener()
		
		); // addActionListener
		
	} // End of the play() method

}
