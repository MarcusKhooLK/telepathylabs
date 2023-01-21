package com.assessment.question2.models;

import jakarta.json.JsonObject;

public class Response {
    private JsonObject data;

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }
}
