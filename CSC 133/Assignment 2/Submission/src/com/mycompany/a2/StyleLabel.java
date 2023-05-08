package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Label;

public class StyleLabel extends Label {
	
	public StyleLabel (String name) {
		
		// Pass the name of the label to the parent class
		super(name);
		
		// Update the style of the label
		getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		
	}
	
	public StyleLabel () {
		
		// Update the style of the label
		getAllStyles().setFgColor(ColorUtil.rgb(0, 0, 255));
		
	}

}
