/**
 *
 * Example: Fade up down
 *
 * Shows how you can fade a fixtures brightness up and down using an oscillator param
 *
 * Assumes you have connected two typical RGB can lights
 * where channel 1 is master fader, channel 2 is red, channel 3 is green, channel 4 is blue
 */
 
import com.jaysonh.dmx4artists.*;

DMXControl dmx;
DMXFixture light1;
DMXFixture light2;

int lightAddress1    = 1;
int lightAddress2    = 9;
int numLightChannels = 8; // number of channels for the light (master fade, red, green, blue)
int numDmxChannels   = 512;  // total number of channels allocated for the dmx device

// These control a param
DMXParam fadeSlow;
DMXParam fadeFast;

void setup()
{
   size( 600, 600 );
   
   // Connect to the first dmx usb device available
   dmx = new DMXControl( 0, numDmxChannels );
   
   light1 = new DMXFixture( this, lightAddress1, numLightChannels); 
   light2 = new DMXFixture( this, lightAddress2, numLightChannels); 
   
   dmx.addFixture( light1 ); // add the rgb light to the dmx controller
   dmx.addFixture( light2 ); // add the rgb light to the dmx controller
   
   // Set up our param oscillators
   // The way these work is you create them at startup and assign them to a fixture channel
   // The params will change over time and automatically be sent to that param
   fadeSlow = new DMXParamOsc( this,
                               10.0,  // oscillate over 10 seconds
                                  0,  // min dmx value
                                255,  // max dmx value
             MoveBehaviour.OSC_SINE,  // This is the type of movement, OSC_SINE means follow a sin function over time
                                  0,  // num times to repeat 0 = infinite repeat
                                   true); // autostart 
                             
   fadeFast = new DMXParamOsc( this,
                                2.5,  // oscillate over 2.5 seconds
                                  0,  // min dmx value
                                255,  // max dmx value
             MoveBehaviour.OSC_SINE,  // This is the type of movement, OSC_SINE means follow a sin function over time
                                 0,   // num times to repeat 0 = infinite repeat
                                 true);  // autostart
 
 
   // set light 1 to be purple
   light1.setParam( 1, fadeSlow ); // set the fader channel to the slow oscillator
   light1.setParam( 2, 255 ); // set the red channel to 255
   light1.setParam( 3, 0 );   // set the green channel to 0
   light1.setParam( 4, 255 ); // set the blue channel to 255
   
   // set light 2 to be green
   light2.setParam( 1, fadeFast ); // set the fader channel to the fast oscillator
   light2.setParam( 2, 0 );        // set the red channel to 0
   light2.setParam( 3, 255 );      // set the green channel to 255
   light2.setParam( 4, 0 );        // set the blue channel to 0
   
}

void draw()
{
    background( 0 );
}