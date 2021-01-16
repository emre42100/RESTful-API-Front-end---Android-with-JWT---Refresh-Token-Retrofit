package com.inalsozluk.app;

import android.os.Bundle;

import android.view.MenuItem;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.inalsozluk.app.Utils.ChangeFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


public class AnaActivity extends AppCompatActivity {
    AdView mAdView;

    private ChangeFragment changeFragment;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            switch (item.getItemId()) {


                case R.id.navigation_dashboard:
                    changeFragment.change(new getMyCustomerActivity());
                    return true;

                case R.id.navigation_profil:
                    changeFragment.change(new AddCustomerActivity());
                    return true;

                case R.id.navigation_settings:
                    changeFragment.change(new AyarlarActivity());
                    return true;

                case R.id.aramaYap:
                    changeFragment.change(new ara_Activity());
                    return true;

                case R.id.ara:
                    changeFragment.change(new araSonuc_Activity());
                    return true;







            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mAdView =findViewById(R.id.adView);

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        changeFragment = new ChangeFragment(AnaActivity.this);
        changeFragment.change(new getMyCustomerActivity());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }



}
