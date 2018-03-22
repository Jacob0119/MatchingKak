package project.capstone.com.matchingkak.Main.home;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ProgressBar;

import project.capstone.com.matchingkak.Main.home.adapter.HomeAdapter;
import project.capstone.com.matchingkak.Main.home.adapter.pagerAdapter;
import project.capstone.com.matchingkak.Main.home.presenter.HomePresenter;
import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.detail_info.DetailActivity;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements HomeContract.View {

    private HomeContract.Presenter presenter;

    private RecyclerView mRecyclerView1,mRecyclerView2;
    private RecyclerView.LayoutManager mLayoutManager1,mLayoutManager2;
    private HomeAdapter mAdapter1,mAdapter2;
    private ProgressBar mProgress;
    private ViewPager pager;
    private pagerAdapter pAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    NestedScrollView nestedScrollView;
     Context context;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER





    public HomeFragment() {

        // Required empty public constructor
    }


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
       // context=getContext();
        super.onCreate(savedInstanceState);
        presenter=new HomePresenter();
        presenter.attachView(this);
        context=getContext();
    }

    @Override
    public void onStart() {
        super.onStart();



       init();
        presenter.showBanner();
        presenter.getData(presenter.MAIN_GAMELIST);
        presenter.getData(presenter.RECOMMEND_GAMELIST);

    }

    void init(){



        mProgress=getView().findViewById(R.id.main_progress);


        ///배너부터
        pAdapter=new pagerAdapter(context);
        presenter.setAdapterModel(pAdapter,presenter.BANNER_ADATER);
        presenter.setAdapterView(pAdapter,presenter.BANNER_ADATER);
        pager =getView().findViewById(R.id.main_view_pager);
        pager.setAdapter(pAdapter);

        ////main list
        mAdapter1=new HomeAdapter(context);
        mAdapter2=new HomeAdapter(context);
        presenter.setAdapterView(mAdapter1,presenter.RECOMMEND_GAMELIST);
        presenter.setAdapterModel(mAdapter1,presenter.RECOMMEND_GAMELIST);
        presenter.setAdapterView(mAdapter2,presenter.MAIN_GAMELIST);
        presenter.setAdapterModel(mAdapter2,presenter.MAIN_GAMELIST);

        this.mLayoutManager1=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        mLayoutManager1.setAutoMeasureEnabled(true);
        mRecyclerView1=getView().findViewById(R.id.home_recyler1);
        mRecyclerView1.setNestedScrollingEnabled(false);
        mRecyclerView1.setLayoutManager(mLayoutManager1);
        mRecyclerView1.setAdapter(mAdapter1);
        mRecyclerView1.setHasFixedSize(false);


      //  this.mLayoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        this.mLayoutManager2=new GridLayoutManager(context,2);
        mLayoutManager2.setAutoMeasureEnabled(true);
        mRecyclerView2=getView().findViewById(R.id.home_recyler2);
        mRecyclerView2.setNestedScrollingEnabled(false);
        mRecyclerView2.setLayoutManager(mLayoutManager2);
        mRecyclerView2.setAdapter(mAdapter2);
        mRecyclerView2.setHasFixedSize(false);

        LayoutAnimationController controller= AnimationUtils.loadLayoutAnimation(context,R.anim.layout_animation_fall_up);
        mRecyclerView2.setLayoutAnimation(controller);



        mRecyclerView2.addOnItemTouchListener(new RecyclerItemClickListener(context, mRecyclerView2, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("gm_no",mAdapter2.getItem(position).getGmNo());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


        swipeRefreshLayout=getView().findViewById(R.id.main_swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               getFirstPage();
            }
        });

/*
        nestedScrollView=getView().findViewById(R.id.main_nested);

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if(v.getChildAt(v.getChildCount() - 1) != null) {
                    if ((scrollY >= (v.getChildAt(v.getChildCount() - 1).getMeasuredHeight() - v.getMeasuredHeight())) &&
                            scrollY > oldScrollY) {
                            getNextPage();
                    }
                }
            }


        });
*/


    }

    void getFirstPage(){

        presenter.showFirstPage();

    }
    void getNextPage(){
            mProgress.setVisibility(View.VISIBLE);
            mProgress.startNestedScroll(3);
            presenter.showNextPage();
        }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_content, container, false);

    }


    @Override
    public void updateBanner() {

    }

    @Override
    public void finishUpdatePage() {
        mProgress.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
        mRecyclerView2.scheduleLayoutAnimation();

    }





}

