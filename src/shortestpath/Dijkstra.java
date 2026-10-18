package shortestpath;
import graph.*;
import mst.Prim;

import java.util.*;
public class Dijkstra {
    public long operations = 0;
    public int[] parent;

    public int[] shortestPaths(Graph graph, int start){
        int n = graph.vertices;

        ArrayList<ArrayList<Edge>> adj = new ArrayList<>();

        for(int i = 0; i< n; i++){
            adj.add(new ArrayList<>());
        }
        for(Edge e : graph.edges){
            if(e.weight < 0){
                throw new IllegalArgumentException("Negative weight");
            }
            adj.get(e.from).add(e);
        }
        int [] dist = new int[n];
        parent = new int[n];

        Arrays.fill(dist , Integer.MAX_VALUE);
        Arrays.fill(parent , - 1);

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                Comparator.comparingInt(a -> a[1])
        );
        dist[start] = 0;
        pq.add(new int[]{start , 0});
        while(!pq.isEmpty()){
            int[] current = pq.poll();
            int v = current[0];

            if(current[1] != dist[v]){
                continue;
            }
            for(Edge e : adj.get(v)){
                operations++;

                if(dist[v] <= Integer.MAX_VALUE - e.weight && dist[v] + e.weight < dist[e.to]){
                    dist[e.to] = dist[v] + e.weight;
                    parent[e.to] =v;
                    pq.add(new int[]{e.to , dist[e.to]});
                }
            }
        }
        return dist;
    }
    public ArrayList<Integer> getPath(int target){
        ArrayList<Integer> path = new ArrayList<>();

        if(parent == null){
            return path;
        }
        int current = target;
        while(current != -1){
            path.add(current);
            current = parent[current];
        }
        Collections.reverse(path);
        return path;
    }
}
