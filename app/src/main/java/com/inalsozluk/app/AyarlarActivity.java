package com.inalsozluk.app;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.ads.AdView;
import com.google.android.material.textfield.TextInputLayout;
import com.inalsozluk.app.Models.Nested.passwordReset_PARAM_request;
import com.inalsozluk.app.Models.generateToken_response;
import com.inalsozluk.app.Models.passwordReset_response;
import com.inalsozluk.app.Models.passwordRest_request;
import com.inalsozluk.app.network.RetrofitBuilder;
import com.inalsozluk.app.network.RetrofitInterface;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AyarlarActivity extends Fragment {



    TokenManager tokenManager;
    Call<generateToken_response> call;
    TextView sozluk, sozlesme;
    Call<passwordReset_response> call2;
    RetrofitInterface service;
    RetrofitInterface api = RetrofitBuilder.getApiService();
    View view;
    TextInputLayout til_passwordEski, til_passwordYeni, til_passwordYeniTekrar;

    Button btn;
    ImageView lgt;
    AwesomeValidation validator;

     AdView mAdView;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_ayarlar, container, false);


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefs", Activity.MODE_PRIVATE);

        tokenManager = TokenManager.getInstance(sharedPreferences);
        service = RetrofitBuilder.createServiceWithAuth(RetrofitInterface.class, tokenManager);
        mAdView = view.findViewById(R.id.adView);
        til_passwordEski = view.findViewById(R.id.til_passwordEski);
        til_passwordYeni = view.findViewById(R.id.til_passwordYeni);
        til_passwordYeniTekrar = view.findViewById(R.id.til_passwordYeniTekrar);
        sozluk = view.findViewById(R.id.sozluk);
        sozlesme = view.findViewById(R.id.sozlesme);
        lgt = view.findViewById(R.id.lgt);

        btn = view.findViewById(R.id.btn_login);
        StringBuilder html = new StringBuilder();
        html.append("<a href='https://inalsozluk.com/gizlilik_ve_kullanici_sozlesmesi.html'>Gizlilik politikası ve Kullanıcı sözleşmesi</a>");

        sozlesme.setText(Html.fromHtml(html.toString()));


        sozlesme.setClickable(true);
        sozlesme.setMovementMethod(LinkMovementMethod.getInstance());

        api = RetrofitBuilder.createService(RetrofitInterface.class);


        validator = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        setupRules();

        lgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Çıkış Yapıldı.", Toast.LENGTH_LONG).show();

                tokenManager.deleteToken();
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);


        btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                String pass_eski = til_passwordEski.getEditText().getText().toString();
                String pass_yeni = til_passwordYeni.getEditText().getText().toString();
                String pass_yeni_tekrar = til_passwordYeniTekrar.getEditText().getText().toString();


                if (!pass_eski.equals("") && !pass_yeni.equals("") && !pass_yeni_tekrar.equals("")) {

                    passwordReset_PARAM_request param = new passwordReset_PARAM_request(pass_eski, pass_yeni, pass_yeni_tekrar);
                    call2 = service.passwordReset(new passwordRest_request("passwordReset", param));

                    call2.enqueue(new Callback<passwordReset_response>() {
                        @Override
                        public void onResponse(Call<passwordReset_response> call, Response<passwordReset_response> response) {


                            if (response.isSuccessful()) {

                                if (response.body() != null) {
                                    if (response.code() == 200) {

                                        if (response.body().getResponse().getResult().equals("Yeni şifreniz eşleşmedi.")) {

                                            if (!response.body().getResponse().getResult().equals("Mevcut girdiğiniz şifre hatalı.")) {
                                                til_passwordEski.setError("");
                                            }

                                            til_passwordYeniTekrar.setError(response.body().getResponse().getResult());
                                            til_passwordYeni.setError(" ");

                                        } else if (response.body().getResponse().getResult().equals("Mevcut girdiğiniz şifre hatalı.")) {

                                            if (!response.body().getResponse().getResult().equals("Yeni şifreniz eşleşmedi.")) {
                                                til_passwordYeniTekrar.setError("");
                                                til_passwordYeni.setError("");
                                            }
                                            til_passwordEski.setError(response.body().getResponse().getResult());
                                            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) sozluk.getLayoutParams();
                                            params.topMargin = 600;

                                        } else if (response.body().getResponse().getResult().equals("Şifre en az 6 karakter olmalıdır.")) {

                                            til_passwordYeniTekrar.setError(response.body().getResponse().getResult());
                                            til_passwordYeni.setError(" ");
                                        }
                                        // Toast.makeText(getContext(), response.body().getResponse().getResult(), Toast.LENGTH_LONG).show();
                                    }
                                }

                                if (response.code() == 201) {
                                    if (response.body() != null) {
                                        Toast.makeText(getContext(), response.body().getResponse().getResult(), Toast.LENGTH_LONG).show();
                                    }
                                    tokenManager.deleteToken();
                                    Intent i = new Intent(getContext(), LoginActivity.class);
                                    startActivity(i);
                                    getActivity().finish();


                                }

                                // getContext().finish();

                            } else {


                                if (response.code() == 401) {
                                    Toast.makeText(getContext(), "oturum zaman aşımına uğradı", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(getContext(), LoginActivity.class);
                                    startActivity(intent);


                                }

                            }
                        }


                        @Override
                        public void onFailure(Call<passwordReset_response> call, Throwable t) {


                        }
                    });

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
