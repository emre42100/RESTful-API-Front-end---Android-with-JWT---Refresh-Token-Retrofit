package com.inalsozluk.app;

import android.content.Intent;
import android.os.Bundle;

import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.material.textfield.TextInputLayout;
import com.inalsozluk.app.Models.SignUp_response;
import com.inalsozluk.app.Models.SignUp_PARAM_request;
import com.inalsozluk.app.Models.SignUp_request;
import com.inalsozluk.app.network.RetrofitBuilder;
import com.inalsozluk.app.network.RetrofitInterface;


import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {


    private static final String TAG = "LoginActivity";


    TextInputLayout tilEmail;
    TextInputLayout tilPassword;
    TextInputLayout tilName;
    Button btn_register;
    TextView go_to_login;

    RetrofitInterface service;
    AwesomeValidation validator;
    Call<SignUp_response> call;
    CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        checkbox = findViewById(R.id.checkBox1);
        TextView sozlesme = findViewById(R.id.textView2);


        checkbox.setText("");

        StringBuilder html = new StringBuilder();
        html.append("<a href='https://inalsozluk.com/gizlilik_ve_kullanici_sozlesmesi.html'>Gizlilik politikası ve Kullanıcı sözleşmesi\n</a>'ni okudum ve kabul ediyorum.");

        sozlesme.setText(Html.fromHtml(html.toString()));


        sozlesme.setClickable(true);
        sozlesme.setMovementMethod(LinkMovementMethod.getInstance());


        tilEmail = findViewById(R.id.til_email);
        tilPassword = findViewById(R.id.til_password);
        tilName = findViewById(R.id.til_name);
        btn_register = findViewById(R.id.btn_register);
        go_to_login = findViewById(R.id.go_to_login);

        go_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                goToRegister();
                finish();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUp();
            }
        });

        service = RetrofitBuilder.createService(RetrofitInterface.class);


        validator = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);


    }


    void SignUp() {


        final String email = tilEmail.getEditText().getText().toString();
        String password = tilPassword.getEditText().getText().toString();
        final String name = tilName.getEditText().getText().toString();

        tilEmail.setError(null);
        tilPassword.setError(null);

        validator.clear();

        if (validator.validate()) {

            SignUp_PARAM_request param = new SignUp_PARAM_request(name, email, password);
            call = service.SignUp(new SignUp_request("SignUp", param));


            if (!checkbox.isChecked()) {

                Toast.makeText(RegisterActivity.this, "Kayıt olmak için Sözleşmeyi kabul etmeniz lazım.", Toast.LENGTH_LONG).show();

            } else if (!email.contains("@")) {

                tilEmail.setError("Geçersiz email adresi girdiniz.");

            } else {

                call.enqueue(new Callback<SignUp_response>() {
                    @Override
                    public void onResponse(Call<SignUp_response> call, Response<SignUp_response> response) {

                        if (response.body() != null) {

                            String hataName = "SQLSTATE[23000]: Integrity constraint violation: 1062 Duplicate entry " + "'" + name + "'" + " for key 'name'";
                            String hataEmail = "SQLSTATE[23000]: Integrity constraint violation: 1062 Duplicate entry " + "'" + email + "'" + " for key 'email'";

                            if (hataName.equals(response.body().getResponse().getResult())) {

                                tilName.setError("Bu nick zaten kayıtlı, başka bir tane deneyin.");
                            }

                            if (hataEmail.equals(response.body().getResponse().getResult())) {

                                tilEmail.setError("Bu email zaten kayıtlı, başka bir tane deneyin.");
                            }


                        }


                        if (response.isSuccessful()) {


                            if (response.code() == 201) {


                                Toast.makeText(RegisterActivity.this, "Kullanıcı başarıyla oluşturuldu", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        } else {


                            if (response.code() == 422) {

                                tilPassword.setError("Şifre en az 6 karakterden oluşmalıdır.");

                            }
                        }


                    }


                    @Override
                    public void onFailure(Call<SignUp_response> call, Throwable t) {


                    }
                });

            }


        }
    }


    void goToRegister() {


        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));


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
