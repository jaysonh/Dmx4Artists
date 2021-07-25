package com.jaysonh.dmx4artists;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.Process;
import java.util.List;
import java.util.ArrayList;
import processing.core.PApplet;

/**
 *
 *
 */

public class OSXCommand
{
   OSXCommand(String... args)
   {
        Process p = PApplet.exec(args);
        try 
        {
          int result = p.waitFor();
          BufferedReader stdInput = new BufferedReader(new 
           InputStreamReader(p.getInputStream()));
      
          BufferedReader stdError = new BufferedReader(new 
          InputStreamReader(p.getErrorStream()));
      
          // Read the output from the command
          String s = null;
          outputRes = "";
          
          while ((s = stdInput.readLine()) != null) 
          {
              outputLines.add( s );
          }
        } 
        catch (InterruptedException e) { e.printStackTrace(); }
        catch (IOException e)          { e.printStackTrace(); } 
   }
   
   String [] getOutput()
   {
     String [] lines = new String[ outputLines.size() ];
     
     lines = outputLines.toArray( lines );
     
     return lines;
   }
   
   ArrayList <String> outputLines = new ArrayList<String>();
   
   String outputRes = "";
}