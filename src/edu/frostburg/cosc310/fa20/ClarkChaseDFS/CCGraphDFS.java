package edu.frostburg.cosc310.fa20.ClarkChaseDFS;

import edu.frostburg.cosc310.fa20.COSC310_P01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CCGraphDFS implements COSC310_P01 {

    // TODO: Do DFS3
    // TODO: cleanup code comments and create jar file

    private int[][] graph;
    private ArrayList<Character> visitedNodes = new ArrayList<Character>();
    private boolean targetAcquired = false; // true if X or Y is found when searching
    private int cost; // summation of edge values
    private boolean giveUp = false; // used for lazyDFS to see if dont searching

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
                        // convert letter to index integer
                        var currentNode = convertLetterToIndex(lineArr[0]);
                        // after this point the values we are looking for are in pairs (connectedNode, cost)
                        // we need to make a loop that works in pairs could use mod 2 or increment index by 2 each time
                        // for loop to i < length linArr ; i += 2 ... we start at i = 1 because 0 is currentNode
                        for (int i = 1; i < lineArr.length; i+=2) {
                            var targetNode = convertLetterToIndex(lineArr[i]);
                            var cost = Integer.parseInt(lineArr[i+1]);
                            // add edge to graph
                            matrix[currentNode][targetNode] = cost;
                        }
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

    /**
     * Takes an index and converts it to a corresponding capital letter
     * @param index
     * @return char letter
     */
    private char convertIndexToLetter(int index) {
        if (index >= 0)
            return (char)(index + 65);
        else
            return '#'; // fail
    }


    /**
     * method to print out 2d array, used for debugging
     */
    private void printMatrix(){
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                System.out.print(graph[i][j]+"   ");
            }
            System.out.println();
        }
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
        System.out.println();
        System.out.println("Program starting...");
        System.out.println("Created by: " + getMyName());
        System.out.println();

        // load the worldmap
        graph = createAdjMatrix("worldmap_0000.txt",26);
        runDFS1();
        runDFS2();
        runDFS3();
    }

    @Override
    public String getMyName() {
        return "Chase Clark";
    }

    /**
     * Visit the adjacent vertices in alphabetic order. (If the adjacents are A, B, and C, visit A first).
     * targets are 23 and 24 which are x/y
     * Output: print results to console
     */
    @Override
    public void runDFS1() {
        // dfs alphabetically
        var visited = new boolean[graph.length];
        // reset list
        visitedNodes.clear();
        // reset cost count
        cost = 0;
        // reset found = false
        targetAcquired = false;
        alphabeticalDFS(0, visited);
        System.out.print("Search 1: Path -> "+visitedNodes.toString()+" Cost -> "+cost+" (");
        // print whether or not target was found
        System.out.println(" "+((targetAcquired) ? "Target Eliminated! Mission Successful! )" : "Target Not Found... Mission Failed... )"));
        System.out.println();
    }
    // our graph is automatically in alphabetical order due to how our matrix is set up
    // need to count the count until X(23) or Y(24) is found
    private void alphabeticalDFS(int current, boolean[] visited) {
        // loop through row
        for (int adj = 0; adj< graph.length; adj++) {
            if (graph[current][adj] > 0 && !visited[adj] && !targetAcquired) {
                // mark as visited
                visited[adj] = true;
                // add letter to list
                visitedNodes.add(convertIndexToLetter(adj));
                // keep track of cost
                cost += graph[current][adj];
                // see if we are done
                if (adj == 23 || adj == 24) // X or Y
                    // here we signal to stop searching
                    targetAcquired = true;
                else
                    alphabeticalDFS(adj, visited); // keep searching
            }
        }
    }

    /**
     * There is a good chance that our robot would say no to difficult tasks, so:
     * Do not visit any vertices with an edge cost > 10
     * Always select the next vertex with the lowest edge cost
     * After visiting 5 vertices, give up no matter what
     */
    @Override
    public void runDFS2() {
        var visited = new boolean[graph.length];
        // reset list
        visitedNodes.clear();
        // reset cost count
        cost = 0;
        // reset found/giveup to false
        targetAcquired = giveUp = false;
        lazyDFS(0,visited,0);
        System.out.print("Search 2: Path -> "+visitedNodes.toString()+" Cost -> "+cost+" (");
        // print whether or not target was found
        System.out.println(" "+((targetAcquired) ? "Target Eliminated! Mission Successful! )" : "Target Not Found... Mission Failed... )"));
        System.out.println();
    }

    private void lazyDFS(int current, boolean[] visited, int visitCount) {
        var adjacents = findAdjacentsSortedByLowestCost(current);
        for (int adj : adjacents) {
            if (graph[current][adj] > 0 && graph[current][adj] < 10 && !visited[adj] && !targetAcquired && !giveUp) {
//              System.out.println("Current: "+current+" Letter: "+convertIndexToLetter(adj)+"("+ adj +")"+" ->Cost: "+graph[current][adj]);
                // mark as visited
                visited[adj] = true;
                // add letter to list
                visitedNodes.add(convertIndexToLetter(adj));
                // keep track of cost
                cost += graph[current][adj];
                visitCount++;
                // see if we are done
                if (adj == 23 || adj == 24) // X or Y
                    // here we signal to stop searching
                    targetAcquired = true;
                else if (visitCount >= 5)
                    giveUp = true;
                else
                    lazyDFS(adj, visited, visitCount); // keep searching
            }
        }
    }

    /**
     * Helper method to find adjacent nodes and sort them
     * @param current the current node
     * @return an arraylist of nodes in ascending order
     */
    private ArrayList<Integer> findAdjacentsSortedByLowestCost(int current){
        ArrayList<Integer> adjacents = new ArrayList<Integer>();
        for (int j = 0; j < graph[current].length; j++) {
            var cost = graph[current][j];
            if ( cost > 0 && cost < 10) {
                // insert in order
                if(adjacents.size() == 0) // first add
                    adjacents.add(j);
                else {
                    // insert j in the correct spot in order of cost
                    for ( int node: adjacents ) {
                        if (cost < graph[current][node]) {
                            adjacents.add(adjacents.indexOf(node),j);
                            break;
                        }
                    }
                }
            }
        }
        return adjacents;// sorted by cost
    }

    /**
     * Vertices can be visited up to 3 times each. (To make sure we didn’t miss anything!)
     * Always select the next vertex that you haven’t visited yet if possible
     * Otherwise, select the vertex with the greatest edge cost
     */
    @Override
    public void runDFS3() {
        // use int[] visited and if (visited[adj] < 3)
        var visited = new int[graph.length];
        // reset list
        visitedNodes.clear();
        // reset cost count
        cost = 0;
        // reset found to false
        targetAcquired = false;
        indecisiveDFS(0,visited);
        System.out.print("Search 3: Path -> "+visitedNodes.toString()+" Cost -> "+cost+" (");
        // print whether or not target was found
        System.out.println(" "+((targetAcquired) ? "Target Eliminated! Mission Successful! )" : "Target Not Found... Mission Failed... )"));
        System.out.println();
    }

    private void indecisiveDFS(int current, int[] visited) {
        // create a method for selecting the next node
        var adjacents = findIndecisiveAdjacents(current, visited);
        for (int adj : adjacents) {
            if (graph[current][adj] > 0 && visited[adj]<3 && !targetAcquired) {
                System.out.println("Current: "+convertIndexToLetter(current)+" Letter: "+convertIndexToLetter(adj)+"("+ adj +")"+" ->Cost: "+graph[current][adj]);
                System.out.println();
                // increment to say this node was visited
                visited[adj]++;
                // add letter to list
                visitedNodes.add(convertIndexToLetter(adj));
                // keep track of total cost
                cost += graph[current][adj];
                // see if we are done
                if (adj == 23 || adj == 24) // X or Y
                    // here we signal to stop searching
                    targetAcquired = true;
                else
                    indecisiveDFS(adj, visited); // keep searching
            }
        }
    }

    /**
     * Return a list of adjacent nodes sorted by unvisited, then greatest cost
     * @param current node
     *
     */
    private ArrayList<Integer> findIndecisiveAdjacents(int current, int[] visited) {
        ArrayList<Integer> adjacents = new ArrayList<Integer>();
        for (int j = 0; j < graph[current].length; j++) {
            var cost = graph[current][j];
            if ( cost > 0) { // ignore blanks
                // insert in order
                if(adjacents.size() == 0) // first add
                    adjacents.add(j);
                else {
                    // insert j in the correct spot in order of cost
                    for ( int node: adjacents ) {
                        if ( visited[node] < 1 ) { // visited goes in front
                            adjacents.add(adjacents.indexOf(node),j);
                            break;
                        }
                        else if(cost > graph[current][node]) { // order by cost
                            adjacents.add(adjacents.indexOf(node),j);
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("current: "+convertIndexToLetter(current)+" -> "+adjacents.toString());
        return adjacents; // sorted by cost
    }

}
