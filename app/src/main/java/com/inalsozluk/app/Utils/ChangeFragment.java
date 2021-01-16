package com.inalsozluk.app.Utils;


import android.content.Context;


import com.inalsozluk.app.R;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;


public class ChangeFragment {

    private Context context;

    public ChangeFragment(Context context) {
        this.context = context;
    }

    public void change(Fragment fragment){

        ((FragmentActivity) context).getSupportFragmentManager().beginTransaction()

                .replace(R.id.fragmentlayout, fragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }



}
