package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.plaf.Border;

public class StyleButton extends Button {
	
	// Constructor
	public StyleButton () {
		
		// Add padding to the buttons
		this.getAllStyles().setPadding(TOP, 5);
		this.getAllStyles().setPadding(BOTTOM, 5);
		
		// Add a 2 px border around the button
		this.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(0, 0, 0)));
		
		// Change the opacity to full
		this.getAllStyles().setBgTransparency(255);
		
		// Change the background color of the button to blue
		this.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		
		// Change the foreground to white
		this.getAllStyles().setFgColor(ColorUtil.rgb(255, 255, 255));
		
		// Change the disable style
		this.getDisabledStyle().setFgColor(ColorUtil.BLUE);
		
	}

}
