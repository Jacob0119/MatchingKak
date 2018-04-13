package project.capstone.com.matchingkak.Main.alarm.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import project.capstone.com.matchingkak.Main.OnClickListener;
import project.capstone.com.matchingkak.Main.ViewHolder;
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
    private View message_btn;
    public AlarmViewHolderToComplete(View v) {
        super(v);

        alarm_title         =v.findViewById(R.id.alarm_list_title);
        alarm_date          =v.findViewById(R.id.alarm_list_date);
        profile             =v.findViewById(R.id.alarm_image);
        message_btn         =v.findViewById(R.id.alarm_send);
    }

    @Override
    public void bind(Context context, Object data, final int pos, final OnClickListener listener) {
        alarmItem item=(alarmItem)data;
        alarm_title.setText(item.getUser()+"님과 경기 성사 완료");
        alarm_date.setText(item.getAlarm_send_date());
        RequestOptions options=new RequestOptions();
        Glide.with(context)
                .load(APIUrl.API_BASE_URL+item.getTm_img())
                .apply(options.centerCrop())
                .into(profile);
        message_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnClick(view,pos);
            }
        });




    }
}
