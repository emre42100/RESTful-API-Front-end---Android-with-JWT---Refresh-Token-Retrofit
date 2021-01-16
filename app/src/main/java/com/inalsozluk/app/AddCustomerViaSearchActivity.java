package com.inalsozluk.app;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.material.textfield.TextInputLayout;
import com.inalsozluk.app.JWT.JWT;
import com.inalsozluk.app.Models.Nested.addCustomer_PARAM_request;
import com.inalsozluk.app.Models.addCustomer_request;
import com.inalsozluk.app.Models.addCustomer_response;
import com.inalsozluk.app.Models.generateToken_response;
import com.inalsozluk.app.network.RetrofitBuilder;
import com.inalsozluk.app.network.RetrofitInterface;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT;

public class AddCustomerViaSearchActivity extends Fragment {


    TokenManager tokenManager;
    Call<generateToken_response> call;
    Call<addCustomer_response> call2;
    RetrofitInterface service;
    RetrofitInterface api = RetrofitBuilder.getApiService();
    JWT jwt;
    String a;
    View view;
    TextInputLayout til_name;
    TextInputLayout til_aciklama;
    TextInputLayout til_adres;
    TextInputLayout til_mobile;
    RelativeLayout actvty;
    Button btn;
    AwesomeValidation validator;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.add_entry_via_search, container, false);

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(SHOW_IMPLICIT, 0);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefs", Activity.MODE_PRIVATE);

        tokenManager = TokenManager.getInstance(sharedPreferences);
        service = RetrofitBuilder.createServiceWithAuth(RetrofitInterface.class, tokenManager);

        til_name = view.findViewById(R.id.til_name);
        til_aciklama = view.findViewById(R.id.til_aciklama);
        til_adres = view.findViewById(R.id.til_adres);
        btn = view.findViewById(R.id.btn_ekle);
        actvty = view.findViewById(R.id.actvty);

        final SharedPreferences sharedPreferencesi = getActivity().getSharedPreferences("al", Context.MODE_PRIVATE);
        sharedPreferencesi.getString("sonuc", "sonuc");

        til_adres.getEditText().setText(sharedPreferencesi.getString("sonuc", "sonuc"));


        final String fgfd = sharedPreferencesi.getString("sonuc", "sonuc");

        System.out.println(sharedPreferencesi.getString("sonuc", "sonuc"));


        api = RetrofitBuilder.createService(RetrofitInterface.class);


        validator = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        setupRules();

        float metrics = getResources().getDisplayMetrics().scaledDensity;
        float densty_dpi = getResources().getDisplayMetrics().densityDpi;
        System.out.println("metrics " + metrics);
        System.out.println("densty_dpi " + densty_dpi);
        //  && metrics == 1.75

        if (metrics == 2.0 && densty_dpi == 320) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) actvty.getLayoutParams();
            System.out.println("met " + metrics);
            params.topMargin =80;

        }




        btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                String aciklama = til_aciklama.getEditText().getText().toString();
                String adres = til_adres.getEditText().getText().toString();

                sharedPreferencesi.getString("sonuc", "sonuc");
                addCustomer_PARAM_request param = new addCustomer_PARAM_request("", aciklama, adres, "");


                String b = til_aciklama.getEditText().getText().toString().trim();
                String h = b.replaceAll("^\\s+", "");

                if (!h.equals("") && !adres.equals("")) {

                    call2 = service.addCustomer(new addCustomer_request("addCustomer", param));

                    call2.enqueue(new Callback<addCustomer_response>() {
                        @Override
                        public void onResponse(Call<addCustomer_response> call, Response<addCustomer_response> response) {

                            if (response.isSuccessful()) {


                                actvty.setVisibility(View.INVISIBLE);
                                Toast.makeText(getContext(), "Başarıyla eklendi", Toast.LENGTH_LONG).show();
                                jwt = new JWT(tokenManager.getToken().getToken());
                                a = jwt.getuserId();
                                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                                imm.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
                                til_aciklama.setVisibility(View.INVISIBLE);
                                btn.setVisibility(View.INVISIBLE);
                                FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.activity_main, new araSonuc_Activity());
                                ft.commit();

//                                Intent i = new Intent(getContext(), AnaActivity.class);
//                                i.putExtra("UserID", a);
//                                startActivity(i);
                                // getActivity().finish();


                            } else {

                                if (response.code() == 401) {
                                    Toast.makeText(getContext(), "oturum zaman aşımına uğradı", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getContext(), LoginActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();


                                } else if (response.code() == 422) {

                                    Toast.makeText(getContext(), "Bir hata oluştu.", Toast.LENGTH_LONG).show();

                                }
                            }
                        }


                        @Override
                        public void onFailure(Call<addCustomer_response> call, Throwable t) {


                        }
                    });

                } else {

                    til_aciklama.setHint("Açıklama - En az 1 karakter girmelisiniz.");


                }
            }
        });

        return view;

    }


    public void setupRules() {


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (call != null) {
            call.cancel();
            call = null;
        }
    }
}
