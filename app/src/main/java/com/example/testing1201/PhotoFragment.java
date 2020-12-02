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
    private boolean focusOnRecycleOne=false;
    private View v;
    private int position;
    public PhotoFragment() {
        // Required empty public constructor
    }
    List<Data> list;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_photo, container, false);
    }

    MyAdapter adapter ;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        v=view;
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
        list=new ArrayList<>();
        for (int i=0;i<60;i++){
            list.add(new Data(String.valueOf(i),String.valueOf(i)));
        }
        adapter.setData(list);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {

    }



    public void setOnHeaderClick(){
       Button button=v.findViewById(R.id.button);
        button.requestFocus();

    }
    public void setFocusOnRecycleOne(boolean focusOnRecycleOne){
        Log.d("7777777777777", "setFocusOnRecycleOne: "+focusOnRecycleOne);
        this.focusOnRecycleOne=focusOnRecycleOne;
        if (adapter!=null){
            Log.d("7777777777777", "adapter!=null: "+focusOnRecycleOne);
            adapter.setFocusOnOne(focusOnRecycleOne);
        }

    }
    public void cleanAllFocus(View v){
        Log.d("aayyyy", "cleanAllFocus: ");
        //v.cleanFocus
        v.clearFocus();

    }
 /**
    *  up -> 0
    *  down -> 1
    *  left -> 2
    *  right -> 3
    * */
    public void onKeyDown(int keyCode, KeyEvent event,int id) {

        if ((keyCode == KeyEvent.KEYCODE_DPAD_DOWN) ) {
            Log.d("wwwwwwwwwwww", "qaaaaaaaaaaaaaq: ");
            if (id==R.id.button){
                Log.d("wwwwwwwwwwww", "onKeyDown: ");
                position=4;
                adapter.setFocusPosition(position,true);
            }
            else {
                Log.d("wwwwwwwwwwww", "qq: ");
                if (position<list.size()){
                    position=position+5;
                    adapter.setChangePosition(position,true,1);
                }

            }
        }
        else if (keyCode==KeyEvent.KEYCODE_DPAD_LEFT){
            Log.d("lllllllllllllll", "onKeyDown: ");
            if (position<list.size()){
                if (position>0){
                    position=position-1;
                }
                else {position=0;}

                adapter.setChangePosition(position,true,1);
            }
        }

        else if (keyCode==KeyEvent.KEYCODE_DPAD_RIGHT){
            if (!(position>=list.size())){
                    position=position+1;
                adapter.setChangePosition(position,true,1);
            }
        }

        else if (keyCode==KeyEvent.KEYCODE_DPAD_UP){
            if (position<list.size()){
                if (position>=0){
                    position=position-5;
                    if (position<0){
                        Log.d("position", "onKeyDown: "+position);
                        Button button=v.findViewById(R.id.button);
                        button.requestFocus();
                    }
                    else {
                        adapter.setChangePosition(position,true,1);
                    }
                }




            }
        }

    }
}
