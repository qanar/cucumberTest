package com.example.cucumberDemo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Email {
    private String value;
    private String uuid;

    public Email(String value) {
        this.value = value.trim().toLowerCase();
        this.uuid = UUID.randomUUID().toString();
    }

    @JsonCreator
    public Email(@JsonProperty("value") String value, @JsonProperty("uuid") String uuid) {
        this.value = value;
        this.uuid = uuid;
    }

    public String getValue() {
        return value;
    }

    public String getUuid() {
        return uuid;
    }
}
