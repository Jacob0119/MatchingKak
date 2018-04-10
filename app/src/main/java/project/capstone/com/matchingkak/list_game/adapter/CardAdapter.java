package project.capstone.com.matchingkak.list_game.adapter;


import android.support.v7.widget.CardView;

public interface CardAdapter {

    int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();
    void addCardItem(CardItem item);
    void notifyItemsetChanged();
    CardView getCardViewAt(int position);

    int getCount();
}
