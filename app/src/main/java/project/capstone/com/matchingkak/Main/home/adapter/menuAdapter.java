package project.capstone.com.matchingkak.Main.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import project.capstone.com.matchingkak.Main.ViewHolder;
import project.capstone.com.matchingkak.Main.home.HomeAdapterContract;
import project.capstone.com.matchingkak.Main.home.adapter.holder.MenuViewHolder;
import project.capstone.com.matchingkak.Main.home.data.MenuData;
import project.capstone.com.matchingkak.R;

/**
 * Created by amco1 on 2018-03-25.
 */

public class menuAdapter extends RecyclerView.Adapter<ViewHolder> implements HomeAdapterContract.View,HomeAdapterContract.Model {
    private Context context;
    List<MenuData> menuList;

   public menuAdapter(Context context){this.context=context;menuList=new ArrayList();}

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_menu_item_view, parent, false);
        ViewHolder vh=new MenuViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

      //  Log.d("menuAdapter",position+"");
       holder.bind(context,menuList.get(position),position,null);



    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    @Override
    public void setUpdate() {
        notifyDataSetChanged();
    }

    @Override
    public void set(List data) {
        menuList=data;

    }

    @Override
    public void add(List data) {
        menuList.addAll(data);
    }
}
