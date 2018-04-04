package project.capstone.com.matchingkak.Main.me.data;

import java.util.List;

import project.capstone.com.matchingkak.restAPI.APIAdapter;
import project.capstone.com.matchingkak.restAPI.APIUrl;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Lee on 2018-04-03.
 */

public class meListService extends APIAdapter {

    public static meListAPI getRetrofit(){

        return (meListAPI) retrofit(meListAPI.class);

    }
    public interface meListAPI {




        @POST(APIUrl.MYPAGE_REQUEST)
        Call<List<gameData>> getRequestList();



        @POST(APIUrl.MYPAGE_POST)
        Call<List<gameData>> getMyPost();

    }

}
