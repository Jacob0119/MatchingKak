package project.capstone.com.matchingkak.detail_info;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import project.capstone.com.matchingkak.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lee on 2017-10-28.
 */

public interface State {

    abstract void setSubmitButton(Button button);
    abstract void setMessageButton(Button button);


}


class MineState implements State{

    private static  MineState instance;
     Context context;
   public static MineState getInstance(Context context){

        if(instance==null){
            instance=new MineState(context);

        }
        return instance;

    }
    MineState(Context context){
        this.context=context;
    }

    @TargetApi(23)
    @Override
    public void setSubmitButton(Button button) {
        if(button!=null&&this.context!=null) {
            button.setText("요청자 목록");
            button.setBackgroundColor(context.getColor(R.color.pretty_red));
        }
    }

    @Override
    public void setMessageButton(Button button) {

    }
}



class NotLoginState implements State{



    private static  NotLoginState instance;

    Context context;
    static NotLoginState getInstance(Context context){
       if(instance==null){
           instance=new NotLoginState(context);

       }
        return instance;

    }
    public NotLoginState(Context context){
        this.context=context;
    }

    @TargetApi(23)
    @Override
    public void setSubmitButton(Button button) {
        if(button!=null&&this.context!=null) {
            button.setText("로그인이 필요합니다.");
            button.setBackgroundColor(context.getColor(R.color.colorPrimary_light));
        }


    }

    @Override
    public void setMessageButton(Button button) {

    }
}



class NormalState implements State{
    private static  NormalState instance;

    Context context;
    Info info;
    static NormalState getInstance(Context context,Info info){
        if(instance==null){
            instance=new NormalState(context,info);

        }
        return instance;

    }
    public NormalState(Context context){
        this.context=context;
    }
    public NormalState(Context context,Info info){
        this(context);
        this.info=info;
    }

    @TargetApi(23)
    @Override
    public void setSubmitButton(Button button) {
        if(button!=null&&this.context!=null) {
            button.setText("요청하기");
            button.setBackgroundColor(context.getColor(R.color.colorPrimary_light));
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                   DetailService.getRetrofit(context).requestMatch(info.getGm_no()).enqueue(new Callback<RequestResult>() {
                      @Override
                      public void onResponse(Call<RequestResult> call, Response<RequestResult> response) {

                          Toast.makeText(context,response.body().msg,Toast.LENGTH_SHORT).show();
                      }

                      @Override
                      public void onFailure(Call<RequestResult> call, Throwable t) {

                      }
                  });



                }
            });
        }


    }

    @Override
    public void setMessageButton(Button button) {

    }
}

