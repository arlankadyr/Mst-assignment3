package Algorithm;

import Model.Edge;
import Model.Graph;

import java.util.*;

public class KruskalMST {
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

    private static class UnionFind {
        private final Map<String, String> parent = new HashMap<>();
        private final Map<String, Integer> rank = new HashMap<>();

        public UnionFind(Set<String> nodes) {
            for (String node : nodes) {
                parent.put(node, node);
                rank.put(node, 0);
            }
        }

        public String find(String x) {
            if (!parent.get(x).equals(x)) {
                parent.put(x, find(parent.get(x)));
            }
            return parent.get(x);
        }

        public boolean union(String x, String y) {
            String rootX = find(x);
            String rootY = find(y);
            if (rootX.equals(rootY)) return false;

            if (rank.get(rootX) > rank.get(rootY)) {
                parent.put(rootY, rootX);
            } else if (rank.get(rootX) < rank.get(rootY)) {
                parent.put(rootX, rootY);
            } else {
                parent.put(rootY, rootX);
                rank.put(rootX, rank.get(rootX) + 1);
            }
            return true;
        }
    }

    public Result findMST(Graph graph) {
        int ops = 0;
        long start = System.nanoTime();

        List<Edge> sortedEdges = new ArrayList<>(graph.getEdges());
        sortedEdges.sort(Edge::compareTo);
        ops += sortedEdges.size() * 2;

        UnionFind uf = new UnionFind(graph.getNodes());
        List<Edge> mstEdges = new ArrayList<>();

        for (Edge edge : sortedEdges) {
            ops++;
            if (uf.union(edge.getFrom(), edge.getTo())) {
                mstEdges.add(edge);
            }
            if (mstEdges.size() == graph.getVertexCount() - 1) break;
        }

        int totalCost = mstEdges.stream().mapToInt(Edge::getWeight).sum();
        long time = (System.nanoTime() - start) / 1_000_000;

        return new Result(mstEdges, totalCost, ops, time);
    }
}