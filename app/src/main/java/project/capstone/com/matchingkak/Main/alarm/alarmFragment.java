package project.capstone.com.matchingkak.Main.alarm;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import project.capstone.com.matchingkak.Main.Contract;
import project.capstone.com.matchingkak.Main.alarm.adapter.alarmAdapter;
import project.capstone.com.matchingkak.Main.alarm.presenter.alarmPresenter;
import project.capstone.com.matchingkak.Main.home.RecyclerItemClickListener;
import project.capstone.com.matchingkak.Message.Message2Activity;
import project.capstone.com.matchingkak.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class alarmFragment extends Fragment implements Contract.View{
    private int lastPosition=-1;
    private int firstPosition=-1;
    private Contract.Presenter presenter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private alarmAdapter mAdapter;
    private ProgressBar mProgress;
    private SwipeRefreshLayout swipeRefreshLayout;
    NestedScrollView nestedScrollView;

    static int page=1;
    Context context;
    public alarmFragment() {
        // Required empty public constructor
    }
public static alarmFragment newInstance(){

    return new alarmFragment();
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.alarm_content, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        init();
        getData(1,true);
    }

    void init(){
        mAdapter=new alarmAdapter(getContext());
        presenter=new alarmPresenter();
        presenter.attatchView(this);
        presenter.setAdapterModel(mAdapter);
        presenter.setAdapterView(mAdapter);

        ////Layout view setting
        mProgress=getView().findViewById(R.id.message_progress);
        context=getContext();
        swipeRefreshLayout=getView().findViewById(R.id.message_swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getData(page=1,true);
                //getData(page,true);
            }
        });

        mRecyclerView=getView().findViewById(R.id.message_recycler);
        mRecyclerView.setNestedScrollingEnabled(false);
       // AnimationManager.runLayoutAnimation(mRecyclerView);

        this.mLayoutManager=new LinearLayoutManager(context);
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager=LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
                int lastVisible=layoutManager.findLastVisibleItemPosition();
                int firstVisible=layoutManager.findFirstVisibleItemPosition();

                int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int itemTotalCount = recyclerView.getAdapter().getItemCount() - 1;
                if (lastVisibleItemPosition == itemTotalCount) {
                    //끝에 도달
                    getData(++page,false);
                     return;
                }else if(firstPosition>firstVisible){
                    View item= layoutManager.findViewByPosition(firstVisible);
                    setAnimation(item,R.anim.item_animation_fall_down);
                    lastPosition=lastVisible;
                    firstPosition=firstVisible;
                }
               else if(lastPosition<lastVisible){
                    View item= layoutManager.findViewByPosition(lastVisible);
                   setAnimation(item,R.anim.item_animation_fall_up);
                    lastPosition=lastVisible;
                    firstPosition=firstVisible;
                }

            }
        });
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Intent intent=new Intent(context, DetailActivity.class);
                //intent.putExtra("gm_no",mAdapter.getItem(position).getGmNo());
                //startActivity(intent);
                //Toast.makeText(context,mAdapter.getItem(position).getMs_content(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


/*
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
        });*/
    }

    void setAnimation(View item,int Rid){


        Animation animation= AnimationUtils.loadAnimation(context,Rid);
        item.startAnimation(animation);


    }
    void getData(int page, final boolean isUpper){
        if(!isUpper) {
            mProgress.setVisibility(View.VISIBLE);
            mProgress.startNestedScroll(3);
        }
        //현재페이지에 맞는 리스트 데이터 가져오기
       presenter.loadItems(context,page,isUpper);
        //mRecyclerView.scheduleLayoutAnimation();


    }
@Override
public void done(int resultCode,String[] input){
    if(resultCode==MAKE_MESSAGE){
        Intent intent=new Intent(getContext(), Message2Activity.class);
        intent.putExtra("mb_nick",input[0]);
        startActivity(intent);

    }
    mProgress.setVisibility(View.GONE);
    swipeRefreshLayout.setRefreshing(false);



    //Toast.makeText(context,input[0],Toast.LENGTH_SHORT).show();
}

}


