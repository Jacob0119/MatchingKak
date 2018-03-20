package project.capstone.com.matchingkak.Main.alarm.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by amco1 on 2018-03-16.
 */

public class alarmItem {

    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("user_no")
    @Expose
    private String user_no;

    @SerializedName("tm_img")
    @Expose
    private String tm_img;
    @SerializedName("rq_isread")
    @Expose
    private String rq_isread;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("mb_no")
    @Expose
    private String mb_no;
    @SerializedName("rq_no")
    @Expose
    private String rq_no;
    @SerializedName("article_title")
    @Expose
    private String article_title;
    @SerializedName("rq_count_no")
    @Expose
    private String rq_count_no;
    @SerializedName("rq_type")
    @Expose
    private String rq_type;

    public String getRq_type() {
        return rq_type;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser_no(String user_no) {
        this.user_no = user_no;
    }

    public String getUser_no() {
        return user_no;
    }

    public String getTm_img() {
        return tm_img;
    }

    public void setTm_img(String tm_img) {
        this.tm_img = tm_img;
    }

    public void setRq_isread(String rq_isread) {
        this.rq_isread = rq_isread;
    }

    public String getRq_isread() {
        return rq_isread;
    }

    public String getRq_no() {
        return rq_no;
    }

    public String getState() {
        return state;
    }

    public String getMb_no() {
        return mb_no;
    }

    public String getRq_count_no() {
        return rq_count_no;
    }

    public String getArticle_title() {
        return article_title;
    }

    public String getAlarm_send_date() {

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = dt.parse(this.time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");


        return dt1.format(date);
    }




}