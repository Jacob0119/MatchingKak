package project.capstone.com.matchingkak.detail_info;

import android.content.Context;

/**
 * Created by Lee on 2017-10-28.
 */

public class StateManager {

    static private StateManager instance;
    static public final int STATE_NOT_LOGIN=0;
    static public final int STATE_MINE=1;
    static public final int STATE_NOT_MINE=2;

    static State state;
     Context context;
    static public StateManager getInstance(Context context){

        if(instance==null){

            instance=new StateManager(context);
        }
        return instance;

    }

    StateManager(Context context){
    this.context=context;
}

    public  State getState(int stateCode,Info info){
        switch (stateCode){

            case STATE_MINE:
                state=MineState.getInstance(context);
                 break;
            case STATE_NOT_LOGIN:
                state=NotLoginState.getInstance(context);
                break;
            case STATE_NOT_MINE:
                state=NormalState.getInstance(context,info);
                break;
        }

        return state;

    }



}
