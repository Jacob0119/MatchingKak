package project.capstone.com.matchingkak.Main;

import android.content.Context;

import project.capstone.com.matchingkak.restAPI.APIAdapter;

/**
 * Created by amco1 on 2018-03-16.
 */

public interface Contract {

interface View{
    static final int MAKE_MESSAGE=0;
    static final int NORMAL=10000;
    void done(int resultCode,String[] res);

}

    interface Presenter {

        void attatchView(View view);
        void detachView();

        void setAdapterModel(AdapterContract.model model);
        void setAdapterView(AdapterContract.view view);
        void setData(APIAdapter adapter);

        void loadItems(Context context,int page,boolean isUpper);


    }


}
