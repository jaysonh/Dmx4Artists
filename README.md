	   ___  __  ____  __  ____   ___   ___  ________________________
	  / _ \/  |/  / |/_/ / / /  / _ | / _ \/_  __/  _/ __/_  __/ __/
	 / // / /|_/ />  <  /_  _/ / __ |/ , _/ / / _/ /_\ \  / / _\ \  
	/____/_/  /_/_/|_|   /_/  /_/ |_/_/|_| /_/ /___/___/ /_/ /___/  
                                                                
# Introduction
This is a processing library to control generic FTDI DMX USB devices, such as the Enttec Open DMX device. The library is designed to be as easy to use as possible for people 
that are new to programming and DMX lighting. It includes methods and classes for creating timelines, oscilators, trigger events and other behaviours to make it easy to control 
your DMX based lighting fixtures.

This library has been tested and works with both Windows and OSX. It is designed to be used with generic FTDI DMX USB devices which are commonly found in the cheap usb DMX 
controllers you find on Taobao and Aliexpress. 

![USB DMX Device](https://github.com/jaysonh/Dmx4Artists/blob/main/assets/images/usb-dmx.jpg)

## Version
The latest version of this library 2.0 has been updated to work with Processing 4 on arm MacBook and Windows 11

## Setting up

The library should now run straight away without any configuration. But if you are having problems please follow the steps below:

### OSX
1. Copy the libftd2xx.1.4.24.dylib in the dependencies folder to /usr/local/lib folder (requires sudo)
2. Set the permissions for symlink: sudo chmod a+r /usr/local/lib/libftd2xx.1.4.24.dylib
3. Create a symlink: sudo ln -sf /usr/local/lib/libftd2xx.1.4.24.dylib /usr/local/lib/libftd2xx.dylib
4. Set the permissions for symlink: sudo chmod a+r /usr/local/lib/libftd2xx.dylib
5. Check that the AppleFTDI driver is not enabled: sudo kextunload -b com.apple.driver.AppleUSBFTDI

### Windows

Put the ftd2xx.dll file in the dependencies/win folder into your C:/windows/system32 folder

### Still having problems?

Try installing qlc plus https://qlcplus.org/ which may set up some extra system properties that need to be set

##

```html
<a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=SW3V3XNHNU9ME">
  <img src="https://raw.githubusercontent.com/stefan-niedermann/paypal-donate-button/master/paypal-donate-button.png" alt="Donate with PayPal" />
</a>
```
If you find this project useful then feel free to make a small donation.

This library was create for the DMX Lighting for Artists workshop for OFCourse in Shanghai, that was run in 2019 and continuously updated since. Credit to Omar Khan whose code helped me to understand how to interface with FTDI device https://github.com/orcaomar
