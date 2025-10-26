package Utils;

import Model.Graph;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {
    public static List<Graph> parseInput(String resourcePath) throws Exception {
        Gson gson = new Gson();
        try (InputStreamReader reader = new InputStreamReader(
                JsonParser.class.getResourceAsStream(resourcePath), StandardCharsets.UTF_8)) {
            JsonArray array = gson.fromJson(reader, JsonArray.class);
            List<Graph> graphs = new ArrayList<>();

            for (JsonElement elem : array) {
                JsonObject obj = elem.getAsJsonObject();
                Graph graph = new Graph();

                JsonArray nodesArray = obj.getAsJsonArray("nodes");
                for (JsonElement node : nodesArray) {
                    graph.addNode(node.getAsString());
                }

                JsonArray edgesArray = obj.getAsJsonArray("edges");
                for (JsonElement edgeElem : edgesArray) {
                    JsonObject edgeObj = edgeElem.getAsJsonObject();
                    String from = edgeObj.get("from").getAsString();
                    String to = edgeObj.get("to").getAsString();
                    int weight = edgeObj.get("weight").getAsInt();
                    graph.addEdge(from, to, weight);
                }
                graphs.add(graph);
            }
            return graphs;
        }
    }
}