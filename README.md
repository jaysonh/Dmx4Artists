	   ___  __  ____  __  ____   ___   ___  ________________________
	  / _ \/  |/  / |/_/ / / /  / _ | / _ \/_  __/  _/ __/_  __/ __/
	 / // / /|_/ />  <  /_  _/ / __ |/ , _/ / / _/ /_\ \  / / _\ \  
	/____/_/  /_/_/|_|   /_/  /_/ |_/_/|_| /_/ /___/___/ /_/ /___/  
                                                                
# Introduction
This is a processing library to control generic FTDI DMX USB devices, such as the Enttec Open DMX device. The library is designed to be as easy to use as possible for people that are new to programming and DMX lighting. It includes methods and classes for creating timelines, oscilators, trigger events and other behaviours to make it easy to control your DMX based lighting fixtures.

## Important Note
This library works with Processing 3.5, not Processing 4.0. I'm still working on getting it working with Processing 4.0 

## Setting up

This library has been tested and works with both Windows and OSX. It is designed to be used with generic FTDI DMX USB devices which are commonly found in the cheap usb DMX controllers you see on Taobao and Aliexpress. 

![USB DMX Device](https://github.com/jaysonh/Dmx4Artists/blob/main/assets/images/usb-dmx.jpg)

### OSX

You may need to install the libftd2xx.dylib file to your /usr/local/lib folder

open the terminal and type: cd /usr/local/lib

if you get a message saying that this folder does not exist then type and retry previous step: sudo mkdir /usr/local/lib

type the following command to open a finder window: sudo open .

now copy the libftd2xx.1.2.2.dylib file from osxDependencies to that folder

back in terminal type: sudo ln -sf /usr/local/lib/libftd2xx.1.2.2.dylib /usr/local/lib/libftd2xx.dylib

it should now work:)

### Windows

Put the ftd2xx.dll file in the windowsDependencies folder into your C:/windows/system32 folder

### Still having problems?

Try installing qlc plus https://qlcplus.org/ which may set up some extra system properties that need to be set

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

