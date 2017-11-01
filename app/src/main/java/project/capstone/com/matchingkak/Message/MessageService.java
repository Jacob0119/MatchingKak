package project.capstone.com.matchingkak.Message;

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

public class MessageService extends APIAdapter {


    public static MessageAPI getRetrofit(Context context){

        return (MessageAPI) retrofit(context,MessageAPI.class);

    }
    public interface MessageAPI {


        /**
         * 로그인 메소드
         *
         * @param ms_receiver
         * @param title
         * @param  content
         * @return
         */
        @FormUrlEncoded
        @POST(APIUrl.SEND_MESSAGE)
        Call<MsgResponse> send(
                @Field("ms_receiver") String ms_receiver,
                @Field("ms_title") String title
                ,@Field("ms_content") String content
        );





    }
    public class MsgResponse {
        String msg;
    }

}
