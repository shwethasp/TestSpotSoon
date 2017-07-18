package com.test.shwetha.testspotsoon.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.test.shwetha.testspotsoon.R;
import com.test.shwetha.testspotsoon.adapters.PagerAdapters;
import com.test.shwetha.testspotsoon.fragment.ImagesFragment;
import com.test.shwetha.testspotsoon.fragment.MileStoneFragment;
import com.test.shwetha.testspotsoon.fragment.VideosFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    int page = 0;
    DrawerLayout drawer;
    Toolbar mToolbar;
    PagerAdapters adapter;
    //Tab view Pager
    ViewPager.OnPageChangeListener tabViewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            Log.d("Posi pageSele", "" + position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private ViewPager mViewPager;
    private MyTopViewPagerAdapter mTopViewPagerAdapter;
    private LinearLayout mLinearDotsLayout;
    private TextView[] mDots;
    private int[] mLayouts;
    //  top viewpager change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            page = position;
            addBottomDots(position);

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };
    private Handler handler;
    private int delay = 5000; //milliseconds
    Runnable runnable = new Runnable() {
        public void run() {
            if (mTopViewPagerAdapter.getCount() == page) {
                page = 0;
            } else {
                page++;
                overridePendingTransition(R.anim.activity_slide_left_in,
                        R.anim.activity_slide_left_out);
            }
            mViewPager.setCurrentItem(page, true);
            handler.postDelayed(this, delay);
        }
    };
    //This is our tablayout
    private TabLayout mTabLayout;
    //This is our view pager
    private ViewPager mTabViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        setContentView(R.layout.activity_main);
        initializeUI();


    }

    private void initializeUI() {
         mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setTitle(getResources().getString(R.string.home));

        //navigation drawer initialization
         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        //Top View Pager
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mLinearDotsLayout = (LinearLayout) findViewById(R.id.layoutDots);

        //Initializing the tab layout
        mTabViewPager = (ViewPager) findViewById(R.id.tab_viewpager);
        setupViewPager(mTabViewPager);

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        //setting tab adapter to its pager

    }

    @Override
    protected void onStart() {
        super.onStart();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mLayouts = new int[]{
                R.layout.top_slide1,
                R.layout.top_slide2,
                R.layout.top_slide3};

        // adding bottom dots
        addBottomDots(0);
        mTopViewPagerAdapter = new MyTopViewPagerAdapter();
        mViewPager.setAdapter(mTopViewPagerAdapter);
        mViewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        mTabLayout.setupWithViewPager(mTabViewPager);
        setupTabIcons();
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mTabViewPager.setCurrentItem(tab.getPosition());
                View view = tab.getCustomView();
                TextView text = (TextView) view.findViewById(R.id.tab_text_view);
                ImageView imageView = (ImageView) view.findViewById(R.id.tab_image_view);
                text.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                switch (tab.getPosition()) {
                    case 0:
                        imageView.setImageResource(R.mipmap.select_video);
                        break;

                    case 1:
                        imageView.setImageResource(R.mipmap.select_image);
                        break;

                    case 2:
                        imageView.setImageResource(R.mipmap.select_milestone);
                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view = tab.getCustomView();
                TextView text = (TextView) view.findViewById(R.id.tab_text_view);
                ImageView imageView = (ImageView) view.findViewById(R.id.tab_image_view);
                text.setTextColor(getResources().getColor(android.R.color.darker_gray));
                switch (tab.getPosition()) {
                    case 0:
                        imageView.setImageResource(R.mipmap.video);
                        break;

                    case 1:
                        imageView.setImageResource(R.mipmap.image);
                        break;

                    case 2:
                        imageView.setImageResource(R.mipmap.milestone);
                        break;
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mTabViewPager.addOnPageChangeListener(tabViewPagerPageChangeListener);

    }

    //adding bottom dots to the top slider
    private void addBottomDots(int currentPage) {
        mDots = new TextView[mLayouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        mLinearDotsLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(colorsInactive[currentPage]);
            mLinearDotsLayout.addView(mDots[i]);
        }

        if (mDots.length > 0)
            mDots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }

    private void setupTabIcons() {
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.custom_tab, null);
        TextView text = (TextView) view.findViewById(R.id.tab_text_view);
        ImageView imageView = (ImageView) view.findViewById(R.id.tab_image_view);
        text.setText("VIDEOS");
        text.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        imageView.setImageResource(R.mipmap.select_video);
        mTabLayout.getTabAt(0).setCustomView(view);

        view = inflater.inflate(R.layout.custom_tab, null);
        text = (TextView) view.findViewById(R.id.tab_text_view);
        imageView = (ImageView) view.findViewById(R.id.tab_image_view);
        text.setText("IMAGES");
        text.setTextColor(getResources().getColor(android.R.color.darker_gray));
        imageView.setImageResource(R.mipmap.image);
        mTabLayout.getTabAt(1).setCustomView(view);

        view = inflater.inflate(R.layout.custom_tab, null);
        text = (TextView) view.findViewById(R.id.tab_text_view);
        imageView = (ImageView) view.findViewById(R.id.tab_image_view);
        text.setText("MILESTONE");
        text.setTextColor(getResources().getColor(android.R.color.darker_gray));
        imageView.setImageResource(R.mipmap.milestone);
        mTabLayout.getTabAt(2).setCustomView(view);

    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new PagerAdapters(getSupportFragmentManager());
        adapter.addFrag(new VideosFragment(), "VIDEOS");
        adapter.addFrag(new ImagesFragment(), "IMAGES");
        adapter.addFrag(new MileStoneFragment(), "MILESTONE");
        viewPager.setAdapter(adapter);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_milestone) {
            Toast.makeText(getApplicationContext(), "You clicked on MileStone", Toast.LENGTH_SHORT).show();
            // Handle the camera action
        } else if (id == R.id.nav_aboutus) {
            Toast.makeText(getApplicationContext(), "You clicked on About us", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, delay);
    }

    /**
     * Top View pager adapter
     */
    public class MyTopViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyTopViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(mLayouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return mLayouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

}
