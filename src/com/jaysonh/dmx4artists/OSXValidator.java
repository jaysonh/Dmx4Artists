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
		OSXCommand command = new OSXCommand("ls -al /usr/local/lib/libftd2xx.dylib");
	    command.run();
	    
	    String[] res = command.getOutput();
	    
	    boolean dylibOK = false;
	    boolean permsOK = false;
	     
	    if(res.length > 0)
	    {
	      String []cols = split( res[0], " ");
	      if(cols.length>=15)
	      {
	        String dylibLink = cols[cols.length-1];
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
	    }
	    
	    if( dylibOK && permsOK)
	    {
	       System.out.println("dylib setup ok, library should work");  

			return true;
	    }else
	    {
	    	System.out.println("could not load libftd2xx.dylib, check folder /usr/local/lib/") ;
	       return false;
	    }
		
	}
	
}