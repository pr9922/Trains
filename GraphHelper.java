package thoughtworks_challenge;

import java.util.*;

public class GraphHelper {
    
    Map<Character, List<Neighbor>> graph;
    
    public GraphHelper(Map<Character, List<Neighbor>> graph){
        this.graph = graph;
    }
    
    // Method for getting the distance of a particular route
    public String getDistance(String route){
        String[] route_arr = route.split("-");
        int distance = distanceHelper(route_arr, 0, 0);
        return distance >= 0 ? "" + distance : "NO SUCH ROUTE";
    }
    
    // Helper for getting distance of particular route
    public int distanceHelper(String[] route, int index, int distance){
        if(index == route.length-1){
            return distance;
        }
        else{
            boolean routeFound = false;
            for(Neighbor curr : graph.get(route[index].charAt(0))){
                if(curr.dest == route[index+1].charAt(0)){
                    routeFound = true;
                    return distanceHelper(route, index+1, distance + curr.distance);
                }
            }
            if(routeFound){
                return distance;
            }
            else{
                return -1;
            }
        }
    }
    
    // Method for getting number of trips with a particular number of stops
    public String getNumTrips(char curr, char tgt, int totalStops, int currStops){
        int numTrips = getNumTripsHelper(curr, tgt, totalStops, currStops);
        return numTrips > 0 ? numTrips+"" : "NO SUCH ROUTE";
    }
    
    // Helper for above method
    public int getNumTripsHelper(char curr, char tgt, int totalStops, int currStops){
        if(currStops >= totalStops && curr != tgt){
            return 0;
        }
        else if(currStops == totalStops && curr == tgt){
            return 1;
        }
        else{
            int result = 0;
            for(Neighbor neighbor : graph.get(curr)){
                result += getNumTripsHelper(neighbor.dest, tgt, totalStops, currStops+1);
            }
            return result;
        }
    }
    
    // Method for getting the shortest route between two nodes
    public String getShortestRoute(char curr, char tgt, int distance){
         Set<Character> visited = new HashSet<>();
         return getShortestRouteHelper(curr, tgt, distance, visited)+"";
    }
    
    // Helper for above method
    public int getShortestRouteHelper(char curr, char tgt, int distance, Set<Character> visited){
        if(curr == tgt && distance > 0){
            return distance;
        }
        else if(visited.contains(curr)){
            return Integer.MAX_VALUE;
        }
        else{
            visited.add(curr);
            int result = Integer.MAX_VALUE;
            for(Neighbor neighbor : graph.get(curr)){
                result = Math.min(result, getShortestRouteHelper(neighbor.dest, tgt, distance + neighbor.distance, visited));
            }
            visited.remove(curr);
            return result;
        }
    }
    
    // Method for getting number of different routes between particular nodes with a distance limit
    public String numDifferentRoutes(char curr, char tgt, int limit){
        return numDifferentRoutesHelper(curr, tgt, limit, 0)+"";
    }
    
    // Helper for above method
    public int numDifferentRoutesHelper(char curr, char tgt, int limit, int currDistance){
        if(currDistance >= limit){
            return 0;
        }
        else if(currDistance > 0 && currDistance < 30 && curr == tgt){
            return 1 + numDifferentRoutesHelper(curr, tgt, limit - currDistance, 0);
        }
        else{
            int result = 0;
            for(Neighbor neighbor : graph.get(curr)){
                result += numDifferentRoutesHelper(neighbor.dest, tgt, limit, currDistance + neighbor.distance);
            }
            return result;
        }
    }
    
}