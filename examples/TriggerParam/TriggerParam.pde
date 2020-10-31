/**
 *
 * Example: Trigger param
 *
 * Shows how you can trigger one param to change after another param is finished
 * For example you can make a light pulse once slowly then when it finishes trigger it to pulse slowly
 *
 * Assumes you have connected two typical RGB can lights
 * where channel 1 is master fader, channel 2 is red, channel 3 is green, channel 4 is blue
 */
 
import com.jaysonh.dmx4artists.*;

DMXControl dmx;
DMXFixture light;

int lightAddress    = 1;
int numLightChannels = 8; // number of channels for the light (master fade, red, green, blue)
int numDmxChannels   = 511;  // total number of channels allocated for the dmx device

// These control a param
DMXParam fadeSlow;
DMXParam fadeFast;

void setup()
{
   size( 600, 600 );
   
   // Connect to the first dmx usb device available
   dmx = new DMXControl( 0, numDmxChannels );
   
   light = new DMXFixture( this, lightAddress, numLightChannels); 
   
   dmx.addFixture( light ); // add the rgb light to the dmx controller
   
   // Set up our param oscillators
   // The way these work is you create them at startup and assign them to a fixture channel
   // The params will change over time and automatically be sent to that param
   fadeSlow = new DMXParamOsc( this,
                               10.0,  // oscillate over 10 seconds
                                  0,  // min dmx value
                                255,  // max dmx value
             MoveBehaviour.OSC_SINE,  // This is the type of movement, OSC_SINE means follow a sin function over time
                                  1); // num times to repeat 0 = infinite repeat
                             
   fadeFast = new DMXParamOsc( this,
                                2.5,  // oscillate over 2.5 seconds
                                  0,  // min dmx value
                                255,  // max dmx value
             MoveBehaviour.OSC_SINE,  // This is the type of movement, OSC_SINE means follow a sin function over time
                                 0 ); // num times to repeat 0 = infinite repeat
 
   // add the fadeFast trigger to the fadeSlow trigger, so that when the fadeSlow trigger finishes the 
   // fadeFast trigger will begin
   fadeSlow.addTrigger( fadeFast );
   
   // set light 1 to be purple
   light.setParam( 0, fadeSlow ); // set the fader channel to the slow oscillator
   light.setParam( 1, 255 ); // set the red channel to 255
   light.setParam( 2, 0 );   // set the green channel to 0
   light.setParam( 3, 255 ); // set the blue channel to 255
  
}

void draw()
{
    background( 0 );
}
