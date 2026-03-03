package com.example.profi26.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.profi26.R;

public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_screen);

        if (!isOnline()) {
            Intent noInternet = new Intent(MainScreenActivity.this, NoInternetActivity.class);
            startActivity(noInternet);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = manager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public void guessTheAnimal(View view) {
        Intent activity = new Intent(MainScreenActivity.this, GuessTheAnimalActivity.class);
        startActivity(activity);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void wordPractice(View view) {
        Intent activity = new Intent(MainScreenActivity.this, wordpractiseActivity.class);
        startActivity(activity);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void audition(View view) {
        Intent activity = new Intent(MainScreenActivity.this, AusitionActivity.class);
        startActivity(activity);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void game(View view) {
        Intent activity = new Intent(MainScreenActivity.this, GameActivity.class);
        startActivity(activity);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void profile(View view) {
        Intent activity = new Intent(MainScreenActivity.this, ProfileActivity.class);
        startActivity(activity);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}