package edu.frostburg.cosc310.fa20.ClarkChaseDFS;

import edu.frostburg.cosc310.fa20.COSC310_P01;

public class CCGraphDFS implements COSC310_P01 {

    int[][] adjMatrix = createAdjMatrix("worldmap_0000.txt",26);

    private int[][] createAdjMatrix(String input, int arrayLength) {
        var matrix = new int[arrayLength][arrayLength];

        // read file from disk




        return matrix;
    }

    /**
     * Convert a letter to index for our matrix.
     * Get the ASCII code and subtract 65 (uppercase)
     * @param c char letter
     * @return an index where A = 0 and Z = 25
     */
    private int convertLetterToIndex(char c)
    {
        return (int)Character.toUpperCase(c) - 65;
    }

    @Override
    public void go() {
        System.out.println("Program starting...");
        System.out.println("Created by: " + getMyName());

        System.out.println("Testing...");
        System.out.println(convertLetterToIndex('A'));
        System.out.println(convertLetterToIndex('Z'));
    }

    @Override
    public String getMyName() {
        return "Chase Clark";
    }

    /**
     * Visit the adjacent vertices in alphabetic order. (If the adjacents are A, B, and C, visit A first).
     * Output: print results to console
     */
    @Override
    public void runDFS1() {

    }

    /**
     * There is a good chance that our robot would say no to difficult tasks, so:
     * Do not visit any vertices with an edge cost > 10
     * Always select the next vertex with the lowest edge cost
     * After visiting 5 vertices, give up no matter what
     */
    @Override
    public void runDFS2() {

    }

    /**
     * Vertices can be visited up to 3 times each. (To make sure we didn’t miss anything!)
     * Always select the next vertex that you haven’t visited yet if possible
     * Otherwise, select the vertex with the greatest edge cost
     */
    @Override
    public void runDFS3() {

    }
}
