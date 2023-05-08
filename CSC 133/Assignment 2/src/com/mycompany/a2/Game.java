package com.mycompany.a2;

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
import com.codename1.ui.Toolbar;

public class Game extends Form {	

	// Private GameWorld variable to ensure that we cannot directly access the information from outside the class
	private GameWorld gw;

	// Declare a Map View variable
	private MapView mv;

	// Declare a Score View variable
	private ScoreView sv;

	
	
	// Game constructor
	public Game() {

		// Change the layout of the screen to be border layout
		this.setLayout(new BorderLayout());

		// The Game constructor instantiates a GameWorld
		gw = new GameWorld();

		// Initiate the Map View variable
		mv = new MapView(gw);

		// Initiate the Score View variable
		sv = new ScoreView(gw);

		// Add each of the views as observers of the GameWorld object
		gw.addObserver(mv);
		gw.addObserver(sv);


		
		
		// Create a new tool bar and set the current layout to this tool bar
		Toolbar toolBar = new Toolbar();
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
		
		
		
		// Create an instance of each of the Command classes
		CommandAbout aboutCmd = new CommandAbout();
		CommandQuit exitCmd = new CommandQuit();
		CommandHelp helpCmd = new CommandHelp();
		CommandFlagCollide flagCollideCmd = new CommandFlagCollide(gw);
		CommandSpiderCollide spiderCollideCmd = new CommandSpiderCollide(gw);
		CommandFoodStationCollide foodStationCollideCmd = new CommandFoodStationCollide(gw);
		CommandAccelerate accelerateCmd = new CommandAccelerate(gw);
		CommandBrake brakeCmd = new CommandBrake(gw);
		CommandRightTurn rightCmd = new CommandRightTurn(gw);
		CommandLeftTurn leftCmd = new CommandLeftTurn(gw);
		CommandTick tickCmd = new CommandTick(gw);
		CommandSound soundCmd = new CommandSound(gw, toolBar);
		
		
		
		
		// Create an instance of the CommandSound object and a new check box.  Set the command of the check box to the CommandSound object and add it to the tool bar
		CheckBox soundCheckBox = new CheckBox();
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


		
		
		// Create a button (flagButton) and associate it with the flagCollideCmd command object
		Button flagButton = new StyleButton();
		flagButton.setCommand(flagCollideCmd);

		// Create a button (spiderButton) and associate it with the spiderCollideCmd command object
		Button spiderButton = new StyleButton();
		spiderButton.setCommand(spiderCollideCmd);

		// Create a button (foodStationButton) and associate it with the foodStationCollideCmd command object
		Button foodStationButton = new StyleButton();
		foodStationButton.setCommand(foodStationCollideCmd);

		// Create a button (tickButton) and associate it with the tickCmd command object
		Button tickButton = new StyleButton();
		tickButton.setCommand(tickCmd);
		
		// Add the flag, spider, foosStation, and tick buttons to the south container
		southContainer.add(flagButton);
		southContainer.add(spiderButton);
		southContainer.add(foodStationButton);
		southContainer.add(tickButton);
		
		
		
		
		// Create a button (brakeButton) and associate it with the accelerateCmd command object and set the margin to be 100 from the top
		Button brakeButton = new StyleButton();
		brakeButton.getAllStyles().setMarginTop(100);
		brakeButton.setCommand(brakeCmd);
		
		// Create a button (rightButton) and associate it with the rightCmd command object
		Button rightButton = new StyleButton();
		rightButton.setCommand(rightCmd);
		
		// Add the brake and right buttons to the east container
		eastContainer.add(brakeButton);
		eastContainer.add(rightButton);
		
		
		

		// Create a button (accelerateButton) and associate it with the accelerateCmd command object and set the margin to be 100 from the top
		Button accelerateButton = new StyleButton();
		accelerateButton.getAllStyles().setMarginTop(100);
		accelerateButton.setCommand(accelerateCmd);
		
		// Create a button (leftButton) and associate it with the leftCmd command object
		Button leftButton = new StyleButton();
		leftButton.setCommand(leftCmd);
		
		// Add the accelerate and left buttons to the west container
		westContainner.add(accelerateButton);
		westContainner.add(leftButton);
		
		
		
		
		// Add keyListeners to the specific commands below
		this.addKeyListener('g', spiderCollideCmd);
		this.addKeyListener('f', foodStationCollideCmd);
		this.addKeyListener('t', tickCmd);
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
		
		
		
		
		// Call a GameWorld method init() to set the initial state of the game
		gw.init();
		

		// Start the game by calling show() which will display all of the graphical components
		this.show();
		
		
		// Set the width and height of the gameWorld
		gw.setWidth(mv.getWidth());
		gw.setHeight(mv.getHeight());

	}

}
