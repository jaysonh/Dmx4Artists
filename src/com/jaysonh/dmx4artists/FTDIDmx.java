package com.jaysonh.dmx4artists;

// Import packages
import com.sun.jna.*;
import com.sun.jna.Memory;
import com.sun.jna.ptr.*;
import com.sun.jna.Pointer;
import java.util.*;

 /** 
  * Class used to interact directly with an FTDI based USB DMX Device
  *
  * @author Jayson Haebich
  * @author www.jaysonh.com
  * @version 0.1
  * @since   0.1
  */
public class FTDIDmx 
{
  /************************************************************************************
   * Public Methods
   ************************************************************************************/
  

  /**
   * Constructs an OpenDmx object on the given device number. If you only have 1 OpenDMX
   * device connected, then you should just input 0. 
   *
   * @param  deviceNum  number of the connected OpenDMX device. Input 0 if you have only 1 such device.
   * @return nothing
   */  
  public FTDIDmx( int deviceNum ) throws FTDIException 
  {
      // load dmx devices into the list
      loadFTDIDevices();
      
      // Open the device
      openDevice( deviceNum );
  }
  /**
   * Constructs an OpenDmx object on the given device number. If you only have 1 OpenDMX
   * device connected, then you should just input 0. 
   *
   * @param  serialNumber  serial number of the connected OpenDMX device. 
   * @return nothing
   */
  public FTDIDmx( String serialNumber ) throws FTDIException
  {
      // load dmx devices into the list
      loadFTDIDevices();
      
      boolean foundDevice = false;
      
      // Now find the deviceIndx for the specified serialNumber
      for( FTDevice device : deviceList )
      {
         if( device.getSerial().equals( serialNumber ) )
         {
            openDevice( device.getDeviceIndx() ); 
            foundDevice = true;
         }
      }
      
      // If we can't find a device throw an exception
      if(!foundDevice)
      {
        throw new FTDIException("Cannot find " + serialNumber);
      }
        
  }  
   
  /**
   * FTDI Commands to connect to device
   *
   * @param  deviceNum  number of the connected OpenDMX device. Input 0 if you have only 1 such device.
   * @return nothing
   */  
  private void openDevice( int deviceNum ) throws FTDIException 
  {
    
      PointerByReference portRef = new PointerByReference();
      
      // these methods are wrapped by runFTDI which does error checking
      
      runFTDI( FTDI_D2XX.INSTANCE.FT_Open( (short)deviceNum, portRef ) );
      
      this.port = portRef.getValue(); // save the port
      
      FTDI_D2XX.INSTANCE.FT_ListDevices( port, port, 0); // list devices
      runFTDI( FTDI_D2XX.INSTANCE.FT_ResetDevice( port )         ); // reset device
      runFTDI( FTDI_D2XX.INSTANCE.FT_SetDivisor(  port, 12 )     ); // set device divisor
      runFTDI( FTDI_D2XX.INSTANCE.FT_SetDataCharacteristics( port, 
                                                             FTDI_D2XX.FT_BITS_8, 
                                                             FTDI_D2XX.FT_STOP_BITS_2, 
                                                             FTDI_D2XX.FT_PARITY_NONE ) ); // set data characteristics
      
      runFTDI( FTDI_D2XX.INSTANCE.FT_SetFlowControl( port, 
                                                     FTDI_D2XX.FT_FLOW_NONE, 
                                                     (byte)0, 
             
                                             (byte)0) ); // set flow control
      runFTDI( FTDI_D2XX.INSTANCE.FT_ClrRts( port ) ); // clear rts
      runFTDI( FTDI_D2XX.INSTANCE.FT_Purge( port, FTDI_D2XX.FT_PURGE_TX ) ); // purge ftdi tx
      runFTDI( FTDI_D2XX.INSTANCE.FT_Purge( port, FTDI_D2XX.FT_PURGE_RX ) ); // purge ftdi rx
  } 
  /**
   * Load list of ftdi devices
   *
   * @return nothing
   */  
  public void loadFTDIDevices() throws FTDIException
  {
       deviceList = new ArrayList< FTDevice >(); // create empty list for devices
       
       IntByReference devNum = new IntByReference();
       runFTDI( FTDI_D2XX.INSTANCE.FT_CreateDeviceInfoList( devNum ) );
       
       System.out.println( "number devices: " + devNum.getValue() );
       
       for( int i = 0; i < devNum.getValue(); i++ )
       {
         // Get the ith device and print
         FTDevice ftDevice = new FTDevice( i );
         ftDevice.printInfo();
         
         deviceList.add( ftDevice ); // Add it to the device list
       }  
  }

  /**
   * Closes the currently open OpenDMX devices. 
   *
   * @return      An error code. Returns FTDI_D2XX.FT_OK if everything is fine, otherwise an error. See FTDI_D2XX class.
   */  
  public int close() {
    assert( port != null );
    return FTDI_D2XX.INSTANCE.FT_Close( port );    
  }
  
  /**
   * Sends data to the OpenDMX device. 
   *
   * @param  data  An array of data for channels 1->N, where N is no greater than 512.
   * @param  len_data The number of bytes to send, read from the beginning of the array. Must be between 0 and 512
   *
   * @return      An error code. Returns FTDI_D2XX.FT_OK if everything is fine, otherwise an error. See FTDI_D2XX class.
   */
  public int sendData( byte[] data, int dataLength ) throws FTDIException
  {
      // check the data is ok
      assert( port != null );
      assert( dataLength >= 0 && dataLength <= 512 );
      
      runFTDI( FTDI_D2XX.INSTANCE.FT_SetBreakOn( port ) );
      runFTDI( FTDI_D2XX.INSTANCE.FT_SetBreakOff( port ) );
      
      IntByReference written = new IntByReference();
      
      byte[] code = {0};  
      
      FTDI_D2XX.INSTANCE.FT_Write( port, code, 1, written ) ;
      FTDI_D2XX.INSTANCE.FT_Write( port, data, dataLength, written );
      
      return 1;
  }
  /**
   * Checks the error code of an FTDI commands
   * used for wrapping ftdi commands
   *
   * @param  checkResult  return code of an FTDI method
   *
   * @return  nothing
   */
  private void runFTDI( int checkResult ) throws FTDIException
  {
    if ( checkResult != FTDI_D2XX.FT_OK ) 
    {
      throw new FTDIException( " error code: "  + checkResult );
    }
  }
  
  /************************************************************************************
   * Private Variables
   ************************************************************************************/  
    
  private ArrayList <FTDevice> deviceList; // List of ftdi devices
  private Pointer port  = null;            // Pointer to open port
}