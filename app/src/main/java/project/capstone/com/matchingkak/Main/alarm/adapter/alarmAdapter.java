package project.capstone.com.matchingkak.Main.alarm.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import project.capstone.com.matchingkak.ActivityStarterManager;
import project.capstone.com.matchingkak.Main.OnClickListener;
import project.capstone.com.matchingkak.Main.ViewHolder;
import project.capstone.com.matchingkak.Main.alarm.BottomSheetDialogContract;
import project.capstone.com.matchingkak.Main.alarm.BottomSheetFragment;
import project.capstone.com.matchingkak.Main.alarm.adapter.holder.AlarmViewHolderToComplete;
import project.capstone.com.matchingkak.Main.alarm.adapter.holder.AlarmViewHolderToSubmit;
import project.capstone.com.matchingkak.Main.alarm.alarmAdapterContract;
import project.capstone.com.matchingkak.Main.alarm.data.alarmItem;
import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.config;

/**
 * Created by amco1 on 2018-03-16.
 */
public class alarmAdapter extends  RecyclerView.Adapter<ViewHolder>  implements BottomSheetDialogContract.Listener,alarmAdapterContract.model,alarmAdapterContract.view,OnClickListener {


    private int lastPosition=-1;
    private int firstPosition=-1;//for use of animation
    private List<alarmItem> mDataset;
    private OnClickListener clicklistener;
    private Context context;
    private FragmentManager fragmentManager;
private final int SUBMIT=0;
    private final int COMPLETE=1;



    public alarmAdapter(Context context){
        this.context=context;
        mDataset=new ArrayList<>();
        setOnClickListener(this);
    }
    public alarmAdapter(Context context, FragmentManager fragmentManager){
        this(context);
        this.fragmentManager=fragmentManager;
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
        if (mDataset.get(position).getState().equals(config.ALARAM_REQEUST)) {
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
        setAnimation(holder.itemView,position);
        //Log.d("holder",position+"");
    }
    private void setAnimation(View viewToAnimate,int position){
        //Log.d("alarmAdapter","anim!");
            Animation animation= AnimationUtils.loadAnimation(context,R.anim.item_animation_fall_down);

            viewToAnimate.startAnimation(animation);
            lastPosition=position;

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


    @Override
    public void OnClick(View v, int position) {
        //Toast.makeText(context,"wow:position",Toast.LENGTH_SHORT).show();
       // BottomSheetDialog dialog=new BottomSheetDialog(context);
        //dialog.show();
        BottomSheetFragment dialog=BottomSheetFragment.newInstance(getItem(position),position);
        dialog.show(fragmentManager,"good");
        dialog.attachListener(this);
    }

    @Override
    public void OnClickListener(int ResultCode,int position) {
        //for BottomDialog
        switch (ResultCode)
        {

            case DELETE:
                try {
                    mDataset.remove(position);
                    this.notifyItemRemoved(position);
                    notifyItemRangeChanged(position, getItemCount());
                    break;
                } catch (android.database.SQLException e) {
                    e.printStackTrace();
                }

            case DETAIL:
                ActivityStarterManager.StartGameDetailActivity(context,getItem(position).getRq_count_no());break;

            case LIKE:
                Toast.makeText(context,"LIKE",Toast.LENGTH_SHORT).show();break;
            case MESSAGE:
                ActivityStarterManager.StartWriteMessageActivity(context,getItem(position).getUser()); break;
            case BottomSheetDialogContract.Listener.SUBMIT:
                Toast.makeText(context,"submit",Toast.LENGTH_SHORT).show();break;
            case REJECT:
                Toast.makeText(context,"rejected",Toast.LENGTH_SHORT).show();break;


        }

    }
}