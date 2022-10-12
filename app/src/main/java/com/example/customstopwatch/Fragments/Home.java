package com.example.customstopwatch.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.customstopwatch.R;

import java.util.Locale;

public class Home extends Fragment {

    public Home() {
        // Required empty public constructor
    }

    TextView tvTime ;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    int Seconds, Minutes, MilliSeconds ;
    private ToggleButton toggleButton;
    ImageView reset_btn;
    int SecondsfromUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tvTime = view.findViewById(R.id.TVTime);
        toggleButton = view.findViewById(R.id.Toggle);
        reset_btn = view.findViewById(R.id.reset_btn);

        handler = new Handler() ;

            SecondsfromUser = Integer.parseInt(this.getArguments().getString("Seconds"));

        toggleButton.setText(null);
        toggleButton.setTextOn(null);
        toggleButton.setTextOff(null);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    StartTime = SystemClock.uptimeMillis();
                    handler.postDelayed(runnable, 0);
                     reset_btn.setVisibility(View.INVISIBLE);
                } else {
                    TimeBuff += MillisecondTime;
                    handler.removeCallbacks(runnable);
                    reset_btn.setVisibility(View.VISIBLE);
                }
            }
        });

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MillisecondTime = 0L ;
                StartTime = 0L ;
                TimeBuff = 0L ;
                UpdateTime = 0L ;
                Seconds = 0 ;
                Minutes = 0 ;
                MilliSeconds = 0 ;
                Bundle bundle = new Bundle();
                bundle.putString("Seconds", "60");
                Home home = new Home();
                home.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(Home.this);
                fragmentTransaction.add(R.id.Main, home);
                fragmentTransaction.commit();
                tvTime.setText("00:00:00");
            }
        });

        return view;
    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / SecondsfromUser;

            Seconds = Seconds % SecondsfromUser;

            MilliSeconds = (int) (UpdateTime % 1000);

            tvTime.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };

}
