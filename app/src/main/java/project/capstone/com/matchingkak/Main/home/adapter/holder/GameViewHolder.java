package project.capstone.com.matchingkak.Main.home.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import project.capstone.com.matchingkak.Main.OnClickListener;
import project.capstone.com.matchingkak.Main.ViewHolder;
import project.capstone.com.matchingkak.Main.home.data.HomeData;
import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.restAPI.APIUrl;

/**
 * Created by amco1 on 2018-03-21.
 */

public class GameViewHolder extends ViewHolder{



    public View game_view;
    private TextView teamName,date,location,title,state_text;
    private ImageView teamLogo,state;
    private TextView sport;



    public GameViewHolder(View v){

        super(v);
        this.game_view=v;
        date        =v.findViewById(R.id.game_view_date);
        location    =v.findViewById(R.id.game_view_location);
        sport       =v.findViewById(R.id.game_view_sport);
        teamLogo    =v.findViewById(R.id.game_view_image);
        state_text =v.findViewById(R.id.game_view_state_text );
    }

    @Override
    public void bind(Context context, Object data, final int pos, final OnClickListener listener) {
        HomeData item=(HomeData) data;
        this.date.setText(item.getGmDate());
        this.location.setText(item.getGmGym());
        this.sport.setText(item.getTmSport());
        RequestOptions options=new RequestOptions();
        View state_back=itemView.findViewById(R.id.game_view_state_back);
        if(item.getGmState().equals("0"))//성사대기
        {   state_back.setBackgroundColor(context.getColor(R.color.pretty_red));

            this.state_text.setText("요청가능");

        }else{
            state_back.setBackgroundColor(context.getColor(R.color.pretty_blue));

            this.state_text.setText("성사완료");
        }
        Glide.with(context)

                .load(APIUrl.API_BASE_URL+item.getTmImg())
                .apply(options.centerCrop())
                .into(this.teamLogo);

        itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(listener!=null)
             listener.OnClick(view,pos);
        }
    });

    }





}


//////
