package com.server.elearning.dto;

import com.server.elearning.model.Content;

import java.util.List;

public class ResponseDTO {
    private String message;
    private String status;

    private boolean success;
    private List<?> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> content) {
        this.data = content;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
