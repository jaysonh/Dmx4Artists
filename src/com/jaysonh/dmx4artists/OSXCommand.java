package com.jaysonh.dmx4artists;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

/**
 *
 *
 */

public class OSXCommand 
{
  public static final String VERSION = "##library.prettyVersion##";

  protected final ArrayList<String> outputBuffer = new ArrayList<String>();
  protected final Runtime runtime = Runtime.getRuntime();

  public String command;
  public boolean success;

  /**
   * 
   */
  public OSXCommand(final String theCommand) {
    command = theCommand;
  }

  /**
   * Runs the command. Returns true if the command was successful.
   * The output of the command can be accessed by calling getOutput().
   *
   * @return true if the command ran successfully,
   * false if there was an error running the command.
   */
  public boolean run() {
    success = false;
    outputBuffer.clear();

     try {
      final Process process = runtime.exec(command);

      final BufferedReader
        out = new BufferedReader(new InputStreamReader(process.getInputStream())), 
        err = new BufferedReader(new InputStreamReader(process.getErrorStream()));

      String read;
      while ((read = out.readLine()) != null)  outputBuffer.add(read);

      final String msg = "COMMAND ERROR(s):\n";
      final StringBuilder sb = new StringBuilder(msg);

      while ((read = err.readLine()) != null)  sb.append(read).append('\n');
      if (sb.length() != msg.length())  System.err.println(sb);

      success = process.waitFor() == 0;
    }
    catch (final IOException e) {
      System.err.println("COMMAND ERROR: " + e.getMessage());
    }
    catch (final InterruptedException e) {
      System.err.println("COMMAND INTERRUPTED: " + e.getMessage());
    }

    return success;
  }

  /**
   * Returns each line of the command's output as an Array of String objects.
   * Useful if you need to capture the results from running a command. 
   */
  public String[] getOutput() {
    return outputBuffer.toArray(new String[outputBuffer.size()]);
  }

  /**
   * Returns each line of the command's output as a List of String objects.
   * Useful if you need to capture the results from running a command. 
   */
  @SuppressWarnings("unchecked") public List<String> getOutputAsList() {
    return (List<String>) outputBuffer.clone();
  }

  /**
   * Returns the command String being used.
   *
   * @return String
   */
  @Override public String toString() {
    return command + ' ' + success;
  }
}