package com.guguzitha.leaderboard.model;

import com.google.gson.annotations.SerializedName;

public class LearnersHours {

    private String name;

    private Integer hours;

    private String country;

    private String badgeUrl;

    public LearnersHours(String name, Integer hours, String country, String url){
        this.name = name;
        this.hours = hours;
        this.country = country;
        this.badgeUrl = url;

    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Integer getHours(){
        return hours;
    }
    public void setHours(Integer hours){
        this.hours = hours;

    }
    public String getCountry(){
        return country;
    }
    public void setCountry(String country){

        this.country = country;
    }
    public String getBadgeUrl(){

        return badgeUrl;
    }
    public void setBadgeUrl(String Url){

        this.badgeUrl = badgeUrl;
    }

}
