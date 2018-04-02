package project.capstone.com.matchingkak.Main.message.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by amco1 on 2018-03-28.
 */

public class MSGData {

    @SerializedName("ms_title")
    @Expose
    private String ms_title;
    @SerializedName("ms_content")
    @Expose
    private String ms_content;

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

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = dt.parse(ms_send_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy/MM/dd");


        return dt1.format(date);
    }

    public void setMs_send_date(String ms_send_date) {
        this.ms_send_date = ms_send_date;
    }



}