package project.capstone.com.matchingkak.Main.me.presenter;

import android.util.Log;

import java.util.HashMap;
import java.util.List;

import project.capstone.com.matchingkak.BaseAdapterContract;
import project.capstone.com.matchingkak.BaseContract;
import project.capstone.com.matchingkak.Main.me.data.InfoData;
import project.capstone.com.matchingkak.Main.me.data.gameData;
import project.capstone.com.matchingkak.Main.me.data.meListService;
import project.capstone.com.matchingkak.Main.me.meAdapterContract;
import project.capstone.com.matchingkak.Main.me.meContract;
import project.capstone.com.matchingkak.restAPI.APIAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mePresenter implements meContract.Presenter {
    private meContract.View view;
    HashMap<Integer,meAdapterContract.model> modelMap;
    HashMap<Integer,meAdapterContract.view> viewMap;

    public mePresenter(){

        modelMap=new HashMap<Integer, meAdapterContract.model>();
        viewMap=new HashMap<Integer, meAdapterContract.view>();

    }

    @Override
    public void attatchView(BaseContract.View view) {
            this.view=(meContract.View)view;

    }

    @Override
    public void detachView() {
            view=null;
    }

    @Override
    public void setAdapterModel(BaseAdapterContract.model model) {

    }

    @Override
    public void setAdapterView(BaseAdapterContract.view view) {

    }

    @Override
    public void setData(APIAdapter adapter) {

    }

    @Override
    public void loadData() {

        meListService.getRetrofit().getMyPost().enqueue(new Callback<List<gameData>>() {
            @Override
            public void onResponse(Call<List<gameData>> call, Response<List<gameData>> response) {
                modelMap.get(mePresenter.ME_MYPOST).addItems(response.body(),true);
                viewMap.get(mePresenter.ME_MYPOST).notifyAdapter();
                Log.d("mePresenter","success post ");
            }

            @Override
            public void onFailure(Call<List<gameData>> call, Throwable t) {
                Log.d("mePresenter","failed post ");

            }
        });
        meListService.getRetrofit().getRequestList().enqueue(new Callback<List<gameData>>() {
            @Override
            public void onResponse(Call<List<gameData>> call, Response<List<gameData>> response) {
                Log.d("mePresenter",response.body().toString());
                modelMap.get(mePresenter.ME_REQUEST).addItems(response.body(),true);
                viewMap.get(mePresenter.ME_REQUEST).notifyAdapter();
                Log.d("mePresenter","success request ");
            }

            @Override
            public void onFailure(Call<List<gameData>> call, Throwable t) {
                Log.d("mePresenter","failed request ");
            }
        });
        meListService.getRetrofit().getMyInfo().enqueue(new Callback<InfoData>() {
            @Override
            public void onResponse(Call<InfoData> call, Response<InfoData> response) {

                    view.updateInfo(response.body());
            }

            @Override
            public void onFailure(Call<InfoData> call, Throwable t) {

            }
        });
    }

    @Override
    public void setAdapterView(BaseAdapterContract.view view, int adapterNumber) {
        viewMap.put(adapterNumber,view);
    }

    @Override
    public void setAdapterModel(BaseAdapterContract.model model, int adapterNumber) {
        modelMap.put(adapterNumber,model);
    }
}
