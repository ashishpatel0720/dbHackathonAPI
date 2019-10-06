package com.db.dbhackathonapi.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class Response {

    private int code;
    private String title;
    private String description;
    private Object data; // it will contain anything we want to send back


    public Response(int code, String title, String description,Object data) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.data=data;
    }

}
