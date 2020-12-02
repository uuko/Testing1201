package com.example.testing1201;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HeaderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HeaderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HeaderFragment extends HeaderIOFFragment implements IOnFocusListenable{

    private Button headerBtn;
    private boolean hasFocus=false;
    private View v;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_header, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        v=view;

        headerBtn=view.findViewById(R.id.headerBtn);
        headerBtn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                hasFocus=b;
                Log.d("ffffffffffff", "onFocusChange: "+hasFocus);
            }
        });
        getView().getViewTreeObserver().addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
            @Override
            public void onWindowFocusChanged(final boolean hasFocus) {
                // do your stuff here
                Log.d("winnnnnnnnnnn header", "onWindowFocusChanged: ");
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

    }

    public void onKeyDown(int keyCode, KeyEvent event,int id) {

        if ((keyCode == KeyEvent.KEYCODE_DPAD_DOWN) ) {
            if (id==R.id.headerBtn){
                Button privateBtn=v.findViewById(R.id.privateBtn);
                privateBtn.requestFocus();
            }
            else if (id==R.id.privateBtn){
                Button button=v.findViewById(R.id.settingsBtn);
                button.requestFocus();
            }
            else if (id==R.id.settingsBtn){
            }

        }
        else if (keyCode==KeyEvent.KEYCODE_DPAD_UP){
            if (id==R.id.headerBtn){

            }
            else if (id==R.id.privateBtn){
                Button button=v.findViewById(R.id.headerBtn);
                button.requestFocus();
            }
            else if (id==R.id.settingsBtn){
                Button privateBtn=v.findViewById(R.id.privateBtn);
                privateBtn.requestFocus();
            }
        }

    }
    public void cleanAllFocus(View v){
        v.clearFocus();
    }

    public void setFocus(View v){

        v.requestFocus();
    }
}
