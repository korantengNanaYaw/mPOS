package com.hubtel.mpos.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.hubtel.mpos.Models.CartItems;
import com.hubtel.mpos.Models.Sale;
import com.hubtel.mpos.Utilities.CalculatorBrain;
import com.hubtel.mpos.R;
import com.hubtel.mpos.Utilities.Typefacer;
import com.hubtel.mpos.Utilities.Utility;

import java.text.DecimalFormat;

import info.hoang8f.widget.FButton;
import me.grantland.widget.AutofitTextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link POSFRAGMENT} interface
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

    private POSFRAGMENTOnFragmentInteractionListener mListener;
    Typefacer typefacer;

    String currency="GH₵";
    AutofitTextView smallscreen,addnote;
    private Boolean userIsInTheMiddleOfTypingANumber = false;
    private CalculatorBrain mCalculatorBrain;
    private static final String DIGITS = "0123456789.";
    Vibrator vibrate ;

    EditText display;
    FButton checkOut;
    Button buttonCart;
    private  int counter;
    DecimalFormat df = new DecimalFormat("@###########");


    public POSFRAGMENT() {
        // Required empty public constructor
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
        vibrate = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        mCalculatorBrain = new CalculatorBrain();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.calculator_layout, container, false);
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        padLabels(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof POSFRAGMENTOnFragmentInteractionListener) {
            mListener = (POSFRAGMENTOnFragmentInteractionListener) context;
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
        vibrate.vibrate(300);
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


    private void padLabels(View v){

        smallscreen=(AutofitTextView)v.findViewById(R.id.smallscreen) ;
        smallscreen.setTypeface(typefacer.getRoboRealThin());


        addnote=(AutofitTextView)v.findViewById(R.id.addnote) ;
        addnote.setTypeface(typefacer.squareLight());
        addnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(getActivity())
                        .title("Add Note")
                        .inputType(InputType.TYPE_CLASS_TEXT )
                        .input(null,null, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                if( input.length()==0 ){

                                    // addnote.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                                    // addnote.setText("");
                                    addnote.setHint("Add Note");
                                }else{

                                    Log.d("mpos","input length = "+input.length());
                                    //addnote.setD

                                    //addnote=(AutofitTeddxtView)v.findViewById(R.id.addnote) ;

                                    addnote.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add_note, 0, 0, 0);
                                    addnote.setText(input.toString());

                                }
                            }
                        }).show();
            }
        });

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

        checkOut=(FButton)v.findViewById(R.id.checkOutB);
        checkOut.setTypeface(typefacer.squareRegular());
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(smallscreen.getText().toString()!=null && !smallscreen.getText().toString().isEmpty()){
                    String astring=  smallscreen.getText().toString();
                    smallscreen.setText("");
                    //mListener.POSFRAGMENTonFragmentInteraction("add2cart",astring);

                    try{


                        Log.d("mpos","help Mpos "+astring);
                        Sale sale=new Sale();
                        sale.setAmount(astring);

                        String saleSub=addnote.getText().toString();

                        if(saleSub.equalsIgnoreCase("Add Note") || saleSub.isEmpty())
                            saleSub="Sale";


                        //.isEmpty()? "Sale":addnote.getText().toString();





                        sale.setSubject(saleSub);

                        sale.setCustomerIdentify("xxxx");


                       // mListener.POSFRAGMENTonFragmentInteraction("checkout", Utility.formatMoney(Double.parseDouble(astring)));
                        mListener.POSFRAGMENTonFragmentInteraction("checkout", sale);
                    }catch (Exception e){e.printStackTrace();}


                }




            }
        });

        buttonCart=(Button)v.findViewById(R.id.buttonCart);
        buttonCart.setTypeface(typefacer.squareLight());
        buttonCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /**    String addNoteTxt="";
                 String smallScreenValue=smallscreen.getText().toString();
                 CartItems cartItems=new CartItems();
                 cartItems.setItemName(addNoteTxt);
                 cartItems.setItemPrice(smallScreenValue);
                 cartItems.setItemQty("0");

                 mListener.addItemsToCart(cartItems);


                 //mListener.POSFRAGMENTonFragmentInteraction("add2cart",smallscreen.getText().toString());
                 smallscreen.setText("");
                 addnote.setText("");
                 addnote.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                 addnote.setHint("Add Note");

                 **/

            }
        });



    }



    public interface POSFRAGMENTOnFragmentInteractionListener {
        // TODO: Update argument type and name
        void POSFRAGMENTonFragmentInteraction(String mode,String uri);
        void POSFRAGMENTonFragmentInteraction(String mode,Sale sale);

        void addItemsToCart(CartItems cartItems);
    }
}
