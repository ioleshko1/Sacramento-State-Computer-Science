package com.mycompany.a3;

import com.codename1.ui.Graphics; 
import com.codename1.charts.models.Point;

public interface ISelectable {
	
	// Method to set the object as selected
	public void setSelected(boolean y);
	
	// Method to check if the object is selected
	public boolean isSelected();
	
	// Method to draw the selected object (this is created in IDrawable as well)
	public void draw(Graphics g, Point pCmpRelPrnt);
	
	// Method to check if the mouse is within the objects bounds
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrint);
	
}
