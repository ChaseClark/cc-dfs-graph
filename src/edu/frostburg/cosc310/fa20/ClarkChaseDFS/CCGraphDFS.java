package edu.frostburg.cosc310.fa20.ClarkChaseDFS;

import edu.frostburg.cosc310.fa20.COSC310_P01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CCGraphDFS implements COSC310_P01 {

    private int[][] adjMatrix;

    private int[][] createAdjMatrix(String path, int arrayLength) {
        var matrix = new int[arrayLength][arrayLength];

        // read file from disk
        try {
            List<String> allLines = Files.readAllLines(Path.of(path));
            for (String line: allLines) {
                if (!line.startsWith("#")) // these lines are comments
                {
                    String[] lineArr = line.split(" "); // each line is space-delimited
                    if (lineArr.length > 0) {// check if line has values
                        // here we need to set the first entry to be the target index/current node (linArr[0])
                        System.out.print("createAdjMatrix -> " + lineArr[0] + "-> length: " + lineArr.length);
                        // convert letter to index integer
                        var currentNode = convertLetterToIndex(lineArr[0]);
                        // after this point the values we are looking for are in pairs (connectedNode, cost)
                        // we need to make a loop that works in pairs could use mod 2 or increment index by 2 each time
                    }
                }
            }
            System.out.println("=== ARMED AND READY ===");
        } catch (IOException e) {
            System.out.println("error finding file at: "+ path);
            e.printStackTrace();
        }
        return matrix;
    }

    /**
     * Convert a letter to index for our matrix.
     * Get the ASCII code and subtract 65 (uppercase)
     * @param c input is a string from a text file but we only want to work with letter characters
     * @return an index where A = 0 and Z = 25
     */
    private int convertLetterToIndex(String c)
    {
        if (c.length() == 1) { // if a single character
            return (int)c.toUpperCase().charAt(0) - 65;
        }
        else
            return -1; // fail
    }

    @Override
    public void go() {
        System.out.println("              .andAHHAbnn.\n" +
                "           .aAHHHAAUUAAHHHAn.\n" +
                "          dHP^~\"        \"~^THb.\n" +
                "    .   .AHF                YHA.   .\n" +
                "    |  .AHHb.              .dHHA.  |\n" +
                "    |  HHAUAAHAbn      adAHAAUAHA  |\n" +
                "    I  HF~\"_____        ____ ]HHH  I\n" +
                "   HHI HAPK\"\"~^YUHb  dAHHHHHHHHHH IHH\n" +
                "   HHI HHHD> .andHH  HHUUP^~YHHHH IHH\n" +
                "   YUI ]HHP     \"~Y  P~\"     THH[ IUP\n" +
                "    \"  `HK                   ]HH'  \"\n" +
                "        THAn.  .d.aAAn.b.  .dHHP\n" +
                "        ]HHHHAAUP\" ~~ \"YUAAHHHH[\n" +
                "        `HHP^~\"  .annn.  \"~^YHH'\n" +
                "         YHb    ~\" \"\" \"~    dHF\n" +
                "          \"YAb..abdHHbndbndAP\"\n" +
                "           THHAAb.  .adAHHF\n" +
                "            \"UHHHHHHHHHHU\"\n" +
                "              ]HHUUHHHHHH[\n" +
                "            .adHHb \"HHHHHbn.\n" +
                "     ..andAAHHHHHHb.AHHHHHHHAAbnn..\n" +
                ".ndAAHHHHHHUUHHHHHHHHHHUP^~\"~^YUHHHAAbn.\n" +
                "  \"~^YUHHP\"   \"~^YUHHUP\"        \"^YUP^\"\n" +
                "       \"\"         \"~~\"");
        System.out.println("=== DESTROY ALL HUMANS ===");
        System.out.println("Program starting...");
        System.out.println("Created by: " + getMyName());


        // load a test file
        System.out.println("Testing...");
        adjMatrix = createAdjMatrix("worldmap_0000.txt",26);
        runDFS1();



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
        System.out.println("Search 1: ");
    }

    /**
     * There is a good chance that our robot would say no to difficult tasks, so:
     * Do not visit any vertices with an edge cost > 10
     * Always select the next vertex with the lowest edge cost
     * After visiting 5 vertices, give up no matter what
     */
    @Override
    public void runDFS2() {
        System.out.println("Search 2: ");

    }

    /**
     * Vertices can be visited up to 3 times each. (To make sure we didn’t miss anything!)
     * Always select the next vertex that you haven’t visited yet if possible
     * Otherwise, select the vertex with the greatest edge cost
     */
    @Override
    public void runDFS3() {
        System.out.println("Search 3: ");
    }
}
