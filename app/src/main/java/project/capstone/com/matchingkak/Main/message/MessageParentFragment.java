package project.capstone.com.matchingkak.Main.message;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import project.capstone.com.matchingkak.Main.message.adapter.MessageMainTabAdapter;
import project.capstone.com.matchingkak.R;

/**
 * Created by amco1 on 2018-03-30.
 */

public class MessageParentFragment extends Fragment {
    private static MessageParentFragment instance;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MessageMainTabAdapter pagerAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.message_main_content,container,false);
    }

    @Override
    public void onStart() {
        super.onStart();
        init();


    }
    void init(){

        viewPager=getView().findViewById(R.id.message_main_viewpager);
        tabLayout=getView().findViewById(R.id.message_main_tablayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addTab(tabLayout.newTab().setText("Received"));
        tabLayout.addTab(tabLayout.newTab().setText("Sent"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        pagerAdapter=new MessageMainTabAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

}
    public static MessageParentFragment newInstance(){

        if(instance==null){
            instance=new MessageParentFragment();
        }
        return instance;
    }
}
