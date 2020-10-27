# DMX4Artists
This is a processing library to control generic FTDI DMX USB devices, such as the Enttec Open DMX device. The library is designed to be as easy to use as possible for people that are new to programming and DMX lighting. It includes methods and classes for creating timelines, oscilators, trigger events and other behaviours to make it easy to control your DMX based lighting fixtures.

## Setting up

This library has been tested and works with both Windows and OSX. It is designed to be used with generic FTDI DMX USB devices which are commonly found in the cheap usb DMX controllers you see on Taobao and Aliexpress. 

![USB DMX Device](assets/images/usb-dmx.png)

### OSX

To use on osx you need to first disable the FTDI drivers that are installed by default on osx. To do this download and run this program: http://www.dmxis.com/release/FtdiDriverControl.zip

Press the "Click to disable the driver" button, you should then be able to run the examples. 

### Windows

Should work straight away without any changes necessary.

##

This library was create for the DMX Lighting for Artists workshop for OFCourse in Shanghai, that was run in 2019. Credit to Omar Khan whose code helped me to understand how to interface with FTDI device https://github.com/orcaomar
