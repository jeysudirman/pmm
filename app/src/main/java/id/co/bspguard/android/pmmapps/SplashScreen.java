package id.co.bspguard.android.pmmapps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(SplashScreen.this);
        String email = prefs.getString("email", null);
        String password = prefs.getString("password", null);

        final Handler handler = new Handler();

        if (email != null && password != null) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }, 3000L); //3000 L = 3 detik
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    finish();
                }
            }, 3000L); //3000 L = 3 detik
        }


    }
}
