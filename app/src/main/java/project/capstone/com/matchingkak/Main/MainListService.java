package project.capstone.com.matchingkak.Main;

import android.content.Context;

import project.capstone.com.matchingkak.restAPI.APIAdapter;
import project.capstone.com.matchingkak.restAPI.APIUrl;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Lee on 2017-10-27.
 */

public class MainListService extends APIAdapter {


    public static MainListAPI getRetrofit(Context context){

        return (MainListAPI) retrofit(context,MainListAPI.class);

    }
    public interface MainListAPI {
        /**
         * 게임 목록 가져오기 메소드
         *
         * @param page

         * @return
         */

        @GET(APIUrl.MAIN_URL)
        Call<ListData> paging(
                @Query("page") int page
        );

        @POST(APIUrl.LOG_OUT_URL)
        Call<Boolean> logout();







    }

}
