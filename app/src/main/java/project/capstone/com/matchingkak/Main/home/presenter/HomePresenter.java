package project.capstone.com.matchingkak.Main.home.presenter;

import java.util.HashMap;
import java.util.List;

import project.capstone.com.matchingkak.Main.home.HomeAdapterContract;
import project.capstone.com.matchingkak.Main.home.HomeContract;
import project.capstone.com.matchingkak.Main.home.data.HomeListService;
import project.capstone.com.matchingkak.Main.home.data.ListData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by amco1 on 2018-03-21.
 */

public class HomePresenter implements HomeContract.Presenter {

    private HashMap<Integer,HomeAdapterContract.Model> adapterModelMap;
    private HashMap<Integer,HomeAdapterContract.View> adapterViewMap;
    private HomeContract.View MainView;
    static int page=1;
    public HomePresenter(){
        adapterModelMap=new HashMap<Integer,HomeAdapterContract.Model>();
        adapterViewMap=new HashMap<Integer,HomeAdapterContract.View>();
    }
    @Override
    public void attachView(HomeContract.View view) {
            MainView=view;
    }

    @Override
    public void detachView(HomeContract.View view) {
            MainView=null;
    }

    @Override
    public void getData(int id) {
        final  HomeAdapterContract.Model model=this.adapterModelMap.get(id);
        final HomeAdapterContract.View view=this.adapterViewMap.get(id);
        Call<ListData> call=null;
        switch (id) {
            case RECOMMEND_GAMELIST:
                call= HomeListService.getRetrofit().getRecommendation();
                break;
            case MAIN_GAMELIST:
                call=HomeListService.getRetrofit().paging(1);
                break;


        }
       if(call!=null)
       call.enqueue(new Callback<ListData>() {
            @Override
            public void onResponse(Call<ListData> call, Response<ListData> response) {
                model.set(response.body().getData());
                view.setUpdate();   //해당 어댑터 업데이트
                MainView.finishUpdatePage();

            }

            @Override
            public void onFailure(Call<ListData> call, Throwable t) {

            }
        });
    }

    @Override
    public void showFirstPage() {
            getPage(page=1);
    }

    @Override
    public void showNextPage() {getPage(++page); }

    private void getPage(final int page){
        final  HomeAdapterContract.Model model=this.adapterModelMap.get(MAIN_GAMELIST);
        final HomeAdapterContract.View view=this.adapterViewMap.get(MAIN_GAMELIST);
        HomeListService.getRetrofit().paging(page).enqueue(new Callback<ListData>() {
            @Override
            public void onResponse(Call<ListData> call, Response<ListData> response) {

                if(page==1)model.set(response.body().getData());
                else if(page<=2)model.add(response.body().getData());
                view.setUpdate();
                MainView.finishUpdatePage();

            }

            @Override
            public void onFailure(Call<ListData> call, Throwable t) {

            }
        });
    }
    @Override
    public void showBanner() {
        final int numberOfAdater=BANNER_ADATER;
       final  HomeAdapterContract.Model model=adapterModelMap.get(numberOfAdater);
        final HomeAdapterContract.View view=adapterViewMap.get(numberOfAdater);
            HomeListService.getRetrofit().getBanner().enqueue(new Callback<List<String>>() {
                @Override
                public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                            model.set(response.body());
                            view.setUpdate();
                }

                @Override
                public void onFailure(Call<List<String>> call, Throwable t) {

                }
            });
    }

    @Override
    public void setAdapterView(HomeAdapterContract.View view,int AdapterNumber) {
                adapterViewMap.put(AdapterNumber,view);
    }

    @Override
    public void setAdapterModel(HomeAdapterContract.Model model,int AdapterNumber) {
                adapterModelMap.put(AdapterNumber,model);
    }


}
