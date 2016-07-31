package com.a92outlook.syedadil786.animals;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
public class MainActivity extends AppCompatActivity {

    int [] mResources = {
            R.drawable.darood1,
            R.drawable.darood2,
            R.drawable.darood3
    };

    MyFragmentAdapter mAdapter;
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT < 16) { //ye olde method
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else { // Jellybean and up, new hotness
            View decorView = getWindow().getDecorView();
            // Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            // Remember that you should never show the action bar if the
            // status bar is hidden, so hide that too if necessary.
            android.support.v7.app.ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.hide();
            }
        }



//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        mAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d("PositiononSelected", ""+position);


            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d("PositiononPage", ""+state);

            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    public class MyFragmentAdapter extends FragmentPagerAdapter {

        public MyFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        // This method returns the fragment associated with the specified position.
        //
        // It is called when the Adapter needs a fragment and it does not exists.
        public Fragment getItem(int position) {

            Log.d("Position Fragment ", ""+position);
            // Create fragment object
            Fragment fragment = new DataFragment();
            // Attach some data to it that we'll use to populate our fragment layouts
            Bundle args = new Bundle();
            args.putInt("page_position", position);

            // Set the arguments on the fragment
            // that will be fetched in DemoFragment@onCreateView
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public int getCount() {
            return 3;
        }
    }

    public class DataFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            // Inflate the layout resource that'll be returned
            View rootView = inflater.inflate(R.layout.fragmentdemo, container, false);

            // Get the arguments that was supplied when the fragment was instantiated in the CustomPagerAdapter
            Bundle args = getArguments();
            int position = args.getInt("page_position");
            ((ImageView) rootView.findViewById(R.id.image)).setImageResource(mResources[position]);
            return rootView;
        }
    }
}
