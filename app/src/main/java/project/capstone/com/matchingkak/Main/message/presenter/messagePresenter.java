package project.capstone.com.matchingkak.Main.message.presenter;

import android.content.Context;

import java.util.List;

import project.capstone.com.matchingkak.BaseAdapterContract;
import project.capstone.com.matchingkak.BaseContract;
import project.capstone.com.matchingkak.Main.message.data.MSGListData;
import project.capstone.com.matchingkak.Main.message.data.MessageListService;
import project.capstone.com.matchingkak.restAPI.APIAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amco1 on 2018-03-28.
 */

public class messagePresenter implements messageContract.Presenter {


    private messageContract.View view;
    private messageAdapterContract.model adapterModel;
    private messageAdapterContract.view adapterView;
    private MessageListService apiAdapter;
    @Override
    public void attatchView(BaseContract.View view) {
        this.view=(messageContract.View)view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void setAdapterModel(BaseAdapterContract.model model) {
                adapterModel=model;
    }

    @Override
    public void setAdapterView(BaseAdapterContract.view view) {
                this.adapterView=view;
    }

    @Override
    public void setData(APIAdapter adapter) {

    }


    @Override
    public void loadData(Context context, final int page) {
        MessageListService.getRetrofit().paging(page)
                .enqueue(new Callback<MSGListData>() {
                    @Override
                    public void onResponse(Call<MSGListData> call, Response<MSGListData> response) {
                        if(response.isSuccessful()) {
                            if(page==1){
                             adapterModel.addItems(response.body().getData(),true);
                                adapterView.notifyAdapter();}
                            else {
                                //addList로 데이터 이어서 추가

                                    adapterModel.addItems(response.body().getData(),false);
                                            adapterView.notifyAdapter();

                            }



                        }
                        view.done(view.SUCCESS,null);
                    }

                    @Override
                    public void onFailure(Call<MSGListData> call, Throwable t) {



                    }
                });

    }

    @Override
    public void loadData(Context context,final int page, String type) {


        MessageListService.getRetrofit().paging(page,type).enqueue(new Callback<MSGListData>() {
            @Override
            public void onResponse(Call<MSGListData> call, Response<MSGListData> response) {
                List data=response.body().getData();
                if(page==1){
                    adapterModel.addItems(data,true);
                    adapterView.notifyAdapter();
                }else{
                    adapterModel.addItems(data,false);
                    adapterView.notifyAdapter();
                }
                view.done(view.SUCCESS,null);
            }

            @Override
            public void onFailure(Call<MSGListData> call, Throwable t) {

            }
        });
    }


}
