package com.alumni.project.dto.error;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {

    private String message;
    private int errorCode;

}
