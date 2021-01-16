package com.inalsozluk.app;

import android.content.SharedPreferences;

import com.inalsozluk.app.Models.Nested.Nested.RESULT_generateToken_response_response;


/**
 * Created by Emre on 18/07/2018.
 */

public class TokenManager {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private static TokenManager INSTANCE = null;

    private TokenManager(SharedPreferences prefs) {
        this.prefs = prefs;
        this.editor = prefs.edit();
    }

    static synchronized TokenManager getInstance(SharedPreferences prefs) {

        if (INSTANCE == null) {
            INSTANCE = new TokenManager(prefs);
        }
        return INSTANCE;

    }

    public void saveToken(RESULT_generateToken_response_response token) {

        editor.putString("ACCESS_TOKEN", token.getToken()).commit();
        editor.putString("REFRESH_TOKEN", token.getRefreshToken()).commit();

    }



    public void deleteToken() {
        editor.remove("ACCESS_TOKEN").commit();
        editor.remove("REFRESH_TOKEN").commit();
    }

    public RESULT_generateToken_response_response getToken() {
        RESULT_generateToken_response_response token = new RESULT_generateToken_response_response();

        token.setToken(prefs.getString("ACCESS_TOKEN", null));
        token.setRefreshToken(prefs.getString("REFRESH_TOKEN", null));


        return token;
    }
}
