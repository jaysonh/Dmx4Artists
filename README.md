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

If you receive an error message when using this library then follow these steps:

1. Check that you have libftd2xx.1.2.2.dylib in the /usr/local/lib folder: ls -al /usr/local/lib/libftd2xx*
2. If not copy it from the dependencies/osx folder (requires sudo)
3. Set the permissions for symlink: sudo chmod a+r /usr/local/lib/libftd2xx.1.2.2.dylib
4. Create a symlink: sudo ln -sf /usr/local/lib/libftd2xx.1.2.2.dylib /usr/local/lib/libftd2xx.dylib
5. Set the permissions for symlink: sudo chmod a+r /usr/local/lib/libftd2xx.dylib
6. Check that the AppleFTDI driver is not enabled: sudo kextunload -b com.apple.driver.AppleUSBFTDI

it should now work:)

### Windows

Put the ftd2xx.dll file in the dependencies/win folder into your C:/windows/system32 folder

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

