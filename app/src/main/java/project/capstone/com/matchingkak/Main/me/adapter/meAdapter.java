package project.capstone.com.matchingkak.Main.me.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import project.capstone.com.matchingkak.ActivityStarterManager;
import project.capstone.com.matchingkak.Main.OnClickListener;
import project.capstone.com.matchingkak.Main.ViewHolder;
import project.capstone.com.matchingkak.Main.me.adapter.holder.GameItemViewholder;
import project.capstone.com.matchingkak.Main.me.data.gameData;
import project.capstone.com.matchingkak.Main.me.meAdapterContract;
import project.capstone.com.matchingkak.R;

/**
 * Created by Lee on 2018-04-03.
 */

public class meAdapter extends RecyclerView.Adapter<ViewHolder> implements meAdapterContract.model,meAdapterContract.view,OnClickListener{

    List<gameData> dataList;
    private int feature;
    private Context context;
    final static int EMPTY=-1;
    private OnClickListener listener;
    public meAdapter(Context context){
        setOnClickListener(this);
        dataList=new ArrayList();
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.game_view,parent,false);
        return new GameItemViewholder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        if(getItemViewType(position)==EMPTY){

        }else{
            holder.bind(context,getItem(position),position,listener);

        }
    }



    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public void notifyAdapter() {

            notifyDataSetChanged();
    }

    @Override
    public void setOnClickListener(OnClickListener clickListener) {
            listener=clickListener;
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public void addItems(List items, boolean isUpper) {
            if(isUpper){  //page 1

                dataList=items;


            }else{

                dataList.addAll(items);
            }
    }

    @Override
    public void OnClick(View v, int position) {
        gameData data=(gameData)getItem(position);
        ActivityStarterManager.StartGameDetailActivity(context,data.getGmNo());
    }
}
