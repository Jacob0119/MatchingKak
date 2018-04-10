package project.capstone.com.matchingkak.list_game.adapter;


import project.capstone.com.matchingkak.restAPI.APIUrl;

public class CardItem {

    private String state;
    private String title;
    private String location;
    private String date;
    private String weather;
    private String picture= APIUrl.API_BASE_URL+"images/team-log2.png";

    public CardItem(int title, int text) {
        //mTitleResource = title;
       // mTextResource = text;
    }
    public CardItem(String title,String date){
            this.title=title;
            this.date=date;
    }
    public String getDate() {
        return date;
    }

    public String getPicture(){
        return this.picture;
    }

    public String getTitle() {
        return title;
    }

    public String getState() {return state;}

    public String getLocation(){return location;}

    public String getWeather(){return weather;}
}
