package project.capstone.com.matchingkak.Main.message.presenter;

import android.content.Context;

import project.capstone.com.matchingkak.BaseContract;

/**
 * Created by amco1 on 2018-03-28.
 */

public interface messageContract {



    interface Presenter extends BaseContract.Presenter {
        static int SENT=1;
        static int RECEIVE=2;

        void loadData(Context context,int page);
        void loadData(Context context,int page,String type);

    }
    interface View extends BaseContract.View{
        int SUCCESS=10000;
    }
}
