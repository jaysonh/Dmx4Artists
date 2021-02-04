	   ___  __  ____  __  ____   ___   ___  ________________________
	  / _ \/  |/  / |/_/ / / /  / _ | / _ \/_  __/  _/ __/_  __/ __/
	 / // / /|_/ />  <  /_  _/ / __ |/ , _/ / / _/ /_\ \  / / _\ \  
	/____/_/  /_/_/|_|   /_/  /_/ |_/_/|_| /_/ /___/___/ /_/ /___/  
                                                                
# Introduction
This is a processing library to control generic FTDI DMX USB devices, such as the Enttec Open DMX device. The library is designed to be as easy to use as possible for people that are new to programming and DMX lighting. It includes methods and classes for creating timelines, oscilators, trigger events and other behaviours to make it easy to control your DMX based lighting fixtures.

## Setting up

This library has been tested and works with both Windows and OSX. It is designed to be used with generic FTDI DMX USB devices which are commonly found in the cheap usb DMX controllers you see on Taobao and Aliexpress. 

![USB DMX Device](https://github.com/jaysonh/Dmx4Artists/blob/main/assets/images/usb-dmx.jpg)

### OSX

Copy the file libftd2xx.1.2.2.dylib from the libraries folder of the repo to your desktop
open terminal (open launchpad and type terminal)
type these commands:
cp ~/Desktop/libftd2xx.1.2.2.dylib /usr/local/lib/
ln -sf /usr/local/lib/libftd2xx.1.2.2.dylib /usr/local/lib/libftd2xx.dylib

If you are having trouble running the examples then try disabling the default ftd2xx drivers. To do this download and run this program: http://www.dmxis.com/release/FtdiDriverControl.zip

Press the "Click to disable the driver" button, you should then be able to run the examples. 


### Windows

Please install the DMX Pro Manager software which will setup the correct drivers: https://www.enttec.com/product/controls/dmx-lighting-control-software/pro-manager/

If you are having trouble running the examples in Processing, such as the error message "A library relies on native code that's not available."  then make sure the ftd2xx.dll and ftd2xx64.dll files are in the C:\Windows\System32 folder

##

This library was create for the DMX Lighting for Artists workshop for OFCourse in Shanghai, that was run in 2019. Credit to Omar Khan whose code helped me to understand how to interface with FTDI device https://github.com/orcaomar

                               _.-~  )
                    _..--~~~~,'   ,-/     _  ((visit ofcourse.io))
                 .-'. . . .'   ,-','    ,' )
               ,'. . . _   ,--~,-'__..-'  ,'
             ,'. . .  (@)' ---~~~~      ,'
            /. . . . '~~             ,-'
           /. . . . .             ,-'
          ; . . . .  - .        ,'
         : . . . .       _     /
        . . . . .          `-.:
       . . . ./  - .          )
      .  . . |  _____..---.._/               

