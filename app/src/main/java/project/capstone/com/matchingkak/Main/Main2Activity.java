package project.capstone.com.matchingkak.Main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.detail_info.DetailActivity;
import project.capstone.com.matchingkak.restAPI.APIUrl;
import project.capstone.com.matchingkak.sns_login.loginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    private RelativeLayout mDrawer;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    private ProgressBar mProgress;
    private Button login_btn,logout_btn,close_drawer_btn;
    private ViewPager pager;
    private  pagerAdapter pAdapter;
    private LinearLayout main_parent; SwipeRefreshLayout swipeRefreshLayout;
    NestedScrollView nestedScrollView;

    static int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mProgress=findViewById(R.id.main_progress);
        getData(page,false);
        init();



        //Toast.makeText(this,"Login state:"+ SharedPreferencesManager.getInstanceOf(this).getLoginState()+"",Toast.LENGTH_SHORT).show();




    }

    void getData(int page, final boolean isUpper){
        if(!isUpper) {
            mProgress.setVisibility(View.VISIBLE);
            mProgress.startNestedScroll(3);
        }

      MainListService.getRetrofit(this).getBanner()
                .enqueue(new Callback<List<String>>() {
                    @Override
                    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                        Log.d("Main2Activity",response.code()+"");
                        pAdapter.setList(response.body());
                    }

                    @Override
                    public void onFailure(Call<List<String>> call, Throwable t) {
                        Log.d("Main2Activity",t.toString());

                    }
                });


        MainListService.getRetrofit(this).paging(page)
                .enqueue(new Callback<ListData>() {
                    @Override
                    public void onResponse(Call<ListData> call, Response<ListData> response) {
                        if(response.isSuccessful()) {
                            if(isUpper){
                                mAdapter.setList(response.body().getData());
                                swipeRefreshLayout.setRefreshing(false);
                            }
                            else {
                                mAdapter.addList(response.body().getData());
                                mProgress.stopNestedScroll();
                                mProgress.setVisibility(View.GONE);
                            }



                        }

                    }

                    @Override
                    public void onFailure(Call<ListData> call, Throwable t) {

                        if(swipeRefreshLayout!=null)swipeRefreshLayout.setRefreshing(false);
                        Toast.makeText(Main2Activity.this, "failed to load", Toast.LENGTH_LONG).show();
                    }
                });

    }

   void init(){


       pAdapter=new pagerAdapter(this);
       pager =findViewById(R.id.main_view_pager);
       pager.setAdapter(pAdapter);
       swipeRefreshLayout=findViewById(R.id.main_swipe_refresh);
       swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
           @Override
           public void onRefresh() {
               page=1;
               getData(page,true);
           }
       });
       Toolbar toolbar =(Toolbar) findViewById(R.id.main_toolbar);
     //toolbar.setLogo(R.drawable.main);
       setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayShowTitleEnabled(false);

       final BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.main_bottom_nav);
       bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
              switch (item.getItemId()){

                  case R.id.b_nav_1:
                      break;
                  case R.id.b_nav_2:
                      break;
                  case R.id.b_nav_3:
                      break;
              }
              return true;
           }
       });



       close_drawer_btn=findViewById(R.id.drawer_close_btn);
       close_drawer_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mDrawerLayout.closeDrawer(mDrawer);
           }
       });



       mDrawer=findViewById(R.id.main_drawer);
       mDrawerLayout=findViewById(R.id.main_drawerLayout);


       main_parent= findViewById(R.id.main_parent);

       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
       getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
       ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.open,R.string.close);


       mDrawerLayout.addDrawerListener(toggle);
       logout_btn=findViewById(R.id.main_logout_btn);
       logout_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(final View v) {

               MainListService.getRetrofit(Main2Activity.this).logout().enqueue(new Callback<Boolean>() {
                   @Override
                   public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                       if(response.body()){

                           Intent intent=new Intent(Main2Activity.this,loginActivity.class);
                           startActivity(intent);
                           finish();

                       }
                   }

                   @Override
                   public void onFailure(Call<Boolean> call, Throwable t) {

                   }
               });

           }
       });


       mRecyclerView=findViewById(R.id.main_recyler);
       mRecyclerView.setNestedScrollingEnabled(false);
       mAdapter=new MyAdapter(this);

       this.mLayoutManager=new LinearLayoutManager(this);
       mLayoutManager.setAutoMeasureEnabled(true);
       mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(false);
mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
    @Override
    public void onItemClick(View view, int position) {
        Intent intent=new Intent(Main2Activity.this, DetailActivity.class);
        intent.putExtra("gm_no",mAdapter.getItem(position).getGmNo());
        startActivity(intent);
    }

    @Override
    public void onLongItemClick(View view, int position) {

    }
}));

       /*
mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        LinearLayoutManager linearManager=
                (LinearLayoutManager)recyclerView.getLayoutManager();

        int totalItemCount=linearManager.getItemCount();
        int lastVisibleItem=linearManager.findLastVisibleItemPosition();

        Log.d("onScroll","total"+totalItemCount+"::item"+lastVisibleItem);
        if(totalItemCount<=lastVisibleItem){
            if(!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN))
                getData(++page,false);


        }
    }
});

*/

        nestedScrollView=findViewById(R.id.main_nested);

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


}


 class MyAdapter extends  RecyclerView.Adapter<MyAdapter.ViewHolder>{



    private List<Datum> mDataset;

     private Context context;


     public static class ViewHolder extends  RecyclerView.ViewHolder{


        public View game_view;
        private TextView teamName,date,location,title,state_text;
        private ImageView teamLogo,state;
         private TextView sport;



        public ViewHolder(View v){

            super(v);
            this.game_view=v;
            teamName    =(TextView)v.findViewById(R.id.game_view_team);
            date        =(TextView)v.findViewById(R.id.game_view_date);
            location    =(TextView)v.findViewById(R.id.game_view_location);
            title       =(TextView)v.findViewById(R.id.game_view_title);
            sport       =(TextView)v.findViewById(R.id.game_view_sport);
            teamLogo    =(ImageView)v.findViewById(R.id.game_view_image);


            state       =(ImageView)v.findViewById(R.id.game_view_state_img);
            state_text =(TextView)v.findViewById(R.id.game_view_state_text );
        }


    }

    public MyAdapter(Context context){
        this.context=context;
        mDataset=new ArrayList<>();
    }
    public MyAdapter(List myDataSet){

        mDataset=myDataSet;
    }

     public MyAdapter(Context context,List myDataSet){
            this.context=context;
         mDataset=myDataSet;
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
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        // create a new view


        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_view, parent, false);


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {



        Datum datum=mDataset.get(position);

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

    public Datum getItem(int position){

        return mDataset.get(position );
    }


}


