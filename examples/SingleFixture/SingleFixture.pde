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
int lightAddress     = 1;
int numLightChannels = 8; // number of channels for the light (master fade, red, green, blue)
int numDmxChannels   = 256;  // total number of channels allocated for the dmx device

void setup()
{
   size( 600, 600 );
   
   // Connect to the first dmx usb device available
   dmx = new DMXControl( 0, numDmxChannels );
   
   rgbLight = new DMXFixture( this, lightAddress, numLightChannels); 
   dmx.addFixture( rgbLight ); // add the rgb light to the dmx controller
   
   
}

void draw()
{
    background( 0 );
}

void keyPressed()
{
   // when we detect a key press then set the rgb light to a random colour
   rgbLight.sendValue( 1, 255 ); // set the fader channel to 255
   rgbLight.sendValue( 2, ( int )random( 0, 255 ) ); // red
   rgbLight.sendValue( 3, ( int )random( 0, 255 ) ); // green
   rgbLight.sendValue( 4, ( int )random( 0, 255 ) ); // blue
   //rgbLight.sendValue( 5, ( int )random( 0, 255 ) ); // white, uncomment if your light has a white channel
}