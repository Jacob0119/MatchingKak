package project.capstone.com.matchingkak.Main.message.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import project.capstone.com.matchingkak.GameItemViewUtils;

/**
 * Created by amco1 on 2018-03-28.
 */

public class MSGData implements Parcelable {

    @SerializedName("ms_title")
    @Expose
    private String ms_title;
    @SerializedName("ms_content")
    @Expose
    private String ms_content;
    @SerializedName("tm_img")
    @Expose
    private String tm_img;
    @SerializedName("mb_nick")
    @Expose
    private String mb_nick;
    @SerializedName("ms_send_date")
    @Expose
    private String ms_send_date;
    @SerializedName("ms_read")
    @Expose
    private String ms_read;

    @SerializedName("ms_no")
    @Expose
    private String ms_no;

    protected MSGData(Parcel in) {
        ms_title = in.readString();
        ms_content = in.readString();
        tm_img = in.readString();
        mb_nick = in.readString();
        ms_send_date = in.readString();
        ms_read = in.readString();
        ms_no = in.readString();
    }

    public static final Creator<MSGData> CREATOR = new Creator<MSGData>() {
        @Override
        public MSGData createFromParcel(Parcel in) {
            return new MSGData(in);
        }

        @Override
        public MSGData[] newArray(int size) {
            return new MSGData[size];
        }
    };

    public String getMs_no() {
        return ms_no;
    }

    public String getMs_title() {
        return ms_title;
    }

    public void setMs_title(String ms_title) {
        this.ms_title=ms_title;
    }

    public String getMs_content() {
        return ms_content;
    }

    public void setMs_content(String ms_content) {
        this.ms_content = ms_content;
    }

    public String getMb_nick() {return mb_nick;
    }
    public String getTm_img(){return tm_img;}

    public void setTm_img(String tm_img) {
        this.tm_img = tm_img;
    }

    public void setMb_nick(String mb_nick) {
        this.mb_nick = mb_nick;
    }

    public boolean getMs_read() {
        return ms_read.equals("0");
    }

    public void setMs_read(String ms_read) {
        this.ms_read = ms_read;
    }



    public String getMs_send_date() {

        return GameItemViewUtils.getCustomDate(GameItemViewUtils.DatePattern,this.ms_send_date,GameItemViewUtils.SimpleDatePattern);
    }

    public void setMs_send_date(String ms_send_date) {
        this.ms_send_date = ms_send_date;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(ms_title);
        parcel.writeString(ms_content);
        parcel.writeString(tm_img);
        parcel.writeString(mb_nick);
        parcel.writeString(ms_send_date);
        parcel.writeString(ms_read);
        parcel.writeString(ms_no);
    }
}