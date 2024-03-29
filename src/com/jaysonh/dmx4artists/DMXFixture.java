package com.jaysonh.dmx4artists;

// Import packages
import java.util.ArrayList;
import processing.core.PApplet;
import processing.*;
import static processing.core.PApplet.*;

/** 
 * Controls a dmx lighting fixture. Represents one light or DMX Object
 * @author Jayson Haebich
 * @author www.jaysonh.com
 * @version 0.1
 * @since   0.1
*/
public class DMXFixture
{
  
  /************************************************************************************
   * Public Methods
   ************************************************************************************/
   
  /**
   * Constructor - Create the fixture at a specific DMX address with a set number of channels 
   *
   * @param  appRef      Reference to parent processing object, so that we can access some of processings methods 
   * @param  dmxAddr     dmx address of this fixture
   * @param  numChannels the number of dmx channels to use with this device
   * @return nothing
   */
  public DMXFixture( PApplet appRef, int dmxAddr, int numChannels )
  {
      this.appRef      = appRef;
      this.dmxAddr     = dmxAddr;  
      this.numChannels = numChannels;
     
      channelParams = new ArrayList<DMXParam>();
     
      for( int i = 0; i < numChannels;i++)
      {
         channelParams.add( new DMXParamStatic( appRef ) ); 
      }
  }
  
  /**
   * Update the param values of this fixture
   *
   * @return nothing
   */
  public void updateParams()
  {
     ArrayList <Integer> replaceList = new ArrayList<Integer>(); // create a list of indexes of params to remove
                                                                 // This is a bit clumsy but the most reliable way
    
    int indx = 0;
    
     // Loop through each parameter for this object and update them
     // If it has finished then add to remove list
     for(DMXParam p : channelParams)
     {
            // Start param if it hasn't started already, required when it is a new param
            if( p.getAutoStart() )
              p.start();
              
            p.update();
            
            // If finished then add index to the replace list for removal after this for loop
            if( p.hasFinished() )
            {
              replaceList.add(indx);              
            }
            indx++;
     }
     
     // Now replace all the params that have finished with their triggers
     while(replaceList.size()>0)
     {
         // Find the index to replace
         int replaceIndx = replaceList.get(0);

         DMXParam nextParam = channelParams.get( replaceIndx ).getLaunchTrigger();
         
         // If there is a param to trigger then replace and start it
         if( nextParam != null )
         {
             channelParams.set( replaceIndx, nextParam );
             nextParam.start();
         }
         
         // Remove the current index from the replace list (will then allow loop to go to next one)
         replaceList.remove( 0 );
     }
  }
   
  /**
   * Get the param at an index
   *
   * @param  indx Index of param to get
   * @return param at given index
   */ 
  public DMXParam getParam(int indx )
  {
      return channelParams.get( indx );
  }
  
  /**
   * Not implemented
   * @param startVal
   */
  public void setStartValu( int startVal )
  {
	  
  }
  
  /**
   * Get the number of channels used by this fixture 
   *
   * @return number of channels used by this fixture
   */
  public int getNumChannels()
  {
     return numChannels; 
  }  
  
  /**
   * Send a value at a given index with a DMXParam object 
   *
   * @param  channel index of param to set
   * @param  param     new param to set in fixture
   * 
   * @return true/false if the param was able to be set
   */
  public boolean sendValue( int paramIndx, DMXParam param )
  {
	  return  setParam( paramIndx, param );
  }
  
  /**
   * Set the param at a given index with a DMXParam object  (deprecated)
   *
   * @param  channel index of param to set
   * @param  param     new param to set in fixture
   * 
   * @return true/false if the param was able to be set
   */
  public boolean setParam( int paramIndx, DMXParam param )
  {
	paramIndx = paramIndx - 1; // adjust paramIndx so it starts from 0 not 1 to align with java array precedence
	
    synchronized( channelParams ) // Make sure we don't access this while using it in another thread
    {
      if( paramIndx >= 0 && paramIndx < channelParams.size() ) // check that paramIndx is within ok range
      {        
        // replace the param within this fixture.
    	//  System.out.println("Setting paramIndx: " + paramIndx);
        channelParams.set( paramIndx, param );
        param.start();
        
        return true;
      }else
      {
    	  System.err.println("Error! Channel out of bounds, channel 0 not used");
        return false;
      }
    }
  }
  
  /**
   * Set the param at a given index with a int value 
   *
   * @param  paramIndx index of param to set
   * @param  param     new param to set in fixture
   * @return true/false if the param was able to be set
   */
  public boolean sendValue( int paramIndx, float value)
  {
	  setParam( paramIndx, (int) value );
	  
	  return true;
  }
  
  /**
   * Set the param at a given index with a int value  (deprecated, use sendValue instead)
   *
   * @param  paramIndx index of param to set
   * @param  param     new param to set in fixture
   * @return true/false if the param was able to be set
   */
  public boolean setParam( int paramIndx, float value)
  {
	  setParam( paramIndx, (int) value );
	  
	  return true;
  }
  
  public boolean setParam( int paramIndx, int value )
  {
	  paramIndx = paramIndx - 1;
      synchronized( channelParams ) // Make sure we don't access this while using it in another thread
      {
          if( paramIndx>=0 && paramIndx < channelParams.size() ) // check that paramIndx is within ok range
          {
        	// Here we need to check remove any other Param effects, except for 
            if( DMXParamStatic.class.isInstance(channelParams.get( paramIndx ))  )
            {
        	  channelParams.get( paramIndx ).setValue( value );    // set the value of the channel
            }else
            {
            	channelParams.set( paramIndx, new DMXParamStatic(appRef, value) );
            }
            return true;
          }else
          {
        	  System.err.println("Error! Channel out of bounds");
            return false;
          }
      }
  }
  
  /**
   * Set the param at a given index with a int value 
   *
   * @param  paramIndx index of param to set
   * @param  param     new param to set in fixture
   * @return true/false if the param was able to be set
   */
  public boolean sendValue( int paramIndx, int value)
  {
	  setParam( paramIndx, value );
	  
	  return true;
  }
  /**
   * Set the address of this fixture
   * 
   * @param  addr address to set 
   * @return true/false if address is ok 
   */
  public boolean setAddress( int addr )
  {
	  if( addr >= minDMXAddress && addr <= maxDMXAddress )
	  {
		  dmxAddr = addr;  
		  return true;
	  }else
		  return false;
  }
  
  /**
   * Get the address of this fixture
   *
   * @return address of the fixture
   */
  public int getAddress() { return dmxAddr; }
  
  public final int minDMXAddress = 1;
  public final int maxDMXAddress = 255;
  
  /************************************************************************************
   * Private Variables
   ************************************************************************************/  
   
  private ArrayList <DMXParam> channelParams; // List of all the params for this fixture, each index of list corresponds to a channel address
  private PApplet              appRef;        // Reference to parent processing sketch
  
  private int numChannels = 0;                // Number of channels used by this fixture
  private int dmxAddr     = 0;                // DMX Address of this fixture
}
