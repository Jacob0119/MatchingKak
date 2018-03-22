package project.capstone.com.matchingkak.Main.home.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
