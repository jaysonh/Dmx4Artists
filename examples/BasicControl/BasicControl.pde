
/**
 *
 * Example: BasicControl
 *
 * This is a simple example where we open the first available dmx device and set dmx channels
 * 0 and 1 to the mouse value
 */
 
//import com.jaysonh.dmx4artists.*;

//DMXControl dmx;


void setup()
{
   size( 600, 600 );
}

void draw()
{
  	background( 0 );
  	
  	int brightness = ( int ) map( mouseX, 0, width, 0, 255 ); // take the mouse pos and convert it to a number between 0 - 255
  	
  	
}