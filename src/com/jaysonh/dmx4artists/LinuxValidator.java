package com.jaysonh.dmx4artists;

public  class LinuxValidator
{
   boolean  isValid()
  {
     // check that ftdi .so is in the correct place
     
      OSXCommand lsCommand = new OSXCommand("ls", "-al", "/usr/local/lib/libftd2xx.so");
      
      String[] res = lsCommand.getOutput();
      
      if(res.length>0) // found libftd2xx.so
      {          
          // now run rmmod to disable VCP ftdi driver
          OSXCommand rmmodCmd1 = new OSXCommand("sudo", "rmmod", "ftdi_sio");
          OSXCommand rmmodCmd2 = new OSXCommand("sudo", "rmmod", "usbserial`");          
          
          if( rmmodCmd1.getOutput().length >0 || 
              rmmodCmd1.getOutput().length >0 )
              {
                System.out.println("cannot disable VCP ftdi driver");
                System.out.println("cannot setup linux drivers");
                return false;
              }
          System.out.println("linux ok");
          return true; 
      }else
      {
    	  System.out.println("cannot find /usr/local/lib/libftd2xx.so");
          return false;
      }
  }
}
