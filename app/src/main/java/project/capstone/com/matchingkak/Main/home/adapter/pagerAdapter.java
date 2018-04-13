package project.capstone.com.matchingkak.Main.home.adapter;

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

import project.capstone.com.matchingkak.GameItemViewUtils;
import project.capstone.com.matchingkak.Main.OnClickListener;
import project.capstone.com.matchingkak.Main.home.HomeAdapterContract;
import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.restAPI.APIUrl;

/**
 * Created by Lee on 2017-11-01.
 */

public class pagerAdapter extends PagerAdapter implements HomeAdapterContract.View,HomeAdapterContract.Model {

    List<String> list;
    private Context context;
    LayoutInflater inflater;

    public pagerAdapter(final Context context)  {
        this.context=context;
        list=new ArrayList<String>();

    }

    public int getRealCount(){return list.size();}
    void setList(List<String> images){

        list=images;

    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        GameItemViewUtils.debug("pagerAdapter",position+"");
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.banner_view,null);
        ImageView image=view.findViewById(R.id.main_view_pager_img);

        RequestOptions options=new RequestOptions();
        position%=getRealCount();
        Glide.with(context)
                .load(APIUrl.API_BASE_URL+list.get(position).toString())
                .apply(options.centerCrop())
                .into(image);
        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
      //  position%=getRealCount();
        //super.destroyItem(container,position,object);

        ViewPager vp=(ViewPager) container;
        View view=(View)object;
        vp.removeView(view);

    }

    @Override
    public int getCount() {
        return list.size()==0?0:Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @Override
    public void setUpdate() {
            notifyDataSetChanged();
    }

    @Override
    public void setOnItemClickListenter(OnClickListener listenter) {

    }

    @Override
    public void set(List data) {
        this.setList(data);
    }

    @Override
    public void add(List data) {
            list.clear();
            this.setList(data);
    }

}
