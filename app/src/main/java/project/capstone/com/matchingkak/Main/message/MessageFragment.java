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
import android.widget.ProgressBar;

import project.capstone.com.matchingkak.Main.home.RecyclerItemClickListener;
import project.capstone.com.matchingkak.Main.message.adapter.MSGAdapter;
import project.capstone.com.matchingkak.Main.message.data.MSGData;
import project.capstone.com.matchingkak.Main.message.presenter.messageContract;
import project.capstone.com.matchingkak.Main.message.presenter.messagePresenter;
import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.config;

public class MessageFragment extends Fragment implements messageContract.View{
    private static MessageFragment instance;
    private messageContract.Presenter presenter;
    final public static String RECEIVED="R";
    final public static String SENT="S";
    final private static String TYPE="TYPE";
    private String type;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MSGAdapter mAdapter;
    private ProgressBar mProgress;
    private SwipeRefreshLayout swipeRefreshLayout;
    NestedScrollView nestedScrollView;

    static int page=1;
    Context context;
    public static MessageFragment newInstance() {

        return newInstance(RECEIVED);

    }
    public static MessageFragment newInstance(String type){
        MessageFragment f=new MessageFragment();
        Bundle args=new Bundle();
        args.putString(TYPE,type);
        f.setArguments(args);

        return f;

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

        presenter=new messagePresenter();
        presenter.attatchView(this);
        mAdapter=new MSGAdapter(getContext());
        presenter.setAdapterModel(mAdapter);
        presenter.setAdapterView(mAdapter);

        mProgress=getView().findViewById(R.id.message_progress);
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


        this.mLayoutManager=new LinearLayoutManager(context);
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent=new Intent(getContext(), MessageDetail.class);
                MSGData data=(MSGData)mAdapter.getItem(position);
                intent.putExtra(config.MSG_KEY,data);
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
    String type=getArguments().getString(TYPE);
        presenter.loadData(context,page,type);


//현재페이지에 맞는 리스트 데이터 가져오기


    }

    @Override
    public void done(int resultCode, String[] input) {
        if(resultCode==SUCCESS){
            mProgress.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}


