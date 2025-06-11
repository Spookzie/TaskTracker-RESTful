package com.spookzie.TaskTracker.domain.dto;

/*
* status - HTTP status
* message - type of error
* details - specifics
*****************************/
public record ErrorResponse (
        int status,
        String message,
        String details
){
}
