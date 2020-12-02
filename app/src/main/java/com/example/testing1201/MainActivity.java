package com.example.testing1201;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private Fragment currentFragment;
    private View headerContainer;
    private View rowsContainer;
    private CustomFrameLayout customFrameLayout;
    private HeaderFragment headerFragment;
    private PhotoFragment photoFragment;
    private PrivateFragment privateFragment;
    private Button button,headerbtn,privateBtn;
    private Context context;
    private RecyclerView recyclerView;
    boolean fu=false;
    private View nowFocus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        headerContainer=findViewById(R.id.header_container);
        rowsContainer=findViewById(R.id.rows_container);
        context=this;
        customFrameLayout = (CustomFrameLayout) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);

        headerFragment=new HeaderFragment();
        photoFragment=new PhotoFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction
                .replace(R.id.header_container, headerFragment, "CustomHeadersFragment")
                .replace(R.id.rows_container, photoFragment, "CustomRowsFragment");
        transaction.commit();

        currentFragment=new HeaderFragment();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View v= this.getCurrentFocus();
        Log.d("vvvvvvvvvvvvvvvvv", "onWindowFocusChanged: "+v);
        if(currentFragment instanceof IOnFocusListenable) {
            ((IOnFocusListenable) currentFragment).onWindowFocusChanged(hasFocus);
        }
    }

    private void setupCustomFrameLayout() {
        customFrameLayout.setOnChildFocusListener(new CustomFrameLayout.OnChildFocusListener() {
            @Override
            public boolean onRequestFocusInDescendants(int direction, Rect previouslyFocusedRect) {
                if (headerFragment.getView() != null && headerFragment.getView().requestFocus(direction, previouslyFocusedRect)) {
                    return true;
                }
                if (photoFragment.getView() != null && photoFragment.getView().requestFocus(direction, previouslyFocusedRect)) {
                    return true;
                }

                return false;
            }

            @Override
            public void onRequestChildFocus(View child, View focused) {
                Log.d("vvvvvvvvvv", "onRequestChildFocus: ");
                if (fu) {
                    int childId = child.getId();
                    if (childId == R.id.rows_container) {
                        Log.d("vvvvvvvvvvv","rowsssssssssssss");
                        Transition transition = new Slide(Gravity.LEFT);
                        transition.setDuration(1000);
                        transition.addTarget(R.id.header_container);
                        TransitionManager.beginDelayedTransition((ViewGroup)headerContainer.getParent(), transition);
                        setMargins(headerContainer,-550,0,0,0);
                        setMargins(rowsContainer,0,0,0,0);
                        if (currentFragment instanceof  IOFragment){
                            ((IOFragment)currentFragment).setFocusOnRecycleOne(true);
                        }

                    } else if (childId == R.id.header_container) {
                        Log.d("vvvvvvvvvvv","headdddddddddddd");
                        Transition transition = new Slide(Gravity.LEFT);
                        transition.setDuration(1000);
                        transition.addTarget(R.id.header_container);
                        TransitionManager.beginDelayedTransition((ViewGroup)headerContainer.getParent(), transition);
                        headerContainer.setVisibility(View.VISIBLE);
                        setMargins(headerContainer,0,0,0,0);
                        setMargins(rowsContainer,600,0,0,0);
                        if (currentFragment instanceof  IOFragment){
                            ((IOFragment)currentFragment).setFocusOnRecycleOne(false);
                        }
                    }

                }

            }
        });



    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        Log.d("jjjjjjjjjjjjjjjj", "onKeyDown: ");
        fu=false;
        if (keyCode==KeyEvent.KEYCODE_DPAD_LEFT ){
            View v= this.getCurrentFocus();
            Transition transition = new Slide(Gravity.LEFT);
            transition.setDuration(1000);
            transition.addTarget(R.id.header_container);
            TransitionManager.beginDelayedTransition((ViewGroup)headerContainer.getParent(), transition);
            headerContainer.setVisibility(View.VISIBLE);
            setMargins(headerContainer,0,0,0,0);
            setMargins(rowsContainer,600,0,0,0);

            ((IOFragment)photoFragment).cleanAllFocus(v);
            ((HeaderIOFFragment)headerFragment).setFocus(nowFocus);

        }
        else if (keyCode==KeyEvent.KEYCODE_DPAD_RIGHT){
            View v= this.getCurrentFocus();
            if (((View)v.getParent().getParent()).getId()==R.id.header_frame){
                Transition transition = new Slide(Gravity.LEFT);
                transition.setDuration(1000);
                transition.addTarget(R.id.header_container);
                TransitionManager.beginDelayedTransition((ViewGroup)headerContainer.getParent(), transition);
                setMargins(headerContainer,-550,0,0,0);
                setMargins(rowsContainer,0,0,0,0);
                if (photoFragment instanceof  IOFragment){
                    nowFocus=this.getCurrentFocus();
                    ((HeaderIOFFragment)headerFragment).cleanAllFocus(v);
                    ((IOFragment)photoFragment).setOnHeaderClick();
                }
            }


        }
        else if (keyCode==KeyEvent.KEYCODE_BACK){
            View v= this.getCurrentFocus();
            Log.d("vvvvvvvvvvv", "on: "+((View) v).getId()+" name    :"+((View)v.getParent().getParent()).getId());
            if (
                    ((View)v.getParent().getParent()).getId()==R.id.photo_frame
                || (((View)v.getParent().getParent().getParent().getParent()).getId()==R.id.photo_frame)){
                Log.d("vvvvvvvvvvv","vvvvvvvvvvvvvv");
                Transition transition = new Slide(Gravity.LEFT);
                transition.setDuration(1000);
                transition.addTarget(R.id.header_container);
                TransitionManager.beginDelayedTransition((ViewGroup)headerContainer.getParent(), transition);
                setMargins(headerContainer,0,0,0,0);
                setMargins(rowsContainer,600,0,0,0);

            }

        }
        else if (keyCode==KeyEvent.KEYCODE_DPAD_DOWN){
            View v= this.getCurrentFocus();
           if (((View)v.getParent().getParent()).getId()==R.id.header_frame){
                ((IOFragment)photoFragment).cleanAllFocus(v);
                ((HeaderIOFFragment) headerFragment).onKeyDown(keyCode, event,v.getId());
            }
           else if (((View)v.getParent().getParent()).getId()==R.id.photo_frame  || (((View)v.getParent().getParent().getParent().getParent()).getId()==R.id.photo_frame) ){
//               ((HeaderIOFFragment) headerFragment).cleanAllFocus(v);
               ((IOFragment)photoFragment).onKeyDown(keyCode,event,v.getId());
           }
        }
        else if (keyCode==KeyEvent.KEYCODE_DPAD_UP){
            View v= this.getCurrentFocus();
            if (((View)v.getParent().getParent()).getId()==R.id.header_frame){
                ((IOFragment)photoFragment).cleanAllFocus(v);
                ((HeaderIOFFragment) headerFragment).onKeyDown(keyCode, event,v.getId());
            }
            Log.d("aaaaaaaaaaa", "onKeyDown: "+v.getId());
        }

//        if(currentFragment instanceof IOnFocusListenable) {
//            ((IOFragment) currentFragment).onKeyDown(keyCode, event);
//            return true;
//        }
        return true;
    }

    public static void setMargins (View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }
    private MyAdapter adapter;
    @Override
    protected void onStart() {
        super.onStart();

        headerbtn = findViewById(R.id.headerBtn);
        privateBtn=findViewById(R.id.privateBtn);
        privateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("123","---------ppp");
                Transition transition = new Slide(Gravity.LEFT);
                transition.setDuration(1000);
                transition.addTarget(R.id.header_container);
                TransitionManager.beginDelayedTransition((ViewGroup)headerContainer.getParent(), transition);
                setMargins(headerContainer,-550,0,0,0);
                setMargins(rowsContainer,0,0,0,0);
                privateFragment=new PrivateFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                transaction
                        .replace(R.id.rows_container,privateFragment, "CustomRowsFragment");
                transaction.commit();

                currentFragment=privateFragment;
                setNowFragment();
                ((IOFragment)currentFragment).setFocusOnRecycleOne(true);
            }
        });

        headerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Log.d("123","HeadG");
                    View v= MainActivity.this.getCurrentFocus();

                    if (((View)v.getParent().getParent()).getId()==R.id.header_frame){
                        Log.d("vvvvvvvvvvv","parent header");
                        Transition transition = new Slide(Gravity.LEFT);
                        transition.setDuration(1000);
                        transition.addTarget(R.id.header_container);
                        TransitionManager.beginDelayedTransition((ViewGroup)headerContainer.getParent(), transition);
                        setMargins(headerContainer,-550,0,0,0);
                        setMargins(rowsContainer,0,0,0,0);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction
                                .replace(R.id.rows_container, photoFragment, "CustomRowsFragment");
                        transaction.commit();
                        currentFragment=photoFragment;
                        setNowFragment();

                    }
                    else if (((View)v.getParent()).getId()==R.id.photo_frame){
                        Log.d("vvvvvvvvvvv","parent photo");
                    }

            }
        });

        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("123","GGGGGGGGGG");
            }
        });
    }


    private void setNowFragment(){
        ((IOFragment)currentFragment).setOnHeaderClick();
    }
}
