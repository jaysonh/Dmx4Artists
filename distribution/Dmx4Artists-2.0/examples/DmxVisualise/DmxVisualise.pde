/**
 * DmxVisualiser
 *
 * This example shows how visualise the DmxFixtures on the screen 
 * useful if you don't have any lights of your own
 */
import com.jaysonh.dmx4artists.*;

DMXControl dmx;

DMXFixture fix1;
DMXFixture fix2;

// Our visualise object
Visualiser visualiser;

int parCanAddr     = 1;
int movingHeadAddr = 11;

int parCanNumChannels = 8;
int movingHeadNumChannels = 11;
void setup()
{
   size( 1280,720); 

   dmx = new DMXControl( 0, 256);
   
   fix1 = new DMXFixture( this, parCanAddr,     parCanNumChannels );
   fix2 = new DMXFixture( this, movingHeadAddr, movingHeadNumChannels ); // uses 11 channel dmx mode for moving head
                                                                         
                                                                         
   dmx.addFixture( fix1 );
   
   // This is the object that will draw the DmxFixtures to the screen
   visualiser = new Visualiser();
   visualiser.add( new ParCanFixture( fix1, 20, 20, 40, 40 ) );
   visualiser.add( new MovingHeadFixture( fix2, 100, 20, 40, 40, true ) ); // assumes that moving head is using rgb colour mode
   //visualiser.add( new MovingHeadFixture( fix2, 100, 20, 40, 40, false) ); 
}

void draw()
{
   background(0); 
   
   fix1.sendValue( 1, 255);
   fix1.sendValue( 2, 255-map( mouseY, 0, height, 0, 255 ));
   fix1.sendValue( 3,  map( mouseX, 0, width,  0, 255 ));
   fix1.sendValue( 4, map( mouseY, 0, height, 0, 255 ));
   
   fix2.sendValue( 2, map( mouseX, 0, width,  0, 255 ));
   fix2.sendValue( 4, map( mouseY, 0, height, 0, 255 ));
   fix2.sendValue( 6, 255);
   fix2.sendValue( 8, 255);
   fix2.sendValue( 9, 0);
   fix2.sendValue( 10, 255);
   
   visualiser.draw();
}
