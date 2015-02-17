import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Dijkstra {

    static HashMap<String, Vertex> mapvertex = new HashMap<String, Vertex>();
    StringBuilder route = new StringBuilder();

    public void dijkstra(Vertex departure, String arrival) {
        Set<String> airportNames = mapvertex.keySet();
        Iterator<String> airportIterator = airportNames.iterator();
        while (airportIterator.hasNext()) {
            Vertex vertex = mapvertex.get(airportIterator.next());
            vertex.setKnown(false);
            vertex.setCost(0);
            vertex.setIsInfinity(true);
            vertex.setPrevVertex(null);
        }
        departure.setIsInfinity(false);
        Vertex vertex = departure;
        vertex.setIsInfinity(false);
        boolean arrivalFound = false;
        PriorityQueue<Vertex> travvertx = new PriorityQueue<>();
        for (;;) {

            try {
                vertex = travvertx.remove();
            } catch (NoSuchElementException nsee) {
                if (vertex == departure) {

                } else {
                    break;
                }
            }

            if (vertex == null) {
                break;
            }
            LinkedList<AdjacentVertex> adjacentVertices = vertex.getlstadjvertx();
            Iterator<AdjacentVertex> iterator = adjacentVertices.iterator();
            while (iterator.hasNext()) {
                AdjacentVertex adjVertex = iterator.next();
                int cost = vertex.getCost() + adjVertex.getCost();
                if (adjVertex.getVertex().getKnown() == false) {

                    if (adjVertex.getVertex().getIsInfinity()) {
                        adjVertex.getVertex().setCost(cost);
                        adjVertex.getVertex().setPrevVertex(vertex);
                        adjVertex.getVertex().setIsInfinity(false);
                    } else {
                        if (adjVertex.getVertex().getCost() > cost) {
                            adjVertex.getVertex().setCost(cost);
                            adjVertex.getVertex().setPrevVertex(vertex);
                        }
                    }
                    if (!travvertx.contains(adjVertex.getVertex())) {
                        travvertx.add(adjVertex.getVertex());
                    }

                }

            }
            vertex.setKnown(true);
        }
    }

    public int printPath(Vertex departure, Vertex arrival) {

        if (arrival == departure) {
            route.append(arrival.getName() + " -> ");
            return -1;
        } else {
            int numberOfConnections = 1 + printPath(departure, arrival.getPrevVertex());
            route.append(arrival.getName() + " -> ");
            return numberOfConnections;
        }

    }
    public static void main(String[] args) throws FileNotFoundException {
        File airports = new File("C:\\Users\\Aditya\\workspace\\Diastra\\airports.txt");
        Scanner sc1 = new Scanner(airports);
        while (sc1.hasNext()) {
            String airportDetail = sc1.nextLine();
            String[] splitString = airportDetail.split("  ");
            Vertex airport = new Vertex(splitString[0]);
            mapvertex.put(splitString[0], airport);
        }
        sc1 = new Scanner(airports);
        while (sc1.hasNext()) {
            String airportDetail = sc1.nextLine();
            String[] splitString = airportDetail.split("  ");
            Vertex airport = mapvertex.get(splitString[0]);
            LinkedList<AdjacentVertex> listadjvertx = new LinkedList<>();
            for (int j = 1; j < splitString.length; j++) {
                String[] subStrings = splitString[j].split(" ");
                int cost = Integer.parseInt(subStrings[1]);
                listadjvertx.add(new AdjacentVertex(mapvertex.get(subStrings[0]),
                        cost));

            }
            airport.setadj(listadjvertx);
        }
        while (true) {
            Dijkstra dij = new Dijkstra();
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter Departure Airport: ");
            String departure = sc.next().toUpperCase();
            System.out.print("Enter Arrival Airport: ");
            String arrival = sc.next().toUpperCase();

            Vertex depvertx = mapvertex.get(departure);
            Vertex arrvertx = mapvertex.get(arrival);
            
            System.out.println("");
            dij.dijkstra(depvertx, arrival);
            System.out.println("By Price:");
            System.out.println("");
            int numberOfConnections = dij.printPath(depvertx, arrvertx);
            System.out.println("Price        : $ " + arrvertx.getCost());
            System.out.println("Connection(s): " + numberOfConnections);
            dij.route.toString();
            System.out.println("Route        : " + dij.route.substring(0, dij.route.length() - 4));
            System.out.println("");
            System.out.println("Check Another Route? (Y/N)");
            if (sc.next().toUpperCase().equals("N")) {
                break;
            }
        }
    }
}
