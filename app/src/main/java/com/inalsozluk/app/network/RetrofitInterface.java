package com.inalsozluk.app.network;

import com.inalsozluk.app.Models.CustomerList;
import com.inalsozluk.app.Models.SignUp_response;
import com.inalsozluk.app.Models.Nested.ara_PARAM_response;
import com.inalsozluk.app.Models.UpdateCustomer_response;
import com.inalsozluk.app.Models.Nested.getCustomerDetailsList_response;
import com.inalsozluk.app.Models.SignUp_request;
import com.inalsozluk.app.Models.UpdateCustomer_request;
import com.inalsozluk.app.Models.addCustomer_request;
import com.inalsozluk.app.Models.addCustomer_response;
import com.inalsozluk.app.Models.ara_request;
import com.inalsozluk.app.Models.deleteCustomer_request;
import com.inalsozluk.app.Models.deleteCustomer_response;
import com.inalsozluk.app.Models.generateToken_request;
import com.inalsozluk.app.Models.generateToken_response;
import com.inalsozluk.app.Models.getAllCustomer_request;
import com.inalsozluk.app.Models.getCustomerDetails_request;
import com.inalsozluk.app.Models.passwordReset_response;
import com.inalsozluk.app.Models.passwordRest_request;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface RetrofitInterface {

    @Headers("Content-Type: application/json")
    @POST("jwt-api/")
    Call<generateToken_response> getStringScalar(@Body generateToken_request body);


    @Headers("Content-Type: application/json")
    @POST("jwt-api/")
    Call<getCustomerDetailsList_response> getMyCustomer(@Body getCustomerDetails_request body);

    @Headers("Content-Type: application/json")
    @POST("jwt-api/")
    Call<CustomerList> getCustomerAll(@Body getAllCustomer_request body);

    @Headers("Content-Type: application/json")
    @POST("jwt-api/")
    Call<SignUp_response> SignUp(@Body SignUp_request body);

    @Headers("Content-Type: application/json")
    @POST("jwt-api/")
    Call<addCustomer_response> addCustomer(@Body addCustomer_request body);

    @Headers("Content-Type: application/json")
    @POST("jwt-api/")
    Call<deleteCustomer_response> deleteCustomer(@Body deleteCustomer_request body);

    @Headers("Content-Type: application/json")
    @POST("jwt-api/")
    Call<UpdateCustomer_response> updateCustomer(@Body UpdateCustomer_request body);

    @Headers("Content-Type: application/json")
    @POST("jwt-api/")
    Call<passwordReset_response> passwordReset(@Body passwordRest_request body);

    @Headers("Content-Type: application/json")
    @POST("jwt-api/")
    Call<ara_PARAM_response> ara(@Body ara_request body);


}