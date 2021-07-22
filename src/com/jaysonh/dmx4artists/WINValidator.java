package com.jaysonh.dmx4artists;

import java.io.File;

 /** 
  * Checks a Windows system to see if it has the correct settings to run the library
  *
  * @author Jayson Haebich
  * @author www.jaysonh.com
  * @version 0.1
  * @since   1.1
  */

public class WINValidator
{
	public boolean isValid()
	{
		File dllFile = new File("C:/Windows/System32/ftd2xx.dll");
		
		if( dllFile.exists())
		{
			System.out.println("Found ftd2xx.dll");
			return true;
		}else
		{
			System.out.println("Cannot find ftd2xx.dll");
			return false;
		}
	}
}