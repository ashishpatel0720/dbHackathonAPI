package com.db.dbhackathonapi.response;

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

    @Override
    public String toString() {
        return "Response{" +
                "code=" + code +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
