package project.capstone.com.matchingkak;

import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Lee on 2018-04-11.
 */

public class GameItemViewUtils {
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

    public static void set_state(TextView v, String state){

        if(state.equals("0"))//성사대기
        {   v.setBackgroundColor(v.getContext().getColor(R.color.pretty_red));

            v.setText("요청가능");

        }else{
            v.setBackgroundColor(v.getContext().getColor(R.color.pretty_blue));

            v.setText("성사완료");
        }
    }
}
