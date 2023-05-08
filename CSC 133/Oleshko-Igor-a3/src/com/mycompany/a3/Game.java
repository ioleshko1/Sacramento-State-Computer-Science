package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.Toolbar;

public class Game extends Form implements Runnable {	

	// Private GameWorld variable to ensure that we cannot directly access the information from outside the class
	private GameWorld gw = new GameWorld();

	// Declare a Map View variable
	private MapView mv;

	// Declare a Score View variable
	private ScoreView sv;
	
	// Declare a UITimer variable
	private UITimer timer;
	
	
	// Declare the Toolbar
	private Toolbar toolBar = new Toolbar();
	
	
	// Initialize an instance of each of the Buttons
	private Button brakeButton = new StyleButton();
	private CheckBox soundCheckBox = new CheckBox();
	private Button pauseButton = new StyleButton();
	private Button rightButton = new StyleButton();
	private Button accelerateButton = new StyleButton();
	private Button leftButton = new StyleButton();
	private Button positionButton = new StyleButton();
	
	
	// Create an instance of each of the Command classes
	private CommandAbout aboutCmd = new CommandAbout();
	private CommandQuit exitCmd = new CommandQuit();
	private CommandHelp helpCmd = new CommandHelp();
	private CommandAccelerate accelerateCmd;
	private CommandBrake brakeCmd;
	private CommandRightTurn rightCmd;
	private CommandLeftTurn leftCmd;
	private CommandSound soundCmd;
	private CommandPause pauseCmd;
	private CommandPosition positionCmd;

	
	
	// Game constructor
	public Game() {

		// Change the layout of the screen to be border layout
		this.setLayout(new BorderLayout());

		// Initiate the Map View variable
		mv = new MapView(gw);

		// Initiate the Score View variable
		sv = new ScoreView(gw);

		// Add each of the views as observers of the GameWorld object
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		
		// Initialize some of the commands
		accelerateCmd = new CommandAccelerate(gw);
		brakeCmd = new CommandBrake(gw);
		rightCmd = new CommandRightTurn(gw);
		leftCmd = new CommandLeftTurn(gw);
		
		
		
		// Initialize the tool bar and set the current layout to this tool bar
		this.setToolbar(toolBar);

		// Set the title of the tool bar to Ants and Spiders
		toolBar.setTitle("Ants and Spiders");
		

		
		// Create the south, east, and west containers
		Container southContainer = new Container(new FlowLayout(Component.CENTER));
		Container eastContainer = new Container((new BoxLayout(BoxLayout.Y_AXIS)));
		Container westContainner = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		
		// Style each of the containers
		southContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		eastContainer.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		westContainner.getAllStyles().setBorder(Border.createLineBorder(1, ColorUtil.BLACK));
		
		
		
		
		
		// Initialize the sound command
		soundCmd = new CommandSound(gw, toolBar);
		
		// Set the command of the soundCheckBox to the CommandSound object
		soundCheckBox.setCommand(soundCmd);
		
		// Modify the appearance of the check box
		soundCheckBox.getAllStyles().setBgTransparency(255);
		soundCheckBox.getAllStyles().setPadding(TOP, 2);
		soundCheckBox.getAllStyles().setPadding(BOTTOM, 2);
		soundCheckBox.getAllStyles().setBgColor(ColorUtil.rgb(118, 139, 168));
		soundCheckBox.getAllStyles().setFgColor(ColorUtil.WHITE);
		
		// Modify the tool bar by adding several command objects to it
		toolBar.addCommandToRightBar(helpCmd);
		toolBar.addCommandToSideMenu(accelerateCmd);
		toolBar.addCommandToSideMenu(aboutCmd);
		toolBar.addCommandToSideMenu(exitCmd);
		toolBar.addComponentToSideMenu(soundCheckBox);


		
		
		// Create a button (positionButton) and associate it with the positionCmd command object
		positionCmd = new CommandPosition(gw);
		positionButton.setCommand(positionCmd);
		positionButton.setEnabled(false);
		
		// Create a button (pauseButton) and associate it with the pauseCmd command object
		pauseCmd = new CommandPause(this);
		pauseButton.setCommand(pauseCmd);
		
		// Add the positionButton and pauseButton buttons to the south container
		southContainer.add(positionButton);
		southContainer.add(pauseButton);
		
		
		
		
		// Associate the brakeButton with the brakeCmd command object and set the margin to be 100 from the top
		brakeButton.getAllStyles().setMarginTop(100);
		brakeButton.setCommand(brakeCmd);
		
		// Create a button (rightButton) and associate it with the rightCmd command object
		rightButton.setCommand(rightCmd);
		
		// Add the brake and right buttons to the east container
		eastContainer.add(brakeButton);
		eastContainer.add(rightButton);
		
		
		

		// Create a button (accelerateButton) and associate it with the accelerateCmd command object and set the margin to be 100 from the top
		accelerateButton.getAllStyles().setMarginTop(100);
		accelerateButton.setCommand(accelerateCmd);
		
		// Create a button (leftButton) and associate it with the leftCmd command object
		leftButton.setCommand(leftCmd);
		
		// Add the accelerate and left buttons to the west container
		westContainner.add(accelerateButton);
		westContainner.add(leftButton);
		
		
		
		
		// Add keyListeners to the specific commands below
		this.addKeyListener('b', brakeCmd);
		this.addKeyListener('r', rightCmd);
		this.addKeyListener('a', accelerateCmd);
		this.addKeyListener('l', leftCmd);
		
		
		
		
		// Add the southContainer to the SOUTH portion of the BorderLayout
		this.add(BorderLayout.SOUTH, southContainer);
		
		// Add the eastContainer to the EAST portion of the BorderLayout
		this.add(BorderLayout.EAST, eastContainer);
		
		// Add the westContainer to the WEST portion of the BorderLayout
		this.add(BorderLayout.WEST, westContainner);
		
		// Add the Map View (mv) to the CENTER of the layout
		this.add(BorderLayout.CENTER, mv);

		// Add the Score View (sv) to the NORTH of the layout
		this.add(BorderLayout.NORTH, sv);
		
		
		

		// Start the game by calling show() which will display all of the graphical components
		this.show();
		
		// Set the width and height of the gameWorld
		gw.setWidth(mv.getWidth());
		gw.setHeight(mv.getHeight());
		
		
		
		// Call a GameWorld method init() to set the initial state of the game
		gw.init();
		
		// Call a GameWorld method createSounds() to initialize the sounds
		gw.createSounds();
		
		// Re-validate the current form
		this.revalidate();
		
		
		// Initialize the timer variable 
		timer = new UITimer(this);
		
		// Schedule the timer to have 20ms for each tick
		timer.schedule(20, true, this);

	}



	@Override
	public void run() {

		// Call the tick method every time the timer ticks
		gw.gameClockTick();
		
	}
	
	
	
	
	// This method will be used to decide what to do if the game is paused or not
	public void gamePausePlay() {
		
		// If the game was not paused when the button was pressed go into the if statement to pause the game
		if (gw.getPaused() == false) {
			
			// Set the text of the Pause/Play component to Play
			pauseButton.setText("Play");
			
			// Call the pauseSound() to pause the background sound
			gw.pauseSound();
						
			// Set the paused variable to true
			gw.setPaused(true);
			
			// Cancel the timer which will stop the tick counter from going up
			timer.cancel();
			
			// Activate the position button so the user will be able to use it
			positionButton.setEnabled(true);
			
			// Deactivate all of the buttons so the user wont be able to use them
			accelerateButton.setEnabled(false);
			brakeButton.setEnabled(false);
			rightButton.setEnabled(false);
			leftButton.setEnabled(false);
			soundCheckBox.setEnabled(false);
			
			// Remove all of the needed commands from the side menu
			toolBar.removeCommand(aboutCmd);
			toolBar.removeCommand(exitCmd);
			toolBar.removeCommand(helpCmd);
			toolBar.removeCommand(accelerateCmd);			
			
			// Deactivate all of the key listeners so the user wont be able to use them
			removeKeyListener('r', rightCmd);
			removeKeyListener('l', leftCmd);
			removeKeyListener('a', accelerateCmd);
			removeKeyListener('b', brakeCmd);
			
		}
		
		// Otherwise we go into the else statement which is when our game was paused and now we are going into play mode
		else {
			
			// Set the text of the Pause/Play component to Pause
			pauseButton.setText("Pause");
			
			// Update the position variable to be false (we cant move any items)
			gw.setPosition(false);
			
			// Call the playSound() to play the background sound if the sound is on
			gw.playSound();
			
			// Set the paused variable to false
			gw.setPaused(false);
			
			// Start up the timer again
			timer.schedule(20, true, this);
			
			// Deactivate the position button so the user will be able to use it
			positionButton.setEnabled(false);

			// Activate all of the commands so the user will be able to use them
			accelerateButton.setEnabled(true);
			brakeButton.setEnabled(true);
			rightButton.setEnabled(true);
			leftButton.setEnabled(true);
			soundCheckBox.setEnabled(true);
			
			// Add all of the needed commands to the side menu
			toolBar.addCommandToRightBar(helpCmd);
			toolBar.addCommandToSideMenu(accelerateCmd);
			toolBar.addCommandToSideMenu(aboutCmd);
			toolBar.addCommandToSideMenu(exitCmd);
			
			// Activate all of the key listeners so the user will be able to use them
			addKeyListener('r', rightCmd);
			addKeyListener('l', leftCmd);
			addKeyListener('a', accelerateCmd);
			addKeyListener('b', brakeCmd);
			
		}
		
	}

}
