package connectivity;

import graph.*;
import java.util.*;
public class Kosaraju {
    public long operations = 0;

    private void dfs(int v , ArrayList<ArrayList<Integer>> adj , boolean[] visited, ArrayList<Integer> order){
        visited[v] = true;
        operations++;

        for(int next : adj.get(v)){
            operations++;

            if(!visited[next]){
                dfs(next , adj , visited , order);
            }
        }
        order.add(v);
    }
    public ArrayList<ArrayList<Integer>> findSCC(Graph graph){
        int n = graph.vertices;

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        ArrayList<ArrayList<Integer>> reverse = new ArrayList<>();

        for(int i = 0; i< n; i++){
            adj.add(new ArrayList<>());
            reverse.add(new ArrayList<>());
        }
        for(Edge e: graph.edges){
            adj.get(e.from).add(e.to);
            reverse.get(e.to).add(e.from);
        }
        boolean[] visited = new boolean[n];
        ArrayList<Integer> order = new ArrayList<>();

        for(int i = 0; i< n; i++){
            if(!visited[i]){
                dfs(i , adj , visited , order);
            }
        }
        Arrays.fill(visited , false);
        ArrayList<ArrayList<Integer>> components = new ArrayList<>();

        for(int i = order.size() - 1 ; i>= 0; i--){
            int v = order.get(i);

            if(!visited[v]){
                ArrayList<Integer> component = new ArrayList<>();
                dfs(v , reverse , visited , component);
                components.add(component);
            }
        }
        return components;
    }
}
