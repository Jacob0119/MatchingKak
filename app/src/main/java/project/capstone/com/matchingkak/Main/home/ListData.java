package project.capstone.com.matchingkak.Main.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ListData {

    @SerializedName("response")
    @Expose
    private String response;
    @SerializedName("isLogin")
    @Expose
    private boolean isLogin;
    @SerializedName("page")
    @Expose
    private String page;
    @SerializedName("data")
    @Expose
    private List<HomeData> data = null;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean getIsLogin(){return isLogin;}

    public void setIsLogin(boolean isLogin){this.isLogin=isLogin;}

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<HomeData> getData() {
        return data;
    }

    public void setData(List<HomeData> data) {
        this.data = data;
    }




}
class HomeData {

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

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = dt.parse(gmDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy/MM/dd");


        return dt1.format(date);
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

