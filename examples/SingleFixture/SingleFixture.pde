/**
 *
 * Example: Single Fixture
 *
 * This is a simple example where we open the first available dmx device and set a
 * a random colour when a key is pressed
 *
 * Assumes you have connected a typical RGB can light where channel 1 is master fader,
 * channel 2 is red, channel 3 is green, channel 4 is blue
 */
 
import com.jaysonh.dmx4artists.*;

DMXControl dmx;
DMXFixture rgbLight;
int lightAddress     = 0;
int numLightChannels = 4; // number of channels for the light (master fade, red, green, blue)
int numDmxChannels   = 4;  // total number of channels allocated for the dmx device

void setup()
{
   size( 600, 600 );
   
   // Connect to the first dmx usb device available
   dmx = new DMXControl( 0, numDmxChannels );
   
   rgbLight = new DMXFixture( this, lightAddress, numLightChannels); 
   dmx.addFixture( rgbLight ); // add the rgb light to the dmx controller
   
   rgbLight.setParam( 1, 255 ); // set the fader channel to 255
   rgbLight.setParam( 2, 255 ); // set the red channel to 255
   rgbLight.setParam( 3, 255 ); // set the green channel to 0
   rgbLight.setParam( 4, 255 ); // set the blue channel to 0
   
}

void draw()
{
    background( 0 );
}

void keyPressed()
{
   // when we detect a key press then set the rgb light to a random colour 
   rgbLight.setParam( 2, ( int )random( 0, 255 ) ); // red
   rgbLight.setParam( 3, ( int )random( 0, 255 ) ); // green
   rgbLight.setParam( 4, ( int )random( 0, 255 ) ); // blue
}