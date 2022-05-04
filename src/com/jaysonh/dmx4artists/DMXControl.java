package com.jaysonh.dmx4artists;

// Import packages
import java.util.*;

/** 
 * Controls a DMX FTDI device. holds a number of fixtures
 * @author Jayson Haebich
 * @author www.jaysonh.com
 * @version 0.1
 * @since   0.1
*/
public class DMXControl  extends Thread
{
  /************************************************************************************
   * Public Methods
   ************************************************************************************/

  /**
   * Constructor - Create the DMXControl for a given device to use a set number of channels
   *               Best practice is to allocate minimal number of channels   
   *
   * @param  deviceSerial  the serial number of the device,
   * @param  numChannels   the number of dmx channels to use with this device
   * @return nothing
   */
   public DMXControl( String serialNum, int numChannels )
   {
      try 
      {  	  
              ftdiDmx = new FTDIDmx( serialNum );
      } 
      catch (FTDIException e) 
      {
              // print the error message and exit
              System.err.println(e);
      }
      setupDevice( numChannels + 1 ); // need to add an extra channel so we don't get overrun
  }
   
  /**
   * Constructor - Create the DMXControl for a given device to use a set number of channels
   *               Best practice is to allocate minimal number of channels   
   *
   * @param  deviceIndx  the index of the device, it's number in the list of FTDI devices
   * @param  numChannels the number of dmx channels to use with this device
   * @return nothing
   */
  public DMXControl( int deviceIndx, int numChannels )
  {
      try 
      {
          ftdiDmx     = new FTDIDmx( deviceIndx );
          connected   = true;
          setupDevice( numChannels );
      }catch ( FTDIException e ) 
      {
          // print the error message and exit
          System.err.println(e);
      }
  }
  
  /**
   * List all dmx devices connected to this computer
   */
  public static String [] listDevices()
  {
	  try
	  {
		  return new FTDIDmx().getDevices();
	  }catch( FTDIException e )
	  {
		  String [] emptyList= {};
          System.out.println(e);
          return emptyList;
	  }
  }
  
  /**
   * start the thread running  
   *
   * @return nothing
   */
  public void start()
  {
      super.start();
  
      threadRunning = true;
  }

  /**
   * Add a fixture to the controller
   *
   * @param  fixture Fixture to add to this controller
   * @return nothing
   */
  public void addFixture( DMXFixture fixture)
  {
      if ( connected )
      {
          fixtureList.add(fixture);
      }
  }

  /**
   * Thread running method
   *
   * @return nothing
   */
  public void run()
  {
      while ( threadRunning ) // thread continues until flag set to false
      {
          // update all the fixtures
          updateFixtures(); 
    
          // try sending to dmx device
          try
          {
            ftdiDmx.sendData( dmxData, numChannels );
          }catch( FTDIException e )
          {
             System.err.println( "Cannot send data" ); 
          }
          // Sleep the thread to allow other processing and for DMX data to send
          try 
          {
              Thread.sleep( SLEEP_TIME );
          } 
          catch ( InterruptedException e ) 
          {
              e.printStackTrace();
          }
      }
  }

  /**
   * Update the fixtures in our list
   *
   * @return nothing
   */
  public void updateFixtures()
  {
      if ( connected ) // make sure we are connected to the dmx devie
      {
          // Update each fixture and set the data
          // Set the dmx data from each fixture
          for ( DMXFixture fixture : fixtureList )
          {
              fixture.updateParams();
      
              // get each channel value from fixture
              for ( int channel = 0; channel < fixture.getNumChannels(); channel++ )
              {
	            	// if we are fading to the next value  
	            	if( fadeVals && fadeRate < 1.0 )
	            	{
	            		// get previous value (not working)
	            		int   prevVal   = Byte.toUnsignedInt( dmxData[ channel + fixture.getAddress() - 1 ] ); // problem!!
	            		float targetVal = ( int ) fixture.getParam( channel ).getValue();
	            		float diff      = (targetVal - (float)prevVal) * fadeRate;
	            		
	            		
	            		float nextVal = ( float )prevVal + diff; 
	            		
	            		double res = ( diff > 0.0 ) ? Math.ceil( nextVal ) : Math.floor( nextVal );
	            		
	            		dmxData[ channel + fixture.getAddress() - 1 ] = ( byte )( res );
		            	
	            	}else
	            	{
	            		// get the channel value from the fixture, and convert to a byte to send
	            		dmxData[ channel + fixture.getAddress() - 1 ] = (byte)( (int)fixture.getParam( channel ).getValue());
	            	}
              }
          }
      }
  }

  /**
   * Send a value to a channel, overrides any existing value
   *
   * @param  channel channel to set value of
   * @param  value   value to set
   * @return nothing
   */
  public void sendValue(int channel, int value)
  {
	if (channel > 0 && channel <= numChannels ) // Check channel is valid, first channel is always 1
	{
	  	// get the channel value from the fixture, and convert to a byte to send
	  	dmxData[ channel - 1 ] = (byte)( value ); 	
	}
  }

  /**
   * Send a value to a channel, overrides any existing value
   *
   * @param  channel channel to set value of
   * @param  value   value to set
   * @return nothing
   */
   public void sendValue(int channel, float value)
   {
      sendValue( channel, (int)value );
   }
   
   /*
    * 
    * need this to stop recursion problems?
    */
   public void setChannelVal( int channel, int value )
   {
	   
	   
   }
  
  /**
   * Stop the thread running
   *
   * @return nothing
   */
  public void close()
  {
    threadRunning = false; // end the thread
  }
  
  
  /**
   * Setup the device to start sending
   *
   * @return nothing
   */
  private void setupDevice( int numChannels )
  {
	  System.out.println("DMXForArtists version: " + VERSION_NUM);
	  
      if ( numChannels <= MAX_CHANNELS ) // make sure we don't have too many channels
      {
          this.numChannels = numChannels;
    
          connected   = true;
          dmxData     = new byte[ numChannels ];
          fixtureList = new ArrayList< DMXFixture >();
      
          this.start(); // start the thread running
          
        } else
        {
            System.err.println("ERROR!, number of channels must be less than " + MAX_CHANNELS);
        } 
  }
  
  /**
   * set true/false for fading between light values
   * 
   * @param status true/false to fade
   */
  public void setFixtureFade( boolean status )
  {
	  fadeVals = status;
  }
  
  /**
   * sets how fast to fade betweeen old values to new values
   * set to 1.0 for instant fade
   * 
   * @param fadeVal - rate to fade into new value
   */
  public void setFixtureFadeRate( float fadeVal )
  {
	  fadeRate = fadeVal;
  }
  
  /************************************************************************************
   * Public Constants
   ************************************************************************************/
  public final static int MAX_CHANNELS = 512;

  /************************************************************************************
   * Private Variables
   ************************************************************************************/
  private final int SLEEP_TIME = 50; // number of ms for the update thread to sleep for

  public final String VERSION_NUM = "1.4";
  
  private FTDIDmx                ftdiDmx;     // dmx control object
  private ArrayList <DMXFixture> fixtureList; // list of all the DMX fixtures

  private int     numChannels;       // number of channels used by the object
  private byte [] dmxData;           // stores the dxm data to send
  private boolean connected;         // are we connected to the dmx device
  private boolean threadRunning ;    // is thre thread runnning
  
  // For fading values
  private boolean fadeVals = false;  // off by default
  private float   fadeRate = 1.0f;    // 1.0 - instant fade 0.0 infinite fade
  
}