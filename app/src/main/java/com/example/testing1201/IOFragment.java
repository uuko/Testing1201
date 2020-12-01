package com.example.testing1201;

import android.util.Log;
import android.view.KeyEvent;

import androidx.fragment.app.Fragment;

public abstract class IOFragment extends Fragment {
    public void onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_ENTER) ) {
            Log.d("keyyyyyyyyyyyyyyy", "onKeyDown: "+keyCode);
        }

    }
    public void setOnHeaderClick(){

    }
    public void setFocusOnRecycleOne(boolean focusOnRecycleOne){}
    public void cleanAllFocus(){}
}
