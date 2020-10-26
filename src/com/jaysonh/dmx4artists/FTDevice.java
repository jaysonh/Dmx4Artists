package com.jaysonh.dmx4artists;

// Import packages
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
public class FTDevice
{
  /************************************************************************************
   * Public Methods
   ************************************************************************************/  
  
    /**
     * Constructor
     */
    FTDevice( int deviceIndx )
    {
       this.deviceIndx = deviceIndx;
      
       // Reference objects passed into FTDI library
       IntByReference flagRef      = new IntByReference();
       IntByReference devTypeRef   = new IntByReference();
       IntByReference devIDRef     = new IntByReference();
       IntByReference locIDRef     = new IntByReference();
       IntByReference ftHandleRef  = new IntByReference();
       Memory         devSerNumRef = new Memory(16);
       Memory         devDescRef   = new Memory(64);     
       
       // Load the FTDI device data from the device
       FTDI_D2XX.INSTANCE.FT_GetDeviceInfoDetail( deviceIndx,
                                                  flagRef,
                                                  devTypeRef,
                                                  devIDRef,
                                                  locIDRef,
                                                  devSerNumRef,
                                                  devDescRef,
                                                  ftHandleRef); 
                                                  
       // Convert references to local variables                                                  
       this.serialNum  = devSerNumRef.getString(0);
       this.devDesc    = devDescRef.getString(0);
       this.devID      = devIDRef.getValue();
       this.locID      = locIDRef.getValue();
       this.serialNum  = devSerNumRef.getString(0);
       this.devDesc    = devDescRef.getString(0);
       this.ftHandle   = ftHandleRef.getValue();                                           
    }
    
    /**
     * Prints the info about this FTDevice to the console
     */
    public void printInfo()
    {
        System.out.println( "deviceIndx: " + this.deviceIndx );
        System.out.println( "flag:       " + this.flag       );
        System.out.println( "devType:    " + this.devType    );
        System.out.println( "devID:      " + this.devID      );
        System.out.println( "locID:      " + this.locID      );
        System.out.println( "serialNum:  " + this.serialNum  );
        System.out.println( "devDesc:    " + this.devDesc    );
        System.out.println( "ftHandle:   " + this.ftHandle   );
        System.out.println( ""                               );
    }
    
    /** 
     * get the device indx
     */
     int getDeviceIndx() { return deviceIndx; }
     
    /** 
     * get the device serial number
     */
     String getSerial() { return serialNum;  }
     
   /************************************************************************************
    * Private Variables
    ************************************************************************************/  
  
    private int    deviceIndx; // device index in the list of all FTDI devices
    private int    flag;
    private int    devType;
    private int    devID;
    private int    locID;
    private String serialNum;
    private String devDesc;
    private int    ftHandle;
}