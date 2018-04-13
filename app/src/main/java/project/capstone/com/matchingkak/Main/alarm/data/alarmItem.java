package project.capstone.com.matchingkak.Main.alarm.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import project.capstone.com.matchingkak.GameItemViewUtils;

/**
 * Created by amco1 on 2018-03-16.
 */

public class alarmItem implements Parcelable {

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

    protected alarmItem(Parcel in) {
        user = in.readString();
        user_no = in.readString();
        tm_img = in.readString();
        rq_isread = in.readString();
        state = in.readString();
        time = in.readString();
        mb_no = in.readString();
        rq_no = in.readString();
        article_title = in.readString();
        rq_count_no = in.readString();
        rq_type = in.readString();
    }

    public static final Creator<alarmItem> CREATOR = new Creator<alarmItem>() {
        @Override
        public alarmItem createFromParcel(Parcel in) {
            return new alarmItem(in);
        }

        @Override
        public alarmItem[] newArray(int size) {
            return new alarmItem[size];
        }
    };

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
/*
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = dt.parse(this.time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");


        return dt1.format(date);

  */

        return GameItemViewUtils.getElapsed_time(GameItemViewUtils.DatePattern,this.time);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(user);
        parcel.writeString(user_no);
        parcel.writeString(tm_img);
        parcel.writeString(rq_isread);
        parcel.writeString(state);
        parcel.writeString(time);
        parcel.writeString(mb_no);
        parcel.writeString(rq_no);
        parcel.writeString(article_title);
        parcel.writeString(rq_count_no);
        parcel.writeString(rq_type);
    }
}