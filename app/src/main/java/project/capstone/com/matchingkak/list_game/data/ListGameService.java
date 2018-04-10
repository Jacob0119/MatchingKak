package project.capstone.com.matchingkak.list_game.data;

import java.util.List;
import java.util.Map;

import project.capstone.com.matchingkak.list_game.adapter.CardItem;
import project.capstone.com.matchingkak.restAPI.APIAdapter;
import project.capstone.com.matchingkak.restAPI.APIUrl;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public class ListGameService extends APIAdapter {

    public static ListGameAPI getRetrofit(){

        return (ListGameAPI)retrofit(ListGameAPI.class);

    }
    public interface ListGameAPI {





        @GET(APIUrl.LISTGAME_GETLIST)
        Call<List<CardItem>> getListOfGames(@QueryMap Map<String,String> options);




    }
}
