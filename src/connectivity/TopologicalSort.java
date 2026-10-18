package connectivity;
import graph.*;
import java.util.*;
public class TopologicalSort {
    public long operations = 0;

    public ArrayList<Integer> sort(Graph graph){
        int[] degree = new int[graph.vertices];
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i = 0; i< graph.vertices; i++){
            adj.add(new ArrayList<>());
        }
        for(Edge e : graph.edges){
            adj.get(e.from).add(e.to);
            degree[e.to]++;
            operations++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < graph.vertices; i++){
            if(degree[i] == 0){
                queue.add(i);
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()){
            int current = queue.poll();
            result.add(current);
            operations++;

            for(int next : adj.get(current)){
                degree[next] --;
                operations++;

                if(degree[next] == 0){
                    queue.add(next);
                }
            }
        }
        if(result.size() != graph.vertices){
            return new ArrayList<>();
        }
        return result;
    }
}
