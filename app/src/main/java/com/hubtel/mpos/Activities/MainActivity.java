package com.hubtel.mpos.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hubtel.mpos.Fragments.ListDialogFragment;
import com.hubtel.mpos.Fragments.POSFRAGMENT;
import com.hubtel.mpos.Fragments.POSFRAGMENT.POSFRAGMENTOnFragmentInteractionListener;
import com.hubtel.mpos.Fragments.WalletFragment.OnFragmentInteractionListener;
import com.hubtel.mpos.R;
import com.hubtel.mpos.Utilities.Typefacer;
import com.hubtel.mpos.Fragments.WalletFragment;
import com.hubtel.mpos.Utilities.Utility;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnMenuTabSelectedListener;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import io.mpos.Mpos;



import io.mpos.accessories.AccessoryFamily;
import io.mpos.accessories.parameters.AccessoryParameters;
import io.mpos.paymentdetails.ApplicationInformation;
import io.mpos.provider.ProviderMode;
import io.mpos.transactionprovider.TransactionProcess;
import io.mpos.transactionprovider.TransactionProcessDetails;
import io.mpos.transactionprovider.TransactionProcessDetailsState;
import io.mpos.transactionprovider.TransactionProcessWithRegistrationListener;
import io.mpos.transactionprovider.TransactionProvider;
import io.mpos.transactionprovider.processparameters.TransactionProcessParameters;
import io.mpos.transactions.Currency;
import io.mpos.transactions.Transaction;
import io.mpos.transactions.parameters.TransactionParameters;
import io.mpos.transactions.receipts.Receipt;
import io.mpos.ui.shared.MposUi;
import io.mpos.ui.shared.model.MposUiConfiguration;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

import static com.hubtel.mpos.Fragments.ListDialogFragment.*;

public class MainActivity extends
        AppCompatActivity implements MaterialTabListener,
        POSFRAGMENTOnFragmentInteractionListener,
        OnFragmentInteractionListener,
        ListDialogFragmentOnFragmentInteractionListener,
        View.OnClickListener {

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


    private final static String MERCHANT_ID="0b74f7bc-ddd3-4150-b4d4-f9fb32f18fe9";
            private final static String MERCHANT_SECRET="QqAvgo6nVmwLc66feF2osg9ppilSNOWI";
     View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         inflater = (LayoutInflater)MainActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        typefacer=new Typefacer();
        setToolBar("MPower POS");
        coordinatorLayout=(CoordinatorLayout)findViewById(R.id.coordinate);
        setBottomNavigation(savedInstanceState);
        setViewHolder();
        inflateLayout();
        setTabHost();




    }
    void initMockPaymentController() {


        MposUi mposUi = MposUi.initialize(MainActivity.this, ProviderMode.TEST,MERCHANT_ID,MERCHANT_SECRET);
       // AccessoryParameters mockAccessoryParameters = new AccessoryParameters.Builder(AccessoryFamily.BBPOS_WISE).bluetooth().build();

        AccessoryParameters mockAccessoryParameters = new AccessoryParameters.Builder(AccessoryFamily.MIURA_MPI).bluetooth().build();


       /** AccessoryParameters parameters = new AccessoryParameters.Builder(AccessoryFamily.BBPOS_WISEPAD)
                .bluetooth()
                .build();
        ***/

        mposUi.getConfiguration().setTerminalParameters(mockAccessoryParameters);
       // mposUi.getConfiguration().setAccessoryFamily(AccessoryFamily.BBPOS_WISEPAD);
        mposUi.getConfiguration().setSummaryFeatures(EnumSet.allOf(MposUiConfiguration.SummaryFeature.class));
    }
    private void setViewHolder() {

        nestedscroll=(NestedScrollView)findViewById(R.id.myScrollingContent) ;


    }

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
        //   startAnim();
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
                       // startAnim();
                        inflateLayout();
                        setTabHost();
                      // stopAnim();
                        // Snackbar.make(coordinatorLayout, "Recent Item Selected", Snackbar.LENGTH_LONG).show();
                        break;
                    case R.id.history:
                        nestedscroll.removeAllViews();
                        break;
                    case R.id.setting:
                        nestedscroll.removeAllViews();
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

    void showDialog(DialogFragment dialogFragment, String tag) {


        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag(tag);
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        dialogFragment.show(ft, tag);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void POSFRAGMENTonFragmentInteraction(String mode,String uri) {



        switch (mode){

            case "add2cart":

                addItemsToCart(uri);

                break;
            case "checkout":

                ListDialogFragment listDialog=new ListDialogFragment().newInstance(uri,"");
                showDialog(listDialog,"checkout");


                break;

        }



    }

    private void addItemsToCart(String uri) {

        try{
            double val1; double amount;
            String toolbarString;
            TextView toolbarnotify=(TextView)findViewById(R.id.toolbarnotify);

            toolbarnotify.setVisibility(View.VISIBLE);
            toolbarnotify.setTypeface(typefacer.squareLight());
            toolbarString=toolbarnotify.getText().toString();

            if(toolbarString.isEmpty()) {
                val1 = 0;
            }else {
                String trimmedString = toolbarString.replace("Ghs", "").toString();
                String string4Double=Utility.prepareString4double(trimmedString);
                val1 = Double.parseDouble(string4Double);

            }

            amount=Double.valueOf(uri);

            double total=val1+amount;

            String formattedAmount= Utility.formatMoney(total);

            toolbarnotify.setText(formattedAmount);

        }catch (Exception e){

            e.printStackTrace();

        }
    }

    @Override
    public void ListDialogFragmentonFragmentInteraction(String uri) {


        try{


       /**     initMockPaymentController();


            //
            MposUi.getInitializedInstance().getConfiguration().getAppearance()
                    .setColorPrimary(Color.parseColor("#3F51B5"))
                    .setColorPrimaryDark(Color.parseColor("#303F9F"))
                    .setBackgroundColor(Color.parseColor("#FFFFFF"))
                    .setTextColorPrimary(Color.WHITE);


            //MIURA When i want to receive a TIP
            TransactionProcessParameters processParameters = new TransactionProcessParameters.Builder()
                    .addAskForTipStep()
                    .build();

            String trim=uri.replace("Ghs","");
            String ur=Utility.prepareString4double(trim);
            startPayment(Double.valueOf(ur), true, null);**/
            hitTransaction();

        }catch (Exception e){



            e.printStackTrace();
        }

    }




    void hitTransaction(){

        final TransactionProvider transactionProvider = Mpos.createTransactionProvider(this,
                ProviderMode.TEST,
                MERCHANT_ID,
                MERCHANT_SECRET);


// When using the Bluetooth Miura Shuttle / M007 / M010, use the following parameters:
    AccessoryParameters accessoryParameters = new AccessoryParameters.Builder(AccessoryFamily.MIURA_MPI)
                                                                     .bluetooth()
                                                                     .build();


        TransactionParameters transactionParameters = new TransactionParameters.Builder()
                .charge(new BigDecimal("5.00"), io.mpos.transactions.Currency.EUR)
                .subject("Bouquet of Flowers")
                .customIdentifier("yourReferenceForTheTransaction")
                .build();




        TransactionProcess paymentProcess = transactionProvider.startTransaction(transactionParameters, accessoryParameters,
                new TransactionProcessWithRegistrationListener() {
                    @Override
                    public void onCompleted(TransactionProcess transactionProcess, Transaction transaction, TransactionProcessDetails transactionProcessDetails) {
                        Log.d("mpos", "completed");

                        if (transactionProcessDetails.getState() == TransactionProcessDetailsState.APPROVED) {
                            // print the merchant receipt
                            Receipt merchantReceipt = transaction.getMerchantReceipt();

                            // print a signature line if required
                            if(merchantReceipt.isSignatureLineRequired()) {
                                System.out.println("");
                                System.out.println("");
                                System.out.println("");
                                System.out.println("------ PLEASE SIGN HERE ------");
                            }

                            // ask the merchant, whether the shopper wants to have a receipt
                            Receipt customerReceipt = transaction.getCustomerReceipt();

                            // and close the checkout UI
                        } else {
                            // Allow your merchant to try another transaction
                        }
                    }

                    @Override
                    public void onStatusChanged(TransactionProcess transactionProcess, Transaction transaction, TransactionProcessDetails transactionProcessDetails) {
                        Log.d("mpos", "status changed: " + Arrays.toString(transactionProcessDetails.getInformation()));

                    }

                    @Override
                    public void onCustomerSignatureRequired(TransactionProcess transactionProcess, Transaction transaction) {

                    }

                    @Override
                    public void onCustomerVerificationRequired(TransactionProcess transactionProcess, Transaction transaction) {
                       // always return false here
                        transactionProcess.continueWithCustomerIdentityVerified(false);
                    }

                    @Override
                    public void onApplicationSelectionRequired(TransactionProcess transactionProcess, Transaction transaction, List<ApplicationInformation> list) {

                    }

                    @Override
                    public void onRegistered(TransactionProcess transactionProcess, Transaction transaction) {
                        Log.d("mpos", "transaction identifier is: " + transaction.getIdentifier() + ". Store it in your backend so that you can always query its status.");
                    }
                });

    }




    void startPayment(double amount, boolean autoCapture, TransactionProcessParameters processParameters) {


        try{

            TransactionParameters params = new TransactionParameters.Builder()
                    .charge(BigDecimal.valueOf(amount),Currency.USD)
                    .subject("How much wood would a woodchuck chuck if a woodchuck could chuck wood?")
                    .customIdentifier("customId")
                    .autoCapture(autoCapture)
                    .build();
            Intent intent = MposUi.getInitializedInstance().createTransactionIntent(params, processParameters);
            startActivityForResult(intent, MposUi.RESULT_CODE_APPROVED);
        }catch (Exception e){

            e.printStackTrace();
        }







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
                "MPOS", "kEYPAD"
        };

        Fragment fragment=null;

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        public Fragment getItem(int num) {



          if(num==0)
            {



                fragment=new WalletFragment();
            } if(num==1)
            {

                fragment=new POSFRAGMENT();



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
