package com.jaysonh.dmx4artists;

 /** 
  * Checks an OSX system to see if it has the correct settings to run the library
  *
  * @author Jayson Haebich
  * @author www.jaysonh.com
  * @version 0.1
  * @since   1.1
  */

public class OSXValidator
{
	public boolean isValid()
	{
		OSXCommand commandDylib = new OSXCommand("ls", "-al", "/usr/local/lib/libftd2xx.1.4.24.dylib");
	    
	    String[] res = commandDylib.getOutput();
	    
	    boolean dylibOK = true;
	    boolean permsOK = true;
	     
	    /*if(res.length > 0)
	    {
	      String []cols = res[0].split(" "); 
	      
	      if( cols[ cols.length-1].trim().equals("/usr/local/lib/libftd2xx.1.2.2.dylib") )
	      {
	    	String dylibLink = cols[cols.length-1].trim();
	        dylibOK = true;
	        
	        // now check permissions for dylib
	        String permissions = cols[0];
	        
	        char rUser  = permissions.charAt(1);
	        char xUser  = permissions.charAt(3);
	        char rGroup = permissions.charAt(4);
	        char xGroup = permissions.charAt(6);
	        char rOther = permissions.charAt(7);
	        char xOther = permissions.charAt(9);
	        
	        // check that user, group and other has r and x permission
	        if( rUser == 'r' && rGroup == 'r' && rOther == 'r' &&
	            xUser == 'x' && xGroup == 'x' && xOther == 'x' )
	        {
	          permsOK = true;
	        }
	      }
	    }*/
	    
	    OSXCommand kextstatCmd = new OSXCommand("kextstat");
	    
	    String[] kextstatRes = kextstatCmd.getOutput();
	    boolean foundAppleFTDI = false;
	    
	    for(int i =0;i < kextstatRes.length; i++)
	    {
	       if( kextstatRes[i].contains("AppleUSBFTDI") )
	         foundAppleFTDI = true;
	    }
	    
	    if( !dylibOK )
	    {
	        System.err.println("missing libftd2xx.1.4.24.dylib in /usr/local/lib");
	        System.err.println("move libftd2xx.1.4.24.dylib from osxDependencies to /usr/local/lib (requires sudo)");
	        System.err.println("");
	    }
	    
	    if( !permsOK )
	    {
	    	System.err.println("invalid permissions for libftd2xx.dylib");
	    	System.err.println("please run these commands in terminal: ");
	    	System.err.println("sudo chmod a+r /usr/local/lib/libftd2xx.1.4.24.dylib");
	    	System.err.println("sudo chmod a+r /usr/local/lib/libftd2xx.dylib");
	    	System.err.println("");
	    }
	    
	    if( foundAppleFTDI )
	    {
	    	System.err.println("error, conflicting FTDI driver found, please remove:");
	    	System.err.println("sudo kextunload -b com.apple.driver.AppleUSBFTDI");
	    	System.err.println("");
	    }
	    
	    if( dylibOK && permsOK && !foundAppleFTDI )
	    {
	        System.out.println("library setup ok");  
	        
	        return true;
	    }else
	    {
	       System.out.println("cannot load FTDI driver, see instructions above to fix") ;
	       
	       return false;
	    }
		
	}
	
}