package project.capstone.com.matchingkak.Main.alarm.data;

import android.content.Context;

import project.capstone.com.matchingkak.restAPI.APIAdapter;
import project.capstone.com.matchingkak.restAPI.APIUrl;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by amco1 on 2018-03-16.
 */

public class alarmListService extends APIAdapter {
    public static alarmAPI getRetrofit(Context context){

        return   (alarmAPI)retrofit(context,alarmAPI.class);

    }

    public interface alarmAPI{
        @GET(APIUrl.ALARM_URL)
        Call<AlarmListData> paging(@Query("page") int page);

    }



}
