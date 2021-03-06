package project.capstone.com.matchingkak.Main.home.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import project.capstone.com.matchingkak.GameItemViewUtils;

public class HomeData {

    @SerializedName("gm_no")
    @Expose
    private String gmNo;
    @SerializedName("gm_title")
    @Expose
    private String gmTitle;
    @SerializedName("tm_name")
    @Expose
    private String tmName;
    @SerializedName("tm_img")
    @Expose
    private String tmImg;
    @SerializedName("tm_sport")
    @Expose
    private String tmSport;
    @SerializedName("gm_date")
    @Expose
    private String gmDate;
    @SerializedName("gm_gym")
    @Expose
    private String gmGym;
    @SerializedName("gm_state")
    @Expose
    private String gmState;

    public String getGmNo() {
        return gmNo;
    }

    public void setGmNo(String gmNo) {
        this.gmNo = gmNo;
    }

    public String getGmTitle() {
        return gmTitle;
    }

    public void setGmTitle(String gmTitle) {
        this.gmTitle = gmTitle;
    }

    public String getTmName() {
        return tmName;
    }

    public void setTmName(String tmName) {
        this.tmName = tmName;
    }

    public String getTmImg() {
        return tmImg;
    }

    public void setTmImg(String tmImg) {
        this.tmImg = tmImg;
    }

    public String getTmSport() {
        return tmSport;
    }

    public void setTmSport(String tmSport) {
        this.tmSport = tmSport;
    }

    public String getGmDate() {

     return GameItemViewUtils.getCustomDate(GameItemViewUtils.DatePattern,this.gmDate,GameItemViewUtils.SimpleDatePattern);
    }

    public void setGmDate(String gmDate) {
        this.gmDate = gmDate;
    }

    public String getGmGym() {
        return gmGym;
    }

    public void setGmGym(String gmGym) {
        this.gmGym = gmGym;
    }

    public String getGmState() {
        return gmState;
    }

    public void setGmState(String gmState) {
        this.gmState = gmState;
    }

}

