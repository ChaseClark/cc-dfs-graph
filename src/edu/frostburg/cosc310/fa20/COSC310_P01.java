/*
 * Interface for The Totally Legit World Peach Project.  Create your own concrete class that 
 * 	implements this interface.  Please do not modify the interface or the provided input files.
 */
package edu.frostburg.cosc310.fa20;

/**
 * Public interface for Graph Project.
 * 
 * @author Steve Kennedy
 */

public interface COSC310_P01 {

	/**
	 * Runs project. You will create an instance of your project in main and then
	 * have it invoke this method to begin running.
	 */
	public void go();

	/** Returns your name **/
	public String getMyName();

	
	/** The following methods should be implemented for this assignment. */
	
	public void runDFS1(); // alphabetical
	
	public void runDFS2(); // lazy
	
	public void runDFS3(); // indecisive

}
