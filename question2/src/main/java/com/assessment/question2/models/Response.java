package com.assessment.question2.models;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Response {

    private double price;

    private List<String> planNames;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getPlanNames() {
        return planNames;
    }

    public void setPlanNames(List<String> planNames) {
        this.planNames = planNames;
    }

    public static Response convert(String jsonString) {
        Response resp = new Response();
        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jObj = reader.readObject();
        resp.setPrice(jObj.getJsonNumber("price").doubleValue());
        JsonArray jsonArray = jObj.getJsonArray("plans");
        List<String> planNames = new ArrayList<>();
        for(int i = 0; i < jsonArray.size(); i++) {
            planNames.add(jsonArray.getString(i));
        }
        resp.setPlanNames(planNames);
        return resp;
    }

    public JsonObject toJson() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for(String s : planNames) {
            arrayBuilder.add(s);
        }
        JsonObject jObj = Json.createObjectBuilder()
                .add("price", price)
                .add("plans", arrayBuilder.build())
                .build();
        return jObj;
    }
}
