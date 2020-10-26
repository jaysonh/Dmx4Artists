package com.jaysonh.dmx4artists;

// Import packages
import static processing.core.PApplet.*;
import static processing.core.PApplet.*;
import processing.core.PApplet;
import processing.*;
  
/** 
 * OSC Param, oscillates between a max and min range 
 *
 * @author Jayson Haebich
 * @author www.jaysonh.com
 * @version 0.1
 * @since   0.1
 */
public class DMXParamOsc extends DMXParam
{
 /************************************************************************************
  * Public Methods
  ************************************************************************************/
  
  /**
   * Constructor
   *
   * @param appRef Reference to parent processing sketch to access processing methods
   * @param period how long the oscilliation will occur (seconds)
   * @param min    minimum value of the oscillation 
   * @param max    maximum value of the oscillation
   * @param moveType behaviour of the movement, how it will go from min to max
   * @param repeat   how many times the oscillation will repeat (0 means infinite repeat)
   * @return nothing
   */
  public DMXParamOsc( PApplet appRef, float period, int min, int max, MoveBehaviour moveType, int repeat )
  {
      this.appRef   = appRef;
      this.period   = period;
      this.min      = min;
      this.max      = max;
      this.moveType = moveType;
      this.repeat   = repeat;
      this.finished = false;
      this.autoStart = false;
  } 
  
  /**
   * updates the oscillation values
   *
   * @return nothing
   */
  public void update()
  {
    if( started )
    {
      float t = 0.0f;
      
      if( repeat == 0 ) // infinite repeat
      {
         t = ( ( ( float )( appRef.millis() - startTime ) / 1000.0f ) % period ) / period;
      }else
      {
        t = ( ( ( float )( appRef.millis() - startTime ) / 1000.0f ) ) / period;
        
        if( t >= ( float ) repeat )
        {
            finished = true;
            t=1.0f;
        }
      }
      
      value = (int) map( getMovement(t), 0.0f, 1.0f, min, max ); // get the value from specific movement type
    }
  }
  
  /**
   * get the type of this param
   *
   * @return type of param
   */  
  public String getType()
  {
    return "osc";
  }
  
  
  /************************************************************************************
   * Private Methods
   ************************************************************************************/  
    
  // Get an up and down value
  private float getOscSine( float t )
  {
    return abs( sin( t * PI ) );
  }
  
  private float getSawWave( float t )
  {
    return t / period; // goes up then resets to 0
  }
  
  private float getOscLinear( float t ) 
  {
      // uses a triangle wave, up then down
      return 1.0f- abs((period/2.0f) - t) * 2.0f;
  }
  
  private float getExp( float t )
  {
     return (float)Math.exp( (t / period) * PI ) / (float)Math.exp(PI);
  }
  private float getMovement( float t )
  {
    float result = 0.0f;
    
    switch( moveType )
    {
        case OSC_LINEAR:
          result = getOscLinear( t );
        break;
        
        case OSC_SINE:
          result = getOscSine( t );
        break;
        
        case OSC_LINEAR_RAMP:
          result = getSawWave( t );
          break;
          
        case OSC_EXP:
          result = getExp( t );
          break;
    }
    
    return result;
  }
  
  
  
  /************************************************************************************
   * Private Variables
   ************************************************************************************/  
    
  private int           repeat;
  private int           min;
  private int           max;
  private float         period;
  private MoveBehaviour moveType;
  
}
