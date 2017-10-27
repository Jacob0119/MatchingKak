package project.capstone.com.matchingkak.sns_login;

import android.content.Context;

import project.capstone.com.matchingkak.restAPI.APIAdapter;
import project.capstone.com.matchingkak.restAPI.APIUrl;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Lee on 2017-10-27.
 */

public class SignService extends APIAdapter {

    public static SignAPI getRetrofit(Context context){

        return (SignAPI) retrofit(context,SignAPI.class);

    }
    public interface SignAPI {
        /**
         * 회원가입 메소드
         *
         * @param email
         * @param pw
         * @param name
         * @return
         */
        @FormUrlEncoded
        @POST(APIUrl.LOG_IN_URL)
        Call<ResData> up(
                @Field("mail") String email,
                @Field("pw") String pw,
                @Field("name") String name
        );

        /**
         * 로그인 메소드
         *
         * @param email
         * @param pw
         * @return
         */
        @FormUrlEncoded
        @POST(APIUrl.LOG_IN_URL)
        Call<ResData> in(
                @Field("mail") String email,
                @Field("pw") String pw
        );




    }


}
