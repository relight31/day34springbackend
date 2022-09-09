package com.vttp.csf.springangulardemo.models;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Registration {
    private String name;
    private String email;
    private String id;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static Registration createRegistration(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject object = reader.readObject();
        Registration reg = new Registration();
        reg.setName(object.getString("name"));
        reg.setEmail(object.getString("email"));
        if (object.containsKey("id")) {
            reg.setId(object.getString("id"));
        }
        return reg;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("name", name)
                .add("email", email)
                .add("id", id)
                .build();
    }
}
