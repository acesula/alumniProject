package com.alumni.project.messaging.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMessage {
    private String content;

    public ResponseMessage(){

    }
    public ResponseMessage(String content){
        this.content = content;
    }
}
