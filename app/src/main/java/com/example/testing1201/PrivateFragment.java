package com.example.testing1201;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PrivateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PrivateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrivateFragment extends IOFragment {

    private boolean focusOnRecycleOne=false;
    private View v;
    public PrivateFragment() {
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
        Log.d("7777777777777", "onCreateView: ");
        v=inflater.inflate(R.layout.fragment_photo, container, false);
        return v;
    }
    MyAdapter adapter=null;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("7777777777777", "onViewCreated: "+focusOnRecycleOne);
        v.setBackgroundColor(Color.MAGENTA);
        RecyclerView recyclerView=v.findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new GridLayoutManager(v.getContext(),5));
        adapter=new MyAdapter(v.getContext());
        recyclerView.setAdapter(adapter);
        List<Data> list=new ArrayList<>();
        for (int i=0;i<20;i++){
            list.add(new Data(String.valueOf(i),String.valueOf(i)));
        }
        adapter.setData(list);
        adapter.setFocusOnOne(focusOnRecycleOne);
    }

    public void setOnHeaderClick(){

    }

    public void setFocusOnRecycleOne(boolean focusOnRecycleOne){
        Log.d("7777777777777", "setFocusOnRecycleOne: "+focusOnRecycleOne);
        this.focusOnRecycleOne=focusOnRecycleOne;
        if (adapter!=null){
            Log.d("7777777777777", "adapter!=null: "+focusOnRecycleOne);
            adapter.setFocusOnOne(focusOnRecycleOne);
        }

    }
}
