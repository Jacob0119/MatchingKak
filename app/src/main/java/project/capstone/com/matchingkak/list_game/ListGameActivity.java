package project.capstone.com.matchingkak.list_game;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.HashMap;

import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.config;
import project.capstone.com.matchingkak.databinding.ActivityListGameBinding;
import project.capstone.com.matchingkak.list_game.adapter.CardPagerAdapter;
import project.capstone.com.matchingkak.list_game.presenter.ListGamePresenter;

public class ListGameActivity extends AppCompatActivity implements ListGameContract.View {
    ActivityListGameBinding binding;
    CardPagerAdapter mAdapter;
    ShadowTransformer transformer;
    ListGameContract.Presenter presenter;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_list_game);
        init();



    }

    void init(){


        android.support.v7.widget.Toolbar toolbar=findViewById(R.id.list_game_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mAdapter=new CardPagerAdapter();
        presenter=new ListGamePresenter();




        transformer=new ShadowTransformer(binding,mAdapter,presenter);
        binding.listGameViewPager.setAdapter(mAdapter);
        binding.listGameViewPager.setPageTransformer(false,transformer);
        binding.listGameViewPager.setOffscreenPageLimit(1);


        presenter.attatchView(this);
        presenter.setAdapterModel(mAdapter);
        presenter.setAdapterView(mAdapter);
        presenter.setParameterMap((HashMap)getIntent().getSerializableExtra(config.LIST_GAME_MAP));
        presenter.loadData();




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void done(int resultCode, String[] input) {

    }
}
