package project.capstone.com.matchingkak;

import android.util.Log;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lee on 2018-04-11.
 */

public class GameItemViewUtils {
    public final static String DatePattern="yyyy-MM-dd HH:mm:ss";
    public final static String SimpleDatePattern="yyyy.MM.dd.";
    public static String getCustomDate(String datePattern,String source,String customPattern){
        String result=source;
        SimpleDateFormat format=new SimpleDateFormat(datePattern);

        try{
            Date date=format.parse(source);
            format.applyPattern(customPattern);
            result =format.format(date);

        }catch (ParseException e){

        }
        return result;
    }
    public static String getD_day(String pattern ,String source){
        String DDay="기한만료";
       // Log.d("DateUtil",source);
        SimpleDateFormat format=new SimpleDateFormat(pattern);
        try{

            Date event_day=format.parse(source);
            Date today      =new Date();

            long diff = event_day.getTime() - today.getTime();
            long diffDays =diff / (24 * 60 * 60 * 1000);


            if(diffDays>=0){
                diffDays++;
              DDay="D-"+diffDays;
            }

        }catch (ParseException e){


        }
        return DDay;
    }

    public static String getElapsed_time(String pattern,String source){
        SimpleDateFormat format=new SimpleDateFormat(pattern);
        try{
            Date event_time=format.parse(source);
            Date today=new Date();
            long diff=(today.getTime()-event_time.getTime())/1000;
            if(diff<60)return "방금 전";
            if((diff/=60)<60) return diff+"분 전";
            if((diff/=60)<24) return diff+"시간 전";
            if((diff/=24)<30) return diff+"일 전";
            format.applyPattern("yyyy.MM.dd.");
            return format.format(format.parse(source)) ;

        }catch (ParseException e){

        }
        return "오래 전";
    }
    public static void set_state(TextView v, String state){

        if(state.equals("0"))//성사대기
        {   v.setBackgroundColor(v.getContext().getColor(R.color.pretty_red));

            v.setText("요청가능");

        }else{
            v.setBackgroundColor(v.getContext().getColor(R.color.pretty_blue));

            v.setText("성사완료");
        }
    }

public static void debug(String TAG,String message) {
        if (config.DEBUG) {
            Log.d(TAG, message);
        }
    }

}
