package project.capstone.com.matchingkak.Main.alarm.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import project.capstone.com.matchingkak.Main.alarm.AdapterContract;
import project.capstone.com.matchingkak.Main.alarm.OnClickListener;
import project.capstone.com.matchingkak.Main.alarm.adapter.holder.AlarmViewHolderToComplete;
import project.capstone.com.matchingkak.Main.alarm.adapter.holder.AlarmViewHolderToSubmit;
import project.capstone.com.matchingkak.Main.alarm.adapter.holder.ViewHolder;
import project.capstone.com.matchingkak.Main.alarm.data.alarmItem;
import project.capstone.com.matchingkak.R;

/**
 * Created by amco1 on 2018-03-16.
 */
public class alarmAdapter extends  RecyclerView.Adapter<ViewHolder>  implements AdapterContract.model,AdapterContract.view {



    private List<alarmItem> mDataset;
    private OnClickListener clicklistener;
    private Context context;
private final int SUBMIT=0;
    private final int COMPLETE=1;



    public alarmAdapter(Context context){
        this.context=context;
        mDataset=new ArrayList<>();
    }
    public alarmAdapter(List MSGDataSet){

        mDataset=MSGDataSet;
    }

    public alarmAdapter(Context context,List MSGDataSet){
        this.context=context;
        mDataset=MSGDataSet;
    }

    public void setList(List list){

        mDataset=list;
        notifyDataSetChanged();
    }
    public void addList(List list){
        mDataset.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if (mDataset.get(position).getState().equals("0")) {
            return SUBMIT;
        } else {
            return COMPLETE;
        }
    }
    public int getRId(int vewType){
        if(vewType==SUBMIT){
            return R.layout.alarm_list_view_for_submit;
        }else
            return R.layout.alarm_list_view_for_complete;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view

        int Rid=getRId(viewType);
        View v = LayoutInflater.from(parent.getContext())
                .inflate(Rid, parent, false);

        if(viewType==SUBMIT)
           return new AlarmViewHolderToSubmit(v);
        else
            return new AlarmViewHolderToComplete(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(holder==null)return;
        holder.bind(context,getItem(position),position,clicklistener);
    }

    // Replace the contents of a view (invoked by the layout manager)


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public alarmItem getItem(int position){

        return mDataset.get(position );
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public void setOnClickListener(OnClickListener clickListener) {
        this.clicklistener=clickListener;
    }



    @Override
    public void addItems(List items, boolean isUpper) {
            if(isUpper) setList(items);
            else addList(items);
    }
}