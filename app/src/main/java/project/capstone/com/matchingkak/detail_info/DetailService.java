package project.capstone.com.matchingkak.detail_info;

import project.capstone.com.matchingkak.restAPI.APIAdapter;
import project.capstone.com.matchingkak.restAPI.APIUrl;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Lee on 2017-10-27.
 */

public class DetailService extends APIAdapter {


    public static DetailAPI getRetrofit(){

        return (DetailAPI) retrofit(DetailAPI.class);

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
        @FormUrlEncoded
        @POST(APIUrl.REQUEST_MATCH_URL)
        Call<RequestResult> requestMatch(
                            @Field("gm_no") String gm_no

        );


    }
}
