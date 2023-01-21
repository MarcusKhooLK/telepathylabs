package com.assessment.question2.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

public class Plan {
    private String name;
    private double price;
    private List<String> features = new ArrayList<>();
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public List<String> getFeatures() {
        return features;
    }
    public void setFeatures(List<String> features) {
        this.features = features;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public static Plan convert(JsonObject jsonObject) {
        Plan p = new Plan();
        p.setName(jsonObject.getString("name"));
        p.setPrice(jsonObject.getJsonNumber("price").doubleValue());
        JsonArray featuresArray = jsonObject.getJsonArray("features");
        List<String> featuresList = new ArrayList<>(featuresArray.size());
        for(int i = 0; i < featuresArray.size(); i++) {
            featuresList.add(featuresArray.getString(i));
        }
        p.setFeatures(featuresList);
        return p;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(name + " | " + price + " | ");
        for(String f : features) {
            builder.append(f + ", ");
        }
        return builder.toString();
    }
}
