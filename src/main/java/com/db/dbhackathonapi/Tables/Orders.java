package com.db.dbhackathonapi.Tables;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Orders {

    String clientname;
    String security;
    String tradedate;
    String tradetime;
    int quantity;
    String tradetype;
    Double limitprice;
    String direction;
    Double value;
    String  broker;
    @Id
    String isinno;

    public Orders(){

    }

    public Orders(String clientname, String security, String tradedate, String tradetime, int quantity, String tradetype, Double limitprice, String direction, Double value, String broker, String isinno) {
        this.clientname = clientname;
        this.security = security;
        this.tradedate = tradedate;
        this.tradetime = tradetime;
        this.quantity = quantity;
        this.tradetype = tradetype;
        this.limitprice = limitprice;
        this.direction = direction;
        this.value = value;
        this.broker = broker;
        this.isinno = isinno;
    }

    @Override
    public String toString() {
        return "orders{" +
                "clientname='" + clientname + '\'' +
                ", security='" + security + '\'' +
                ", tradedate='" + tradedate + '\'' +
                ", tradetime='" + tradetime + '\'' +
                ", quantity=" + quantity +
                ", tradetype='" + tradetype + '\'' +
                ", limitprice=" + limitprice +
                ", direction='" + direction + '\'' +
                ", value=" + value +
                ", broker='" + broker + '\'' +
                ", isinno='" + isinno + '\'' +
                '}';
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public String getTradedate() {
        return tradedate;
    }

    public void setTradedate(String tradedate) {
        this.tradedate = tradedate;
    }

    public String getTradetime() {
        return tradetime;
    }

    public void setTradetime(String tradetime) {
        this.tradetime = tradetime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTradetype() {
        return tradetype;
    }

    public void setTradetype(String tradetype) {
        this.tradetype = tradetype;
    }

    public Double getLimitprice() {
        return limitprice;
    }

    public void setLimitprice(Double limitprice) {
        this.limitprice = limitprice;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getBroker() {
        return broker;
    }

    public void setBroker(String broker) {
        this.broker = broker;
    }

    public String getIsinno() {
        return isinno;
    }

    public void setIsinno(String isinno) {
        this.isinno = isinno;
    }
}

