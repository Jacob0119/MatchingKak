package project.capstone.com.matchingkak.Main.home.data;

import java.util.List;

import project.capstone.com.matchingkak.restAPI.APIAdapter;
import project.capstone.com.matchingkak.restAPI.APIUrl;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Lee on 2017-10-27.
 */

public class HomeListService extends APIAdapter {


    public static HomeListAPI getRetrofit(){

        return (HomeListAPI) retrofit(HomeListAPI.class);

    }
    public interface HomeListAPI {


        @POST(APIUrl.MAIN_RECOMMENDATION)
        Call<ListData> getRecommendation();


        /**
         * 게임 목록 가져오기 메소드
         *
         * @param page

         * @return
         */
        @GET(APIUrl.MAIN_URL)
        Call<ListData> paging(@Query("page") int page);

        @POST(APIUrl.LOG_OUT_URL)
        Call<Boolean> logout();


        @POST(APIUrl.GET_BANNER)
        Call<List<String>> getBanner();


        @POST(APIUrl.HOME_MENU_URL)
        Call<List<MenuData>> getMenu();

    }




}
