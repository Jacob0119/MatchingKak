package project.capstone.com.matchingkak;

import project.capstone.com.matchingkak.restAPI.APIAdapter;

/**
 * Created by amco1 on 2018-03-28.
 */

public interface BaseContract {
    interface View{

        void done(int resultCode,String[] input);

    }

    interface Presenter {

        void attatchView(BaseContract.View view);
        void detachView();

        void setAdapterModel(BaseAdapterContract.model model);
        void setAdapterView(BaseAdapterContract.view view);
        void setData(APIAdapter adapter);}


}
