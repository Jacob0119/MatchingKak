package project.capstone.com.matchingkak.Main.message.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import project.capstone.com.matchingkak.Main.OnClickListener;
import project.capstone.com.matchingkak.Main.message.data.MSGData;
import project.capstone.com.matchingkak.R;

/**
 * Created by amco1 on 2018-03-28.
 */
public  class ViewHolder extends   project.capstone.com.matchingkak.Main.ViewHolder{

    View msg_view;
    private TextView ms_title,date,mb_nick;
    ImageView mb_image,ms_new;

    public ViewHolder(View v){

        super(v);
        this.msg_view=v;
        ms_title         =v.findViewById(R.id.message_list_title);
        date             =v.findViewById(R.id.message_list_date);
        mb_nick          =v.findViewById(R.id.message_list_sender);
        mb_image          =v.findViewById(R.id.message_list_image);
        ms_new          =v.findViewById(R.id.message_new);
    }

    @Override
    public void bind(Context context, Object item, int pos, OnClickListener listener) {
        MSGData datum=(MSGData)item;
        ms_title.setText(datum.getMs_title());

        date.setText(datum.getMs_send_date());
        mb_nick.setText(datum.getMb_nick());

        if(datum.getMs_read())ms_new.setVisibility(View.VISIBLE);
        else ms_new.setVisibility(View.GONE);

        Glide.with(context).load("http://www.matchingkak.com/images/temp.png")
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(mb_image);
    }


}
