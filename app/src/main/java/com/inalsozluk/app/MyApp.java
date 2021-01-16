package com.inalsozluk.app;

import android.app.Application;

//import com.facebook.stetho.Stetho;
//import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Emre on 18/07/2018.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
      //  Stetho.initializeWithDefaults(this);

//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
        // Normal app init code...
    }
}
