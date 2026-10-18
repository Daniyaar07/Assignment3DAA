import graph.*;
import mst.*;
import connectivity.*;
import shortestpath.*;
public class Tests {
    public static void main(String[] args){
        Graph graph = new Graph(3);
        graph.addEdge(0 , 1 ,2);
        graph.addEdge(1 , 2 ,3);
        graph.addEdge(0 , 2,4);

        Kruskal kruskal = new Kruskal();
        Prim prim = new Prim();

        if(kruskal.findMST(graph) ==5 && prim.findMST(graph) == 5){
            System.out.println("MST: PASS" );
        }
        else{
            System.out.println("MST: FAIL");
        }
        Graph disconnected = new Graph(3);
        disconnected.addEdge(0 , 1 ,2);

        if(kruskal.findMST(disconnected) == -1 && prim.findMST(disconnected) == -1) {
            System.out.println("Disconnected: PASS");
        }
        else{
            System.out.println("Disconnected: FAIL");
        }
        Graph single = new Graph(1);
        if(kruskal.findMST(single) == 0 && prim.findMST(single) == 0){
            System.out.println("Single vertex: PASS");
        }
        else{
            System.out.println("Single vertex: FAIL");
        }
        Graph equal = new Graph(3);
        equal.addEdge(0 , 1, 1);
        equal.addEdge(1 , 2 ,1);
        equal.addEdge(0 , 2 , 1);

        if(kruskal.findMST(equal) == 2 && prim.findMST(equal) == 2){
            System.out.println("Equal weights: PASS");
        }
        else{
            System.out.println("Equal weights: FAIL");
        }
        Graph directed = new Graph(3);
        directed.addEdge(0 , 1, 1);
        directed.addEdge(1 , 2, 1);

        TopologicalSort topo = new TopologicalSort();

        if(topo.sort(directed).size()== 3){
            System.out.println("Topological: PASS");
        }
        else {
            System.out.println("Topological: FAIL");
        }
        directed.addEdge(2 , 0 , 1);
        if(topo.sort(directed).isEmpty()){
            System.out.println("Cycle: PASS");
        }
        else{
            System.out.println("Cycle: FAIL");
        }
        Kosaraju kosaraju = new Kosaraju();

        if(kosaraju.findSCC(directed).size()==1){
            System.out.println("SCC: PASS");
        }
        else{
            System.out.println("SCC: FAIL");
        }
        Graph paths = new Graph(4);
        paths.addEdge(0, 1, 5);
        paths.addEdge(0, 2, 2);
        paths.addEdge(2, 1, 1);

        Dijkstra dijkstra = new Dijkstra();
        int[] distances = dijkstra.shortestPaths(paths , 0);

        if(distances[1] == 3){
            System.out.println("Dijkstra: PASS");
        }
        else{
            System.out.println("Dijkstra: FAIL");
        }
        if (distances[3] == Integer.MAX_VALUE) {
            System.out.println("Unreachable: PASS");
        }
        else{
            System.out.println("Unreachable: FAIL");
        }
    }
}
