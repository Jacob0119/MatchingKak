package project.capstone.com.matchingkak.list_game;

import android.support.v7.widget.CardView;
import android.view.View;

import project.capstone.com.matchingkak.BaseAdapterContract;
import project.capstone.com.matchingkak.list_game.adapter.CardItem;

public interface ListGameAdapterContract extends BaseAdapterContract {

    interface view extends BaseAdapterContract.view{
        int MAX_ELEVATION_FACTOR = 8;

        float getBaseElevation();
        void addCardItem(CardItem item);
        void notifyItemsetChanged();
        CardView getCardViewAt(int position);
        void setChangeRootBackground(View v, int position);
        int getCount();
    }

}
