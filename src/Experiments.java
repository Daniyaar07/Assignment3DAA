import graph.*;
import mst.*;
import connectivity.*;
import shortestpath.*;

import java.util.*;
import java.io.*;
public class Experiments {
    public static void main(String[] args) throws Exception {
        int[] vertices = {10, 100, 1000};
        int[] edges = {30, 500, 5000};

        Random random = new Random(42);

        new File("results/tables").mkdirs();

        PrintWriter file = new PrintWriter("results/tables/benchmark.csv");
        file.println("Algorithm,Vertices,Edges,Time_ms,Operations");
        for (int k = 0; k < 3; k++) {
            int n = vertices[k];
            int m = edges[k];

            Graph graph = new Graph(n);
            Graph directed = new Graph(n);

            for (int i = 1; i < n; i++) {
                graph.addEdge(i - 1, i, random.nextInt(20) + 1);
                directed.addEdge(i - 1, i, random.nextInt(20) + 1);
            }
            while (graph.edges.size() < m) {
                int a = random.nextInt(n);
                int b = random.nextInt(n);

                if (a != b) {
                    graph.addEdge(a, b, random.nextInt(20) + 1);
                }
            }
            while (directed.edges.size() < m) {
                int a = random.nextInt(n);
                int b = random.nextInt(n);

                if (a < b) {
                    directed.addEdge(a, b, random.nextInt(20) + 1);
                }
            }
            String[] names = {
                    "Kruskal", "Prim", "Topological", "Kosaraju", "Dijkstra"
            };
            for (String name : names) {
                double totalTime = 0;
                long totalOperations = 0;

                for (int i = 0; i < 5; i++) {
                    long start = System.nanoTime();
                    long operations = 0;

                    if (name.equals("Kruskal")) {
                        Kruskal a = new Kruskal();
                        a.findMST(graph);
                        operations = a.operations;
                    }
                    if (name.equals("Prim")) {
                        Prim a = new Prim();
                        a.findMST(graph);
                        operations = a.operations;
                    }
                    if (name.equals("Kosaraju")) {
                        Kosaraju a = new Kosaraju();
                        a.findSCC(directed);
                        operations = a.operations;
                    }
                    if (name.equals("Dijkstra")) {
                        Dijkstra a = new Dijkstra();
                        a.shortestPaths(directed, 0);
                        operations = a.operations;
                    }
                    if (name.equals("Topological")) {
                        TopologicalSort a = new TopologicalSort();
                        a.sort(directed);
                        operations = a.operations;
                    }
                    long end = System.nanoTime();
                    totalTime += (end - start) / 1000000.0;
                    totalOperations += operations;
                }
                double average = totalTime / 5;
                long averageOperations = totalOperations / 5;

                System.out.println(name + "V=" + n + " E=" + m + " Time=" + average + " ms");

                file.println(name + "," + n + "," + m + "," + average + "," + averageOperations);
            }
        }
        file.close();
        System.out.println("Experiments completed!");
    }
}