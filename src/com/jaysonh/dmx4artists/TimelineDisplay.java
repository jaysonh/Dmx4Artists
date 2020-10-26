package com.jaysonh.dmx4artists;

// Import packages
import processing.core.*;
import java.util.*;
import processing.core.PGraphics;
import processing.core.PApplet; 
import static processing.core.PApplet.*;

 /** 
  * Class to display a timeline to the screen, useful for debugging and visually seeing your dmx timeline
  *
  * @author Jayson Haebich
  * @author www.jaysonh.com
  * @version 0.1
  * @since   0.1
  */
public class TimelineDisplay
{
  /************************************************************************************
   * Public methods
   ************************************************************************************/  
   
   /**
    * Constructor
    * 
    * @param width width of the canvas
    * @param height height of the canvas
    * @param appRef reference to processing sketch    
    */
   public TimelineDisplay( int width, int height, PApplet appRef )
   {
       this.appRef = appRef; // need to store this so that we can use some in built processing functions
       
       resizeCanvas( width, height );
   }
   
   /**
    * update and return the canvas of the timeline display
    * 
    * @param  timeline timeline that is being drawn
    * @param  width width of the canvas to draw
    * @param  height height of the canvas to draw
    * @return canvas that has been drawn into 
    */
   public PGraphics update( Timeline timeline, int width, int height )
   {
       // Resize canvas if necessary
       if( width != this.width || height != this.height )
       {
           resizeCanvas( width, height );    
       }
       
       canvas.beginDraw();
       
       canvas.background( 0 );
       
       // draw all the fixtures
       ArrayList < TimelineEvent > timelineEvents = timeline.getEvents();
       
       canvas.noStroke();
       appRef.randomSeed(0);
       
       float eventHeight = this.height / (float)timelineEvents.size();
       
       // Draw all our events
       int numEvents = 0;
       for( TimelineEvent event : timelineEvents )
       {
          float start = event.getStart();
          float end   = event.getEnd();
          
          float xPosStart = PApplet.map( start, 0, timeline.getDuration(), 0, this.width );
          float xPosEnd   = PApplet.map( end,   0, timeline.getDuration(), 0, this.width );
          
          canvas.fill( 255 );
          canvas.text( "addr:" + event.getFixture().getAddress(), xPosStart, (float)numEvents * eventHeight + 10 );
          canvas.text( "type:" + event.getParam().getType(),      xPosStart, (float)numEvents * eventHeight + 20 );
          
          canvas.fill( appRef.random( 0.0f, 255.0f ), 125.0f );
          canvas.rect( xPosStart,(float)numEvents * eventHeight, xPosEnd-xPosStart, eventHeight );
          
          numEvents++;
       }
       
       // Write the timeline position
       canvas.fill( 255 );
       canvas.text( (int)timeline.getPosition() + "/" + (int)timeline.getDuration(), 0, this.height-10);
       
       float timePos = map( timeline.getPositionPerc(), 0.0f, 1.0f, 0.0f, (float)this.width);
       
       // Draw a line showing the timleine position
       canvas.stroke( 255, 255, 255);
       canvas.line( timePos, 0, timePos, this.height );
       canvas.endDraw();
       
       return canvas;  
   }
   
  /************************************************************************************
   * Private Methods
   ************************************************************************************/  
 
  /**
    * resize the canvas of the timeline display
    *
    * @param w  width of window to resize to
    * @param h  heigh tof window to resize to
    * @return nothing
    */
   private void resizeCanvas( int w, int h )
   {
       this.width  = w;
       this.height = h;
           
       canvas = appRef.createGraphics( w, h ); // recreate canvas with new dimensions
   }
   
   /************************************************************************************
   * Private Variables
   ************************************************************************************/  
      
   private int       width;  // width of canvas
   private int       height; // height of canvas
   private PGraphics canvas; // canvas that this dispaly is drawn into
   private PApplet   appRef; // reference to parent object
}
