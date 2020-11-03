/**
 *
 * Example: BasicControl
 *
 * This is a simple example where we open the first available dmx device and set dmx channels
 * 0 and 1 to the mouse value
 * Assumes you have connected a typical RGB can light where channel 0 is master fader and channel 1 is red (or white)
 */
 
import com.jaysonh.dmx4artists.*;

DMXControl dmx;

int numDmxChannels = 8; // number of channels we will use

void setup()
{
   size( 600, 600 );
   
   // Connect to the first dmx usb device available
   dmx = new DMXControl( 0, numDmxChannels );
}

void draw()
{
  	background( 0 );
  	
  	// take the mouse pos and convert it to a number between 0 - 255
  	int brightness = ( int ) map( mouseX, 0, width, 0, 255 ); 
  	
  	// Send the mapped value to the dmx channels 1 and 2 to control the light
    dmx.sendValue( 1, brightness );
    dmx.sendValue( 2, brightness );
}