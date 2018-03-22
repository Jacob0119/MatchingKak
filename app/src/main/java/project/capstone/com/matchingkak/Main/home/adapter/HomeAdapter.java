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
import project.capstone.com.matchingkak.Main.home.adapter.holder.GameViewHolder;
import project.capstone.com.matchingkak.Main.home.data.HomeData;
import project.capstone.com.matchingkak.R;


/**
 * Created by amco1 on 2018-03-21.
 */
public class HomeAdapter extends  RecyclerView.Adapter<ViewHolder> implements HomeAdapterContract.Model,HomeAdapterContract.View{



    private List<HomeData> mDataset;

    private Context context;

    @Override
    public void setUpdate() {
        notifyDataSetChanged();
    }

    @Override
    public void set(List data) {
        mDataset=data;
    }

    @Override
    public void add(List data) {
        mDataset.addAll(data);
        notifyDataSetChanged();
    }



    public HomeAdapter(Context context){
        this.context=context;
        mDataset=new ArrayList<>();
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view


        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_view, parent, false);

        ViewHolder vh = new GameViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {




        holder.bind(context,mDataset.get(position),position,null);


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public HomeData getItem(int position){

        return mDataset.get(position );
    }


}
