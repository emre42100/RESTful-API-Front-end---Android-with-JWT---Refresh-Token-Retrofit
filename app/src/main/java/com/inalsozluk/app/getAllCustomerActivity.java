package com.inalsozluk.app;

import android.app.ProgressDialog;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inalsozluk.app.Adapters.CustomersAdapter;
import com.inalsozluk.app.Models.Customer;
import com.inalsozluk.app.Models.CustomerList;
import com.inalsozluk.app.Models.Nested.getAllCustomer_PARAM_request;
import com.inalsozluk.app.Models.getAllCustomer_request;
import com.inalsozluk.app.network.RetrofitBuilder;
import com.inalsozluk.app.network.RetrofitInterface;


import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class getAllCustomerActivity extends Fragment {

    private ArrayList<Customer> CustomerList;
    private ProgressDialog pDialog;
    View view;
    TextView say,bos2,sabit2;
    private RecyclerView recyclerView;
    private CustomersAdapter eAdapter;
    RetrofitInterface api = RetrofitBuilder.getApiService();
    Call<CustomerList> call2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.getallcustomer, container, false);

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Veriler yükleniyor..");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        say = view.findViewById(R.id.say2);
        sabit2 = view.findViewById(R.id.sabit2);
        bos2 = view.findViewById(R.id.bos2);


        getAllCustomer_PARAM_request param_customer = new getAllCustomer_PARAM_request("DFSG456dsfS!D-6!F§4§5d§sf4_!-6s-µµ**5_21§45W4d_ç!fD§3!$*ù6x§w54");
        call2 = api.getCustomerAll(new getAllCustomer_request("getallcustomer", param_customer));


        call2.enqueue(new Callback<CustomerList>() {
            @Override
            public void onResponse(Call<CustomerList> call, Response<CustomerList> response) {

                pDialog.dismiss();


                if (response.isSuccessful()) {

                    if (response.body() != null) {
                        CustomerList = response.body().getData();
                    }
                    recyclerView = view.findViewById(R.id.recycler_view);

                    eAdapter = new CustomersAdapter(CustomerList);
                    RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(eLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(eAdapter);

                    if (response.body() != null) {

                        say.setText(Integer.toString(response.body().getToplam()));
                    }
                } else {

                    if (response.code() == 404) {

                        bos2.setVisibility(View.VISIBLE);
                       sabit2.setVisibility(View.INVISIBLE);
                       say.setVisibility(View.INVISIBLE);
                    }

                }
            }

            @Override
            public void onFailure(Call<CustomerList> call, Throwable t) {
                pDialog.dismiss();
            }
        });

        return view;
    }


}
