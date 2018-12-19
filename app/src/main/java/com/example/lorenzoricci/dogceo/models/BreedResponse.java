package com.example.lorenzoricci.dogceo.models;

import java.util.List;

public class BreedResponse {
    private List<String> message = null;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
