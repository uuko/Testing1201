package com.example.testing1201;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;


public class PhotoFragment extends IOFragment implements IOnFocusListenable {

    private View v;
    public PhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_photo, container, false);
        return v;
    }

    MyAdapter adapter ;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getView().getViewTreeObserver().addOnWindowFocusChangeListener(new ViewTreeObserver.OnWindowFocusChangeListener() {
            @Override
            public void onWindowFocusChanged(final boolean hasFocus) {
                // do your stuff here
                Log.d("winnnnnnnnnnn photo", "onWindowFocusChanged: ");
            }
        });
        RecyclerView recyclerView=v.findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new GridLayoutManager(v.getContext(),5));
         adapter=new MyAdapter(v.getContext());
        recyclerView.setAdapter(adapter);
        List<Data> list=new ArrayList<>();
        for (int i=0;i<20;i++){
            list.add(new Data(String.valueOf(i),String.valueOf(i)));
        }
        adapter.setData(list);

        v.setOnKeyListener(null);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

    }

//    public void onKeyDown(int keyCode, KeyEvent event) {
//
//        if ((keyCode == KeyEvent.KEYCODE_ENTER) ) {
//            Log.d("keyyyyyyyyyyyyyyy", "onKeyDown: "+keyCode);
//        }
//
//    }

    public void setOnHeaderClick(){
       Button button=v.findViewById(R.id.button);
        button.requestFocus();

    }

    public void cleanAllFocus(){
        Log.d("aayyyy", "cleanAllFocus: ");
        //v.cleanFocus
        adapter.cleanFocus();

    }
}
