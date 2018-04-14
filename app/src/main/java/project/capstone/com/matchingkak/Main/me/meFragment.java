package project.capstone.com.matchingkak.Main.me;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import project.capstone.com.matchingkak.ActivityStarterManager;
import project.capstone.com.matchingkak.Main.me.adapter.meAdapter;
import project.capstone.com.matchingkak.Main.me.data.InfoData;
import project.capstone.com.matchingkak.Main.me.presenter.mePresenter;
import project.capstone.com.matchingkak.MainActivity;
import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.databinding.MeContentBinding;
import project.capstone.com.matchingkak.databinding.MeUpperPartBinding;
import project.capstone.com.matchingkak.restAPI.APIUrl;


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
    private MeContentBinding binding;
    private InfoData info;
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
        binding= DataBindingUtil.inflate(inflater,R.layout.me_content,container,false);

        return binding.getRoot();

    }

    @Override
    public void onStart() {
        super.onStart();
        init();
        presenter.loadData();

    }
    void init(){

        ImageView edit=getView().findViewById(R.id.me_edit_image);
        ImageView profile_picture=getView().findViewById(R.id.mypage_picture);
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

        View v=getView().findViewById(R.id.me_edit_image);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityStarterManager.StartWebActivity(getContext(),APIUrl.API_BASE_URL+APIUrl.POFILE_URL,"프로필 수정");
            }
        });

    }
    @Override
    public void done(int resultCode, String[] input) {

    }


    @Override
    public void updateInfo(InfoData info) {
        MeUpperPartBinding upper=binding.constraintLayout2;

        ImageView profile_pic=upper.mypagePicture;
        Glide.with(this).load(APIUrl.API_BASE_URL+info.getTmImg())
                .apply(RequestOptions.circleCropTransform())
                .into(profile_pic);
        upper.mypageTeamname.setText(info.getTmName());
        upper.mypageUsername.setText(info.getMbNick());
        upper.mypagePenaltyCount.setText(info.getTmPenalty());
        upper.mypageMatchingCount.setText(info.getTmMatchCount());

    }
}
