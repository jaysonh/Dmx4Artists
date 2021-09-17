package com.jaysonh.dmx4artists;

import processing.core.*;
import java.util.*;
import processing.core.PGraphics;
import processing.core.PApplet; 
import static processing.core.PApplet.*;

 /** 
  * Converts RGB color to HSB color
  *
  * @author Jayson Haebich
  * @author www.jaysonh.com
  * @version 0.1
  * @since   0.1
  */

public class HSBColor 
{
	/************************************************************************************
	 * Public Methods
     ************************************************************************************/  
	
	/**
	  * Constructs a HSBColor 
	  *
	  * @param  appRef	reference to the parent object so that we can call Processing functions from our code
	  * @param  h       hue
	  * @param  s       saturation
	  * @param  b       brightness
	  * 
	  */  
	public HSBColor( PApplet appRef, int h, int s, int b )
	{
		this.appRef = appRef;
		
		create( h, s, b );
	}
	
	/**
	  * Get the RGB color object
	  *
	  * @returns color object
	  * 
	  */
	public int getRGB() 
	{
		return rgbCol;
	}
	
	/**
	  * Set the range for the h, s and b
	  *
	  * param	range max value of the h, s and b
	  * 
	  */
	public void setRange( int range )
	{
		this.range = range;
		
		create( h, s, b ); // recalculate the hsb values with the new range
	}
	
	/**
	  * Get the hue
	  *
	  * return hue
	  * 
	  */
	public int getHue() { return h; }
	
	/**
	  * Get the brightness
	  *
	  * return brightness
	  * 
	  */
	public int getBri() { return b; }
	
	/**
	  * Get the saturation
	  *
	  * return saturation
	  * 
	  */
	public int getSat() { return s; }
	
	/************************************************************************************
	 * Private Methods
     ************************************************************************************/  
	
	/**
	 * 
	 * @param h hue
	 * @param s saturation
	 * @param b brightness
	 */
	private void create( int h, int s, int b )
	{
		this.h = h;
		this.s = s;
		this.b = b;
		
		// use processings inbuilt functions to convert from HSB to RGB
		appRef.pushStyle();
		
		appRef.colorMode( HSB, range, range, range );
		rgbCol = appRef.color( this.h, this.s, this.b );
		
		appRef.popStyle();
	}
	
	/************************************************************************************
	 * Constants
     ************************************************************************************/  
	   
	public static final int DEFAULT_RANGE = 255;
	
	/************************************************************************************
	 * Private Variables 
     ************************************************************************************/  
	private PApplet appRef;
	private int   range = DEFAULT_RANGE;
	private int   rgbCol;
	private int   h, s, b;
	
}
