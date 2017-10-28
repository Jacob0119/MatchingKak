package project.capstone.com.matchingkak.Main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


import java.util.ArrayList;
import java.util.List;

import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.restAPI.APIUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;
    private LinearLayout mDrawer;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    ArrayList<Datum> list=new ArrayList<Datum>();
    static int page=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getData(page);
        init();



    }

    void getData(int page){

        MainListService.getRetrofit(this).paging(page)
                .enqueue(new Callback<ListData>() {
                    @Override
                    public void onResponse(Call<ListData> call, Response<ListData> response) {
                        if(response.isSuccessful()) {
                            Toast.makeText(Main2Activity.this, response.body().getResponse(), Toast.LENGTH_LONG).show();
                            list.addAll(response.body().getData());
                            mAdapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onFailure(Call<ListData> call, Throwable t) {
                        Toast.makeText(Main2Activity.this, "failed to load", Toast.LENGTH_LONG).show();
                    }
                });

    }

   void init(){


       Toolbar toolbar =(Toolbar) findViewById(R.id.main_toolbar);
     toolbar.setLogo(R.drawable.main);
       setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayShowTitleEnabled(false);


       mDrawer=(LinearLayout)findViewById(R.id.main_drawer);
       mDrawerLayout=(DrawerLayout)findViewById(R.id.main_drawerLayout);
       mDrawerLayout.closeDrawer(mDrawer);

       mRecyclerView=(RecyclerView )findViewById(R.id.main_recyler);
       mAdapter=new MyAdapter(this,list);
       this.mLayoutManager=new LinearLayoutManager(this);
       mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);





   }


}


 class MyAdapter extends  RecyclerView.Adapter<MyAdapter.ViewHolder>{



    private List<Datum> mDataset;

     private Context context;


     public static class ViewHolder extends  RecyclerView.ViewHolder{


        public View game_view;
        private TextView teamName,date,location,title;
        private ImageView teamLogo,state;




        public ViewHolder(View v){

            super(v);
            this.game_view=v;
            teamName    =(TextView)v.findViewById(R.id.game_view_team);
            date        =(TextView)v.findViewById(R.id.game_view_date);
            location    =(TextView)v.findViewById(R.id.game_view_location);
            title       =(TextView)v.findViewById(R.id.game_view_title);

            teamLogo    =(ImageView)v.findViewById(R.id.game_view_image);


            state       =(ImageView)v.findViewById(R.id.game_view_state);

        }


    }

    public MyAdapter(List myDataSet){

        mDataset=myDataSet;
    }

     public MyAdapter(Context context,List myDataSet){
            this.context=context;
         mDataset=myDataSet;
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

        RequestOptions options=new RequestOptions();

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



}

public class RecyclerViewOnItemClickListener extends RecyclerView.SimpleOnItemTouchListener {





}

