package com.example.cucumberDemo.cucumber;

import com.jayway.restassured.response.Response;

import javax.servlet.ServletResponseWrapper;

public class ScenarioWorld {
    private static ScenarioWorld instance;
    private Response response;

    public static ScenarioWorld getInstance() {
        if (instance == null) {
            instance = new ScenarioWorld();
        }
        return instance;
    }

    private ScenarioWorld() {
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
