package com.hubtel.mpos.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hubtel.mpos.Application.Application;
import com.hubtel.mpos.Models.MenuItem;
import com.hubtel.mpos.ViewAdapters.MenuItemsRecycleViewAdapter;
import com.hubtel.mpos.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WalletFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WalletFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WalletFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    RecyclerView walletRecycle;

    MenuItemsRecycleViewAdapter menuItemsRecycleViewAdapter;

    public WalletFragment() {
        // Required empty public constructor
    }


    List<MenuItem> listOfMenuItems(){

        List<MenuItem> listOfMenuItems=new ArrayList<>();

        MenuItem menuItem=new MenuItem();
        menuItem.setTitle("Mobile Money");
        menuItem.setImageIcon(R.drawable.ic_airtime);
        listOfMenuItems.add(menuItem);



        menuItem=new MenuItem();
        menuItem.setTitle("Bank Card");
        menuItem.setImageIcon(R.drawable.ic_credit);
        listOfMenuItems.add(menuItem);


        menuItem=new MenuItem();
        menuItem.setTitle("Bill Pay");
        menuItem.setImageIcon(R.drawable.ic_invoice);

        listOfMenuItems.add(menuItem);

        menuItem=new MenuItem();
        menuItem.setTitle("Airtime");
        menuItem.setImageIcon(R.drawable.ic_airtimeo);

        listOfMenuItems.add(menuItem);



        return  listOfMenuItems;

    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WalletFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WalletFragment newInstance(String param1, String param2) {
        WalletFragment fragment = new WalletFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_wallet, container, false);
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
        walletRecycle=(RecyclerView)view.findViewById(R.id.walletRecycle);
        menuItemsRecycleViewAdapter=new MenuItemsRecycleViewAdapter(Application.getAppContext(),listOfMenuItems());
        RecyclerView.LayoutManager mlayout=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        walletRecycle.setAdapter(menuItemsRecycleViewAdapter);
        walletRecycle.setLayoutManager(mlayout);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
