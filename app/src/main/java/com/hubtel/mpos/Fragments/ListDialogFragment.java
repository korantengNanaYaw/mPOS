package com.hubtel.mpos.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.hubtel.mpos.Activities.MobileMoney;
import com.hubtel.mpos.Models.Sale;
import com.hubtel.mpos.R;
import com.hubtel.mpos.Utilities.Typefacer;
import com.hubtel.mpos.Utilities.Utility;

import java.math.BigDecimal;
import java.util.Arrays;
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

public class ListDialogFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Sale mParam1;
    private String mParam2;



    private ListDialogFragmentOnFragmentInteractionListener mListener;

    TextView titleView,selProviderLabel;
    Typefacer typefacer;


    EditText MobileMoneyphoneValue;

    Spinner providerSpinner;

    String[] strings = {"Airtel Money","MTN Mobile Money"};

//233240088705
    // 0220088705

    int arr_images[] = {
            R.drawable.ic_airtelmoney,
            R.drawable.ic_mtn,

    };
    RelativeLayout MenuButtonLayout,cashOutCashMode,MobileMoneyLayout,CardModeLayout;

    FButton checkOut1,checkOut2,checkOut3;

    FButton   CshCancelButton,mmCancelButton;
    FButton CardAbortButton,cardCancelButton;



    public ListDialogFragment() {
        // Required empty public constructor
    }

    public static ListDialogFragment newInstance(String param1, String param2) {
        ListDialogFragment fragment = new ListDialogFragment();
        Bundle args = new Bundle();


        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static ListDialogFragment newInstance(Sale sale) {
        ListDialogFragment fragment = new ListDialogFragment();
        Bundle args = new Bundle();


        args.putSerializable(ARG_PARAM1, sale);
       // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (Sale)getArguments().getSerializable(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }

        typefacer=new Typefacer();


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





        eventListenersAndLabels(view);
    }

    private void eventListenersAndLabels(View view) {


        MenuButtonLayout =(RelativeLayout)view.findViewById(R.id.MenuButtonLayout);
        cashOutCashMode=(RelativeLayout)view.findViewById(R.id.cashOutCashMode);
        CardModeLayout=(RelativeLayout)view.findViewById(R.id.CardModeLayout);
        MobileMoneyLayout=(RelativeLayout)view.findViewById(R.id.MobileMoneyLayout);
        titleView=(TextView)view.findViewById(R.id.titleView);
        titleView.setTypeface(typefacer.squareLight());
        titleView.setText(" "+ Utility.formatMoney(Double.parseDouble(mParam1.getAmount())));



        //Card is here .it all happens here
        statusOne=(TextView)view.findViewById(R.id.statusText);
        statusOne.setTypeface(typefacer.squareLight());



        txtIcon=(TextView)view.findViewById(R.id.txtIcon);

        cardCancelButton=(FButton)view.findViewById(R.id.cardCancelButton);
        cardCancelButton.setTypeface(typefacer.squareLight());
        cardCancelButton.setVisibility(View.VISIBLE);
        cardCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardModeLayout.setVisibility(View.GONE);
                MenuButtonLayout.setVisibility(View.VISIBLE);
            }
        });

        CardAbortButton=(FButton)view.findViewById(R.id.CardAbortButton);
        CardAbortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hitTransaction();
            }
        });

        checkOut1=(FButton)view.findViewById(R.id.checkOutA);
        checkOut1.setTypeface(typefacer.squareLight());
        checkOut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              // MenuButtonLayout.setVisibility(View.GONE);
            //  CardModeLayout.setVisibility(View.VISIBLE);
            //  hitTransaction();

                dismiss();
                mListener.ListDialogFragmentonFragmentInteraction(mParam1);

            }
        });




        //MobileMoNEY Spinner


        selProviderLabel=(TextView)view.findViewById(R.id.selProviderLabel);
        selProviderLabel.setTypeface(typefacer.squareLight());

        providerSpinner = (Spinner)view.findViewById(R.id.providerSpinner);
        providerSpinner.setAdapter(new MyAdapter(getActivity(), R.layout.spinner_rows, strings));
        // spinner.setAdapter(new MenuListAdapter(this,_getData(),true));
        providerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                TextView value = (TextView) view.findViewById(R.id.company);
                String valueString = value.getText().toString();
                //selectedSpinnerItem = valueString;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //  selectedSpinnerItem = "";
            }
        });


        //MobileMoney
        checkOut3=(FButton)view.findViewById(R.id.checkOutC);
        checkOut3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuButtonLayout.setVisibility(View.GONE);
               // cashOutCashMode.setVisibility(View.GONE);
                MobileMoneyLayout.setVisibility(View.VISIBLE);

                MobileMoneyphoneValue.requestFocus();
               // MobileMoneyphoneValue.requestFocus();
            }
        });



        //MobileMoNEY cANCEL bUTTON
        mmCancelButton=(FButton)view.findViewById(R.id.mmCancelButton);
        mmCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MobileMoneyLayout.setVisibility(View.GONE);
                MenuButtonLayout.setVisibility(View.VISIBLE);
            }
        });


        MobileMoneyphoneValue=(EditText)view.findViewById(R.id.phoneValue);




        //CashCancelButton
        CshCancelButton=(FButton)view.findViewById(R.id.CshCancelButton);
        CshCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cashOutCashMode.setVisibility(View.GONE);
                MenuButtonLayout.setVisibility(View.VISIBLE);
                LockDialog(false);
            }
        });


        //Cash CheckOut
        checkOut2=(FButton)view.findViewById(R.id.checkOutB);
        checkOut2.setTypeface(typefacer.squareLight());
        checkOut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MenuButtonLayout.setVisibility(View.GONE);
               // MenuButtonLayout.animate().alpha(0.0f);
                cashOutCashMode.setVisibility(View.VISIBLE);
                LockDialog(true);
               // dismiss();
              //  mListener.ListDialogFragmentonFragmentInteraction(mParam1);

            }
        });








    }


    void LockDialog(boolean val){

        if (val)
            getDialog().setCancelable(false);
        else
            getDialog().setCancelable(true);
    }
    private void SetSpinner(View view) {


        providerSpinner = (Spinner)view.findViewById(R.id.providerSpinner);
        providerSpinner.setAdapter(new MyAdapter(getActivity(), R.layout.spinner_rows, strings));
        // spinner.setAdapter(new MenuListAdapter(this,_getData(),true));
        providerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                TextView value = (TextView) view.findViewById(R.id.company);
                String valueString = value.getText().toString();
                //selectedSpinnerItem = valueString;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //  selectedSpinnerItem = "";
            }
        });

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.fragment_list_dialog, container, false);
    }


    public void onButtonPressed(Sale uri) {
        if (mListener != null) {
            mListener.ListDialogFragmentonFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ListDialogFragmentOnFragmentInteractionListener) {
            mListener = (ListDialogFragmentOnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public class MyAdapter extends ArrayAdapter<String> {
        Typefacer typefacer;


        public MyAdapter(Context context, int textViewResourceId, String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public View getDropDownView(int position, View convertView,ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {


            typefacer=new Typefacer();
            LayoutInflater inflater=getLayoutInflater(null);
            View row=inflater.inflate(R.layout.spinner_rows, parent, false);
            TextView label=(TextView)row.findViewById(R.id.company);
            label.setTypeface(typefacer.squareRegular());
            label.setText(strings[position]);



            ImageView icon=(ImageView)row.findViewById(R.id.image);

            icon.setImageResource(arr_images[position]);



            return row;
        }


    }

    private final static String MERCHANT_ID="0b74f7bc-ddd3-4150-b4d4-f9fb32f18fe9";
    private final static String MERCHANT_SECRET="QqAvgo6nVmwLc66feF2osg9ppilSNOWI";
    TransactionProcess paymentProcess;

    TextView statusOne,txtStatus,txtIcon;


    Button buttonOne,buttonTwo;
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
    void hitTransaction(){

        final TransactionProvider transactionProvider = Mpos.createTransactionProvider(getActivity(),
                ProviderMode.TEST,
                MERCHANT_ID,
                MERCHANT_SECRET);


// When using the Bluetooth Miura Shuttle / M007 / M010, use the following parameters:
        AccessoryParameters accessoryParameters = new AccessoryParameters.Builder(AccessoryFamily.MIURA_MPI)
                .bluetooth()
                .build();


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

                        Intent i= new Intent(getActivity(),MobileMoney.class);

                        getActivity().startActivity(i);

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
    private String statusIcon(TransactionProcessDetailsState state, TransactionProcessDetailsStateDetails stateDetails) {
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface ListDialogFragmentOnFragmentInteractionListener {
        // TODO: Update argument type and name
        void ListDialogFragmentonFragmentInteraction(Sale uri);
    }
}
