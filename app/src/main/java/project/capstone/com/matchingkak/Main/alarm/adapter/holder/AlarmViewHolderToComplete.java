package project.capstone.com.matchingkak.Main.alarm.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import project.capstone.com.matchingkak.Main.alarm.OnClickListener;
import project.capstone.com.matchingkak.Main.alarm.data.alarmItem;
import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.restAPI.APIUrl;

/**
 * Created by amco1 on 2018-03-17.
 */

public class AlarmViewHolderToComplete extends ViewHolder {

    private TextView alarm_title,alarm_date;
    private ImageView profile;
    private OnClickListener listener;
    public AlarmViewHolderToComplete(View v) {
        super(v);
        alarm_title         =v.findViewById(R.id.alarm_list_title);
        alarm_date          =v.findViewById(R.id.alarm_list_date);
        profile             =v.findViewById(R.id.alarm_image);
    }

    @Override
    public void bind(Context context, alarmItem item, int pos, OnClickListener listener) {
        alarm_title.setText(item.getUser()+"님과 경기가 성사되었습니다..");
        alarm_date.setText(item.getAlarm_send_date());
        RequestOptions options=new RequestOptions();
        Glide.with(context)
                .load(APIUrl.API_BASE_URL+item.getTm_img())
                .apply(options.centerCrop())
                .into(profile);





    }
}
