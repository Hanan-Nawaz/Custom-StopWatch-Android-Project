package com.example.customstopwatch.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.customstopwatch.R;

import org.w3c.dom.Text;

public class Settings extends Fragment {


    public Settings() {
        // Required empty public constructor
    }

    EditText EtSeconds;
    TextView TVErrorBox;
    Button btnSave;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_settings, container, false);

        EtSeconds = view.findViewById(R.id.ETSeconds);
        btnSave = view.findViewById(R.id.btnSave);
        TVErrorBox = view.findViewById(R.id.TVError);

        EtSeconds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TVErrorBox.setVisibility(View.INVISIBLE);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sec = EtSeconds.getText().toString();

                if(sec.equals("")){
                    TVErrorBox.setVisibility(View.VISIBLE);
                }
                else{
                    Bundle bundle = new Bundle();
                    bundle.putString("Seconds", sec);
                    Home home = new Home();
                    home.setArguments(bundle);
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.hide(Settings.this);
                    fragmentTransaction.add(R.id.Main, home);
                    fragmentTransaction.commit();
                }
            }
        });

        return view;
    }
}