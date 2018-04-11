package project.capstone.com.matchingkak.list_game.adapter;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import project.capstone.com.matchingkak.GameItemViewUtils;

public class CardItem {
    @SerializedName("gm_no")
    @Expose
    private String gmNo;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("teamName")
    @Expose
    private String teamName;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("sport")
    @Expose
    private String sport;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("state")
    @Expose
    private String state;


    public String getGmNo() {
        return gmNo;
    }

    public void setGmNo(String gmNo) {
        this.gmNo = gmNo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getDate() {
        String result="정보없음";
        try{
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date                time         =simpleDateFormat.parse(this.date);
            simpleDateFormat.applyPattern("yyyy.MM.dd.");
            result      =simpleDateFormat.format(time);
        }catch (ParseException e){

        }


        return  result;
    }

    public String get_DDay(){

        return GameItemViewUtils.getD_day("yyyy-MM-dd HH:mm:ss",this.date);

    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
