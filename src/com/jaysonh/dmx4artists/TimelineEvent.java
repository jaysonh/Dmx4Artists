package com.jaysonh.dmx4artists;


 /** 
  * Timeline event for adding to a timeline
  *
  * @author Jayson Haebich
  * @author www.jaysonh.com
  * @version 0.1
  * @since   0.1
  */
public class TimelineEvent
{
   /************************************************************************************
    * Public Methods
    ************************************************************************************/  
   
   /**
    * Setup this Timeline event
    * @param parentFixture fixture that this event applies on 
    * @param param         param that is used
    * @param paramChannel  channel of param that is affected
    * @param start         start time in seconds
    * 
    * @return nothing
    */
   public TimelineEvent( DMXFixture parentFixture, // fixture to assign this timeline event to
                         DMXParam   param,         // dmx param to assign this timeline event to
                         int        paramChannel,  // param indx to assign to
                         float      start          // start time (seconds)
                       )
   {
     setup(parentFixture,param, paramChannel, start, -1.0f, false); // -1.0 signifies that the param should continue until it
                                                            // completes it's behaviour, useful for OSCParams
   } 
   
   /**
    * Constructor
    *
    * @param parentFixture fixture that this event applies on 
    * @param param         param that is used
    * @param paramChannel  channel of param that is affected
    * @param start         start time in seconds
    * @param end           end time in seconds
    * 
    * @return nothing
    */
   public TimelineEvent( DMXFixture parentFixture, // fixture to assign this timeline event to
                         DMXParam   param,         // dmx param to assign this timeline event to
                         int        paramChannel,  // param indx to assign to
                         float      start,         // start time (seconds)  
                         float      end            // end time (seconds) 
                 )
   {
     setup( parentFixture, param, paramChannel, start, end, false );
   } 
   
   public TimelineEvent( DMXFixture parentFixture, // fixture to assign this timeline event to
           DMXParam   param,         // dmx param to assign this timeline event to
           int        paramChannel,  // param indx to assign to
           float      start,         // start time (seconds)  
           float      end,           // end time (seconds) 
           boolean    contValue
   )
{
setup( parentFixture, param, paramChannel, start, end, contValue );
} 
   
   /**
    * Setup this Timeline event
    *
    * @param parentFixture fixture that this event applies on 
    * @param param         param that is used
    * @param paramChannel  channel of param that is affected
    * @param start         start time in seconds
    * @param end           end time in seconds
    * 
    * @return nothing
    */
   public void setup( DMXFixture  parentFixture, // fixture to assign this timeline event to
                      DMXParam param,            // dmx param to assign this timeline event to
                      int      paramChannel,     // param indx to assign to
                      float    start,            // start time (seconds)
                      float    end,              // end time (seconds) 
                      boolean  contValue		 // continue value
             )
   {
     this.start        = start;
     this.end          = end;
     this.paramChannel = paramChannel;
     this.param        = param.getCopy();
     this.fixture      = parentFixture;
     this.contValue    = contValue;
   }
   
   /**
    * Reset the param of the timeline
    * 
    * @return nothing
    */
   public void reset()
   {
       param.reset();
   }
   
   /**
    * Update the timeline event
    * 
    * @param timer the current time of the parent timeline
    * @return nothing
    */
   public void update( float parentTime )
   {
       // start the param if it hasn't been started and after start time
	   if( parentTime >= start && parentTime < end && !param.getStarted() )
	   {
		   // This is for the next version
		   /*if( contValue )
		   {

			   int lastVal = ( int )fixture.getParam( paramChannel ).getValue();

			   param.setStartVal( lastVal );
		   }*/
		   
		   fixture.setParam( paramChannel, param );
		   
	       
		   param.start(); 
	   }

	   if( parentTime > end )
	   {
		    param.stop();
	   }
	   
       /*if( parentTime >= start &&  parentTime < end && !param.getStarted() && !param.hasFinished() )
       {
          // assign param to fixture
          fixture.setParam( paramChannel, param );
          param.start(); 
       }
       
       // Need to end the param here
       if( parentTime >=  end  )
       {
    	   System.out.println("Ending param");
           param.setFinished( true );
       } */
   }  
   
   /**
    * get the start time of this event 
    * 
    * @return The start time of this event as seconds as a float
    */
   public float getStart() { return start; }
   
   /**
    * get the end time of this event 
    * 
    * @return The end time of this event as seconds as a float
    */
   public float getEnd()   { return end; }
   
   /**
    * get the fixture
    * 
    * @return The fixture  of this timeline event
    */
   public DMXFixture getFixture() { return fixture; }
   
   /**
    * get the param
    * 
    * @return The param  of this timeline event
    */
   public DMXParam   getParam()   { return param;   }
   
   /**
    * get the param channel
    * 
    * @return The param channel of this timeline event
    */
   public int   getParamChannel()   { return paramChannel;   }
   
   /**
    * Set status to continue from previous value
    * if true then the timeline event will continue, 
    * if false it will restart from 0
    * 
    * @param status
    */
   public void setContinueValue( boolean status )
   {
	   this.contValue = status;
   }
   
  /************************************************************************************
   * Private Variables
   ************************************************************************************/
   
    private float      start;        // start time of this timeline event
    private float      end;          // end time of this timleine event
    private DMXParam   param;        // param that is affected
    private DMXFixture fixture;      // fixture that is controlled
    private int        paramChannel; // param channel that is affected
    private boolean    contValue;    // continue from the previous value, if false it 
    								 // will restart from 0
}  
