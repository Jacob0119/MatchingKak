package project.capstone.com.matchingkak.Main.me.data;

/**
 * Created by Lee on 2018-04-05.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InfoData {

    @SerializedName("mb_nick")
    @Expose
    private String mbNick;
    @SerializedName("tm_name")
    @Expose
    private String tmName;
    @SerializedName("tm_img")
    @Expose
    private String tmImg;
    @SerializedName("tm_match_count")
    @Expose
    private String tmMatchCount;
    @SerializedName("tm_penalty")
    @Expose
    private String tmPenalty;

    public String getMbNick() {
        return mbNick;
    }

    public void setMbNick(String mbNick) {
        this.mbNick = mbNick;
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

    public String getTmMatchCount() {
        return tmMatchCount;
    }

    public void setTmMatchCount(String tmMatchCount) {
        this.tmMatchCount = tmMatchCount;
    }

    public String getTmPenalty() {
        return tmPenalty;
    }

    public void setTmPenalty(String tmPenalty) {
        this.tmPenalty = tmPenalty;
    }

}