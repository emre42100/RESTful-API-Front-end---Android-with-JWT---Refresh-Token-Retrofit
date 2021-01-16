
package com.inalsozluk.app;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.material.textfield.TextInputLayout;
import com.inalsozluk.app.JWT.JWT;
import com.inalsozluk.app.Models.Nested.generateToken_PARAM_request;
import com.inalsozluk.app.Models.generateToken_request;
import com.inalsozluk.app.Models.generateToken_response;


import com.inalsozluk.app.network.RetrofitBuilder;
import com.inalsozluk.app.network.RetrofitInterface;


import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public static SharedPreferences sharedPreferences;


    TextInputLayout tilEmail;
    TextInputLayout tilPassword;
    String a;
    JWT jwt;
    RetrofitInterface service;
    TokenManager tokenManager = null;
    Button btn_login;
    AwesomeValidation validator;
    TextView go_to_register;
    public String get;
    public generateToken_PARAM_request param;
    Call<generateToken_response> call;


    public LoginActivity() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btn_login = findViewById(R.id.btn_login);
        tilEmail = findViewById(R.id.til_email);
        tilPassword = findViewById(R.id.til_password);
        go_to_register = findViewById(R.id.go_to_register);

        go_to_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();
                finish();
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                login();

            }
        });


        service = RetrofitBuilder.createService(RetrofitInterface.class);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));


        if (tokenManager.getToken().getToken() != null) {


            Intent g = new Intent(getApplicationContext(), AnaActivity.class);
            jwt = new JWT(tokenManager.getToken().getToken());
            a = jwt.getuserId();
            g.putExtra("UserID", a);
            startActivity(g);
            finish();


        }

        validator = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        setupRules();
    }


    public void login() {


        tilEmail.setError(null);
        tilPassword.setError(null);

        validator.clear();

        if (validator.validate()) {
            final String email = tilEmail.getEditText().getText().toString();
            final String password = tilPassword.getEditText().getText().toString();


            param = new generateToken_PARAM_request(email, password);
            call = service.getStringScalar(new generateToken_request("generateToken", param));


            call.enqueue(new Callback<generateToken_response>() {
                @Override
                public void onResponse(Call<generateToken_response> call, Response<generateToken_response> response) {


                    sharedPreferences = getSharedPreferences("saveData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("value", email);
                    editor.putString("val", password);
                    editor.apply();

                    if (response.isSuccessful()) {


                        if (response.code() == 200) {

                            if (response.body() != null) {

                                tokenManager.saveToken(response.body().getResponse().getResult());
                            }

                            jwt = new JWT(tokenManager.getToken().getToken());
                            a = jwt.getuserId();


                            Intent i = new Intent(getApplicationContext(), AnaActivity.class);
                            i.putExtra("UserID", a);
                            i.putExtra("email", email);
                            get = email;

                            startActivity(i);
                            finish();


                        }


                    } else {
                        if (response.code() == 401) {
                            tokenManager.deleteToken();
                            tilPassword.setError("E-posta adresi veya şifre hatalı");
                            tilEmail.setError(" ");

                        }
                    }
                }

                @Override
                public void onFailure(Call<generateToken_response> call, Throwable t) {


                }


            });

        }


    }


    void goToRegister() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }


    public void setupRules() {

        validator.addValidation(this, R.id.til_email, Patterns.EMAIL_ADDRESS, R.string.emre);
        validator.addValidation(this, R.id.til_password, RegexTemplate.NOT_EMPTY, R.string.b);
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

