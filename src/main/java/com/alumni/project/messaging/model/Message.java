package com.alumni.project.messaging.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Message {

    private String sender;
    private String receiver;
    private String message;
    private String date;
    private Status status;
}
