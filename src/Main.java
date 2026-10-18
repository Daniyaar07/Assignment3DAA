import graph.*;
import mst.*;
import connectivity.*;
import shortestpath.*;

import java.util.*;
public class Main {
    public static void main(String[] args){
        Graph roads = new Graph(5);
        roads.addEdge(0 , 1 ,4);
        roads.addEdge(0 , 2 , 2);
        roads.addEdge(1 , 2 ,1 );
        roads.addEdge(1 , 3 ,5 );
        roads.addEdge(2 , 3, 8);
        roads.addEdge(3 , 4, 3);

        Kruskal kruskal = new Kruskal();
        Prim prim = new Prim();

        System.out.println("Kruskal MST: " + kruskal.findMST(roads));
        System.out.println("Prim MST: " + prim.findMST(roads));

        Graph directed = new Graph(5);
        directed.addEdge(0 , 1, 4);
        directed.addEdge(0 , 2, 2);
        directed.addEdge(1 , 3, 5);
        directed.addEdge(2 ,3 ,1);
        directed.addEdge(3 ,4 ,3);

        TopologicalSort topo = new TopologicalSort();
        System.out.println("Topological Sort: " + topo.sort(directed));

        Kosaraju kosaraju = new Kosaraju();
        System.out.println("SCC: " + kosaraju.findSCC(directed));

        Dijkstra dijkstra = new Dijkstra();
        int[] distances = dijkstra.shortestPaths(directed , 0);
        System.out.println("Dijkstra");

        for(int i = 0; i < distances.length; i++){
            if(distances[i] == Integer.MAX_VALUE){
                System.out.println(i + ": Unreachable");
            }
            else{
                System.out.println(i + ": " + distances[i] + " Path:" + dijkstra.getPath(i));
            }
        }
    }
}
