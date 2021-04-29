package com.jaysonh.dmx4artists;

// import packages
import static processing.core.PApplet.*;
import processing.core.PApplet;
import processing.*;
import java.util.List;

/** 
 * Parent class for all DMXParams 
 * @author Jayson Haebich
 * @author www.jaysonh.com
 * @version 0.1
 * @since   0.1
 */
public abstract class DMXParam
{
  /************************************************************************************
   * Public Methods
   ************************************************************************************/

  /**
   * Constructor
   *
   * @return nothing
   */  
  public DMXParam()
  {    
  }
  
 /**
  * Has the param finished running
  *
  * @return true/false if has finished
  */ 
  public boolean hasFinished()
  {
     return this.started && this.finished;
  }
  
 /**
  * Constructor
  *
  * @return nothing
  */  
  public DMXParam getLaunchTrigger()
  {
     return triggerParam;
  }
  
  /**
   * abstract function that is implemented by each individual param
   */
  public abstract void update();
  
  /**
   * This adds a param that is started when the current param finishes
   *
   * @param  param to add that triggers when this param finishes running
   * @return nothing
   */
  public void addTrigger( DMXParam triggerParam )
  {
      this.triggerParam =  triggerParam; // store the param trigger
    
      // By default set the trigger param to not auto start, since it should start when 
      // it's parent DMXParam has finished
      this.triggerParam.setAutoStart( false );                               

  }
  
  /**
   * Start running the thread for this param
   *
   * @return nothing
   */
  public void start()
  {
    if( !started )
    {  
       startTime = appRef.millis();
       started   = true;
    }
  }
  
  /**
   * Reset the param, restart the thread  
   *
   * @return nothing
   */
  public void reset()
  {
    started = false;
  }
  
  /**
   * Set the value of the param
   *
   * @param  value to set
   * @return nothing
   */
  public void setValue( int value )
  { 
    this.value = value;
  }  
  
  /**
   * Set whether the param will autostart
   *
   * @param  autoStart do we autostart the param
   * @return nothing
   */
  public void setAutoStart( boolean autoStart )
  {
    this.autoStart=autoStart;
  }
  
  /**
   * Get if the param has started
   *
   * @return if the param has started
   */
  public boolean getStarted()
  {
    return started;
  }
  
  
  /**
   * Get the value of the param
   *
   * @return value of the param
   */
  
  public float getValue()
  {
	  return value;
  }
  
  /**
   * Get the auto start
   *
   * @return if the param will auto start
   */
  public boolean getAutoStart()
  {
    return autoStart;
  }
   
   /**
   * Get the type of the param as a string (used for debugging)
   *
   * @return type of the param as a string 
   */
  abstract public String getType();
  
  
 /************************************************************************************
  * Protected Variables
  ************************************************************************************/           
  
  protected float   value     = 0;     // Value that this param holds
  protected long    startTime;         // Time that the param started
  protected boolean autoStart = true;  // Will this param start automatically when program starts or will need to be triggered manually to start
  protected boolean started   = false; // has the param started or not
  protected boolean finished  = false; // has the param finsihed
  
  protected DMXParam triggerParam;     // Param to trigger when this one finishes
  protected PApplet appRef;            // Reference to processing app

}
