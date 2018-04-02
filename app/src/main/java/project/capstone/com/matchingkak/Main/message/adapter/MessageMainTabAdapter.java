package project.capstone.com.matchingkak.Main.message.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.annotation.Nullable;
import android.support.v13.app.FragmentStatePagerAdapter;

import project.capstone.com.matchingkak.Main.message.MessageFragment;

/**
 * Created by amco1 on 2018-03-30.
 */

public class MessageMainTabAdapter extends FragmentStatePagerAdapter {
    private static int TAB_COUNT=2;
    private String[] title={"received","sent"};
    public MessageMainTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
               return MessageFragment.newInstance();

            case  1:
                return MessageFragment.newInstance();


        }
        return MessageFragment.newInstance();
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
