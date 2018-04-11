package project.capstone.com.matchingkak.Main.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import project.capstone.com.matchingkak.ActivityStarterManager;
import project.capstone.com.matchingkak.Main.OnClickListener;
import project.capstone.com.matchingkak.Main.ViewHolder;
import project.capstone.com.matchingkak.Main.home.HomeAdapterContract;
import project.capstone.com.matchingkak.Main.home.adapter.holder.MenuViewHolder;
import project.capstone.com.matchingkak.Main.home.data.MenuData;
import project.capstone.com.matchingkak.R;

/**
 * Created by amco1 on 2018-03-25.
 */

public class menuAdapter extends RecyclerView.Adapter<ViewHolder> implements HomeAdapterContract.View,HomeAdapterContract.Model,OnClickListener {
    private Context context;
    List<MenuData> menuList;
    private OnClickListener listener;
   public menuAdapter(Context context){
       this.context=context;
       setOnItemClickListenter(this);
       menuList=new ArrayList();

   }

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
       holder.bind(context,menuList.get(position),position,listener);



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
    public void setOnItemClickListenter(OnClickListener listenter) {
        this.listener=listenter;
    }

    @Override
    public void set(List data) {
        menuList=data;

    }

    @Override
    public void add(List data) {
        menuList.addAll(data);
    }

    @Override
    public void OnClick(View v, int position) {

        HashMap<String,String> map=new HashMap<>();
        String sportIdx=String.valueOf(getItem(position).getSport_index());
        Log.d("menuAdapter",sportIdx);
        map.put("sport",sportIdx);

        ActivityStarterManager.StartListGameActivity(v.getContext(),map);
    }

    public MenuData getItem(int position){return menuList.get(position);}
}
