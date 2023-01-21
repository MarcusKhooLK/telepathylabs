package com.assessment.question2.services;

import java.io.FileInputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

import org.springframework.stereotype.Service;

import com.assessment.question2.models.Plan;
import com.assessment.question2.models.Response;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class PlanService {

    private List<Plan> plans = new ArrayList<>();
    private List<String> selectedFeatures = new ArrayList<>();
    private List<Plan> minPlans = new ArrayList<>();
    private Double minPrice = Double.MAX_VALUE;

    public String processFromFile(String filePath, String featureSet) throws Exception {
        reset();

        Scanner reader = new Scanner(new FileInputStream(filePath));
        while(reader.hasNext()) {
            Plan p = new Plan();
            String[] splitted = reader.nextLine().split(",");
            p.setName(splitted[0]);
            p.setPrice(Double.parseDouble(splitted[1]));
            List<String> features = new ArrayList<>();
            for(int i = 2; i < splitted.length; i++) {
                features.add(splitted[i]);
            }
            p.setFeatures(features);
            plans.add(p);
        }

        String[] splitted = featureSet.split(",");
        selectedFeatures = Arrays.asList(splitted);

        findCombinations(new ArrayList<>(), 0, 0.0);

        StringJoiner joiner = new StringJoiner(", ");
        minPlans.forEach(item -> joiner.add(item.getName()));
        return "%.2f, ".formatted(minPrice) + joiner.toString();
    }

    public Response processFromJsonString(String jsonString) {
        reset();

        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jObj = reader.readObject();
        JsonArray selectedFeaturesArray = jObj.getJsonArray("selectedFeatures");
        for(int i = 0; i < selectedFeaturesArray.size(); i++) {
            selectedFeatures.add(selectedFeaturesArray.getString(i));
        }
        JsonArray plansArray = jObj.getJsonArray("allPlans");
        for(int i = 0; i < plansArray.size(); i++) {
            Plan p = Plan.convert(plansArray.getJsonObject(i));
            plans.add(p);
        }

        findCombinations(new ArrayList<>(), 0, 0.0);

        Response resp = new Response();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        minPlans.forEach(item -> {
            arrayBuilder.add(item.getName());
        });
        jObj = Json.createObjectBuilder().add("price", minPrice).add("plans", arrayBuilder.build()).build();
        resp.setData(jObj);
        return resp;
    }

    private void findCombinations(List<Plan> currentPlans, int startIndex, double currentPrice) {
        if(containsAllFeatures(currentPlans)) {
            if(currentPrice < minPrice) {
                minPrice = currentPrice;
                minPlans = new ArrayList<>(currentPlans);
            }
        } else {
            for(int i = startIndex; i < plans.size(); i++) {
                currentPlans.add(plans.get(i));
                findCombinations(currentPlans, i + 1, currentPrice + plans.get(i).getPrice());
                currentPlans.remove(currentPlans.size() - 1);
            }
        }
    }

    private boolean containsAllFeatures(List<Plan> plans) {
        List<String> planFeatures = new ArrayList<>();
        for(Plan p : plans) {
            planFeatures.addAll(p.getFeatures());
        }
        for(String f : selectedFeatures) {
            if(!planFeatures.contains(f))
                return false;
        }
        return true;
    }

    private void reset() {
        plans = new ArrayList<>();
        selectedFeatures = new ArrayList<>();
        minPlans = new ArrayList<>();
        minPrice = Double.MAX_VALUE;
    }
}
