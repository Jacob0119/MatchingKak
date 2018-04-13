package project.capstone.com.matchingkak.Main.home;

import android.os.HandlerThread;
import android.support.v4.view.ViewPager;

import java.util.TimerTask;

/**
 * Created by Lee on 2018-04-12.
 */

public class MyTimerTask extends TimerTask {


        private HandlerThread handlerThread;
        private ViewPager pager;

        MyTimerTask(ViewPager viewPager){
            this.pager=viewPager;


        }
        @Override
        public void run() {



        }


}
