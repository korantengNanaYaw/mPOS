package com.hubtel.mpos.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.hubtel.mpos.R;
import com.hubtel.mpos.Utilities.Typefacer;

import info.hoang8f.widget.FButton;

public class ListDialogFragment extends DialogFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ListDialogFragmentOnFragmentInteractionListener mListener;

    TextView titleView;
    Typefacer typefacer;

    FButton checkOut1,checkOut2,checkOut3;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        typefacer=new Typefacer();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        titleView=(TextView)view.findViewById(R.id.titleView);
        titleView.setTypeface(typefacer.squareBold());
        titleView.setText("Charge : "+mParam1);



        checkOut1=(FButton)view.findViewById(R.id.checkOutA);
        checkOut1.setTypeface(typefacer.squareLight());
        checkOut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                mListener.ListDialogFragmentonFragmentInteraction(mParam1);

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return inflater.inflate(R.layout.fragment_list_dialog, container, false);
    }


    public void onButtonPressed(String uri) {
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





    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface ListDialogFragmentOnFragmentInteractionListener {
        // TODO: Update argument type and name
        void ListDialogFragmentonFragmentInteraction(String uri);
    }
}
