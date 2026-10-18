package mst;

import graph.*;
import dsu.DSU;
import java.util.ArrayList;
import java.util.Comparator;

public class Kruskal{
    public long operations = 0;

    public int findMST(Graph graph){
        ArrayList<Edge> edges = new ArrayList<>(graph.edges);
        edges.sort(Comparator.comparingInt(e -> e.weight));

        DSU dsu = new DSU(graph.vertices);
        int total = 0;
        int count = 0;

        for(Edge edge : edges){
            operations++;

            if(dsu.union(edge.from , edge.to)){
                total+= edge.weight;
                count++;
            }
            if(count == graph.vertices - 1){
                break;
            }
        }
        operations+= dsu.operations;
        if (count != graph.vertices- 1){
            return -1;
        }
        return total;
    }
}
