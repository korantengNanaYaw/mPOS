package com.hubtel.mpos.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hubtel.mpos.CalculatorBrain;
import com.hubtel.mpos.R;
import com.hubtel.mpos.Typefacer;

import java.text.DecimalFormat;

import info.hoang8f.widget.FButton;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link POSFRAGMENT.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link POSFRAGMENT#newInstance} factory method to
 * create an instance of this fragment.
 */
public class POSFRAGMENT extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    Typefacer typefacer;

String currency="GH₵";
    TextView smallscreen;
    private Boolean userIsInTheMiddleOfTypingANumber = false;
    private CalculatorBrain mCalculatorBrain;
    private static final String DIGITS = "0123456789.";

    EditText display;

    private  int counter;
    DecimalFormat df = new DecimalFormat("@###########");


    public POSFRAGMENT() {
        // Required empty public constructor
    }
    private void padLabels(View v){

         smallscreen=(TextView)v.findViewById(R.id.smallscreen) ;
        smallscreen.setTypeface(typefacer.getRoboRealThin());

        Button buttonC=(Button)v.findViewById(R.id.buttonC);
        buttonC.setOnClickListener(this);
        buttonC.setTypeface(typefacer.squareLight());


        Button but0=(Button)v.findViewById(R.id.button0);
        but0.setOnClickListener(this);
        but0.setTypeface(typefacer.squareLight());

        Button but1=(Button) v. findViewById(R.id.button1);
        but1.setOnClickListener(this);
        but1.setTypeface(typefacer.squareLight());


        Button but2=(Button)v.findViewById(R.id.activeButton);
        but2.setOnClickListener(this);
        but2.setTypeface(typefacer.squareLight());


        Button but3=(Button)v.findViewById(R.id.button3);
        but3.setOnClickListener(this);
        but3.setTypeface(typefacer.squareLight());



        Button but4=(Button) v.findViewById(R.id.button4);
        but4.setOnClickListener(this);
        but4.setTypeface(typefacer.squareLight());





        Button but5=(Button)v.findViewById(R.id.button5);
        but5.setOnClickListener(this);
        but5.setTypeface(typefacer.squareLight());





        Button but6=(Button)v.findViewById(R.id.button6);
        but6.setOnClickListener(this);
        but6.setTypeface(typefacer.squareLight());




        Button but7=(Button)v. findViewById(R.id.button7);
        but7.setOnClickListener(this);
        but7.setTypeface(typefacer.squareLight());


        Button but8=(Button)v. findViewById(R.id.button8);
        but8.setOnClickListener(this);
        but8.setTypeface(typefacer.squareLight());


        Button but9=(Button)  v.findViewById(R.id.button9);
        but9.setOnClickListener(this);
        but9.setTypeface(typefacer.squareLight());


        Button forget=(Button)   v.findViewById(R.id.buttonForget);
        forget.setOnClickListener(this);
        forget.setTypeface(typefacer.squareRegular());

        FButton checkOut=(FButton)v.findViewById(R.id.checkOut);
        checkOut.setTypeface(typefacer.squareLight());

        FButton buttonCart=(FButton)v.findViewById(R.id.buttonCart);
        buttonCart.setTypeface(typefacer.squareLight());



      //  ImageButton clear=(ImageButton) v.findViewById(R.id.buttonClear);

        //clear.setTypeface(typefacer.squareLight());




    }

    public static POSFRAGMENT newInstance(String param1, String param2) {
        POSFRAGMENT fragment = new POSFRAGMENT();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        typefacer=new Typefacer();
        mCalculatorBrain = new CalculatorBrain();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.calculator_layout, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        padLabels(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {

        String buttonPressed = ((Button) v).getText().toString();

        if (DIGITS.contains(buttonPressed)) {

            // digit was pressed
            if (userIsInTheMiddleOfTypingANumber) {

                if (buttonPressed.equals(".") && smallscreen.getText().toString().contains(".")) {
                    // ERROR PREVENTION
                    // Eliminate entering multiple decimals
                } else {
                   // smallscreen.setText(currency);
                    smallscreen.append(buttonPressed);
                }

            } else {

                if (buttonPressed.equals(".")) {
                    // ERROR PREVENTION
                    // This will avoid error if only the decimal is hit before an operator, by placing a leading zero
                    // before the decimal
                    smallscreen.setText(0 + buttonPressed);
                } else {
                    smallscreen.setText(buttonPressed);
                }

                userIsInTheMiddleOfTypingANumber = true;
            }

        } else {

            if(buttonPressed.equals("+")){

                if(smallscreen.getText().length()<=0){

                }else {

                 //   String tmpdop = useful.formatMoney(Double.parseDouble(smallscreen.getText().toString()));
                 //   communicator.addToPreparedItems("Item "+1, tmpdop);
                    //counter = counter + 1;
                    // Message.message(getActivity(),"operand");
                    smallscreen.setText("");
                }
            }else if(buttonPressed.equals("C")){

                smallscreen.setText("");


            }



        }

    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
