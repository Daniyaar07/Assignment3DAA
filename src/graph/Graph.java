package graph;

import java.util.ArrayList;

public class Graph {
    public int  vertices;
    public ArrayList<Edge> edges = new ArrayList<>();

    public Graph(int vertices){
        this.vertices = vertices;
    }
    public void addEdge(int from , int to , int weight){
        edges.add(new Edge(from , to , weight));
    }
}
