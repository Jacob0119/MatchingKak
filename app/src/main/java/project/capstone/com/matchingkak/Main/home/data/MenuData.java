package project.capstone.com.matchingkak.Main.home.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by amco1 on 2018-03-25.
 */

public class MenuData {
    @SerializedName("sport_img")
    @Expose
    private String sport_img;

    @SerializedName("sport_name")
    @Expose
    private String sport_name;
    @SerializedName("sport_index")
    @Expose
    private int sport_index;

    public String getSport_img(){
        return sport_img;
    }
    public String getSport_Name(){
        return sport_name;
    }

    public int getSport_index(){
        return sport_index;
    }
}
