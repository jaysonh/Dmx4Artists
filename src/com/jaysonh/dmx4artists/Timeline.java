//package com.jaysonh.dmx4artists;

// Import packages
import java.util.*;
import java.util.ArrayList;
import static processing.core.PApplet.*;
import processing.core.PApplet;
import processing.*;

 /** 
  * Timeline for adding params to
  *
  * @author Jayson Haebich
  * @author www.jaysonh.com
  * @version 0.1
  * @since   0.1
  */
public class Timeline extends Thread
{
  /************************************************************************************
   * Public Methods
   ************************************************************************************/  
   
  /**
   * Constructor
   * 
   * @param appRef   reference to processing sketch    
   * @param duration total length of the timeline
   */ 
  public Timeline( PApplet appRef , float duration )
  {
     this.duration  = duration;
     this.appRef    = appRef;
     timelineEvents = new ArrayList < TimelineEvent >();
  }
  
  /**
   * Start the timeline
   * 
   * @return nothing
   */ 
  public void begin()
  {
     start   = ( float ) appRef.millis() / 1000.0f; // get the current time in seconds
     end     = start + duration;                    // calculate the end time
     running = true;
     
     this.start();  // start thread running
  }
    
  /**
   * Reset the timeline
   * 
   * @return nothing
   */ 
  public void resetTimeline()
  {
      float currTime = ( ( float ) appRef.millis() ) / 1000.0f; // current time as seconds
      
      start    = start    + duration; // move new start
      position = currTime - start;    // recalc start
          
      // reset everything on timeline
      for( TimelineEvent t : timelineEvents )
      {
         t.reset();
      }
  }
  
  /**
   * Thread function, continously runs until thread ends
   * 
   * @return nothing
   */ 
  public void run()
  {
    while(running)
    {
      float currTime = ( ( float ) appRef.millis() ) / 1000.0f; // get current time as second
      position = currTime - start; 
      
      if( repeat && position > duration ) // if we are past the end
      {
        resetTimeline( );
        
      }
      
      // Update each event in this timeline
      for( TimelineEvent t : timelineEvents )
      {
          t.update( position );
      }
      
      // Sleep the thread to allow other processes to run
      try
      {
          Thread.sleep( THREAD_SLEEP_TIME );
      }catch( InterruptedException e )
      {
          e.printStackTrace();
      }
    }
  }
  /**
   * Add a event to this timeline
   * 
   * @param  parentFixture  fixture for the event to processing sketch    
   * @param  param          param for the event
   * @param  paramChannel   channel that is effected
   * @param  start          start time for the event
   * @param  end            end time for the event
   * @return nothing
   */ 
  public void add( DMXFixture  parentFixture, // fixture to assign this timeline event to
                   DMXParam param,            // dmx param to assign this timeline event to
                   int      paramChannel,     // param indx to assign to
                   float    start,            // start time (seconds)
                   float    end               // end time (seconds) 
                )
  {
      // add a new event to timeline with all the parameters
      timelineEvents.add( new TimelineEvent( parentFixture,
                                             param,
                                             paramChannel,
                                             start,
                                             end ) );
  }
  
  
  /**
   * Set the event to repeat or not
   *
   * @param  repeat if the param will repeat
   * @return nothing
   */
  public void setRepeat( boolean repeat )
  {
      this.repeat = repeat;
  }
  
  /**
   * Get the events from this timeline
   *
   * @return arraylist of events
   */
  public ArrayList < TimelineEvent > getEvents()
  {
      return timelineEvents; 
  }
  
  /**
   * Get the ptotal duration as seconds
   * 
   * @return duration fo the timeline as seconds
   */
  public float getDuration()
  {
      return duration;
  }
  
  /**
   * Get the position of the timeline as second
   * 
   * @return position of the timeline in seconds
   */ 
  public float getPosition()
  {
     return position;
  }
  
  /**
   * Get the position of the timeline as a percentage (0.0-1.0)
   * 
   * @return position of the timeline as percentage
   */
  public float getPositionPerc()
  {
     return position / duration; 
  }
  /************************************************************************************
   * Public Constants
   ************************************************************************************/
     
  public final int THREAD_SLEEP_TIME = 50;
  
  /************************************************************************************
   * Private Variables
   ************************************************************************************/  
   
  private boolean repeat   = true;  // If the timeline repeats when finished
  private float   start    = 0.0f;  // start time of timeline
  private float   end      = 0.0f;  // end time of timeline
  private float   duration = 0.0f;  // total duration of timeline
  private boolean running  = false; // is the thread running
  private float   position = 0.0f;  // current position of timeline 
  private PApplet appRef;           // reference to the parent processing sketch to access processing methods
  
  private ArrayList < TimelineEvent > timelineEvents;  // list of all the timeline events
}
