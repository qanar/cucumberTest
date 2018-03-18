package com.example.cucumberDemo.cucumber;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import org.apache.http.HttpStatus;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RestBaseSteps {

    protected void get(String path, Object... pathParams) {
        ScenarioWorld.getInstance().setResponse(getResponse(path, pathParams));
    }

    protected void isResponseOk() {
        assertThat(ScenarioWorld.getInstance().getResponse().statusCode(), is(HttpStatus.SC_OK));
    }

    protected Response getResponse(String path, Object... pathParams) {
        return given().contentType(ContentType.JSON)
                .get(path, mapToStrings(pathParams));
    }

    protected Response post(String body, String path, Object... pathParams) {
        Response response = given().contentType(ContentType.JSON)
                .body(body)
                .post(path, mapToStrings(pathParams));
        ScenarioWorld.getInstance().setResponse(response);
        return response;
    }

    private Object[] mapToStrings(Object... pathParams) {
        for (int i = 0; i < pathParams.length; i++) {
            if (pathParams[i] != null) {
                pathParams[i] = pathParams[i].toString();
            } else {
                String test = "test";
            }
        }
        return pathParams;
    }
}
