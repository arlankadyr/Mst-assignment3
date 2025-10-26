package Utils;

import Algorithm.PrimMST;
import Algorithm.KruskalMST;
import Model.Edge;


import Algorithm.PrimMST;
import Algorithm.KruskalMST;
import Model.Edge;
import Model.Graph;
import com.google.gson.*;

import java.io.FileWriter;
import java.util.List;

public class ResultRecorder {
    public static void recordResults(String inputFile, String outputFile) throws Exception {
        List<Graph> graphs = JsonParser.parseInput(inputFile);
        JsonArray results = new JsonArray();

        for (int i = 0; i < graphs.size(); i++) {
            Graph graph = graphs.get(i);
            JsonObject resultObj = new JsonObject();

            resultObj.addProperty("graph_id", i + 1);
            JsonObject stats = new JsonObject();
            stats.addProperty("vertices", graph.getVertexCount());
            stats.addProperty("edges", graph.getEdgeCount());
            resultObj.add("input_stats", stats);

            // Prim
            PrimMST prim = new PrimMST();
            PrimMST.Result primRes = prim.findMST(graph);
            JsonObject primObj = toJsonObject(primRes, "prim");
            resultObj.add("prim", primObj);

            // Kruskal
            KruskalMST kruskal = new KruskalMST();
            KruskalMST.Result kruskalRes = kruskal.findMST(graph);
            JsonObject kruskalObj = toJsonObject(kruskalRes, "kruskal");
            resultObj.add("kruskal", kruskalObj);

            results.add(resultObj);
        }

        try (FileWriter writer = new FileWriter(outputFile)) {
            JsonObject root = new JsonObject();
            root.add("results", results);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(root, writer);
        }
    }

    private static JsonObject toJsonObject(Object res, String type) {
        if (res instanceof PrimMST.Result) {
            PrimMST.Result r = (PrimMST.Result) res;
            JsonObject obj = new JsonObject();
            JsonArray edgesArray = new JsonArray();
            for (Edge e : r.mstEdges) {
                JsonObject edgeObj = new JsonObject();
                edgeObj.addProperty("from", e.getFrom());
                edgeObj.addProperty("to", e.getTo());
                edgeObj.addProperty("weight", e.getWeight());
                edgesArray.add(edgeObj);
            }
            obj.add("mst_edges", edgesArray);
            obj.addProperty("total_cost", r.totalCost);
            obj.addProperty("operations_count", r.operationsCount);
            obj.addProperty("execution_time_ms", r.executionTimeMs);
            return obj;
        } else if (res instanceof KruskalMST.Result) {
            KruskalMST.Result r = (KruskalMST.Result) res;
            JsonObject obj = new JsonObject();
            JsonArray edgesArray = new JsonArray();
            for (Edge e : r.mstEdges) {
                JsonObject edgeObj = new JsonObject();
                edgeObj.addProperty("from", e.getFrom());
                edgeObj.addProperty("to", e.getTo());
                edgeObj.addProperty("weight", e.getWeight());
                edgesArray.add(edgeObj);
            }
            obj.add("mst_edges", edgesArray);
            obj.addProperty("total_cost", r.totalCost);
            obj.addProperty("operations_count", r.operationsCount);
            obj.addProperty("execution_time_ms", r.executionTimeMs);
            return obj;
        }
        return new JsonObject();
    }
}