package com.example.itsoftware.newwanandroid;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ITSoftware on 5/21/2019.
 */

public class MyFragment1 extends Fragment {

    public MyFragment1() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg1_content, container, false);
        TextView txt_content = (TextView) view.findViewById(R.id.txt_content);

        return view;
    }
}
