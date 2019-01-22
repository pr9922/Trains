package train_challenge;

import java.util.*;
import java.io.*;

public class Trains {

    public static void main(String[] args) throws Exception{
        
        try {
            Scanner scanner = new Scanner(new File("src/thoughtworks_challenge/input.txt"));
            while (scanner.hasNextLine()) {
                Map<Character, List<Neighbor>> currGraph = makeGraph(scanner.nextLine());
                GraphHelper graphHelper = new GraphHelper(currGraph);
                printOutput(currGraph, graphHelper);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        // Uncomment this chunk to test input without using file.
        /*     
        Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
        Map<Character, List<Neighbor>> currGraph = makeGraph(scanner.nextLine());
        GraphHelper graphHelper = new GraphHelper(currGraph);
        printOutput(currGraph, graphHelper);     
        */
    }
    
    // Print specified output
    public static void printOutput(Map<Character, List<Neighbor>> graph, GraphHelper graphHelper){
        
        // Get Distance of Routes
        System.out.println("Output #1: " + graphHelper.getDistance("A-B-C"));
        System.out.println("Output #2: " + graphHelper.getDistance("A-D"));
        System.out.println("Output #3: " + graphHelper.getDistance("A-D-C"));
        System.out.println("Output #4: " + graphHelper.getDistance("A-E-B-C-D"));
        System.out.println("Output #5: " + graphHelper.getDistance("A-E-D"));
        
        // Get Number of Trips
        System.out.println("Output #6: " + graphHelper.getNumTrips('C', 'C', 4, 0));
        System.out.println("Output #7: " + graphHelper.getNumTrips('A', 'C', 4, 0));
        
        // Get Shortest Route
        System.out.println("Output #8: " + graphHelper.getShortestRoute('A', 'C', 0));
        System.out.println("Output #9: " + graphHelper.getShortestRoute('B', 'B', 0));
       
        // Num different routes with distance less than blah
        System.out.println("Output #10: " + graphHelper.numDifferentRoutes('C', 'C', 30));
        
    }
    
    // Helper function to make a graph for a particular line of input
    public static Map<Character, List<Neighbor>> makeGraph(String test_input){
        
        Map<Character, List<Neighbor>> graph = new HashMap<>();
        String[] input = test_input.replace("Graph: ", "").replaceAll(" ", "").split(",");
        
        for(String curr : input){
            char source = curr.charAt(0);
            char dest = curr.charAt(1);
            int distance = curr.charAt(2) - '0';
            if(!graph.containsKey(source)){
                graph.put(source, new ArrayList<>());
            }
            if(!graph.containsKey(dest)){
                graph.put(dest, new ArrayList<>());
            }
            graph.get(source).add(new Neighbor(dest, distance));
        }
        return graph;
    }
    
}
