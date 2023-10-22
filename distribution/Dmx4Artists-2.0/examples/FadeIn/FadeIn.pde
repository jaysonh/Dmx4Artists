/**
 *
 * Example: FadeIn
 *
 * This examples shows how you can set lights to fade to a value
 * So instead of instantly changing the colour it softly moves that colour for nicer transitions
 * Assumes you have connected a typical RGB can light where channel 0 is master fader and channel 1 is red (or white)
 */
 
import com.jaysonh.dmx4artists.*;

DMXControl dmx;

int numDmxChannels = 256; // number of channels we will use

int lightAddress  = 1;
int lightChannels = 8;

DMXFixture fixture;

void setup()
{
   size( 600, 600 );
   
   // Connect to the first dmx usb device available
   dmx = new DMXControl( 0, numDmxChannels );
   
   dmx.setFixtureFade( true );    // Enable the fixture value fading in
   dmx.setFixtureFadeRate( 0.1 ); // (0.0-1.0) closer to 0 then the slower it will fade
   
   fixture = new DMXFixture( this, lightAddress, lightChannels );
   dmx.addFixture( fixture );
}

void draw()
{
    background( 0 );
    
    // take the mouse pos and convert it to a number between 0 - 255
    int brightness = ( int ) map( mouseX, 0, width, 0, 255 ); 
    
}

void mousePressed()
{
     int brightness = (int)random(0,255);
    // Send the mapped value to the dmx channels 1 and 2 to control the light
    fixture.sendValue( 1, brightness );
    fixture.sendValue( 2, 255);
}
