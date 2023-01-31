import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.Queue;
import java.util.HashSet;

public class GraphAlgorithms {

    /**
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the bfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> breadthFirstSearch(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Vertex does not exist");
        }
        List<Vertex<T>> visitedList = new ArrayList<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        visitedList.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            Vertex<T> vertex = queue.remove();
            List<VertexDistance<T>> vertexDistances = adjList.get(vertex);
            for (VertexDistance<T> vertaxDistance : vertexDistances) {
                Vertex<T> w = vertaxDistance.getVertex();
                if (!visitedList.contains(w)) {
                    queue.add(w);
                    visitedList.add(w);
                }
            }
        }
        return list;
    }

    /**
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the dfs on
     * @param graph the graph to search through
     * @return list of vertices in visited order
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph
     */
    public static <T> List<Vertex<T>> depthFirstSearch(Vertex<T> start, Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Vertex does not exist");
        }
        List<Vertex<T>> visitList = new ArrayList<>();
        Set<Vertex<T>> visitedSet = new HashSet<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        depthFirstSearch(start, adjList, list, visitedSet);
        return visitList;
    }

    /**
     * Helper method for dfs
     * @param start starting vertex
     * @param adjacentList adjacency list
     * @param list list of vertices visited
     * @param visitedSet set of visited vertices
     * @param <T> generic type
     */
    private static <T> void depthFirstSearch(Vertex<T> start,
                                Map<Vertex<T>, List<VertexDistance<T>>> adjacentList,
                                List<Vertex<T>> visitList,
                                Set<Vertex<T>> visitedSet) {
        if (!visitedSet.contains(start)) {
            visitList.add(start);
            visitedSet.add(start);
            List<VertexDistance<T>> vertexDistances = adjacentList.get(start);
            for (VertexDistance<T> vertexDistance: vertexDistances) {
                Vertex<T> v = vertexDistance.getVertex();
                depthFirstSearch(v, adjacentList, visitList, visitedSet);
            }
        }
    }

    /**
     * @param <T>   the generic typing of the data
     * @param start the vertex to begin the Dijkstra's on (source)
     * @param graph the graph we are applying Dijkstra's to
     * @return a map of the shortest distances from start to every
     * other node in the graph
     * @throws IllegalArgumentException if any input is null, or if start
     *                                  doesn't exist in the graph.
     */
    public static <T> Map<Vertex<T>, Integer> dijkstrasAlgorithm(Vertex<T> start,
                                                        Graph<T> graph) {
        if (start == null || graph == null) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (!graph.getAdjList().containsKey(start)) {
            throw new IllegalArgumentException("Vertex does not exist");
        }
        Set<Vertex<T>> visited = new HashSet<>();
        Map<Vertex<T>, Integer> map = new HashMap<>();
        PriorityQueue<VertexDistance<T>> pq = new PriorityQueue<>();
        Map<Vertex<T>, List<VertexDistance<T>>> adjList = graph.getAdjList();
        for (Vertex<T> vertex : adjList.keySet()) {
            if (start.equals(vertex)) {
                map.put(start, 0);
            } else {
                map.put(vertex, Integer.MAX_VALUE);
            }
        }
        VertexDistance<T> vertexDistance = new VertexDistance<>(start, 0);
        pq.add(vertexDistance);
        while (!pq.isEmpty() && visited.size() < graph.getVertices().size()) {
            VertexDistance<T> v1 = pq.remove();
            Vertex<T> vertex = v1.getVertex();
            List<VertexDistance<T>> vds = graph.getAdjList().get(vertex);
            visited.add(vertex);
            for (VertexDistance<T> v2 : vds) {
                Vertex<T> vertex2 = v2.getVertex();
                int d1 = v1.getDistance();
                int d2 = v2.getDistance();
                if (map.get(vertex2) > (d1 + d2)) {
                    map.put(v2.getVertex(), d1 + d2);
                    VertexDistance<T> v3 = new VertexDistance<>(v2.getVertex(), d1 + d2);
                    pq.add(v3);
                }
            }
        }
        return map;
    }

    /**
     * @param <T>   the generic typing of the data
     * @param graph the graph we are applying Kruskals to
     * @return the MST of the graph or null if there is no valid MST
     * @throws IllegalArgumentException if any input is null
     */
    public static <T> Set<Edge<T>> kruskalsAlgorithm(Graph<T> graph) {
        if (graph == null) {
            throw new IllegalArgumentException("Input is invalid");
        }
        Set<Edge<T>> mst = new HashSet<>();
        PriorityQueue<Edge<T>> pq = new PriorityQueue<>(graph.getEdges());
        DisjointSet<Vertex<T>> ds = new DisjointSet<>();
        int i = graph.getVertices().size();
        while (!pq.isEmpty()) {
            Edge<T> uv = pq.remove();
            Vertex<T> u = uv.getU();
            Vertex<T> v = uv.getV();
            Vertex<T> uds = ds.find(u);
            Vertex<T> vds = ds.find(v);
            if (!uds.equals(vds) || (uds == null || vds == null)) {
                Edge<T> vu = new Edge<>(v, u, uv.getWeight());
                mst.add(uv);
                mst.add(vu);
                ds.union(u, v);
            }
            if (mst.size() >= (i - 1) * 2) {
                break;
            }
        }
        if (mst.size() == (i - 1) * 2) {
            return mst;
        } else {
            return null;
        }
    }
}
