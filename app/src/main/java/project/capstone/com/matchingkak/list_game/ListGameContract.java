package project.capstone.com.matchingkak.list_game;

import java.util.HashMap;

import project.capstone.com.matchingkak.BaseContract;

public interface ListGameContract extends BaseContract {

    interface Presenter extends BaseContract.Presenter{
        void loadData();
        void loadNext();
        void setParameterMap(HashMap<String,String> map);

    }
}
