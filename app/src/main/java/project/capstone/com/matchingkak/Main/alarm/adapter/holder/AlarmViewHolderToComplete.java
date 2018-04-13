package project.capstone.com.matchingkak.Main.alarm.adapter.holder;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import project.capstone.com.matchingkak.Main.OnClickListener;
import project.capstone.com.matchingkak.Main.ViewHolder;
import project.capstone.com.matchingkak.Main.alarm.data.alarmItem;
import project.capstone.com.matchingkak.databinding.AlarmListViewForCompleteBinding;
import project.capstone.com.matchingkak.restAPI.APIUrl;

/**
 * Created by amco1 on 2018-03-17.
 */

public class AlarmViewHolderToComplete extends ViewHolder {
    AlarmListViewForCompleteBinding binding;

    public AlarmViewHolderToComplete(View v) {
        super(v);

    }

    @Override
    public void bind(final Context context, Object data, final int pos, final OnClickListener listener) {
        alarmItem item=(alarmItem)data;
        binding= DataBindingUtil.bind(itemView);

        binding.alarmListTitle.setText(item.getUser()+"님과 경기 성사 완료");
        binding.alarmListDate.setText(item.getAlarm_send_date());
        RequestOptions options=new RequestOptions();
        Glide.with(context)
                .load(APIUrl.API_BASE_URL+item.getTm_img())
                .apply(options.centerCrop())
                .into(binding.alarmImage);
        binding.alarmImage.setClickable(true);
        binding.alarmMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.OnClick(view,pos);
            }
        });


    }
}
