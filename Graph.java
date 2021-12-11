import java.io.*;
import java.util.*;

class Vertex {
  int vertexID;
  HashMap<Vertex, Double> adjVertices;
  /* Konstruktor */
  Vertex(int vertexID) {
    this.vertexID = vertexID;
    adjVertices = new HashMap<Vertex, Double>();
  }
  /* Fungsi untuk menambahkan adjacent vertex */
  void addAdjacentVertex(Vertex vertex, double weight) {
    this.adjVertices.put(vertex, weight);
  }
}

public class Graph {
  static void dijkstraShortestPath(Vertex start, Vertex dest) {
    HashMap<Integer, Double> shortestPathTable = new HashMap<Integer, Double>();
    HashMap<Integer, Integer> shortestPreviousStopoverTable = new HashMap<Integer, Integer>();

    ArrayList<Vertex> unvisited = new ArrayList<Vertex>();
    HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
    
    shortestPathTable.put(start.vertexID, 0.0);

    Vertex current = start;
    while (current != null) {
      visited.put(current.vertexID, true);
      unvisited.remove(current);
      for (HashMap.Entry<Vertex, Double> entry : current.adjVertices.entrySet()) {
        Vertex adjV = entry.getKey();
        Double weight = entry.getValue();
        if (!visited.containsKey(adjV.vertexID)) {
          unvisited.add(adjV);
        }
        double weightThroughCurrent = shortestPathTable.get(current.vertexID) + weight;
        if (!shortestPathTable.containsKey(adjV.vertexID) || weightThroughCurrent < shortestPathTable.get(adjV.vertexID)) {
          shortestPathTable.put(adjV.vertexID, weightThroughCurrent);
          shortestPreviousStopoverTable.put(adjV.vertexID, current.vertexID);
        }
      }
      double minWeight = -1.0;
      for (Vertex v: unvisited) {
        if (minWeight == -1.0) {
          minWeight = shortestPathTable.get(v.vertexID);
          current = v;
        } else {
          double tmp = shortestPathTable.get(v.vertexID);
          if (tmp < minWeight) {
            minWeight = tmp;
            current = v;
          }
        }
      }
      if (minWeight == -1.0) {
        current = null;
      }
    }

    ArrayList<Integer> shortestPath = new ArrayList<Integer>();

    int currentID = dest.vertexID;
    while (currentID != start.vertexID) {
      shortestPath.add(currentID);
      currentID = shortestPreviousStopoverTable.get(currentID);
    }
    shortestPath.add(start.vertexID);

    System.out.println("Shortest path from " + start.vertexID + " to " + dest.vertexID + " is: ");
    /* Cetak array */
    for (int i = shortestPath.size() - 1; i >= 0; i --) {
      if (i > 0) {
        System.out.print(shortestPath.get(i) + " -> ");
      } else {
        System.out.println(shortestPath.get(i));
      }
    }
    System.out.println("Total weight for that route is " + shortestPathTable.get(dest.vertexID) + ".");
  }

  public static void main(String[] args) {
    /* Inisialisasi graf */
    HashMap<Integer, Vertex> v = new HashMap<Integer, Vertex>();
    for (int i = 1; i <= 40; i++) {
      Vertex tmp = new Vertex(i);
      v.put(i, tmp);
    }

    /* Memasukkan data graf sesuai yang telah dibuat */
    v.get(1).addAdjacentVertex(v.get(2), 0.85);

    v.get(2).addAdjacentVertex(v.get(1), 0.85);
    v.get(2).addAdjacentVertex(v.get(3), 0.67);
    v.get(2).addAdjacentVertex(v.get(40), 1.62);

    v.get(3).addAdjacentVertex(v.get(2), 0.67);
    v.get(3).addAdjacentVertex(v.get(4), 1.85);

    v.get(4).addAdjacentVertex(v.get(3), 1.85);
    v.get(4).addAdjacentVertex(v.get(5), 0.72);
    v.get(4).addAdjacentVertex(v.get(20), 1.14);

    v.get(5).addAdjacentVertex(v.get(4), 0.72);
    v.get(5).addAdjacentVertex(v.get(6), 0.85);

    v.get(6).addAdjacentVertex(v.get(5), 0.85);
    v.get(6).addAdjacentVertex(v.get(7), 0.72);

    v.get(7).addAdjacentVertex(v.get(6), 0.72);
    v.get(7).addAdjacentVertex(v.get(8), 0.33);

    v.get(8).addAdjacentVertex(v.get(7), 0.33);
    v.get(8).addAdjacentVertex(v.get(9), 0.84);
    v.get(8).addAdjacentVertex(v.get(13), 0.48);

    v.get(9).addAdjacentVertex(v.get(8), 0.84);
    v.get(9).addAdjacentVertex(v.get(10), 0.51);

    v.get(10).addAdjacentVertex(v.get(9), 0.51);
    v.get(10).addAdjacentVertex(v.get(11), 0.41);

    v.get(11).addAdjacentVertex(v.get(10), 0.41);
    v.get(11).addAdjacentVertex(v.get(12), 0.86);

    v.get(12).addAdjacentVertex(v.get(11), 0.86);
    v.get(12).addAdjacentVertex(v.get(13), 0.48);
    v.get(12).addAdjacentVertex(v.get(14), 0.45);

    v.get(13).addAdjacentVertex(v.get(8), 0.48);
    v.get(13).addAdjacentVertex(v.get(12), 0.48);
    v.get(13).addAdjacentVertex(v.get(19), 1.14);

    v.get(14).addAdjacentVertex(v.get(12), 0.45);
    v.get(14).addAdjacentVertex(v.get(15), 1.56);

    v.get(15).addAdjacentVertex(v.get(14), 1.56);
    v.get(15).addAdjacentVertex(v.get(16), 0.51);
    v.get(15).addAdjacentVertex(v.get(17), 0.45);
    v.get(15).addAdjacentVertex(v.get(28), 0.63);

    v.get(16).addAdjacentVertex(v.get(15), 0.51);

    v.get(17).addAdjacentVertex(v.get(15), 0.45);
    v.get(17).addAdjacentVertex(v.get(18), 0.44);

    v.get(18).addAdjacentVertex(v.get(17), 0.44);
    v.get(18).addAdjacentVertex(v.get(19), 0.5);

    v.get(19).addAdjacentVertex(v.get(13), 1.14);
    v.get(19).addAdjacentVertex(v.get(18), 0.5);
    v.get(19).addAdjacentVertex(v.get(20), 1.59);

    v.get(20).addAdjacentVertex(v.get(4), 1.14);
    v.get(20).addAdjacentVertex(v.get(19), 1.59);
    v.get(20).addAdjacentVertex(v.get(21), 0.58);

    v.get(21).addAdjacentVertex(v.get(20), 0.58);
    v.get(21).addAdjacentVertex(v.get(22), 0.43);
    v.get(21).addAdjacentVertex(v.get(39), 0.92);

    v.get(22).addAdjacentVertex(v.get(21), 0.43);
    v.get(22).addAdjacentVertex(v.get(23), 0.58);

    v.get(23).addAdjacentVertex(v.get(22), 0.58);
    v.get(23).addAdjacentVertex(v.get(24), 0.43);

    v.get(24).addAdjacentVertex(v.get(23), 0.43);
    v.get(24).addAdjacentVertex(v.get(24), 0.34);

    v.get(25).addAdjacentVertex(v.get(24), 0.34);
    v.get(25).addAdjacentVertex(v.get(26), 0.8);
    v.get(25).addAdjacentVertex(v.get(34), 0.9);

    v.get(26).addAdjacentVertex(v.get(25), 0.8);
    v.get(26).addAdjacentVertex(v.get(27), 0.44);
    v.get(26).addAdjacentVertex(v.get(31), 0.31);

    v.get(27).addAdjacentVertex(v.get(26), 0.44);
    v.get(27).addAdjacentVertex(v.get(28), 0.9);

    v.get(28).addAdjacentVertex(v.get(15), 0.63);
    v.get(28).addAdjacentVertex(v.get(27), 0.9);
    v.get(28).addAdjacentVertex(v.get(29), 0.77);

    v.get(29).addAdjacentVertex(v.get(28), 0.77);
    v.get(29).addAdjacentVertex(v.get(30), 0.6);

    v.get(30).addAdjacentVertex(v.get(29), 0.6);
    v.get(30).addAdjacentVertex(v.get(31), 0.33);
    v.get(30).addAdjacentVertex(v.get(32), 0.55);

    v.get(31).addAdjacentVertex(v.get(26), 0.31);
    v.get(31).addAdjacentVertex(v.get(30), 0.33);

    v.get(32).addAdjacentVertex(v.get(30), 0.55);
    v.get(32).addAdjacentVertex(v.get(33), 0.55);

    v.get(33).addAdjacentVertex(v.get(32), 0.55);
    v.get(33).addAdjacentVertex(v.get(34), 0.6);

    v.get(34).addAdjacentVertex(v.get(25), 0.9);
    v.get(34).addAdjacentVertex(v.get(33), 0.6);
    v.get(34).addAdjacentVertex(v.get(35), 0.6);

    v.get(35).addAdjacentVertex(v.get(34), 0.6);
    v.get(35).addAdjacentVertex(v.get(36), 0.74);

    v.get(36).addAdjacentVertex(v.get(35), 0.74);
    v.get(36).addAdjacentVertex(v.get(37), 1.17);

    v.get(37).addAdjacentVertex(v.get(36), 1.17);
    v.get(37).addAdjacentVertex(v.get(38), 0.5);

    v.get(38).addAdjacentVertex(v.get(37), 0.5);
    v.get(38).addAdjacentVertex(v.get(39), 0.58);
    v.get(38).addAdjacentVertex(v.get(40), 0.92);

    v.get(39).addAdjacentVertex(v.get(21), 0.92);
    v.get(39).addAdjacentVertex(v.get(38), 0.58);

    v.get(40).addAdjacentVertex(v.get(2), 1.62);
    v.get(40).addAdjacentVertex(v.get(38), 0.92);
    
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter starting node: ");
    int startID = sc.nextInt();
    System.out.print("Enter target node: ");
    int targetID = sc.nextInt();

    dijkstraShortestPath(v.get(startID), v.get(targetID));
  }
}