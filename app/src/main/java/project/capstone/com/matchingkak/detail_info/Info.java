package project.capstone.com.matchingkak.detail_info;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import project.capstone.com.matchingkak.GameItemViewUtils;


class BtnState {

    @SerializedName("btnState")
    @Expose
    private String btnState;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("msg")
    @Expose
    private String msg;

    public String getBtnState() {
        return btnState;
    }

    public void setBtnState(String btnState) {
        this.btnState = btnState;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}



public class Info {

    @SerializedName("gm_no")
    @Expose
    private String gmNo;
    @SerializedName("tm_no")
    @Expose
    private String tmNo;
    @SerializedName("gm_date")
    @Expose
    private String gmDate;
    @SerializedName("gm_addr")
    @Expose
    private String gmAddr;
    @SerializedName("gm_gym")
    @Expose
    private String gmGym;
    @SerializedName("gm_state")
    @Expose
    private String gmState;
    @SerializedName("gm_match_mbno")
    @Expose
    private Object gmMatchMbno;
    @SerializedName("gm_game_time")
    @Expose
    private String gmGameTime;
    @SerializedName("gm_write_date")
    @Expose
    private String gmWriteDate;
    @SerializedName("gm_title")
    @Expose
    private String gmTitle;
    @SerializedName("gm_img")
    @Expose
    private String gmImg;
    @SerializedName("gm_color")
    @Expose
    private String gmColor;
    @SerializedName("gm_memo")
    @Expose
    private String gmMemo;
    @SerializedName("mb_nick")
    @Expose
    private String mbNick;
    @SerializedName("tm_name")
    @Expose
    private String tmName;
    @SerializedName("tm_info")
    @Expose
    private String tmInfo;
    @SerializedName("tm_match_count")
    @Expose
    private String tmMatchCount;
    @SerializedName("tm_sport")
    @Expose
    private String tmSport;
    @SerializedName("tm_img")
    @Expose
    private String tmImg;
    @SerializedName("btn_state")
    @Expose
    private BtnState btnState;

    public String getGmNo() {
        return gmNo;
    }

    public void setGmNo(String gmNo) {
        this.gmNo = gmNo;
    }

    public String getTmNo() {
        return tmNo;
    }

    public void setTmNo(String tmNo) {
        this.tmNo = tmNo;
    }

    public String getGmDate() {

        return GameItemViewUtils.getCustomDate(GameItemViewUtils.DatePattern,this.gmDate,GameItemViewUtils.SimpleDatePattern);
    }

    public void setGmDate(String gmDate) {
        this.gmDate = gmDate;
    }

    public String getGmAddr() {
        return gmAddr;
    }

    public void setGmAddr(String gmAddr) {
        this.gmAddr = gmAddr;
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

    public Object getGmMatchMbno() {
        return gmMatchMbno;
    }

    public void setGmMatchMbno(Object gmMatchMbno) {
        this.gmMatchMbno = gmMatchMbno;
    }

    public String getGmGameTime() {
        return gmGameTime;
    }

    public void setGmGameTime(String gmGameTime) {
        this.gmGameTime = gmGameTime;
    }

    public String getGmWriteDate() {
        return gmWriteDate;
    }

    public void setGmWriteDate(String gmWriteDate) {
        this.gmWriteDate = gmWriteDate;
    }

    public String getGmTitle() {
        return gmTitle;
    }

    public void setGmTitle(String gmTitle) {
        this.gmTitle = gmTitle;
    }

    public String getGmImg() {
        return gmImg;
    }

    public void setGmImg(String gmImg) {
        this.gmImg = gmImg;
    }

    public String getGmColor() {
        return gmColor;
    }

    public void setGmColor(String gmColor) {
        this.gmColor = gmColor;
    }

    public String getGmMemo() {
        return gmMemo;
    }

    public void setGmMemo(String gmMemo) {
        this.gmMemo = gmMemo;
    }

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

    public String getTmInfo() {
        return tmInfo;
    }

    public void setTmInfo(String tmInfo) {
        this.tmInfo = tmInfo;
    }

    public String getTmMatchCount() {
        return tmMatchCount;
    }

    public void setTmMatchCount(String tmMatchCount) {
        this.tmMatchCount = tmMatchCount;
    }

    public String getTmSport() {
        return tmSport;
    }

    public void setTmSport(String tmSport) {
        this.tmSport = tmSport;
    }

    public String getTmImg() {
        return tmImg;
    }

    public void setTmImg(String tmImg) {
        this.tmImg = tmImg;
    }

    public BtnState getBtnState() {
        return btnState;
    }

    public void setBtnState(BtnState btnState) {
        this.btnState = btnState;
    }

}