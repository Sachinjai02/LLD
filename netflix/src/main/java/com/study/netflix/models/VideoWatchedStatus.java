package com.study.netflix.models;

import lombok.Getter;

@Getter
public enum VideoWatchedStatus {

    INPROGRESS("IN PROGRESS"),
    COMPLETED("COMPLETED");

    private final String status;
    VideoWatchedStatus(String status) {
        this.status = status;
    }
}
