package project.capstone.com.matchingkak.Main.alarm.presenter;


import android.content.Context;
import android.view.View;

import project.capstone.com.matchingkak.Main.alarm.AdapterContract;
import project.capstone.com.matchingkak.Main.alarm.Contract;
import project.capstone.com.matchingkak.Main.alarm.OnClickListener;
import project.capstone.com.matchingkak.Main.alarm.data.AlarmListData;
import project.capstone.com.matchingkak.Main.alarm.data.alarmListService;
import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.restAPI.APIAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amco1 on 2018-03-16.
 */

public class alarmPresenter implements Contract.Presenter,OnClickListener {
    private Contract.View view;
    private alarmListService service;
    AdapterContract.model adapterModel;
    AdapterContract.view adapterView;
    @Override
    public void attatchView(Contract.View view) {
        this.view=view;
    }

    @Override
    public void detachView() {
        this.view=null;
    }

    @Override
    public void setAdapterModel(AdapterContract.model model) {
        adapterModel=model;

    }

    @Override
    public void setAdapterView(AdapterContract.view view) {
        adapterView=view;
        adapterView.setOnClickListener(this);
    }


    @Override
    public void setData(APIAdapter dataAPI) {
        ///
        this.service=(alarmListService)dataAPI;

    }

    @Override
    public void loadItems(Context context,int page,final boolean isUpper) {

        service.getRetrofit(context).paging(page).enqueue(new Callback<AlarmListData>() {
            @Override
            public void onResponse(Call<AlarmListData> call, Response<AlarmListData> response) {

                if(response.body().getData()!=null)
                   adapterModel.addItems(response.body().getData(),isUpper);
                    view.done("complete");

            }

            @Override
            public void onFailure(Call<AlarmListData> call, Throwable t) {

            }
        });

    }



    @Override
    public void OnClick(View v, int position) {

        switch (v.getId()){

            case R.id.alarm_submit:
                view.done("submit");
                break;
            case R.id.alarm_resect:
                view.done("reject");
                break;

        }

    }
}
