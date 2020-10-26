package com.jaysonh.dmx4artists;

 /** 
  * Enum for the different types of movement behaviour of a DMXParamOsc
  *
  * @author Jayson Haebich
  * @author www.jaysonh.com
  * @version 0.1
  * @since   0.1
  */
public enum MoveBehaviour{ OSC_LINEAR,      // straight up and down
                           OSC_LINEAR_RAMP, // straight up, then restart at 0, saw wave
                           OSC_SINE,        // up and down with a sin wave (rounded shape)
                           OSC_EXP         // Exponential ramp up, then restart at zero
                           
  }
  
