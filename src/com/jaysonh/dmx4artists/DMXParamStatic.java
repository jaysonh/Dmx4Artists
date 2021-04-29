package com.jaysonh.dmx4artists;

// Import packages
import static processing.core.PApplet.*;
import static processing.core.PApplet.*;
import processing.core.PApplet;
import processing.*;

 /** 
  * Static Param, is set directly and does not change 
  *
  * @author Jayson Haebich
  * @author www.jaysonh.com
  * @version 0.1
  * @since   0.1
  */
public  class DMXParamStatic extends DMXParam
{
  /************************************************************************************
   * Public Methods
   ************************************************************************************/
  public DMXParamStatic( PApplet appRef )
  {
      this.appRef   = appRef;
      this.value = 0;
  }
  
  public DMXParamStatic( PApplet appRef, int value )
  {
      this.appRef   = appRef;
      this.value = value; 
  }
  
  public DMXParamStatic( PApplet appRef, float value )
  {
      this.appRef = appRef;
      this.value  = value; 
  }
  
  public String getType()
  {
    return "static";
  }
  public void update()
  {
    
  }
  
  
}
