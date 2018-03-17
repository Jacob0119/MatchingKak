package project.capstone.com.matchingkak.Main.alarm.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import project.capstone.com.matchingkak.Main.alarm.OnClickListener;
import project.capstone.com.matchingkak.Main.alarm.data.alarmItem;
import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.restAPI.APIUrl;

/**
 * Created by amco1 on 2018-03-16.
 */
public  class AlarmViewHolderToSubmit extends  ViewHolder {


    private TextView alarm_title,alarm_date;
    private Button submit,reject;
    private ImageView profile;
    private OnClickListener listener;
    public AlarmViewHolderToSubmit(View v){

        super(v);

        alarm_title         =v.findViewById(R.id.alarm_list_title);
        alarm_date          =v.findViewById(R.id.alarm_list_date);
        submit              =v.findViewById(R.id.alarm_submit);
        reject              =v.findViewById(R.id.alarm_resect);
        profile             =v.findViewById(R.id.alarm_image);
    }




    @Override
    public   void bind(Context context, alarmItem item, final int pos, final OnClickListener listener){

     this.listener=listener;


            alarm_date.setText(item.getAlarm_send_date());
             RequestOptions options=new RequestOptions();

             Glide.with(context)
             .load(APIUrl.API_BASE_URL+item.getTm_img())
             .apply(options.centerCrop())
             .into(profile);

            alarm_title.setText(item.getUser()+"님이 경기를 요청하였습니다.");
            submit.setFocusableInTouchMode(false);
            reject.setFocusableInTouchMode(false);
            setOnClickListener(submit, pos);
            setOnClickListener(reject, pos);

    }

    void setOnClickListener(Button v,final int pos){


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClick(v,pos);
            }
        });
    }

}