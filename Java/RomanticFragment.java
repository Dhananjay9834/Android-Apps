package com.example.mymusicplayer;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class RomanticFragment extends Fragment {
TextView tv_first;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_romantic, container, false);

        tv_first = view.findViewById(R.id.tv_firstsong);


        tv_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), RomanticActivity.class);
                startActivity(i);
            }
        });
        return view;
    }
}