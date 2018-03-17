package project.capstone.com.matchingkak.Main.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.restAPI.APIUrl;

/**
 * Created by Lee on 2017-11-01.
 */

public class pagerAdapter extends PagerAdapter {

    List<String> list;
    private Context context;
    LayoutInflater inflater;
    public pagerAdapter(final Context context)  {
        this.context=context;
        list=new ArrayList<String>();

    }


    void setList(List<String> images){

        list=images;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.banner_view,null);
        ImageView image=view.findViewById(R.id.main_view_pager_img);

        RequestOptions options=new RequestOptions();
        //Log.d("pAdapter",APIUrl.API_BASE_URL+list.get(position).toString());
        Glide.with(context)
                .load(APIUrl.API_BASE_URL+list.get(position).toString())
                .apply(options.centerCrop())
                .into(image);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        ViewPager vp=(ViewPager) container;
        View view=(View)object;
        vp.removeView(view);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
