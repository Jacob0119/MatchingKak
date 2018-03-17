package project.capstone.com.matchingkak.Main.home;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.detail_info.DetailActivity;
import project.capstone.com.matchingkak.restAPI.APIUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {


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




    private static int page=1;
    private OnFragmentInteractionListener mListener;

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
        context=getContext();
    }

    @Override
    public void onStart() {
        super.onStart();



       init();
        page=1;
        getData(page,false);

    }

    void init(){



        mProgress=getView().findViewById(R.id.main_progress);
        pAdapter=new pagerAdapter(context);
        pager =getView().findViewById(R.id.main_view_pager);
        pager.setAdapter(pAdapter);
        swipeRefreshLayout=getView().findViewById(R.id.main_swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page=1;
                getData(page,true);
            }
        });

        mRecyclerView=getView().findViewById(R.id.main_recyler);
        mRecyclerView.setNestedScrollingEnabled(false);


        mAdapter=new HomeAdapter(context);

        this.mLayoutManager=new LinearLayoutManager(context);
        mLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(false);
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



        nestedScrollView=getView().findViewById(R.id.main_nested);

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
/////서버로 부터 배너 이미지 가져오기
        MainListService.getRetrofit(context).getBanner()
                .enqueue(new Callback<List<String>>() {
                    @Override
                    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                        Log.d("Main2Activity",response.headers().toString()+"");

                        pAdapter.setList(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<String>> call, Throwable t) {
                        Log.d("Main2Activity",t.toString());

                    }
                });

//현재페이지에 맞는 리스트 데이터 가져오기
        MainListService.getRetrofit(context).paging(page)
                .enqueue(new Callback<ListData>() {
                    @Override
                    public void onResponse(Call<ListData> call, Response<ListData> response) {
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
                    public void onFailure(Call<ListData> call, Throwable t) {

                        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(context, "failed to load", Toast.LENGTH_LONG).show();
                    }
                });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_content, container, false);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }



    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onStop() {
        Log.d("home","stop");
        super.onStop();
    }
}

class HomeAdapter extends  RecyclerView.Adapter<HomeAdapter.ViewHolder>{



    private List<HomeData> mDataset;

    private Context context;


    public static class ViewHolder extends  RecyclerView.ViewHolder{


        public View game_view;
        private TextView teamName,date,location,title,state_text;
        private ImageView teamLogo,state;
        private TextView sport;



        public ViewHolder(View v){

            super(v);
            this.game_view=v;
            teamName    =v.findViewById(R.id.game_view_team);
            date        =v.findViewById(R.id.game_view_date);
            location    =v.findViewById(R.id.game_view_location);
            title       =v.findViewById(R.id.game_view_title);
            sport       =v.findViewById(R.id.game_view_sport);
            teamLogo    =v.findViewById(R.id.game_view_image);


            state       =v.findViewById(R.id.game_view_state_img);
            state_text =v.findViewById(R.id.game_view_state_text );
        }


    }

    public HomeAdapter(Context context){
        this.context=context;
        mDataset=new ArrayList<>();
    }
    public HomeAdapter(List HomeDataSet){

        mDataset=HomeDataSet;
    }

    public HomeAdapter(Context context,List HomeDataSet){
        this.context=context;
        mDataset=HomeDataSet;
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
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        // create a new view


        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_view, parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {



        HomeData datum=mDataset.get(position);

        holder.teamName.setText(datum.getTmName());
        holder.title.setText(datum.getGmTitle());
        holder.date.setText(datum.getGmDate());
        holder.location.setText(datum.getGmGym());
        holder.sport.setText(datum.getTmSport());
        RequestOptions options=new RequestOptions();

        if(datum.getGmState().equals("0"))//성사대기
        {
            holder.state.setImageDrawable(context.getDrawable(R.drawable.wait));
            holder.state_text.setText("요청가능");

        }else{
            holder.state.setImageDrawable(context.getDrawable(R.drawable.check));
            holder.state_text.setText("성사완료");
        }
        Glide.with(context)

                .load(APIUrl.API_BASE_URL+datum.getTmImg())
                .apply(options.centerCrop())
                .into(holder.teamLogo);

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