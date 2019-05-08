package com.example.pizzastorecall.datas;

import java.io.Serializable;

public class Store implements Serializable {

    private String name;
    private String open;
    private String close;
    private String callNum;
    private String logoImgUrl;

    public Store(String name, String open, String close, String callNum, String logoImgUrl) {
        this.name = name;
        this.open = open;
        this.close = close;
        this.callNum = callNum;
        this.logoImgUrl = logoImgUrl;
    }

    public String getName() {
        return name;
    }

    public String getOpen() {
        return open;
    }

    public String getClose() {
        return close;
    }

    public String getCallNum() {
        return callNum;
    }

    public String getLogoImgUrl() {
        return logoImgUrl;
    }
}
