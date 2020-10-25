//package com.jaysonh.dmx4artists;

import java.sql.Timestamp;
/**
 * Custom Exception for OpenDMX hardware
 *
 * @author      Jayson Haebich
 * @version     0.1
 */
public class FTDIException extends Exception 
{  
  /************************************************************************************
   * Public Methods
   ************************************************************************************/
   
  /**
  * Constructs an exception 
  *
  * @param  device_number  number of the connected OpenDMX device. Input 0 if you have only 1 such device.
  */  
  public FTDIException( String message ) 
  {
    // Create an error message with timestamp to throw
    super( new Timestamp( System.currentTimeMillis() ) + " FTDIException: " + message );
  } 
}