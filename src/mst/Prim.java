package mst;
import graph.*;
import java.util.*;
public class Prim {
    public long operations = 0;

    public int findMST(Graph graph){
        ArrayList<ArrayList<Edge>> adj = new ArrayList<>();

        for(int i = 0; i< graph.vertices; i++){
            adj.add(new ArrayList<>());
        }
        for(Edge e : graph.edges){
            adj.get(e.from).add(e);
            adj.get(e.to).add(new Edge(e.to , e.from , e.weight));
        }
        boolean[] visited = new boolean[graph.vertices];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        pq.add(new Edge(0 , 0 , 0));

        int total = 0;
        int count = 0;
        while(!pq.isEmpty()){
            Edge current = pq.poll();
            operations++;
            if(visited[current.to]){
                continue;
            }
            visited[current.to] = true;
            total+= current.weight;
            count++;

            for(Edge next : adj.get(current.to)){
                operations++;

                if (!visited[next.to]){
                    pq.add(next);
                }
            }
        }
        if(count != graph.vertices){
            return -1;
        }
        return total;
    }
}
