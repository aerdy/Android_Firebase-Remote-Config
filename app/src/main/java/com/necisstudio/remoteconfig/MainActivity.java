package com.necisstudio.remoteconfig;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class MainActivity extends AppCompatActivity {

    private static final String FONT_CONFIG_KEY = "font";

    FirebaseRemoteConfig mFirebaseRemoteConfig = ConfigApp.mFirebaseRemoteConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFeatch = (Button) findViewById(R.id.button);
        btnFeatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchDiscount();
            }
        });
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build();
        mFirebaseRemoteConfig.setConfigSettings(configSettings);
        mFirebaseRemoteConfig.setDefaults(R.xml.remotemain);
        fetchDiscount();
    }

    private void fetchDiscount() {
        mFirebaseRemoteConfig.setDefaults(R.xml.remotemain);
        mFirebaseRemoteConfig.fetch()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.e("remote", "Fetch Succeeded");
                            mFirebaseRemoteConfig.activateFetched();
                        } else {
                            Log.e("remote", "Fetch failed");
                        }
                        displayPrice();
                    }
                });
    }

    private void displayPrice() {
        Log.e("remote data", "" + (mFirebaseRemoteConfig.getString(FONT_CONFIG_KEY)));
    }
}
