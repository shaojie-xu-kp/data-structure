package Graph;

import java.util.Iterator;
import java.util.LinkedList;

public class HighestRankVerticePair {

    public static void main(String[] args){
//        int[] A = {1, 2, 3, 3, 1, 2};
//        int[] B = {2, 3, 1, 4, 4, 4};
        int[] A = {1, 2, 4, 5};
        int[] B = {2, 3, 5, 6};

        int N = 6;

        MaxRankGraph g = new MaxRankGraph(N);

        for (int i = 0; i < A.length; i++) {
            g.addEdge(A[i]-1, B[i]-1);
        }
        g.BFS();
//        g.DFS();
        System.out.println();
        System.out.println("The maxRank is " + g.maxRank);
    }

}


class MaxRankGraph {
    int V;
    LinkedList<Integer>[] adjListArray;
    int maxRank;

    MaxRankGraph(int V) {
        this.V = V;
        adjListArray = new LinkedList[V];

        for(int i = 0; i < V; i++){
            adjListArray[i] = new LinkedList<>();
        }
    }

    void addEdge( int src, int dest) {
        adjListArray[src].add(dest);
        adjListArray[dest].add(src);
    }

    void DFSUtil(int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v+" ");
        for (int x : adjListArray[v]) {
            if(!visited[x]) {
                DFSUtil(x,visited);
            }
        }
    }

    void DFS() {
        boolean[] visited = new boolean[V];
        for(int v = 0; v < V; v++) {
            if(!visited[v]) {
                DFSUtil(v, visited);
                System.out.println();
            }
        }
    }

    // prints BFS traversal from a given source s
    void BFS()
    {
        // Mark all the vertices as not visited(By default
        // set as false)
        boolean visited[] = new boolean[V];

        // Create a queue for BFS
        LinkedList<Integer> queue = new LinkedList<Integer>();

        // Mark the current node as visited and enqueue it
        for (int s = 0; s < V; s++) {
            if (visited[s] == true) {
                continue;
            }
            visited[s] = true;
            queue.add(s);
            while (queue.size() != 0)
            {
                // Dequeue a vertex from queue and print it
                s = queue.poll();
                System.out.print(s+" ");

                // Get all adjacent vertices of the dequeued vertex s
                // If a adjacent has not been visited, then mark it
                // visited and enqueue it
                Iterator<Integer> i = adjListArray[s].listIterator();
                while (i.hasNext())
                {
                    int n = i.next();
                    if (!visited[n])
                    {
                        int rank = adjListArray[s].size() + adjListArray[n].size() - 1;
                        if (maxRank < rank) {
                            maxRank = rank;
                        }
                        visited[n] = true;
                        queue.add(n);
                    }
                }
            }
        }
    }
}