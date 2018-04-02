package project.capstone.com.matchingkak.Main.message.data;

import project.capstone.com.matchingkak.restAPI.APIAdapter;
import project.capstone.com.matchingkak.restAPI.APIUrl;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by amco1 on 2018-03-13.
 */

public class MessageListService extends APIAdapter {

    public static MessageListAPI getRetrofit(){

       return   (MessageListAPI)retrofit(MessageListAPI.class);

    }

    public interface MessageListAPI{
        @GET(APIUrl.MSG_URL)
        Call<MSGListData> paging(@Query("page") int page);

    }


}
