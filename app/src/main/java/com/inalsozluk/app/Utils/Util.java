package com.inalsozluk.app.Utils;


import com.inalsozluk.app.ApiError;
import com.inalsozluk.app.network.RetrofitBuilder;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by Emre on 18/07/2018.
 */

public class Util {

    public static ApiError converErrors(ResponseBody response){
        Converter<ResponseBody, ApiError> converter = RetrofitBuilder.getRetrofit().responseBodyConverter(ApiError.class, new Annotation[0]);

        ApiError apiError= null;

        try {
             apiError = converter.convert(response);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return  apiError;
    }
}
