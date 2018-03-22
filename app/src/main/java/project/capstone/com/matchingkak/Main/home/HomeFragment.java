package project.capstone.com.matchingkak.Main.home;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import project.capstone.com.matchingkak.AnimationManager;
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
    private int lastPosition=-1;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private HomeAdapter mAdapter;
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
        presenter.showFirstPage();

    }

    void init(){



        mProgress=getView().findViewById(R.id.main_progress);


        ///배너부터
        pAdapter=new pagerAdapter(context);
        presenter.setAdapterModel(pAdapter,presenter.BANNER_ADATER);
        presenter.setAdapterView(pAdapter,presenter.BANNER_ADATER);
        pager =getView().findViewById(R.id.main_view_pager);
        pager.setAdapter(pAdapter);
        presenter.showBanner();
        ////main list
        mAdapter=new HomeAdapter(context);

        presenter.setAdapterView(mAdapter,presenter.MAIN_GAMELIST);
        presenter.setAdapterModel(mAdapter,presenter.MAIN_GAMELIST);

      //  this.mLayoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        this.mLayoutManager=new GridLayoutManager(context,2);
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView=getView().findViewById(R.id.main_recyler);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(false);

        AnimationManager.runLayoutAnimation(mRecyclerView);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("gm_no",mAdapter.getItem(position).getGmNo());
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
        mRecyclerView.scheduleLayoutAnimation();

    }





}

