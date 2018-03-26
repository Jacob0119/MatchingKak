package project.capstone.com.matchingkak.Main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import project.capstone.com.matchingkak.Main.Editor.EditorActivity;
import project.capstone.com.matchingkak.Main.alarm.alarmFragment;
import project.capstone.com.matchingkak.Main.home.HomeFragment;
import project.capstone.com.matchingkak.Main.me.meFragment;
import project.capstone.com.matchingkak.Main.message.MessageFragment;
import project.capstone.com.matchingkak.R;
import project.capstone.com.matchingkak.restAPI.APIUrl;

import static project.capstone.com.matchingkak.R.drawable.alarm;


public class Main2Activity extends AppCompatActivity {

    private CustomViewPager mViewPager;
    private ViewPagerAdapter mAdapter;
    private BottomNavigationView navigationView;
    Fragment[] frag=new Fragment[5];

    private int selectedTab=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        init();


        if (savedInstanceState == null) {

            mViewPager.setCurrentItem(selectedTab);
        }


    }

    private void loadFragment(int i) {

        if(frag[i]==null)
            frag[i] = getFrament(i);

        FragmentTransaction ft=getFragmentManager().beginTransaction();

        ft.replace(R.id.main_view_pager, frag[i]);
        ft.commit();
    }

    Fragment getFrament(int i){

        switch (i){
            case 0:return HomeFragment.newInstance();
            case 1: return HomeFragment.newInstance();
            case 2:return meFragment.newInstance();
            case 3:return MessageFragment.newInstance();
            case 4:return HomeFragment.newInstance();
            default:return HomeFragment.newInstance();
        }

    }
   void init(){








        mViewPager =  findViewById(R.id.main_view_pager);
       mViewPager.setOnTouchListener(new View.OnTouchListener() {
           @Override
           public boolean onTouch(View v, MotionEvent event) {
               return false;
           }
       });
        mAdapter = new ViewPagerAdapter (this.getFragmentManager());
       mAdapter.addFragment(HomeFragment.newInstance(), "home");
       mAdapter.addFragment(MessageFragment.newInstance(), "message");
       mAdapter.addFragment(meFragment.newInstance(), "me");
       mAdapter.addFragment(alarmFragment.newInstance(), "alarm");

       mViewPager.setAdapter(mAdapter);
       mViewPager.setOffscreenPageLimit(mAdapter.getCount() - 1);


       final BottomNavigationBar bottomNavigationView=findViewById(R.id.main_bottom_nav);

        bottomNavigationView.addItem(new BottomNavigationItem(R.drawable.home,"홈"))
                .addItem(new BottomNavigationItem(R.drawable.mail,"쪽지"))
                .addItem(new BottomNavigationItem(R.drawable.me,"나"))
                .addItem(new BottomNavigationItem(alarm,"알림"))
                .addItem(new BottomNavigationItem(R.drawable.edit,"글쓰기"))
                .initialise();

     bottomNavigationView.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
         @Override
         public void onTabSelected(int i) {
                  if(i!=4) {
                      mViewPager.setCurrentItem(i, false);
                        selectedTab=i;
                  }
                  else {
                      Intent intent = new Intent(getApplicationContext(), EditorActivity.class);
                      intent.putExtra("url", APIUrl.API_BASE_URL+APIUrl.EDITOR_URL);
                      startActivity(intent);
                      bottomNavigationView.selectTab(selectedTab);
                  }
         }

         @Override
         public void onTabUnselected(int i) {

         }

         @Override
         public void onTabReselected(int i) {
            onTabSelected(i);
         }
     });
/*
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
*/







     //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
     //   getSupportActionBar().setHomeButtonEnabled(true);
     //  getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);








   }
    private class ViewPagerAdapter extends FragmentPagerAdapter {
        private final SparseArray<WeakReference<Fragment>> instantiatedFragments = new SparseArray<>();
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            final Fragment fragment = (Fragment) super.instantiateItem(container, position);
            instantiatedFragments.put(position, new WeakReference<>(fragment));
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            instantiatedFragments.remove(position);
            super.destroyItem(container, position, object);
        }

        @Nullable
        Fragment getFragment(final int position) {
            final WeakReference<Fragment> wr = instantiatedFragments.get(position);
            if (wr != null) {
                return wr.get();
            } else {
                return null;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}


class CustomViewPager extends ViewPager {

    private boolean isPagingEnabled;

    public CustomViewPager(Context context) {
        super(context);
        this.isPagingEnabled = true;
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.isPagingEnabled = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    //for samsung phones to prevent tab switching keys to show on keyboard
    @Override
    public boolean executeKeyEvent(KeyEvent event) {
        return isPagingEnabled && super.executeKeyEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event);
    }

    public void setPagingEnabled(boolean enabled) {
        this.isPagingEnabled = enabled;
    }
}


