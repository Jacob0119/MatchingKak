package project.capstone.com.matchingkak.list_game;

import java.util.Map;

import project.capstone.com.matchingkak.BaseContract;

public interface ListGameContract extends BaseContract {

    interface Presenter extends BaseContract.Presenter{
        void loadData(Map<String ,String> options);
        void loadNext(Map<String,String> options);
    }
}
