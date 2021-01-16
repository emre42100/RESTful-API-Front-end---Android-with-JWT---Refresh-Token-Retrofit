package com.inalsozluk.app;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;



import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import android.widget.Button;

import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT;


public class ara_Activity extends Fragment {


    View view;
    Button araButon;
    public static SharedPreferences sharedPreferences;
    TextInputLayout ara;
    LinearLayout ln;
    TextInputEditText ds, aramaYapa;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        view = inflater.inflate(R.layout.arama_activity, container, false);

        ara = view.findViewById(R.id.aramaYap);
        ds = view.findViewById(R.id.aramaYapa);
        araButon = view.findViewById(R.id.ara);
        ln = view.findViewById(R.id.yok);

        ds.requestFocus();

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(SHOW_IMPLICIT, 0);
        ds.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    yolla();
                }

                return false;
            }
        });


        return view;
    }

    public void yolla() {

        int length = ara.getEditText().getText().length();
        String b = ara.getEditText().getText().toString().trim();
        String h = b.replaceAll("^\\s+", "");
        if (!h.equals("")) {


            if (length > 50) {

                ara.setHint("Başlık Ara - En fazla 50 karakter girebilirsiniz");
            } else {


                InputMethodManager keyboard = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                keyboard.hideSoftInputFromWindow(ara.getWindowToken(), 0);
                ara.setError("");

                ln.setPadding(0, -200, 0, 0);

//                araButon.setVisibility(View.INVISIBLE);
                ds.setVisibility(View.INVISIBLE);
                ara.setHint("");


                sharedPreferences = getActivity().getSharedPreferences("al", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("sonuc", ara.getEditText().getText().toString());
                editor.apply();


                FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.aramaYap, new araSonuc_Activity());
                ft.commit();
            }

        } else {
           ara.setHint("Başlık Ara - En az 1 karakter girmelisiniz");

            if (!ara.getEditText().getText().toString().equals("")) {
                ara.setError("");

            }
        }
    }


}
