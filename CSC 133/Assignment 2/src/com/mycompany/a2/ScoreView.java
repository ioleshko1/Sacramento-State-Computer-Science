package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer {
	
	// Declare the GameWorld object
	private GameWorld gw;
	
	// Create private labels which we will use to display the current values
	private Label healthLevel;
	private Label sound;
	private Label lives;
	private Label timer;
	private Label lastFlagReached;
	private Label foodLevel;
	
	
	// Constructor
	public ScoreView(GameWorld gw) {
		
		// Initialize the GameWorld object
		this.gw = gw;
		
		

		// Set the layout of the ScoreView to be a BoxLayout along the x axis
		this.setLayout(new FlowLayout(Component.CENTER));
		
		
		
		// Create a Label (timerLabel) which will act ask the header for the timer value and initialize the timer variable to a new label
		Label timerLabel = new StyleLabel("Time:");
		timer = new StyleLabel();
		
		// Add the Labels to the ScoreView
		this.add(timerLabel);
		this.add(timer);
		


		// Create a new Label (livesLabel) which will act as the header for the lives value and initialize the lives variable to a new label
		Label livesLabel = new StyleLabel("Lives Left:");
		lives = new StyleLabel();
		
		// Add the Labels to the ScoreView
		this.add(livesLabel);
		this.add(lives);
		
		
		
		// Create a new Label (lastFlagReachedLabel) which will act as the header for the lastFlagReached value and initialize the timer variable to a new label
		Label lastFlagReachedLabel = new StyleLabel("Last Flag Reached:");
		lastFlagReached = new StyleLabel();
		
		// Add the Labels to the ScoreView
		this.add(lastFlagReachedLabel);
		this.add(lastFlagReached);
		
		
		
		// Create a new Label (foodLevelLabel) which will act as the header for the foodLevel value and initialize the timer variable to a new label
		Label foodLevelLabel = new StyleLabel("Food Level:");
		foodLevel = new StyleLabel();
		
		// Add the Labels to the ScoreView
		this.add(foodLevelLabel);
		this.add(foodLevel);
		
		
		
		// Create a new Label (healthLevelLabel) which will act as the header for the healthLevel value and initialize the timer variable to a new label
		Label healthLevelLabel = new StyleLabel("Health Level: ");
		healthLevel = new StyleLabel();
		
		// Add the Labels to the ScoreView
		this.add(healthLevelLabel);
		this.add(healthLevel);
		
		
		
		// Create a new Label (soundLabel) which will act as the header for the sound value and initialize the timer variable to a new label
		Label soundLabel = new StyleLabel("Sound: ");
		sound = new StyleLabel();
		
		// Add the Labels to the ScoreView
		this.add(soundLabel);
		this.add(sound);
		
	}
	
	
	@Override
	public void update (Observable observer, Object data) {
		
		// Set the labels to the values using get methods
		lives.setText(" " + gw.getLives());
		timer.setText(" " + gw.getTimer());
		lastFlagReached.setText(" " + gw.getLastFlagReached());
		foodLevel.setText(" " + gw.getFoodLevel());
		healthLevel.setText(" " + gw.getHealthLevel());
		
		// Use the getSound() to check to see if the sound is on
		if (gw.getSound()) {
			
			// If the sound is true then we set the the text of the sound label to ON
			sound.setText("ON");
			
		} else {
			
			// If the sound is false then we set the text of the sound label to OFF
			sound.setText("OFF");
			
		}
		
		// Update the screen
		this.revalidate();
		
	}

}
