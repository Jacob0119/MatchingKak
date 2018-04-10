package project.capstone.com.matchingkak.list_game.presenter;

import android.util.Log;

import java.util.List;
import java.util.Map;

import project.capstone.com.matchingkak.BaseAdapterContract;
import project.capstone.com.matchingkak.list_game.ListGameAdapterContract;
import project.capstone.com.matchingkak.list_game.ListGameContract;
import project.capstone.com.matchingkak.list_game.adapter.CardItem;
import project.capstone.com.matchingkak.list_game.data.ListGameService;
import project.capstone.com.matchingkak.restAPI.APIAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListGamePresenter implements ListGameContract.Presenter {

    ListGameContract.View view;
    ListGameAdapterContract.model adapterModel;
    ListGameAdapterContract.view adpaterView;
    private int page=1;

    @Override
    public void attatchView(ListGameContract.View view) {
        this.view=view;
    }

    @Override
    public void detachView() {
        this.view=null;
    }

    @Override
    public void setAdapterModel(BaseAdapterContract.model model) {
            this.adapterModel=model;
    }

    @Override
    public void setAdapterView(BaseAdapterContract.view view) {
            this.adpaterView=view;
    }

    @Override
    public void setData(APIAdapter adapter) {

    }


    @Override
    public void loadData(final Map<String, String> options) {


        ListGameService.getRetrofit().getListOfGames(options).enqueue(new Callback<List<CardItem>>() {
            @Override
            public void onResponse(Call<List<CardItem>> call, Response<List<CardItem>> response) {


                adapterModel.addItems(response.body(), page==1);
                adpaterView.notifyAdapter();
            }

            @Override
            public void onFailure(Call<List<CardItem>> call, Throwable t) {
                Log.d("ListGamePresenter","failed");
            }
        });
    }

    @Override
    public void loadNext(Map<String, String> options) {
        page++;
        options.put("page",String.valueOf(page));
        loadData(options);

    }
}
