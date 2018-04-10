package project.capstone.com.matchingkak.Main.home.adapter.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import project.capstone.com.matchingkak.Main.OnClickListener;
import project.capstone.com.matchingkak.Main.ViewHolder;
import project.capstone.com.matchingkak.Main.home.data.MenuData;
import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.restAPI.APIUrl;

/**
 * Created by amco1 on 2018-03-25.
 */

public class MenuViewHolder extends ViewHolder {

    ImageView sport_img;
    TextView sport_name;

    public MenuViewHolder(View itemView) {


        super(itemView);

        this.sport_img=itemView.findViewById(R.id.home_menu_sport_img);
        this.sport_name=itemView.findViewById(R.id.home_menu_sport_name);

    }

    @Override
    public void bind(Context context, Object item, final int pos, final OnClickListener listener) {

        MenuData data=(MenuData)item;
       // Log.d("MenuHolder",APIUrl.API_BASE_URL+((MenuData) item).getSport_img());
        Glide.with(context)
                .load(APIUrl.API_BASE_URL+((MenuData) item).getSport_img())
                .apply(RequestOptions.centerCropTransform())
                .into(sport_img);

        sport_name.setText(data.getSport_Name());

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(listener!=null) listener.OnClick(view,pos);
            }
        });

    }
}
