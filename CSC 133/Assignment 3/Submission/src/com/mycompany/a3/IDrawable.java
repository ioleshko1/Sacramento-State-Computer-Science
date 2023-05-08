package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public interface IDrawable {

	// Create a draw method which will need to be filled out in the concrete classes
	void draw(Graphics g, Point pCmpRelPrnt);
	
}
