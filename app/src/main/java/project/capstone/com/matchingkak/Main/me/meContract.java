package project.capstone.com.matchingkak.Main.me;

import project.capstone.com.matchingkak.BaseContract;

public interface meContract {

    interface Presenter extends BaseContract.Presenter{
        int ME_REQUEST=1111;
        int ME_MYPOST=1112;

    }
    interface  View  extends  BaseContract.View{

    }

}
