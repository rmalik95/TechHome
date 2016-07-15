package com.techHome.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.techHome.R;
import com.techHome.util.Splash;

/**
 * Created by Dell on 4/13/2016.
 */


//Splash Screen

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    Toast.makeText(SplashActivity.this, "Some Error Occured", Toast.LENGTH_LONG).show();
                } finally {
                    Splash splash = new Splash();
                    if (splash.isLogin(SplashActivity.this)){
                        Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    }else {
                        Intent intent = new Intent(SplashActivity.this, EnterModeActivity.class);
                        startActivity(intent);
                    }
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void onBackPressed() {

    }
}
