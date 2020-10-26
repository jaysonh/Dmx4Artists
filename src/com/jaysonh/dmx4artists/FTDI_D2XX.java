package com.jaysonh.dmx4artists;

// Import packages
import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.*;

/**
 * Interface for interacting with the DLL driver that controls the OpenDMX hardware. DO NOT DO ANYTHING WITH THIS CLASS DIRECTLY.
 * Unless you know what you're doing.
 *
 * @author      Omar Khan
 */ 
/** 
  * Interface used to interact directly ftdi library
  * adapted from the OpenDMX class By Omar Khan
  *
  * @author Jayson Haebich
  * @author www.jaysonh.com
  * @version 0.1
  * @since   0.1
  */
public interface FTDI_D2XX extends Library
{
  /************************************************************************************
   * Public Constants
   ************************************************************************************/  
    
  final static byte FT_OK = 0;
  final static byte FT_INVALID_HANDLE = 1;
  final static byte FT_DEVICE_NOT_FOUND = 2;
  final static byte FT_DEVICE_NOT_OPENED = 3;
  final static byte FT_IO_ERROR = 4;
  final static byte FT_INSUFFICIENT_RESOURCES = 5;
  
  final static byte FT_BITS_8 = 8; // ' Word Lengths
  final static byte FT_STOP_BITS_2 = 2; // Stop Bits
  final static byte FT_PARITY_NONE = 0; // Parity
  final static byte FT_FLOW_NONE = 0; // Flow Control
  final static byte FT_PURGE_RX = 1; // Purge rx and tx buffers
  final static byte FT_PURGE_TX = 2;

  FTDI_D2XX INSTANCE = (FTDI_D2XX) Native.loadLibrary("ftd2xx", FTDI_D2XX.class);
  int FT_Open(short intDeviceNumber, PointerByReference handle);
  int FT_Close(Pointer handle);
  int FT_ResetDevice (Pointer handle);
  int FT_SetDivisor(Pointer handle, int div);
  int FT_SetDataCharacteristics(Pointer handle, byte byWordLength, byte byStopBits, byte byParity);
  int FT_SetFlowControl(Pointer handle, short intFlowControl, byte byXonChar, byte byXoffChar);
  int FT_ClrRts(Pointer handle);
  int FT_SetRts(Pointer handle);
  int FT_Purge(Pointer handle, int lngMask);
  int FT_SetBreakOn(Pointer handle);
  int FT_SetBreakOff(Pointer handle);
  int FT_Write (Pointer handle, byte[] buffer, int lngBufferSize, IntByReference lngBytesWritten);
  
  int FT_CreateDeviceInfoList( IntByReference lpdwNumDevs );
  int FT_ListDevices(Pointer pvArg1, Pointer pvArg2, int dwFlags);
  int FT_GetDeviceInfoDetail( int dwIndex, 
                              IntByReference lpdwFlags,
                              IntByReference lpdwType, 
                              IntByReference lpdwID,
                              IntByReference lpdwLocId, 
                              Pointer pcSerialNumber,
                              Pointer pcDescription, 
                              IntByReference ftHandle   );


}