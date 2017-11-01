package project.capstone.com.matchingkak.detail_info;

import android.content.Context;
import android.widget.Button;

/**
 * Created by Lee on 2017-10-28.
 */

public class StateManager {

    static private StateManager instance;
   public static final int STATE_ALERT=0;
    public static final int STATE_MINE=1;
    public static final int STATE_NORMAL=2;
    public static final int STATE_COMPLETE=3;
    static State state;
     Context context;
    static public StateManager getInstance(){

        if(instance==null){

            instance=new StateManager();
        }
        return instance;

    }

        public void setBtn(Button b){
            state.setSubmitButton(b);
        }



    public  void setState(int stateCode,Info info,Context context){
        switch (stateCode){

            case STATE_MINE:
                state=new MineState(context );
                 break;
            case STATE_ALERT:
                state= new AlertState(context,info);
                break;
            case STATE_NORMAL:
                state=new NormalState(context,info);
                break;
            case STATE_COMPLETE:
                state=new AlertState(context,info);
                break;

        }



    }



}
