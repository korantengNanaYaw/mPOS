package com.hubtel.mpos.Activities;

import android.content.Context;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hubtel.mpos.POSFRAGMENT;
import com.hubtel.mpos.R;
import com.hubtel.mpos.Typefacer;
import com.hubtel.mpos.WalletFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabSelectedListener;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class MainActivity extends AppCompatActivity implements MaterialTabListener,
        POSFRAGMENT.OnFragmentInteractionListener,WalletFragment.OnFragmentInteractionListener, View.OnClickListener {
    CoordinatorLayout coordinatorLayout;
    Toolbar toolbar;
    TextView toolbarTitle;
    Typefacer typefacer;
    RelativeLayout relativelayout;
    ViewPager pager;
    private TabLayout tabLayout;
    ViewPagerAdapter pagerAdapter;
    MaterialTabHost tabHost;
    NestedScrollView nestedscroll;
    LayoutInflater inflater;
     View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         inflater = (LayoutInflater)MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        typefacer=new Typefacer();
        setToolBar("mPos");
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.coordinate);
        setBottomNavigation(savedInstanceState);
        setViewHolder();
        inflateLayout();
        setTabHost();




    }

    private void setViewHolder() {

        nestedscroll=(NestedScrollView)findViewById(R.id.myScrollingContent) ;


    }



    /**private void setTabHost(){

        pager = (ViewPager) this.findViewById(R.id.pager);
        setupViewPager(pager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new POSFRAGMENT(), "POS");
        adapter.addFragment(new WalletFragment(), "Wallet");

        viewPager.setAdapter(adapter);
    }
**/

    private void setTabHost() {

        tabHost = (MaterialTabHost) this.findViewById(R.id.materialTabHost);
        pager = (ViewPager) this.findViewById(R.id.pager);
        // init view pager
        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);

            }
        });

        // insert all tabs from pagerAdapter data
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(pagerAdapter.getPageTitle(i))
                            .setTabListener(this)
            );

        }
    }

    private void inflateLayout(){

      //  View view;



       try{

           view=null;
           view = inflater.inflate(R.layout.payment_layout, null);
           nestedscroll.addView(view);
           //setTabHost();

       }catch (Exception ex){


           ex.printStackTrace();
       }


    }
    private void setBottomNavigation( Bundle savedInstanceState){
        BottomBar bottomBar = BottomBar.attachShy((CoordinatorLayout) findViewById(R.id.coordinate),
                findViewById(R.id.myScrollingContent), savedInstanceState);

       // bottomBar.setBackgroundColor(R.color.colorAccent);
        //BottomBar bottomBar = BottomBar.attach(this, savedInstanceState);
        bottomBar.setItemsFromMenu(R.menu.three_buttons_menu, new OnMenuTabSelectedListener() {
            @Override
            public void onMenuItemSelected(int itemId) {
                switch (itemId) {
                    case R.id.payment:

                        inflateLayout();
                        setTabHost();
                        // Snackbar.make(coordinatorLayout, "Recent Item Selected", Snackbar.LENGTH_LONG).show();
                        break;
                    case R.id.history:
                        nestedscroll.removeAllViewsInLayout();
                        break;
                    case R.id.setting:
                        nestedscroll.removeAllViewsInLayout();
                        // Snackbar.make(coordinatorLayout, "Location Item Selected", Snackbar.LENGTH_LONG).show();
                        break;


                }
            }
        });
        bottomBar.setDefaultTabPosition(0);
        bottomBar.mapColorForTab(0, "#7B1FA2");
        bottomBar.mapColorForTab(1, "#FF5252");
        bottomBar.mapColorForTab(2, "#FF9800");
        //  bottomBar.setActiveTabColor("#C2185B");
    }
    private void setToolBar(String title){
        toolbar=(Toolbar)findViewById(R.id.app_bar);
        toolbarTitle= (TextView) findViewById(R.id.toolbarTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbarTitle.setText(title);
        toolbarTitle.setTypeface(typefacer.squareLight());
        TextView toolbarnotify=(TextView)findViewById(R.id.toolbarnotify);
        toolbarnotify.setVisibility(View.GONE);


    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View v) {

    }

    /**
     * class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
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

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    **/
    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private String[] titles = {
                "KeyPad", "MPOS"
        };

        Fragment fragment=null;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        public Fragment getItem(int num) {



          if(num==0)
            {

                // actionButton.setVisibility(View.GONE);
                fragment=new POSFRAGMENT();


            } if(num==1)
            {
               // Button checkoutBtn = (Button)findViewById(R.id.checkoutbtn);
            //    checkoutBtn.setVisibility(View.INVISIBLE);
                // actionButton.setVisibility(View.GONE);
                fragment=new WalletFragment();
                // actionButton.setVisibility(View.GONE);
                // fragment=new MiddleFragment();
                //  new connectOptician().execute();

            }
            return fragment;




        }



        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

    }


    @Override
    public void onTabSelected(MaterialTab tab) {
        pager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }
}
