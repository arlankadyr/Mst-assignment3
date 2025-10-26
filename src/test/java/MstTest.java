
import Algorithm.PrimMST;
import Algorithm.KruskalMST;
import Model.Graph;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MstTest {

    @Test
    void testMstCostEquality() {
        Graph graph = new Graph();
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 2);
        graph.addEdge("A", "C", 4);

        PrimMST.Result primRes = new PrimMST().findMST(graph);
        KruskalMST.Result kruskalRes = new KruskalMST().findMST(graph);

        assertEquals(primRes.totalCost, kruskalRes.totalCost, "MST costs must be equal");
    }

    @Test
    void testEdgeCount() {
        Graph graph = new Graph();
        graph.addEdge("A", "B", 1);
        graph.addEdge("B", "C", 2);

        PrimMST.Result res = new PrimMST().findMST(graph);
        assertEquals(2, res.mstEdges.size(), "MST edges = V-1 = 2");
    }

    @Test
    void testNonNegativeTime() {
        Graph graph = new Graph();
        graph.addEdge("A", "B", 1);

        PrimMST.Result res = new PrimMST().findMST(graph);
        assertTrue(res.executionTimeMs >= 0, "Time must be non-negative");
    }

    @Test
    void testReproducibleResults() {
        Graph graph = new Graph();
        graph.addEdge("A", "B", 1);

        PrimMST.Result res1 = new PrimMST().findMST(graph);
        PrimMST.Result res2 = new PrimMST().findMST(graph);

        assertEquals(res1.totalCost, res2.totalCost, "Results must be reproducible");
    }
}