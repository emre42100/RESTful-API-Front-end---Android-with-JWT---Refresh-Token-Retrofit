
package com.inalsozluk.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdView;
import com.inalsozluk.app.Adapters.MyCustomerAdapter;
import com.inalsozluk.app.JWT.JWT;
import com.inalsozluk.app.Models.Nested.deleteCustomer_PARAM_request;
import com.inalsozluk.app.Models.Nested.generateToken_PARAM_request;
import com.inalsozluk.app.Models.Nested.getCustomerDetailsList_response;
import com.inalsozluk.app.Models.Nested.getCustomerDetails_PARAM_request;
import com.inalsozluk.app.Models.deleteCustomer_request;
import com.inalsozluk.app.Models.deleteCustomer_response;
import com.inalsozluk.app.Models.generateToken_request;
import com.inalsozluk.app.Models.generateToken_response;
import com.inalsozluk.app.Models.getCustomerDetails_request;
import com.inalsozluk.app.Models.getCustomerDetails_response;
import com.inalsozluk.app.network.RetrofitBuilder;
import com.inalsozluk.app.network.RetrofitInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.inalsozluk.app.Models.get.sharedPreferences;


@SuppressLint("ValidFragment")
public class getMyCustomerActivity extends Fragment implements Authenticator {

    TokenManager tokenManager;
    Call<generateToken_response> call;

    /**
     * Json Array[] içinde ki key-value leri getirir.
     */
    private ArrayList<getCustomerDetails_response> CustomerList;
    private ProgressDialog pDialog;
    private RecyclerView recyclerView;

    private MyCustomerAdapter eAdapter;
    ImageView nokta;

    RetrofitInterface api = RetrofitBuilder.getApiService();
    /**
     * json Array'i alir.
     */
    Call<getCustomerDetailsList_response> call2;
    Call<deleteCustomer_response> call3;
    JWT jwt;
    String a;
    View view;
    RelativeLayout activity_main;
    TextView say, bos, sabit, designation;
    AdView mAdView;
    public getMyCustomerActivity() {
    }


    public final String[] aciklamaYolla = new String[1];
    public final String[] nameYolla = new String[1];
    public final String[] adresYolla = new String[1];
    public final String[] mobileYolla = new String[1];

    public final String[] emails = new String[1];
    public final String[] passs = new String[1];


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sharedPreferences = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            sharedPreferences = Objects.requireNonNull(this.getActivity()).getSharedPreferences("saveData", Context.MODE_PRIVATE);
        }

        emails[0] = sharedPreferences.getString("value", "value");
        passs[0] = sharedPreferences.getString("val", "val");


        view = inflater.inflate(R.layout.getmycustomer, container, false);








        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefs", Activity.MODE_PRIVATE);
        Bundle extrasi = getActivity().getIntent().getExtras();
        if (extrasi != null) {
            extrasi.getString("geto");

        }


        tokenManager = TokenManager.getInstance(sharedPreferences);
        api = RetrofitBuilder.createServiceWithAuth(RetrofitInterface.class, tokenManager);
        mAdView = view.findViewById(R.id.adView);

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Veriler yükleniyor..");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        recyclerView = view.findViewById(R.id.recycler_vieww);
        say = view.findViewById(R.id.say);
        bos = view.findViewById(R.id.bos);
        sabit = view.findViewById(R.id.sabit);
        nokta = view.findViewById(R.id.nokta);
        designation = view.findViewById(R.id.designation);
        activity_main = view.findViewById(R.id.activity_main);


        ilanlarimiGoruntule();
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
        return view;
    }


    @Override
    public Request authenticate(Route route, okhttp3.Response response) throws IOException {

        if (responseCount(response) >= 2) {
            return null;
        }


        RetrofitInterface service = RetrofitBuilder.createServiceWithAuth(RetrofitInterface.class, tokenManager);


        generateToken_PARAM_request g = new generateToken_PARAM_request(emails[0], passs[0]);
        generateToken_request f = new generateToken_request("generateToken", g);


        Call<generateToken_response> call = service.getStringScalar(f);

        retrofit2.Response<generateToken_response> res = call.execute();

        if (res.isSuccessful()) {

            tokenManager.saveToken(res.body().getResponse().getResult());

            return response.request().newBuilder().header("Authorization", "Bearer " + res.body().getResponse().getResult().getToken()).build();
        } else {
            return null;
        }


    }


    public void ilanlarimiGoruntule() {

        designation = view.findViewById(R.id.designation);


        Bundle extras = getActivity().getIntent().getExtras();
        Integer value = null;

        if (extras != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                value = Integer.valueOf(Objects.requireNonNull(extras.getString("UserID")));


            }
        }


        getCustomerDetails_PARAM_request getCustomerDetails_param_request = new getCustomerDetails_PARAM_request(value, "");
        call2 = api.getMyCustomer(new getCustomerDetails_request("getCustomerDetails", getCustomerDetails_param_request));


        call2.enqueue(new Callback<getCustomerDetailsList_response>() {
            @Override
            public void onResponse(Call<getCustomerDetailsList_response> call, Response<getCustomerDetailsList_response> response) {
                pDialog.dismiss();


                if (response.isSuccessful()) {



                    if (response.body() != null) {

                        CustomerList = response.body().getData();


                    }




                    recyclerView = view.findViewById(R.id.recycler_vieww);
                    nokta = view.findViewById(R.id.nokta);
                    eAdapter = new MyCustomerAdapter(CustomerList, getContext(), getActivity());
                    RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getContext());

                    recyclerView.setLayoutManager(eLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(eAdapter);

//                    nokta.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Toast.makeText(getContext(), "ben geldim", Toast.LENGTH_LONG).show();
//                        }
//                    });


                    eAdapter.setOnItemClickListener(new MyCustomerAdapter.OnItemClickListener() {

                        @Override
                        public void onItemClick(int position) {


                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                ilanlarimAlertDialog(this, Integer.parseInt(CustomerList.get(position).getId()));
                            }

                            Integer fd = position;
                            sharedPreferences = getActivity().getSharedPreferences("tu", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putInt("gel", fd);
                            editor.apply();


                            String aciklama = (CustomerList.get(position).getAciklama());
                            aciklamaYolla[0] = aciklama;


                            String name = (CustomerList.get(position).getName());
                            nameYolla[0] = name;


                            String adres = (CustomerList.get(position).getAddress());
                            adresYolla[0] = adres;


                            String mobile = (CustomerList.get(position).getMobile());
                            mobileYolla[0] = mobile;


                        }
                    });
                    if (response.body() != null) {

                        say.setText(Integer.toString(response.body().getToplam()));

                    }
                } else {

                    if (response.code() == 404) {
                        say.setVisibility(View.INVISIBLE);
                        sabit.setVisibility(View.INVISIBLE);
                        bos.setVisibility(View.VISIBLE);


                    }

                    if (response.code() == 401) {

                        tokenManager.deleteToken();
                        Toast.makeText(getContext(), "Oturum zaman aşımına uğradı.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getContext(), LoginActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }

                    if (response.code() == 403) {
                        Toast.makeText(getContext(), "Geçersiz kimlik", Toast.LENGTH_LONG).show();

                    }


                }
            }

            @Override
            public void onFailure(Call<getCustomerDetailsList_response> call, Throwable t) {

            }
        });


    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void sil(final int ilan_id) {


        Bundle extras = getActivity().getIntent().getExtras();
        Integer value = null;
        if (extras != null) {
            value = Integer.valueOf(Objects.requireNonNull(extras.getString("UserID")));
        }

        deleteCustomer_PARAM_request deleteCustomer_param_request = new deleteCustomer_PARAM_request(ilan_id, value);
        try {
            call3 = api.deleteCustomer(new deleteCustomer_request("deleteCustomer", deleteCustomer_param_request));
        } catch (Exception e) {
            e.printStackTrace();
        }


        call3.enqueue(new Callback<deleteCustomer_response>() {
            @Override
            public void onResponse(Call<deleteCustomer_response> call, Response<deleteCustomer_response> response) {


                if (response.isSuccessful()) {

                    Toast.makeText(getContext(), "İçerik silindi.", Toast.LENGTH_LONG).show();

                } else {


                    if (response.code() == 401) {

                        startActivity(new Intent(getContext(), LoginActivity.class));
                        tokenManager.deleteToken();
                    }

                }


            }

            @Override
            public void onFailure(Call<deleteCustomer_response> call, Throwable t) {

            }
        });


    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void ilanlarimAlertDialog(MyCustomerAdapter.OnItemClickListener activity, final int ilan_id) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.alertlayout, null);

        Button Silbutton = view.findViewById(R.id.buton);
        Button GuncelleButon = view.findViewById(R.id.GuncelleButon);
        Button GoruntuleButon = view.findViewById(R.id.goruntuleButon);
        Button IptalButon = view.findViewById(R.id.IptalEtButon);

        AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setView(view);
        alert.setCancelable(false);
        final AlertDialog dialog = alert.create();

        Intent i = new Intent(getContext(), UpdateCustomerActivity.class);
        final String j = String.valueOf(ilan_id);


        IptalButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });


        GoruntuleButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("tu", Context.MODE_PRIVATE);
                sharedPreferences.getInt("gel", 0);


                String fd = eAdapter.getCustomers().get(sharedPreferences.getInt("gel", 0)).getAddress();



                sharedPreferences = getActivity().getSharedPreferences("al", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("sonuc", fd);
                editor.apply();

                recyclerView.setVisibility(View.INVISIBLE);


                float metrics = getResources().getDisplayMetrics().scaledDensity;
                float densty_dpi = getResources().getDisplayMetrics().densityDpi;

                //  && metrics == 1.75

                if (densty_dpi >=300 && densty_dpi <=399 ) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) activity_main.getLayoutParams();
                    params.topMargin =-70;

                }

                if (densty_dpi >200 && densty_dpi <=299 ) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) activity_main.getLayoutParams();
                   params.topMargin =-75;

                }





                FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.activity_main, new araSonuc_Activity());
                ft.commit();
                dialog.cancel();


            }
        });


        Silbutton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {

                try {
                    Toast.makeText(getContext(), "İçerik silindi.", Toast.LENGTH_LONG).show();
                    sil(ilan_id);
                    eAdapter.removeAt(0);
                    Thread.sleep(100);
                    eAdapter.notifyDataSetChanged();
                    recyclerView.setAdapter(new MyCustomerAdapter(CustomerList, getContext(), getActivity()));
                    ilanlarimiGoruntule();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                dialog.cancel();

            }
        });


        GuncelleButon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                jwt = new JWT(tokenManager.getToken().getToken());
                a = jwt.getuserId();

                Intent i = new Intent(getContext(), UpdateCustomerActivity.class);


                i.putExtra("UserID", a);
                i.putExtra("yolla", j);
                String yollaAciklama = Arrays.toString(aciklamaYolla)

                        .replace("[", "")  //remove the right bracket
                        .replace("]", "")  //remove the left bracket
                        .trim();
                i.putExtra("aciklama", yollaAciklama);

                String yollaName = Arrays.toString(nameYolla)

                        .replace("[", "")  //remove the right bracket
                        .replace("]", "")  //remove the left bracket
                        .trim();
                i.putExtra("name", yollaName);


                String yollaAdres = Arrays.toString(adresYolla)

                        .replace("[", "")  //remove the right bracket
                        .replace("]", "")  //remove the left bracket
                        .trim();
                i.putExtra("adres", yollaAdres);


                String yollaMobile = Arrays.toString(mobileYolla)

                        .replace("[", "")  //remove the right bracket
                        .replace("]", "")  //remove the left bracket
                        .trim();
                i.putExtra("mobile", yollaMobile);


                startActivity(i);
                dialog.cancel();

            }
        });


        dialog.show();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        if (call != null) {
            call.cancel();
            call = null;
        }

        if (pDialog != null && pDialog.isShowing()) {
            pDialog.cancel();
        }
    }


    private int responseCount(okhttp3.Response response) {

        int result = 1;

        while ((response = response.priorResponse()) != null) {

            result++;
        }
        return result;
    }

    private static getMyCustomerActivity INSTANCE;


    private getMyCustomerActivity(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
        emails[0] = sharedPreferences.getString("value", "value");
        passs[0] = sharedPreferences.getString("val", "val");

    }

    public static synchronized getMyCustomerActivity getInstance(TokenManager tokenManager) {
        if (INSTANCE == null) {

            INSTANCE = new getMyCustomerActivity(tokenManager);
        }

        return INSTANCE;
    }
}