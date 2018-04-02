package project.capstone.com.matchingkak.Main.message.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import project.capstone.com.matchingkak.Main.OnClickListener;
import project.capstone.com.matchingkak.Main.message.adapter.holder.ViewHolder;
import project.capstone.com.matchingkak.Main.message.data.MSGData;
import project.capstone.com.matchingkak.Main.message.presenter.messageAdapterContract;
import project.capstone.com.matchingkak.R;

/**
 * Created by amco1 on 2018-03-28.
 */
public class MSGAdapter extends  RecyclerView.Adapter<ViewHolder> implements messageAdapterContract.model,messageAdapterContract.view{



    private List<MSGData> mDataset;

    private Context context;

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void setOnClickListener(OnClickListener clickListener) {

    }




    public MSGAdapter(Context context){
        this.context=context;
        mDataset=new ArrayList<>();
    }
    public MSGAdapter(List MSGDataSet){

        mDataset=MSGDataSet;
    }

    public MSGAdapter(Context context,List MSGDataSet){
        this.context=context;
        mDataset=MSGDataSet;
    }

    public void setList(List list){

        mDataset=list;
        notifyDataSetChanged();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view


        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_list_view, parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }



    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {



        MSGData datum=mDataset.get(position);

        holder.bind(context,datum,position,null);



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    @Override
    public Object getItem(int position) {
        return mDataset.get(position);
    }

    @Override
    public void addItems(List items, boolean isUpper) {

        if(isUpper ){
            setList(items);
        }else
        {
            mDataset.addAll(items);
        }

    }
}
