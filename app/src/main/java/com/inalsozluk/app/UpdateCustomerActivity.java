package com.inalsozluk.app;


import android.content.Intent;
import android.os.Build;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import android.widget.Toast;


import com.google.android.material.textfield.TextInputLayout;
import com.inalsozluk.app.JWT.JWT;
import com.inalsozluk.app.Models.Nested.UpdateCustomer_PARAM_request;


import com.inalsozluk.app.Models.UpdateCustomer_request;
import com.inalsozluk.app.Models.UpdateCustomer_response;

import com.inalsozluk.app.Models.generateToken_response;

import com.inalsozluk.app.network.RetrofitBuilder;
import com.inalsozluk.app.network.RetrofitInterface;

import java.util.Objects;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateCustomerActivity extends AppCompatActivity {

    private static final String TAG = "PostActivity";
    TokenManager tokenManager;
    Call<generateToken_response> call;

    RetrofitInterface api = RetrofitBuilder.getApiService();

    Call<UpdateCustomer_response> call3;
    JWT jwt;
    String a;

    TextInputLayout til_name, til_aciklama, til_adres;
    Button btn_guncelle;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer);
        String aciklamam = null;
        String adresim = null;
        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            aciklamam = (extras.getString("aciklama"));
            adresim = (extras.getString("adres"));
        }


        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        api = RetrofitBuilder.createServiceWithAuth(RetrofitInterface.class, tokenManager);


        til_name = findViewById(R.id.til_name);
        til_aciklama = findViewById(R.id.til_aciklama);
        til_adres = findViewById(R.id.til_adres);

        btn_guncelle = findViewById(R.id.btn_guncelle);


        Objects.requireNonNull(til_aciklama.getEditText()).setText(aciklamam);
        Objects.requireNonNull(til_adres.getEditText()).setText(adresim);



        guncelle();

    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void guncelle() {


        btn_guncelle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String aciklama = til_aciklama.getEditText().getText().toString();
                String adres = til_adres.getEditText().getText().toString();


                Bundle extras = getIntent().getExtras();
                Integer value = null;
                Integer valuea = null;

                if (extras != null) {
                    value = Integer.valueOf(Objects.requireNonNull(extras.getString("UserID")));
                }
                if (extras != null) {
                    valuea = Integer.valueOf((Objects.requireNonNull(extras.getString("yolla"))));
                }


                if (!aciklama.equals("") && !adres.equals("")) {

                    UpdateCustomer_PARAM_request UpdateCustomer_PARAM_request = new UpdateCustomer_PARAM_request(valuea, value, aciklama, adres,"");
                    call3 = api.updateCustomer(new UpdateCustomer_request("updateCustomer", UpdateCustomer_PARAM_request));

                    call3.enqueue(new Callback<UpdateCustomer_response>() {
                        @Override
                        public void onResponse(Call<UpdateCustomer_response> call, Response<UpdateCustomer_response> response) {


                            if (response.isSuccessful()) {

                                Toast.makeText(UpdateCustomerActivity.this, "Başarıyla Güncellendi.", Toast.LENGTH_LONG).show();
                                jwt = new JWT(tokenManager.getToken().getToken());
                                a = jwt.getuserId();


                                Intent i = new Intent(getApplicationContext(), AnaActivity.class);
                                i.putExtra("UserID", a);
                                startActivity(i);
                                finish();


                            } else {

                                if (response.code() == 401) {
                                    Toast.makeText(UpdateCustomerActivity.this, "Oturum zaman aşımına uğradı", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(UpdateCustomerActivity.this, LoginActivity.class));
                                    finish();
                                    tokenManager.deleteToken();

                                }

                            }


                        }

                        @Override
                        public void onFailure(Call<UpdateCustomer_response> call, Throwable t) {

                        }
                    });
                } else {

                    til_aciklama.setError("Tüm alanları doldurduğunuzdan emin olun.");
                    til_adres.setError(" ");


                }

            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (call != null) {
            call.cancel();
            call = null;
        }

    }


}
