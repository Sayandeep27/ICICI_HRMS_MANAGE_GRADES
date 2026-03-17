package com.hrms.gradeband.response;

import java.time.LocalDateTime;

public class ApiResponse {

    private LocalDateTime timestamp;
    private int status;
    private boolean success;
    private String message;
    private Object data;

    public ApiResponse(int status, boolean success, String message, Object data) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public Object getData() { return data; }

    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public void setStatus(int status) { this.status = status; }
    public void setSuccess(boolean success) { this.success = success; }
    public void setMessage(String message) { this.message = message; }
    public void setData(Object data) { this.data = data; }
}