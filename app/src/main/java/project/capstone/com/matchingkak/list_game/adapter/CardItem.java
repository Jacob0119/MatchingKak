package project.capstone.com.matchingkak.list_game.adapter;


public class CardItem {

    private String mTextResource;
    private String mTitleResource;

    public CardItem(int title, int text) {
        //mTitleResource = title;
       // mTextResource = text;
    }
    public CardItem(String title,String text){
            mTextResource=title;
            mTitleResource=text;
    }
    public String getText() {
        return mTextResource;
    }

    public String getTitle() {
        return mTitleResource;
    }
}
