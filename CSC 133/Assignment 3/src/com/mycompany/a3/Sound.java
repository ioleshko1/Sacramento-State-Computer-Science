package com.mycompany.a3;

import com.codename1.ui.Display;
import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;


public class Sound {
	
	// Private Media variable so we can use it to create sound
	private Media m;
	
	
	// Constructor
	public Sound(String fileName) {
		
		// Check to see if the current display has not been created yet
		if (Display.getInstance().getCurrent() == null) {
			
			// Display an error
			System.out.println("Error: Create sound objects after calling show()!");
			
			// Exit the game
			System.exit(0);
			
		}
		
		
		// Loop through while the media is null
		while (m == null) {
			
			// Use try/catch just in case something is wrong with the file
			try {
				
				// Create an InputStream object using the file class and fileName provided
				InputStream in = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
				
				// Use the Media private variable to create a new media using the input stream and the audio file
				m = MediaManager.createMedia(in, "audio/wav");
				
			} 
			
			// If an error is found
			catch(Exception e) {
				
				// Print the stack trace of it
				e.printStackTrace();
				
			}
			
		}
		
	}
	
	public void play() {
		
		// Set the initial play time to the beginning of the sound file
		m.setTime(0);
		
		// Call the play method to play the sound
		m.play();
		
	}
}
