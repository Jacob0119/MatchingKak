package project.capstone.com.matchingkak;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.Timer;
import java.util.TimerTask;

import project.capstone.com.matchingkak.Main.home.adapter.pagerAdapter;

/**
 * Created by Lee on 2018-04-13.
 */

public class InfiniteViewPager extends ViewPager {
    private Timer timer;
    private MyTimerTask timerTask;
    public InfiniteViewPager(Context context) {

        super(context);

    }

    public InfiniteViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        timer=new Timer();
        timerTask=new MyTimerTask(this);
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
        // offset first element so that we can scroll to the left



    }

    @Override
    public void setCurrentItem(int item) {
        // offset the current item to ensure there is space to scroll
        setCurrentItem(item, false);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        if (getAdapter().getCount() == 0) {
            super.setCurrentItem(item, smoothScroll);
            return;
        }

        super.setCurrentItem(item, smoothScroll);
    }



    public void stopAutoScroll(){
        if(timer!=null) {
            timer.purge();
            timer.cancel();
        }
    }
    public void startAutoScroll(){

        timer=new Timer();
        timerTask=new MyTimerTask(this);
        timer.scheduleAtFixedRate(timerTask,4000,4000);
    }



    private int getOffsetAmount() {
        if (getAdapter().getCount() == 0) {
            return 0;
        }
        if (getAdapter() instanceof pagerAdapter) {
            pagerAdapter infAdapter = (pagerAdapter) getAdapter();
            // allow for 100 back cycles from the beginning
            // should be enough to create an illusion of infinity
            // warning: scrolling to very high values (1,000,000+) results in
            // strange drawing behaviour
            return infAdapter.getRealCount() * 100;
        } else {
            return 0;
        }
    }

    void onScroll(){
        int item=this.getCurrentItem();
        GameItemViewUtils.debug("InfiViewPager",getCurrentItem()+"");
        this.setCurrentItem(item+1,true);
    }

    class MyTimerTask extends TimerTask {

        private InfiniteViewPager pager;
        private Handler handler;
        MyTimerTask(InfiniteViewPager viewPager){
            this.pager=viewPager;

            handler=new Handler();
        }
        @Override
        public void run() {


            handler.post(new Runnable() {
                @Override
                public void run() {
                    pager.onScroll();
                }
            });


        }


    }
}
