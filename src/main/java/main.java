package com.mst;

import com.mst.utils.ResultRecorder;

public class Main {
    public static void main(String[] args) {
        try {
            String basePath = "src/main/resources/";
            ResultRecorder.recordResults(basePath + "input_small.json", "results/output_small.json");
            ResultRecorder.recordResults(basePath + "input_medium.json", "results/output_medium.json");
            ResultRecorder.recordResults(basePath + "input_large.json", "results/output_large.json");
            System.out.println("MST calculations completed! Check results/ folder.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}