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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.inputmethod.InputMethodManager.SHOW_IMPLICIT;

public class AddCustomerActivity extends Fragment {


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
    Button btn;
    AwesomeValidation validator;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.activity_add_customer, container, false);

        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(SHOW_IMPLICIT, 0);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefs", Activity.MODE_PRIVATE);

        tokenManager = TokenManager.getInstance(sharedPreferences);
        service = RetrofitBuilder.createServiceWithAuth(RetrofitInterface.class, tokenManager);

        til_name = view.findViewById(R.id.til_name);
        til_aciklama = view.findViewById(R.id.til_aciklama);
        til_adres = view.findViewById(R.id.til_adres);
        btn = view.findViewById(R.id.btn_ekle);


        api = RetrofitBuilder.createService(RetrofitInterface.class);


        validator = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        setupRules();

        btn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                String aciklama = til_aciklama.getEditText().getText().toString();
                String adres = til_adres.getEditText().getText().toString();

                int length = til_adres.getEditText().getText().length();
                String b = til_adres.getEditText().getText().toString().trim();


                if (!aciklama.equals("") && !b.equals("")) {

                    if (length > 50) {

                        System.out.println("50 geldi");
                        til_adres.setHint("En fazla 50 karakter girebilirsiniz.");
                        til_adres.setError(" ");

                    } else {

                        addCustomer_PARAM_request param = new addCustomer_PARAM_request("", aciklama, adres, "");
                        call2 = service.addCustomer(new addCustomer_request("addCustomer", param));

                        call2.enqueue(new Callback<addCustomer_response>() {
                            @Override
                            public void onResponse(Call<addCustomer_response> call, Response<addCustomer_response> response) {

                                if (response.isSuccessful()) {

                                    Toast.makeText(getContext(), "Başarıyla eklendi", Toast.LENGTH_LONG).show();
                                    jwt = new JWT(tokenManager.getToken().getToken());
                                    a = jwt.getuserId();


                                    Intent i = new Intent(getContext(), AnaActivity.class);
                                    i.putExtra("UserID", a);
                                    startActivity(i);
                                    getActivity().finish();


                                } else {

                                    if (response.code() == 401) {
                                        Toast.makeText(getContext(), "oturum zaman aşımına uğradı", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(getContext(), LoginActivity.class);
                                        startActivity(intent);
                                        getActivity().finish();


                                    } else if (response.code() == 422) {

                                        Toast.makeText(getContext(), "", Toast.LENGTH_LONG).show();

                                    }
                                }
                            }


                            @Override
                            public void onFailure(Call<addCustomer_response> call, Throwable t) {


                            }
                        });
                    }


                } else {
                    til_aciklama.setError("Tüm alanları doldurduğunuzdan emin olun.");
                    til_adres.setError(" ");


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
