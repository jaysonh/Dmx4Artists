package com.jaysonh.dmx4artists;

public class PathFinder
{
   String getLibraryPath()
   {
      String res = System.getProperty("java.class.path");

      String [] comps;
      
      int os = new SystemValidator().checkOS();
      
      // if osx then need to split by :
      // if win then need to split by ;
      if(os == SystemValidator.OS_OSX)
      {
    	  comps = res.split(":");
      }else if(os == SystemValidator.OS_WIN)
      {
    	  comps = res.split(";");
      }else
      {
    	  comps = null; //empty 
      }
      
      boolean foundLibPath = false;
      String dmxLibPath = "";
      
      for(int i = 0; i < comps.length && !foundLibPath;i++)
      {
        if( comps[i].contains("Dmx4Artists"))
        {
          String [] paths =comps[i].split("Dmx4Artists");
          
          if(os == SystemValidator.OS_OSX)
          {
        	  dmxLibPath = paths[0] + "Dmx4Artists/dependencies/osx/libftd2xx.1.2.2.dylib"; 
          }else if(os == SystemValidator.OS_WIN)
          {
        	  dmxLibPath = paths[0] + "Dmx4Artists/dependencies/win/ftd2xx.dll"; 
          }
          System.out.println("found path: " + dmxLibPath);
          foundLibPath = true;
        }
      }
      
      return dmxLibPath;
  }
}