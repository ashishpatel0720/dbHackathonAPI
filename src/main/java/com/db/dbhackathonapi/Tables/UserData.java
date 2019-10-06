package com.db.dbhackathonapi.Tables;

public class UserData {
    public  Double price;
    public  String direction;
    public String symbol;
    public String brokerid;
    public int quantity;
    public String clientname;
    public String isinno;
    public int hours;
    public int minutes;


    public UserData(){

    }

    public UserData(Double price, String direction, String symbol, String brokerid, int quantity, String clientname, String isinno, int hours, int minutes) {
        this.price = price;
        this.direction = direction;
        this.symbol = symbol;
        this.brokerid = brokerid;
        this.quantity = quantity;
        this.clientname = clientname;
        this.isinno = isinno;
        this.hours = hours;
        this.minutes = minutes;
    }

    public String getIsinno() {
        return isinno;
    }

    public void setIsinno(String isinno) {
        this.isinno = isinno;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getBrokerid() {
        return brokerid;
    }

    public void setBrokerid(String brokerid) {
        this.brokerid = brokerid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }
}
