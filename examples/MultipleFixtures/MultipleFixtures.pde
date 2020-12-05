/**
 *
 * Example: Multiple Fixtures
 *
 * This is a simple example where we control two lights 
 *
 * Assumes you have connected a typical RGB can light where channel 1 is master fader,
 * channel 2 is red, channel 3 is green, channel 4 is blue
 */
 
import com.jaysonh.dmx4artists.*;

DMXControl dmx;
DMXFixture light1;
DMXFixture light2;

int lightAddress1    = 1;
int lightAddress2    = 7;
int numLightChannels = 6; // number of channels for the light (master fade, red, green, blue)
int numDmxChannels   = 511;  // total number of channels allocated for the dmx device, must not be more than 511 

void setup()
{
   size( 600, 600 );
   
   // Connect to the first dmx usb device available
   dmx = new DMXControl( 0, numDmxChannels );
   
   light1 = new DMXFixture( this, lightAddress1, numLightChannels); 
   light2 = new DMXFixture( this, lightAddress2, numLightChannels); 
   
   dmx.addFixture( light1 ); // add the rgb light to the dmx controller
   dmx.addFixture( light2 ); // add the rgb light to the dmx controller
   
   // set light 1 to be purple
   light1.setParam( 1, 255 ); // set the fader channel to 255
   light1.setParam( 2, 255 ); // set the red channel to 255
   light1.setParam( 3, 0 );   // set the green channel to 0
   light1.setParam( 4, 255 ); // set the blue channel to 255
   
   // set light 2 to be green
   light2.setParam( 1, 255 ); // set the fader channel to 255
   light2.setParam( 2, 0 );   // set the red channel to 0
   light2.setParam( 3, 255 ); // set the green channel to 255
   light2.setParam( 4, 0 );   // set the blue channel to 0
   
}

void draw()
{
    background( 0 );
}