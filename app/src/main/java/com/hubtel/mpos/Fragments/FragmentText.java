package com.hubtel.mpos.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hubtel.mpos.Application.Application;

/**
 * Created by apple on 21/06/16.
 */
public class FragmentText extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView text = new TextView(Application.getAppContext());
        text.setText("Fragment content");
        text.setGravity(Gravity.CENTER);

        return text;
    }
}