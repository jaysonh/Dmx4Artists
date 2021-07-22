package com.jaysonh.dmx4artists;

import java.io.File;
import java.util.StringTokenizer;

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
	/**
	   * Check if windows can find the ftd2xx.dll file
	   *  
	   * @return boolean if the function can find the ftd2x.dll file
	   */ 
	public boolean isValid()
	{
		boolean exists    = false;
	    String  foundPath = "";
	   
	    // Get the system paths for DLL files
	    String property = System.getProperty("java.library.path");
	    StringTokenizer parser = new StringTokenizer(property, ";");
	    
	    // Loop through all the folders that the system searches for dll files in
	    while (parser.hasMoreTokens())
	    {
	    	// Check if ftd2xx.dll is in this folder
	        String searchPath   = parser.nextToken() + "\\ftd2xx.dll";
	        
	        if( new File( searchPath ).exists() )
	        {
	          exists    = true;
	          foundPath = searchPath;
	        }
	    }
	     
	    if(exists)
	    {      
	         System.out.println("found in: " + foundPath);
	         
	         return true;
	    } else
	    {
	         System.err.println("Cannot find ftd2xx.dll, please copy from winDependencies to one of these folders:");
	           
	         StringTokenizer dllFolders = new StringTokenizer( property, ";" );
	         
	         // If there are no folders in the System path
	         if( dllFolders.countTokens() <= 0 )
	         {
	             System.err.println( "Error! No search paths for DLL files?" );  
	         }
	      
	         // Print each folder to the console
	         while ( dllFolders.hasMoreTokens())
	         {
	              System.err.println( dllFolders.nextToken() );
	         }
	         
	         return false;
	      }
	}
}