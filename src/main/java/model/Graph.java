package Model;

import java.util.*;

public class Graph {
    private final Set<String> nodes = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();

    public void addNode(String node) {
        nodes.add(node);
    }

    public void addEdge(String from, String to, int weight) {
        addNode(from);
        addNode(to);
        edges.add(new Edge(from, to, weight));
    }

    public Set<String> getNodes() {
        return new HashSet<>(nodes);
    }

    public List<Edge> getEdges() {
        return new ArrayList<>(edges);
    }

    public int getVertexCount() {
        return nodes.size();
    }

    public int getEdgeCount() {
        return edges.size();
    }

    @Override
    public String toString() {
        return "Graph{nodes=" + nodes + ", edges=" + edges + "}";
    }
}