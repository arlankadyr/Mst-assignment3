package Algorithm; 

import Model.Edge;
import Model.Graph;
import java.util.*;

public class PrimMST {
    public static class Result {
        public List<Edge> mstEdges;
        public int totalCost;
        public int operationsCount;
        public long executionTimeMs;

        public Result(List<Edge> mstEdges, int totalCost, int operationsCount, long executionTimeMs) {
            this.mstEdges = mstEdges;
            this.totalCost = totalCost;
            this.operationsCount = operationsCount;
            this.executionTimeMs = executionTimeMs;
        }
    }

    public Result findMST(Graph graph) {
        int ops = 0;
        long start = System.nanoTime();

        Set<String> nodes = graph.getNodes();
        if (nodes.isEmpty()) {
            return new Result(new ArrayList<>(), 0, 0, 0);
        }

        Set<String> inMST = new HashSet<>();
        List<Edge> mstEdges = new ArrayList<>();
        Map<String, Integer> key = new HashMap<>();
        Map<String, String> parent = new HashMap<>();

        String startNode = nodes.iterator().next();
        for (String node : nodes) {
            key.put(node, Integer.MAX_VALUE);
            parent.put(node, null);
        }
        key.put(startNode, 0);
        ops += nodes.size();

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(null, startNode, 0));

        while (!pq.isEmpty() && inMST.size() < nodes.size()) {
            ops++;
            Edge edge = pq.poll();
            String u = edge.getTo();

            if (inMST.contains(u)) continue;

            inMST.add(u);
            if (edge.getFrom() != null) {
                mstEdges.add(new Edge(edge.getFrom(), u, edge.getWeight()));
            }

            for (Edge adj : graph.getEdges()) {
                ops++;
                String v = adj.getFrom().equals(u) ? adj.getTo() : adj.getFrom();
                if (!inMST.contains(v) && adj.getWeight() < key.get(v)) {
                    key.put(v, adj.getWeight());
                    parent.put(v, u);
                    pq.add(new Edge(u, v, adj.getWeight()));
                    ops += 2;
                }
            }
        }

        int totalCost = mstEdges.stream().mapToInt(Edge::getWeight).sum();
        long time = (System.nanoTime() - start) / 1_000_000;

        return new Result(mstEdges, totalCost, ops, time);
    }
}