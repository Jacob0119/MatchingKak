package project.capstone.com.matchingkak.Main.alarm.adapter.holder;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import project.capstone.com.matchingkak.Main.OnClickListener;
import project.capstone.com.matchingkak.Main.ViewHolder;
import project.capstone.com.matchingkak.Main.alarm.data.alarmItem;
import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.databinding.AlarmListViewForSubmitBinding;
import project.capstone.com.matchingkak.restAPI.APIUrl;

/**
 * Created by amco1 on 2018-03-16.
 */
public  class AlarmViewHolderToSubmit extends ViewHolder {

        AlarmListViewForSubmitBinding binding;
    private TextView alarm_title,alarm_date;
    private ImageView profile;
    private OnClickListener listener;
    public AlarmViewHolderToSubmit(View v){

        super(v);


        alarm_title         =v.findViewById(R.id.alarm_list_title);
        alarm_date          =v.findViewById(R.id.alarm_list_date);
        profile             =v.findViewById(R.id.alarm_image);
    }




    @Override
    public   void bind(Context context, Object data, final int pos, final OnClickListener listener){

       binding= DataBindingUtil.bind(this.itemView);
            alarmItem item=(alarmItem)data;

            this.listener=listener;

            binding.alarmListDate.setText(item.getAlarm_send_date());
             RequestOptions options=new RequestOptions();

             Glide.with(context)
             .load(APIUrl.API_BASE_URL+item.getTm_img())
             .apply(options.centerCrop())
             .into(binding.alarmImage);

            binding.alarmListTitle.setText(item.getUser()+"님이 경기를 요청하였습니다.");

            setOnClickListener(binding.alarmMenu,pos);
    }

    void setOnClickListener(View v,final int pos){


        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClick(v,pos);
            }
        });
    }

}