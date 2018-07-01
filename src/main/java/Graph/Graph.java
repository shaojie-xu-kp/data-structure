package Graph;

import lombok.ToString;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

@ToString
public class Graph {

    private int verticesNumber;  // No. of vertices
    private LinkedList<Integer> adj[]; // Adjacency Lists

    public Graph(int verticesNumber) {
        this.verticesNumber = verticesNumber;
        adj = new LinkedList[verticesNumber];
        for (int i = 0; i < verticesNumber; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    private void addEdge(int v,int w)
    {
        adj[v].add(w);
    }

    private void breadthFirstTraversal(int startVertice) {

        // mark all un-visited
        boolean[] visited = new boolean[verticesNumber];

        Queue<Integer> queue = new LinkedList<>();

        visited[startVertice] = true;
        queue.add(startVertice);

        while (!queue.isEmpty()) {

            int verticeToVisit = queue.poll();
            System.out.print(verticeToVisit + " ");

            for(Integer vertice : adj[verticeToVisit]) {
                if (!visited[vertice]) {
                    visited[vertice] = true;
                    queue.add(vertice);
                }
            }
        }
    }

    private void depthFirstTraversal(int startVertice) {

        // mark all un-visited
        boolean[] visited = new boolean[verticesNumber];

        depthFirstTraversalRec(startVertice, visited);
    }

    private void depthFirstTraversalRec(int startVertice, boolean[] visited) {

        visited[startVertice] = true;
        System.out.print(startVertice + " ");

        for(Integer vertice : adj[startVertice]) {
            if (!visited[vertice]) {
                visited[vertice] = true;
                depthFirstTraversalRec(vertice, visited);
            }
        }

    }



    // Driver method to
    public static void main(String args[])
    {
        Graph g1 = new Graph(4);

        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(2, 0);
        g1.addEdge(2, 3);
        g1.addEdge(3, 3);
        System.out.println(g1);
        g1.breadthFirstTraversal(2);
        System.out.println();
        g1.depthFirstTraversal(2);
        System.out.println();

        // Create a graph given in the above diagram
        Graph g2 = new Graph(6);
        g2.addEdge(5, 2);
        g2.addEdge(5, 0);
        g2.addEdge(4, 0);
        g2.addEdge(4, 1);
        g2.addEdge(2, 3);
        g2.addEdge(3, 1);

        System.out.println("Following is a Topological " +
                "sort of the given graph");
        g2.topologicalSort();



    }

    private void topologicalSort() {
        Deque<Integer> verticeStack = new ArrayDeque<>();

        // mark all un-visited
        boolean[] visited = new boolean[verticesNumber];

        for(int i = 0; i < verticesNumber; i++) {
            if (!visited[i])
                topologicalSortRec(i, visited, verticeStack);
        }

        verticeStack.stream().forEach(vertice -> System.out.print(vertice + " "));
    }

    private void topologicalSortRec(int vertice, boolean[] visited, Deque<Integer> verticeStack) {

        visited[vertice] = true;

        for(Integer verticeTovisit : adj[vertice]) {
            if(!visited[verticeTovisit]) {
                topologicalSortRec(verticeTovisit, visited, verticeStack);
            }
        }

        verticeStack.push(vertice);
    }


}
