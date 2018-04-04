package project.capstone.com.matchingkak.Main.me;

import project.capstone.com.matchingkak.BaseAdapterContract;
import project.capstone.com.matchingkak.BaseContract;

public interface meContract {

    interface Presenter extends BaseContract.Presenter{
        int ME_REQUEST=1111;
        int ME_MYPOST=1112;

        void loadData();
        void setAdapterView(BaseAdapterContract.view view,int adapterNumber);
        void setAdapterModel(BaseAdapterContract.model model,int adapterNumber);


    }
    interface  View  extends  BaseContract.View{

    }

}
