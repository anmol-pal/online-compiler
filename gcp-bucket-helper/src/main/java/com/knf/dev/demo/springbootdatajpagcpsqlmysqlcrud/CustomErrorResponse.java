package com.knf.dev.demo.springbootdatajpagcpsqlmysqlcrud;

import java.time.LocalDateTime;

public class CustomErrorResponse {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
}