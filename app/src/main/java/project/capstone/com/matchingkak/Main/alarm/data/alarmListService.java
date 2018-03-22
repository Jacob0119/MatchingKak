package project.capstone.com.matchingkak.Main.alarm.data;

import project.capstone.com.matchingkak.restAPI.APIAdapter;
import project.capstone.com.matchingkak.restAPI.APIUrl;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by amco1 on 2018-03-16.
 */

public class alarmListService extends APIAdapter {
    public static alarmAPI getRetrofit(){

        return   (alarmAPI)retrofit(alarmAPI.class);

    }

    public interface alarmAPI{
        @GET(APIUrl.ALARM_URL)
        Call<AlarmListData> paging(@Query("page") int page);

        @FormUrlEncoded
        @POST(APIUrl.MATCHING_TRANSACTION_URL)
        Call<Void> matching(@Field("rq_no") String rq_no, @Field("rq_type") String rq_type
                        ,@Field("rq_count_no") String rq_count_no,@Field("gm_matching_mb_no") String gm_match_mb_no);

        @FormUrlEncoded
        @POST(APIUrl.MATCHING_REJECT_TRANSACTION_URL)
        Call<Void> matching_reject(@Field("rq_no") String rq_no);
    }



}
