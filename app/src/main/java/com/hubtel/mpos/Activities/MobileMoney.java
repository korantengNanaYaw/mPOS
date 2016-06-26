package com.hubtel.mpos.Activities;

import android.content.Context;
import android.graphics.Color;
import android.os.Vibrator;
import android.renderscript.Type;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.hubtel.mpos.R;
import com.hubtel.mpos.Utilities.Typefacer;

public class MobileMoney extends AppCompatActivity {
    Toolbar toolbar;
    TextView toolbarTitle;
    Typefacer typefacer;
    TextView toolbarnotify;

    TextView providerLabel;
    Spinner spinner;
    Button continueButton;
  EditText input_phone_number,input_amount;
    TextInputLayout input_phone_number_layout,input_amount_edt;


    CoordinatorLayout cordinator;
    Vibrator vibrate ;
    String[] strings = {"Airtel Money","MTN Mobile Money"};

//233240088705
    // 0220088705

    int arr_images[] = {
            R.drawable.ic_airtelmoney,
            R.drawable.ic_mtn,

    };



    private void SetSpinner() {


        spinner = (Spinner) findViewById(R.id.telcosSpinner);
        spinner.setAdapter(new MyAdapter(MobileMoney.this, R.layout.spinner_rows, strings));
        // spinner.setAdapter(new MenuListAdapter(this,_getData(),true));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_money);
        typefacer=new Typefacer();
        vibrate = (Vibrator) MobileMoney.this.getSystemService(Context.VIBRATOR_SERVICE);

        setToolBar("Mobile Money");

        setLabels();

        SetSpinner();
    }

    private void setLabels() {
        providerLabel=(TextView)findViewById(R.id.providerLabel);
        providerLabel.setTypeface(typefacer.squareRegular());


        input_phone_number=(EditText)findViewById(R.id.input_phone_number);
        input_amount=(EditText)findViewById(R.id.input_amount);
        cordinator=(CoordinatorLayout)findViewById(R.id.cordinator);


        input_phone_number_layout=(TextInputLayout) findViewById(R.id.input_phone_number_layout);
        input_phone_number_layout.setTypeface(typefacer.squareLight());


        input_amount_edt=(TextInputLayout) findViewById(R.id.input_amount_edt);
        input_amount_edt.setTypeface(typefacer.squareLight());


        continueButton=(Button)findViewById(R.id.continueButton);
        continueButton.setTypeface(typefacer.squareLight());

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateForms();
            }
        });
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
            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.spinner_rows, parent, false);
            TextView label=(TextView)row.findViewById(R.id.company);
            label.setTypeface(typefacer.squareRegular());
            label.setText(strings[position]);



            ImageView icon=(ImageView)row.findViewById(R.id.image);

            icon.setImageResource(arr_images[position]);



            return row;
        }

        private void setLabels() {
            providerLabel=(TextView)findViewById(R.id.providerLabel);
            providerLabel.setTypeface(typefacer.squareRegular());



            input_phone_number_layout=(TextInputLayout) findViewById(R.id.input_phone_number_layout);
            input_phone_number_layout.setTypeface(typefacer.squareLight());


            input_amount_edt=(TextInputLayout) findViewById(R.id.input_amount_edt);
            input_amount_edt.setTypeface(typefacer.squareLight());


            continueButton=(Button)findViewById(R.id.continueButton);
            continueButton.setTypeface(typefacer.squareLight());
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
    }



    void validateForms(){

        if(input_amount.getText().toString().isEmpty()){

            vibrate.vibrate(500);

            Snackbar snackbar = Snackbar
                    .make(cordinator,"Please enter Amount.", Snackbar.LENGTH_SHORT);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.RED);
            snackbar.show();
            return;
        }else if(input_phone_number.getText().toString().isEmpty()){

            vibrate.vibrate(500);

            Snackbar snackbar = Snackbar
                    .make(cordinator,"Please enter Phone Number.", Snackbar.LENGTH_SHORT);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.RED);
            snackbar.show();
            return;


        }else if(spinner.getSelectedItem().toString().isEmpty()){

            vibrate.vibrate(500);

            Snackbar snackbar = Snackbar
                    .make(cordinator,"Please Select a Provider.", Snackbar.LENGTH_SHORT);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.RED);
            snackbar.show();
            return;


        }

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
}
