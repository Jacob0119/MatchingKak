package project.capstone.com.matchingkak.Main.alarm.presenter;


import android.content.Context;
import android.view.View;

import project.capstone.com.matchingkak.BaseContract;
import project.capstone.com.matchingkak.Main.OnClickListener;
import project.capstone.com.matchingkak.Main.alarm.alarmAdapterContract;
import project.capstone.com.matchingkak.Main.alarm.alarmContract;
import project.capstone.com.matchingkak.Main.alarm.data.AlarmListData;
import project.capstone.com.matchingkak.Main.alarm.data.alarmItem;
import project.capstone.com.matchingkak.Main.alarm.data.alarmListService;
import project.capstone.com.matchingkak.restAPI.APIAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amco1 on 2018-03-16.
 */

public class alarmPresenter implements alarmContract.Presenter,OnClickListener {
    private alarmContract.View view;
    private alarmListService service;
    alarmAdapterContract.model adapterModel;
    alarmAdapterContract.view adapterView;


    @Override
    public void attatchView(BaseContract.View view) {
        this.view=(alarmContract.View)view;
    }

    @Override
    public void detachView() {
        this.view=null;
    }

    @Override
    public void setAdapterModel(alarmAdapterContract.model model) {
        adapterModel=model;

    }

    @Override
    public void setAdapterView(alarmAdapterContract.view view) {
        adapterView=view;

    }


    @Override
    public void setData(APIAdapter dataAPI) {
        ///
        this.service=(alarmListService)dataAPI;

    }

    @Override
    public void loadItems(Context context,int page,final boolean isUpper) {

        service.getRetrofit().paging(page).enqueue(new Callback<AlarmListData>() {
            @Override
            public void onResponse(Call<AlarmListData> call, Response<AlarmListData> response) {

                if(response.body().getData()!=null)
                   adapterModel.addItems(response.body().getData(),isUpper);
                    view.done(alarmContract.View.NORMAL,null);

            }

            @Override
            public void onFailure(Call<AlarmListData> call, Throwable t) {

            }
        });

    }



    @Override
    public void OnClick(final View v, int position) {
        alarmItem temp=(alarmItem)adapterModel.getItem(position);
        switch (v.getId()){
/*
            case R.id.alarm_submit:
                Toast.makeText(v.getContext(),temp.getRq_type(),Toast.LENGTH_SHORT).show();
                service.getRetrofit().matching(temp.getRq_no()
                        ,temp.getRq_type()
                        ,temp.getRq_count_no()
                        ,temp.getMb_no()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                            loadItems(v.getContext(), 1, true);
                         view.done(alarmContract.View.NORMAL,null);


                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("presenter","fail");
                    }
                });
                break;
            case R.id.alarm_reject:
               service.getRetrofit().matching_reject(temp.getRq_no()).enqueue(
                       new Callback<Void>() {
                           @Override
                           public void onResponse(Call<Void> call, Response<Void> response) {

                               loadItems(v.getContext(), 1, true);
                               view.done(alarmContract.View.NORMAL,null);
                           }

                           @Override
                           public void onFailure(Call<Void> call, Throwable t) {

                           }
                       }
               );
                break;

            case R.id.alarm_send:
                String[] input={temp.getUser()};
                view.done(view.MAKE_MESSAGE,input);

                break;
*/
        }

    }
}
