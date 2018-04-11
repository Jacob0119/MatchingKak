package project.capstone.com.matchingkak.list_game;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;

import project.capstone.com.matchingkak.databinding.ActivityListGameBinding;
import project.capstone.com.matchingkak.list_game.adapter.CardAdapter;


public class ShadowTransformer implements ViewPager.OnPageChangeListener, ViewPager.PageTransformer {

    private ViewPager mViewPager;
    private ListGameAdapterContract.view mAdapter;
    private float mLastOffset;
    private boolean mScalingEnabled;
    private int prevPosition;
    private ListGameContract.Presenter presenter;
    ActivityListGameBinding binding;
    public ShadowTransformer(ActivityListGameBinding binding, ListGameAdapterContract.view adapter,ListGameContract.Presenter presenter) {
        this.binding=binding;
        mViewPager = binding.listGameViewPager;
        mViewPager.addOnPageChangeListener(this);
        mAdapter = adapter;
        mScalingEnabled=true;
        this.presenter=presenter;
        prevPosition=0;
    }

    public void enableScaling(boolean enable) {
       // if(mAdapter.getCount() !=0)

        if (mScalingEnabled && !enable) {
            // shrink main card
            CardView currentCard = mAdapter.getCardViewAt(mViewPager.getCurrentItem());
            if (currentCard != null) {
                currentCard.animate().scaleY(1);
                currentCard.animate().scaleX(1);
            }
        }else if(!mScalingEnabled && enable){
            // grow main card
            CardView currentCard = mAdapter.getCardViewAt(mViewPager.getCurrentItem());
            if (currentCard != null) {
                currentCard.animate().scaleY(1.1f);
                currentCard.animate().scaleX(1.1f);
            }
        }

        mScalingEnabled = enable;
    }

    @Override
    public void transformPage(View page, float position) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int realCurrentPosition;
        int nextPosition;
        float baseElevation = mAdapter.getBaseElevation();
        float realOffset;
        boolean goingLeft = mLastOffset > positionOffset;


        // If we're going backwards, onPageScrolled receives the last position
        // instead of the current one
        if (goingLeft) {
            realCurrentPosition = position + 1;
            nextPosition = position;
            realOffset = 1 - positionOffset;
        } else {
            nextPosition = position + 1;
            realCurrentPosition = position;
            realOffset = positionOffset;
        }

        // Avoid crash on overscroll
        if (nextPosition > mAdapter.getCount() - 1
                || realCurrentPosition > mAdapter.getCount() - 1) {
            return;
        }

        CardView currentCard = mAdapter.getCardViewAt(realCurrentPosition);

        // This might be null if a fragment is being used
        // and the views weren't created yet
        if (currentCard != null) {
            if (mScalingEnabled) {
                currentCard.setScaleX((float) (1 + 0.1 * (1 - realOffset)));
                currentCard.setScaleY((float) (1 + 0.1 * (1 - realOffset)));
            }
            currentCard.setCardElevation((baseElevation + baseElevation
                    * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (1 - realOffset)));
        }

        CardView nextCard = mAdapter.getCardViewAt(nextPosition);

        // We might be scrolling fast enough so that the next (or previous) card
        // was already destroyed or a fragment might not have been created yet
        if (nextCard != null) {
            if (mScalingEnabled) {
                nextCard.setScaleX((float) (1 + 0.1 * (realOffset)));
                nextCard.setScaleY((float) (1 + 0.1 * (realOffset)));
            }
            nextCard.setCardElevation((baseElevation + baseElevation
                    * (CardAdapter.MAX_ELEVATION_FACTOR - 1) * (realOffset)));
        }

        mLastOffset = positionOffset;
    }

    @Override
    public void onPageSelected(int position) {

       // mAdapter.setChangeRootBackground(binding.listGameBg,position);
      //  Log.d("ShadowTransformer","Position"+position);
        if(prevPosition>=position) return;
            if(position>=mAdapter.getCount()-2){

               presenter.loadNext();
                prevPosition=position;
            }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
