package com.hubtel.mpos.Activities;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hubtel.mpos.Models.Sale;
import com.hubtel.mpos.R;
import com.hubtel.mpos.Utilities.Typefacer;

import java.math.BigDecimal;
import java.util.List;

import info.hoang8f.widget.FButton;
import io.mpos.Mpos;
import io.mpos.accessories.AccessoryFamily;
import io.mpos.accessories.parameters.AccessoryParameters;
import io.mpos.paymentdetails.ApplicationInformation;
import io.mpos.provider.ProviderMode;
import io.mpos.transactionprovider.TransactionProcess;
import io.mpos.transactionprovider.TransactionProcessDetails;
import io.mpos.transactionprovider.TransactionProcessDetailsState;
import io.mpos.transactionprovider.TransactionProcessDetailsStateDetails;
import io.mpos.transactionprovider.TransactionProcessWithRegistrationListener;
import io.mpos.transactionprovider.TransactionProvider;
import io.mpos.transactions.Currency;
import io.mpos.transactions.Transaction;
import io.mpos.transactions.parameters.TransactionParameters;
import io.mpos.transactions.receipts.Receipt;

public class CardCheckOut extends AppCompatActivity {

    private final static String MERCHANT_ID="0b74f7bc-ddd3-4150-b4d4-f9fb32f18fe9";
    private final static String MERCHANT_SECRET="QqAvgo6nVmwLc66feF2osg9ppilSNOWI";
    TransactionProcess paymentProcess;

    TextView statusOne,txtStatus,txtIcon;
    private Sale mParam1;
    Toolbar toolbar;
    TextView toolbarTitle;
    Typefacer typefacer;
    TextView toolbarnotify;
    FButton CardAbortButton,cardCancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_check_out);
        typefacer=new Typefacer();
        setToolBar("Sale");


        txtIcon=(TextView)findViewById(R.id.txtIcon);

        statusOne=(TextView)findViewById(R.id.statusText);
        statusOne.setTypeface(typefacer.squareLight());
        CardAbortButton=(FButton)findViewById(R.id.CardAbortButton);
        CardAbortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitTransaction();
            }
        });


        cardCancelButton=(FButton)findViewById(R.id.cardCancelButton);
        cardCancelButton.setTypeface(typefacer.squareLight());
        cardCancelButton.setVisibility(View.VISIBLE);
        Bundle args = getIntent().getExtras();
        if (args != null) {
            mParam1 = (Sale)args.getSerializable("sale");

            hitTransaction();
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }



       // ((AppCompatActivity)CardCheckOut.this).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }



    private void setToolBar(String title){
        toolbar=(Toolbar)findViewById(R.id.app_bar);
        toolbarTitle= (TextView) findViewById(R.id.toolbarTitle);
        toolbarnotify=(TextView)findViewById(R.id.toolbarnotify);

        toolbarTitle.setText(title);
        toolbarTitle.setTypeface(typefacer.squareLight());

        toolbarnotify.setVisibility(View.GONE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setDisplayHomeAsUpEnabled(true);


    }


    public static String joinAndTrimStatusInformation(String[] information) {
        if (information == null) {
            return "";
        }

        String retVal = "";
        for (String line : information) {
            retVal += line.trim() + "\n";
        }

        return retVal.trim();
    }


    private String statusIcon(TransactionProcessDetailsState state,
                              TransactionProcessDetailsStateDetails stateDetails) {
        switch (stateDetails) {
            case CONNECTING_TO_ACCESSORY:
            case CONNECTING_TO_ACCESSORY_CHECKING_FOR_UPDATE:
            case CONNECTING_TO_ACCESSORY_UPDATING:
                return getString(R.string.mpu_fa_lock);
            case CONNECTING_TO_ACCESSORY_WAITING_FOR_READER:
                return getString(R.string.mpu_fa_search);
            case PROCESSING_WAITING_FOR_PIN:
                return getString(R.string.mpu_fa_th);
        }

        switch (state) {
            case CREATED:
            case INITIALIZING_TRANSACTION:
                return getString(R.string.mpu_fa_lock);
            case PREPARING:
                return getString(R.string.mpu_fa_info);
            case PROCESSING:
                return getString(R.string.mpu_fa_bank);
            case WAITING_FOR_CARD_PRESENTATION:
            case WAITING_FOR_CARD_REMOVAL:
                return getString(R.string.mpu_fa_credit_card);
            case APPROVED:
            case DECLINED:
            case ABORTED:
                return getString(R.string.mpu_fa_bank);
            case FAILED:
                return getString(R.string.mpu_fa_times_circle);
        }

        return "";
    }
    void hitTransaction(){

        final TransactionProvider transactionProvider = Mpos.createTransactionProvider(CardCheckOut.this,
                ProviderMode.TEST,
                MERCHANT_ID,
                MERCHANT_SECRET);


// When using the Bluetooth Miura Shuttle / M007 / M010, use the following parameters:
        AccessoryParameters accessoryParameters = new AccessoryParameters.Builder(AccessoryFamily.MIURA_MPI)
                .bluetooth()
                .build();



        //Get the transaction process here s
        TransactionParameters transactionParameters = new TransactionParameters.Builder()
                .charge(new BigDecimal(mParam1.getAmount()), Currency.GHS)
                .subject(mParam1.getSubject())
                .customIdentifier(mParam1.getCustomerIdentify())
                .build();




        paymentProcess = transactionProvider.startTransaction(transactionParameters, accessoryParameters,
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
                        } else if(transactionProcessDetails.getState()==TransactionProcessDetailsState.FAILED){


                            CardAbortButton.setText("Retry");
                            cardCancelButton.setVisibility(View.VISIBLE);


                            // Allow your merchant to try another transaction
                        }else{

                            cardCancelButton.setVisibility(View.GONE);


                        }
                    }

                    @Override
                    public void onStatusChanged(TransactionProcess transactionProcess, Transaction transaction, TransactionProcessDetails transactionProcessDetails) {

                        //   TransactionProcessDetailsState.PROCESSING



                        //transactionProcess.getTransaction().getStatus().

                        String msg = joinAndTrimStatusInformation(transactionProcessDetails.getInformation());
                        statusOne.setText(msg);

                        txtIcon.setText(statusIcon(transactionProcessDetails.getState(), transactionProcessDetails.getStateDetails()));



                        Log.d("mpos", "Transaction status "+transactionProcessDetails.getState().name()+
                                "  Transaction status Details"+transactionProcessDetails.getStateDetails().name());



                        if(transactionProcessDetails.getState()==TransactionProcessDetailsState.FAILED){
                            cardCancelButton.setVisibility(View.VISIBLE);
                        }else {
                            cardCancelButton.setVisibility(View.GONE);

                        }

                        //   Log.d("mpos", "Transaction status Details"+transactionProcessDetails.getStateDetails().name());

                        //  Log.d("mpos", "TransactionProcess status Details"+transactionProcess.getDetails().getState().toString());

                    }

                    @Override
                    public void onCustomerSignatureRequired(TransactionProcess transactionProcess, Transaction transaction) {


                        // getDialog().hide();

                        //  Intent i= new Intent(getActivity(),MobileMoney.class);

                        //  getActivity().startActivity(i);

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



        // paymentProcess.

    }

    @Override
    public void onBackPressed() {

        if(paymentProcess!=null & paymentProcess.canBeAborted() ){

          //  NavUtils.navigateUpFromSameTask(this);

            Toast.makeText(CardCheckOut.this,"Transaction cannot be aborted",Toast.LENGTH_SHORT).show();

        }else{

            Toast.makeText(CardCheckOut.this,"Transaction cannot be aborted",Toast.LENGTH_SHORT).show();


        }
    }
}
