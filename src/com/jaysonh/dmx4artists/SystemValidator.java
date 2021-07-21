package com.jaysonh.dmx4artists;

 /** 
  * Checks to see if the system is valid
  *
  * @author Jayson Haebich
  * @author www.jaysonh.com
  * @version 0.1
  * @since   1.1
  */

public class OSXValidator
{
	public bool isValid()
	{
		if( checkOS() == OS_OSX )
		{
			return new OSXValidator().osxValid.isValid();
		}else if(checkOS() == OS_WIN )
			return true;
		return false;
	}
	
	int checkOS()
	{
		  String osName = System.getProperty("os.name").toLowerCase();
		  
		  //mac os x
		  //windows 10
		  if(osName.startsWith("windows 10"))
				return OS_WIN;
		  else if(osName.startsWith("mac os x"))
			  	return OS_OSX;
		  else
			  	return OS_UNK;
	}
	static final int OS_UNK = 0;
	static final int OS_OSX = 1;
	static final int OS_WIN = 2;

}