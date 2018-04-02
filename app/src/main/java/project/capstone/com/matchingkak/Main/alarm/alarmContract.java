package project.capstone.com.matchingkak.Main.alarm;

import android.content.Context;

import project.capstone.com.matchingkak.BaseContract;

/**
 * Created by amco1 on 2018-03-16.
 */

public interface alarmContract extends BaseContract {

    interface View extends BaseContract.View{
        int MAKE_MESSAGE=1;
        int NORMAL=2;
    }

    interface Presenter extends BaseContract.Presenter{

        void loadItems(Context context,int page,boolean isUpper);


    }


}
