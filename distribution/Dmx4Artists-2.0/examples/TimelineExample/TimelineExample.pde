import com.jaysonh.dmx4artists.*;

DMXControl dmx;
DMXFixture fixture;

DMXParam   fadeOscSlow;
DMXParam   fadeOscFast;

int maxDmxChannels   = 9;
int numLightChannels = 8;
int lightAddress     = 1;

Timeline timeline;
TimelineDisplay timeDisplay;

void setup()
{
  size(600,600);
  
  dmx     = new DMXControl( 0, maxDmxChannels );  // connect to the first dmx device (0)
  
  
  fadeOscSlow = new DMXParamOsc( this,
                                  5.0, // oscillate over 5 seconds
                                    0, // min dmx value
                                  255, // max dmx value
                               MoveBehaviour.OSC_SINE, 
                                    1,// num times to repeat 0 = infinite repeat
                                    false); // do not autostart
                             
   fadeOscFast = new DMXParamOsc( this,
                                  2.5, // oscillate over 2.5 seconds
                                  0, // min dmx value
                                  255, // max dmx value
                                  MoveBehaviour.OSC_SINE, 
                                  2, // num times to repeat 0 = infinite repeat
                                  false); // do not autostart
                                  
  fixture = new DMXFixture( this, lightAddress, numLightChannels); // create a new fixture
  dmx.addFixture( fixture );  // add the fixture to the dmx controller
  
  
  fixture.setParam(2,255);
  fixture.setParam(3,255);
  
  DMXParamStatic p0 = new DMXParamStatic(this,50);
  DMXParamStatic p1 = new DMXParamStatic(this,100);
  DMXParamStatic p2 = new DMXParamStatic(this,150);
  DMXParamStatic p3 = new DMXParamStatic(this,200);
  DMXParamStatic p4 = new DMXParamStatic(this,250);
  
  timeDisplay = new TimelineDisplay( width, height,this );
  timeline = new Timeline( this, 30.0 ); // create a timline with a duration of 30 seconds, it will repeat by default
 
  timeline.add( fixture, fadeOscSlow, 1, 1.0, 5.0);
  timeline.add( fixture, p1,          1, 8.0, 10.0);
  timeline.add( fixture, fadeOscFast, 1, 10.0,15.0);
  timeline.add( fixture, p3,          1, 15.0,20.0);
  timeline.add( fixture, p4,          1, 20.0,25.0);
  
  timeline.begin();
}

void draw()
{
  background(0,0,0);

  PGraphics timelineDisp = timeDisplay.update( timeline, width, height );
  image( timelineDisp, 0, 0 );
}