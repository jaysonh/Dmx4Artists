/**
 *
 * Example: ConnectDevice
 *
 * This example shows connecting to a device with a specified serialNum
 * Useful if you have multiple devices connected and want to connect to a specific one of them
 *
 * Assumes you have connected a typical RGB can light where channel 1 is master fader,
 * channel 2 is red, channel 3 is green, channel 4 is blue
 */
 
import com.jaysonh.dmx4artists.*;

DMXControl dmx;
DMXFixture light;

int lightAddress     = 1;
int numLightChannels = 8; // number of channels for the light (master fade, red, green, blue)
int numDmxChannels   = 512;  // total number of channels allocated for the dmx device

void setup()
{
   size( 600, 600 );
   
   // Connect to dmx device with serial number: AG0JGSJV
   dmx = new DMXControl( "AG0JGSJV", numDmxChannels );
   
   light = new DMXFixture( this, lightAddress, numLightChannels); 
   
   dmx.addFixture( light ); // add the rgb light to the dmx controller
   
   // set light to be purple
   light.setParam( 1, 255 ); // set the fader channel to 255
   light.setParam( 2, 255 ); // set the red channel to 255
   light.setParam( 3, 0 );   // set the green channel to 0
   light.setParam( 4, 255 ); // set the blue channel to 255
}

void draw()
{
    background( 0 );
}