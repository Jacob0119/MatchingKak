package project.capstone.com.matchingkak.Main.home;

import android.app.Fragment;
import android.content.Context;
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
import android.widget.TextView;

import project.capstone.com.matchingkak.ActivityStarterManager;
import project.capstone.com.matchingkak.Main.home.adapter.HomeAdapter;
import project.capstone.com.matchingkak.Main.home.adapter.menuAdapter;
import project.capstone.com.matchingkak.Main.home.adapter.pagerAdapter;
import project.capstone.com.matchingkak.Main.home.presenter.HomePresenter;
import project.capstone.com.matchingkak.R;


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

    private RecyclerView mRecyclerView1,mRecyclerView2,mRecyclerView3;
    private RecyclerView.LayoutManager mLayoutManager1,mLayoutManager2,mLayoutManager3;
    private HomeAdapter mAdapter1,mAdapter2;
    private menuAdapter mAdapter3;
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

    loadAll();
    }

    void loadAll(){
        presenter.showBanner();
        presenter.getData(HomeContract.Presenter.MAIN_GAMELIST);
        presenter.getData(HomeContract.Presenter.RECOMMEND_GAMELIST);
        presenter.getMenu();
    }
    void init(){


        mProgress=getView().findViewById(R.id.main_progress);


        ///배너부터
        pAdapter=new pagerAdapter(context);
        presenter.setAdapterModel(pAdapter,HomeContract.Presenter.BANNER_ADATER);
        presenter.setAdapterView(pAdapter,HomeContract.Presenter.BANNER_ADATER);
        pager =getView().findViewById(R.id.main_view_pager);
        pager.setAdapter(pAdapter);

        ////main list
        mAdapter1=new HomeAdapter(context);
        mAdapter2=new HomeAdapter(context);
        mAdapter3=new menuAdapter(context);
        presenter.setAdapterView(mAdapter1,HomeContract.Presenter.RECOMMEND_GAMELIST);
        presenter.setAdapterModel(mAdapter1,HomeContract.Presenter.RECOMMEND_GAMELIST);
        presenter.setAdapterView(mAdapter2,HomeContract.Presenter.MAIN_GAMELIST);
        presenter.setAdapterModel(mAdapter2,HomeContract.Presenter.MAIN_GAMELIST);
        presenter.setAdapterView(mAdapter3,HomeContract.Presenter.MENU_LIST);
        presenter.setAdapterModel(mAdapter3,HomeContract.Presenter.MENU_LIST);

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

        this.mLayoutManager3=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        mLayoutManager3.setAutoMeasureEnabled(true);
        mRecyclerView3=getView().findViewById(R.id.home_recyler3);
        mRecyclerView3.setNestedScrollingEnabled(false);
        mRecyclerView3.setLayoutManager(mLayoutManager3);
        mRecyclerView3.setAdapter(mAdapter3);
        mRecyclerView3.setHasFixedSize(false);


        TextView more=getView().findViewById(R.id.home_content2_more);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityStarterManager.StartListGameActivity(getContext());
            }
        });
/*
        RecyclerItemClickListener listener2=new RecyclerItemClickListener(context, mRecyclerView2, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("gm_no",mAdapter2.getItem(position).getGmNo());
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
        RecyclerItemClickListener listener1=new RecyclerItemClickListener(context, mRecyclerView1, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("gm_no",mAdapter1.getItem(position).getGmNo());
                startActivity(intent);

            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        });
       // mRecyclerView2.addOnItemTouchListener(listener2);
        //mRecyclerView1.addOnItemTouchListener(listener1);
*/
        swipeRefreshLayout=getView().findViewById(R.id.main_swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
              loadAll();
            }
        });




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

