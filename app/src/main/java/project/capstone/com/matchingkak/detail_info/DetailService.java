package project.capstone.com.matchingkak.detail_info;

import android.content.Context;

import project.capstone.com.matchingkak.restAPI.APIAdapter;
import project.capstone.com.matchingkak.restAPI.APIUrl;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Lee on 2017-10-27.
 */

public class DetailService extends APIAdapter {


    public static DetailAPI getRetrofit(Context context){

        return (DetailAPI) retrofit(context,DetailAPI.class);

    }
    public interface DetailAPI {
        /**
         * 회원가입 메소드
         *
         * @param gm_no

         * @return
         */

        @GET(APIUrl.DETAIL_URL)
        Call<Info> requestInfo(
                @Query("gm_no") String gm_no

        );


    }
}
