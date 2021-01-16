package com.inalsozluk.app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.inalsozluk.app.Adapters.araAdapter;

import com.inalsozluk.app.Models.Nested.ara_PARAM_request;
import com.inalsozluk.app.Models.Nested.ara_PARAM_response;

import com.inalsozluk.app.Models.ara_request;
import com.inalsozluk.app.Models.ara_response;

import com.inalsozluk.app.network.RetrofitBuilder;
import com.inalsozluk.app.network.RetrofitInterface;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class araSonuc_Activity extends Fragment {

    private ArrayList<ara_response> araList;
    private ProgressDialog pDialog;
    View view;
    TextView say, bos2, sabit2, isimcek, nokta;
    private RecyclerView recyclerView;
    private araAdapter eAdapter;
    RelativeLayout activity_main;
    LinearLayout linerButon;
    ImageView entrygir;
    Button entry_yolla;
    RetrofitInterface api = RetrofitBuilder.getApiService();
    Call<ara_PARAM_response> call2;
    public static SharedPreferences sharedPreferences;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.ara_activity, container, false);

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("yükleniyor..");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        say = view.findViewById(R.id.say2);
        sabit2 = view.findViewById(R.id.sabit2);
        bos2 = view.findViewById(R.id.bos2);
        isimcek = view.findViewById(R.id.isimcek);
        linerButon = view.findViewById(R.id.linerButon);
        nokta = view.findViewById(R.id.nokta);
        entry_yolla = view.findViewById(R.id.entry_yolla);
        entrygir = view.findViewById(R.id.entrygir);
        activity_main = view.findViewById(R.id.activity_main);


        final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("al", Context.MODE_PRIVATE);
        sharedPreferences.getString("sonuc", "sonuc");

        final String fgfd = sharedPreferences.getString("sonuc", "sonuc");




        ara_PARAM_request param_customer = new ara_PARAM_request(sharedPreferences.getString("sonuc", "sonuc"));
        call2 = api.ara(new ara_request("ara", param_customer));


        call2.enqueue(new Callback<ara_PARAM_response>() {
            @Override
            public void onResponse(Call<ara_PARAM_response> call, Response<ara_PARAM_response> response) {

                pDialog.dismiss();


                if (response.isSuccessful()) {


                    if (response.body() != null) {
                        araList = response.body().getData();
                    }
                    recyclerView = view.findViewById(R.id.recycler_view);

                    eAdapter = new araAdapter(araList);
                    RecyclerView.LayoutManager eLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(eLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(eAdapter);


                    Integer baslikUzunlugu = response.body().getName().length();
                    final float metrics = getResources().getDisplayMetrics().scaledDensity;
                    final float densty_dpi = getResources().getDisplayMetrics().densityDpi;
                    System.out.println("metrics " + metrics);
                    System.out.println("densty_dpi " + densty_dpi);
                    //  && metrics == 1.75


                    if (densty_dpi == 260) {
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) activity_main.getLayoutParams();

                        // bunun yuzunden
                        params.topMargin = 90;

                    }
                    final float met = Resources.getSystem().getDisplayMetrics().density;
                    if (densty_dpi >=400 && densty_dpi <=499 ) {
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) activity_main.getLayoutParams();
                        ViewGroup.MarginLayoutParams paramsd = (ViewGroup.MarginLayoutParams) sabit2.getLayoutParams();
                        ViewGroup.MarginLayoutParams paramsdd = (ViewGroup.MarginLayoutParams) entrygir.getLayoutParams();
                        paramsd.topMargin = 35;

                       //paramsdd.leftMargin = 800;

                    }

//                    if (densty_dpi == 420) {
//                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) activity_main.getLayoutParams();
//                        ViewGroup.MarginLayoutParams paramsd = (ViewGroup.MarginLayoutParams) sabit2.getLayoutParams();
//                        ViewGroup.MarginLayoutParams paramsdd = (ViewGroup.MarginLayoutParams) entrygir.getLayoutParams();
//                        paramsd.topMargin = 35;
//                        params.topMargin = 30;
//                        paramsdd.leftMargin = 900;
//
//
//                    }


//                    if (metrics == 2.0 && densty_dpi == 320) {
//                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) activity_main.getLayoutParams();
//
//                        System.out.println("met " + metrics);
//                        params.topMargin = 70;
//
//                    }

                    if (densty_dpi >=200 && densty_dpi <=299) {
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) activity_main.getLayoutParams();

                        System.out.println("met " + metrics);
                        params.topMargin = 100;

                    }

                    if (densty_dpi >=300 && densty_dpi <=399) {
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) activity_main.getLayoutParams();
                        ViewGroup.MarginLayoutParams paramsd = (ViewGroup.MarginLayoutParams) sabit2.getLayoutParams();
                        ViewGroup.MarginLayoutParams paramsdd = (ViewGroup.MarginLayoutParams) entrygir.getLayoutParams();
                        paramsd.topMargin = 25;
                        params.topMargin = 50;
                        paramsdd.leftMargin = 600;

                    }


                    if (densty_dpi >=500 && densty_dpi <=599){

                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) activity_main.getLayoutParams();
                        ViewGroup.MarginLayoutParams paramsd = (ViewGroup.MarginLayoutParams) sabit2.getLayoutParams();
                        ViewGroup.MarginLayoutParams paramsdd = (ViewGroup.MarginLayoutParams) entrygir.getLayoutParams();
                        paramsd.topMargin = 40;
                        params.topMargin = -30;
                        paramsdd.leftMargin = 1200;

                    }

//                    if (densty_dpi == 560 || densty_dpi == 540 || densty_dpi == 531 || densty_dpi == 568|| densty_dpi == 522 || densty_dpi == 576 || densty_dpi == 577 ||
//                            densty_dpi == 534 || densty_dpi == 521 || densty_dpi == 570 || densty_dpi == 571 || densty_dpi == 529 || densty_dpi == 572 || densty_dpi == 565
//                    || densty_dpi == 515 || densty_dpi == 510 || densty_dpi == 537 || densty_dpi == 523)  {
//
//
//                    }





                    if (baslikUzunlugu >= 40 && metrics == 3.0) {
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) recyclerView.getLayoutParams();
                        ViewGroup.MarginLayoutParams txt = (ViewGroup.MarginLayoutParams) sabit2.getLayoutParams();

                        System.out.println("met " + metrics);
                        params.topMargin = 150;
                        txt.topMargin = 20;
                    }

                    if (baslikUzunlugu >= 39) {

                        if (metrics == 2.0 || metrics == 1.75) {
                            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) recyclerView.getLayoutParams();
                            ViewGroup.MarginLayoutParams txt = (ViewGroup.MarginLayoutParams) sabit2.getLayoutParams();
                            System.out.println("met " + metrics);
                            params.topMargin = 10;
                            txt.topMargin = 15;
                        }
                    }


                    if (response.body() != null) {

                        say.setText(Integer.toString(response.body().getToplam()));
                        sabit2.setText(response.body().getName());


                    }

                    entrygir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            activity_main.setVisibility(View.INVISIBLE);

                            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) recyclerView.getLayoutParams();
                            params.topMargin = 10;

                            final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("al", Context.MODE_PRIVATE);
                            sharedPreferences.getString("sonuc", "sonuc");


                            if (densty_dpi == 260) {
                                ViewGroup.MarginLayoutParams paramsd = (ViewGroup.MarginLayoutParams) activity_main.getLayoutParams();
                                System.out.println("met " + metrics);
                                // bunun yuzunden
                                paramsd.topMargin = -90;

                            }


                            FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.activity_main, new AddCustomerViaSearchActivity());

                            ft.commit();
                        }
                    });

                } else {
                    final float densty_dpi = getResources().getDisplayMetrics().densityDpi;
                    final float densty_djpi = getResources().getDisplayMetrics().scaledDensity;
                    if (response.code() == 404) {



//                        if (densty_dpi == 540 || densty_dpi == 560) {
//                            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) linerButon.getLayoutParams();
//                            ViewGroup.MarginLayoutParams paramsd = (ViewGroup.MarginLayoutParams) bos2.getLayoutParams();
//                            ViewGroup.MarginLayoutParams paramsdd = (ViewGroup.MarginLayoutParams) isimcek.getLayoutParams();
//                            ViewGroup.MarginLayoutParams dsf = (ViewGroup.MarginLayoutParams) activity_main.getLayoutParams();
//                            paramsd.leftMargin = 100;
//                            params.leftMargin = 100;
//                            paramsdd.leftMargin = 90;
//                            //dsf.leftMargin = 100;
//                           linerButon.setPadding(5,5,5,5);
//                            System.out.println("evet");
//                        }else {
//                            System.out.println("hayir");
//                        }




                        entrygir.setVisibility(View.INVISIBLE);
                        bos2.setVisibility(View.VISIBLE);
                        sabit2.setVisibility(View.INVISIBLE);
                        say.setVisibility(View.INVISIBLE);
                        isimcek.setText(sharedPreferences.getString("sonuc", "sonuc"));
                        isimcek.setVisibility(View.VISIBLE);
                        nokta.setVisibility(View.INVISIBLE);
                        linerButon.setVisibility(View.VISIBLE);

                        Integer baslikUzunluk = isimcek.getText().length();


//                        if (baslikUzunluk >= 39) {
//
//                            if (metrics == 2.0 || metrics == 1.75) {
//
//                                ViewGroup.MarginLayoutParams ism = (ViewGroup.MarginLayoutParams) isimcek.getLayoutParams();
//                                System.out.println("met " + metrics);
//                             ism.bottomMargin = 400;
//
//
//                            }
//                        }

                        entry_yolla.setVisibility(View.VISIBLE);

                        Integer baslikUzunlugum = isimcek.length();

                        if (baslikUzunlugum >= 40) {
                            ViewGroup.MarginLayoutParams txt = (ViewGroup.MarginLayoutParams) isimcek.getLayoutParams();
                            txt.topMargin = 785;
                        }


                        entry_yolla.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                final SharedPreferences sharedPreferences = getActivity().getSharedPreferences("al", Context.MODE_PRIVATE);
                                sharedPreferences.getString("sonuc", "sonuc");

                                FragmentTransaction ft = getFragmentManager().beginTransaction().replace(R.id.aramaYap, new AddCustomerViaSearchActivity());
                                ft.commit();

                            }
                        });


                    }

                }
            }

            @Override
            public void onFailure(Call<ara_PARAM_response> call, Throwable t) {
                pDialog.dismiss();
            }
        });

        return view;
    }


}
