package com.hotelsystem.hotelsystem.web.data_models;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ServerResponse {
    private Date timeStamp;
    private int errorStatus;
    private String errorReason;
    private String errorMessage;
    private String errorPath;

    public ServerResponse(HttpStatus httpStatus, String errorMessage, String errorPath) {
        this.timeStamp = new Date();
        this.errorStatus = httpStatus.value();
        this.errorReason = httpStatus.getReasonPhrase();
        this.errorMessage = errorMessage;
        this.errorPath = errorPath;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public int getErrorStatus() {
        return errorStatus;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorPath() {
        return errorPath;
    }
}
