package project.capstone.com.matchingkak.Main.me;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import project.capstone.com.matchingkak.Main.me.adapter.meAdapter;
import project.capstone.com.matchingkak.Main.me.presenter.mePresenter;
import project.capstone.com.matchingkak.MainActivity;
import project.capstone.com.matchingkak.R;


public class meFragment extends Fragment implements meContract.View{

    private MainActivity.BackPressCloseSystem backPressCloseSystem;
    // TODO: Rename and change types of parameters

    private meAdapter requestAdater;
    private meAdapter postAdapter;
    private RecyclerView requestRecyclerView;
    private RecyclerView postRecyclerView;
    private meContract.Presenter presenter;
    private RecyclerView.LayoutManager mLayoutManager1;
    private RecyclerView.LayoutManager mLayoutManager2;

    public meFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static meFragment newInstance() {
        meFragment fragment = new meFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.me_content_temp, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        init();
        presenter.loadData();

    }
    void init(){

        ImageView edit=getView().findViewById(R.id.edit_image);
        ImageView profile_picture=getView().findViewById(R.id.me_picture);
        Glide.with(this).load(R.drawable.edit_profile).apply(RequestOptions.circleCropTransform()).into(edit);
        Glide.with(this ).load(R.drawable.main_icon2).apply(RequestOptions.circleCropTransform()).into(profile_picture);
        presenter=new mePresenter();
        presenter.attatchView(this);

        this.mLayoutManager1=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        mLayoutManager1.setAutoMeasureEnabled(true);
        requestRecyclerView=getView().findViewById(R.id.me_myrequest_recyclerview);
        requestRecyclerView.setLayoutManager(mLayoutManager1);
        requestAdater     =new meAdapter(getContext());
        requestRecyclerView.setAdapter(requestAdater);
        presenter.setAdapterModel(requestAdater,presenter.ME_REQUEST);
        presenter.setAdapterView(requestAdater,presenter.ME_REQUEST);

        mLayoutManager2=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        mLayoutManager2.setAutoMeasureEnabled(true);

        postRecyclerView=getView().findViewById(R.id.me_mypost_recyclerview);
        postRecyclerView.setLayoutManager(mLayoutManager2);
        postAdapter     =new meAdapter(getContext());
        postRecyclerView.setAdapter(postAdapter);
        presenter.setAdapterView(postAdapter,presenter.ME_MYPOST);
        presenter.setAdapterModel(postAdapter,presenter.ME_MYPOST);



    }
    @Override
    public void done(int resultCode, String[] input) {

    }



}
