package project.capstone.com.matchingkak.list_game;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.databinding.ActivityListGameBinding;
import project.capstone.com.matchingkak.list_game.adapter.CardItem;
import project.capstone.com.matchingkak.list_game.adapter.CardPagerAdapter;

public class ListGameActivity extends AppCompatActivity {
    ActivityListGameBinding binding;
    CardPagerAdapter mAdapter;
    ShadowTransformer transformer;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_list_game);
        init();



    }

    void init(){

        mAdapter=new CardPagerAdapter();
        mAdapter.addCardItem(new CardItem("1","Example1"));
        mAdapter.addCardItem(new CardItem("2","Example2"));
        mAdapter.addCardItem(new CardItem("3","Example3"));
        mAdapter.addCardItem(new CardItem("4","Example4"));
        mAdapter.addCardItem(new CardItem("5","Example5"));

      transformer=new ShadowTransformer(binding.listGameViewPager,mAdapter);
    transformer.enableScaling(true);
        binding.listGameViewPager.setAdapter(mAdapter);
       binding.listGameViewPager.setPageTransformer(false,transformer);
        binding.listGameViewPager.setOffscreenPageLimit(3);

    }
}
