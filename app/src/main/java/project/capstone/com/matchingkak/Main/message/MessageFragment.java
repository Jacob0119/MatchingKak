package project.capstone.com.matchingkak.Main.message;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import project.capstone.com.matchingkak.Main.home.RecyclerItemClickListener;
import project.capstone.com.matchingkak.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MSGAdapter mAdapter;
    private ProgressBar mProgress;
    private SwipeRefreshLayout swipeRefreshLayout;
    NestedScrollView nestedScrollView;

    static int page=1;
    Context context;
    public static MessageFragment newInstance() {

        return new MessageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.message_content, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        init();
        getData(page,false);
    }
    void init(){
        mProgress=getView().findViewById(R.id.message_progress);
        context=getContext();
        swipeRefreshLayout=getView().findViewById(R.id.message_swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                getData(page,true);
            }
        });

        mRecyclerView=getView().findViewById(R.id.message_recycler);
        mRecyclerView.setNestedScrollingEnabled(false);
        mAdapter=new MSGAdapter(context);

        this.mLayoutManager=new LinearLayoutManager(context);
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Intent intent=new Intent(context, DetailActivity.class);
                //intent.putExtra("gm_no",mAdapter.getItem(position).getGmNo());
                //startActivity(intent);
                //Toast.makeText(context,mAdapter.getItem(position).getMs_content(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(),MessageDetail.class);
                intent.putExtra("ms_no",mAdapter.getItem(position).getMs_no());
                intent.putExtra("type","receive");
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));



        nestedScrollView=getView().findViewById(R.id.message_nested);

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(v.getChildAt(v.getChildCount() - 1) != null) {
                    if ((scrollY >= (v.getChildAt(v.getChildCount() - 1).getMeasuredHeight() - v.getMeasuredHeight())) &&
                            scrollY > oldScrollY) {
                        getData(++page,false);
                    }
                }
            }
        });
    }
    void getData(int page, final boolean isUpper){
        if(!isUpper) {
            mProgress.setVisibility(View.VISIBLE);
            mProgress.startNestedScroll(3);
        }



//현재페이지에 맞는 리스트 데이터 가져오기
        MessageListService.getRetrofit(context).paging(page)
                .enqueue(new Callback<MSGListData>() {
                    @Override
                    public void onResponse(Call<MSGListData> call, Response<MSGListData> response) {
                        if(response.isSuccessful()) {
                            if(isUpper){
                                mAdapter.setList(response.body().getData());
                                swipeRefreshLayout.setRefreshing(false);
                            }
                            else {
                                //addList로 데이터 이어서 추가
                                mAdapter.addList(response.body().getData());
                                mProgress.stopNestedScroll();
                                mProgress.setVisibility(View.GONE);
                            }



                        }

                    }

                    @Override
                    public void onFailure(Call<MSGListData> call, Throwable t) {

                        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(context, "failed to load", Toast.LENGTH_LONG).show();
                    }
                });

    }
}

class MSGAdapter extends  RecyclerView.Adapter<MSGAdapter.ViewHolder>{



    private List<MSGData> mDataset;

    private Context context;


    public static class ViewHolder extends  RecyclerView.ViewHolder{

        View msg_view;
        private TextView ms_title,date,mb_nick;
        ImageView mb_image;

        public ViewHolder(View v){

            super(v);
            this.msg_view=v;
            ms_title    =v.findViewById(R.id.message_list_title);
            date        =v.findViewById(R.id.message_list_date);
            mb_nick    =v.findViewById(R.id.message_list_sender);
            mb_image       =v.findViewById(R.id.message_list_image);
        }


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
    public void addList(List list){
        mDataset.addAll(list);
        notifyDataSetChanged();
    }


    @Override
    public MSGAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
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

        holder.ms_title.setText(datum.getMs_title());

        holder.date.setText(datum.getMs_send_date());
        holder.mb_nick.setText(datum.getMb_nick());



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public MSGData getItem(int position){

        return mDataset.get(position );
    }}


