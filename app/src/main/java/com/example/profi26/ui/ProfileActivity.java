package com.example.profi26.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.profi26.R;
import com.example.profi26.model.SharedManger;
import com.google.android.material.button.MaterialButton;

public class ProfileActivity extends AppCompatActivity {

    SharedManger manager;
    MaterialButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        manager = SharedManger.getInstance(this);
        button = findViewById(R.id.swicthTo);

        if (manager.getNightTheme()) {
            button.setText("Switch to Light");
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void changeImage(View view) {

    }

    public void changeLanguage(View view) {
        Intent activity = new Intent(ProfileActivity.this, LanguageSelectActivity.class);
        startActivity(activity);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void switchTo(View view) {
        MaterialButton button = (MaterialButton) view;

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            // Включаем светлую
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            manager.setNightTheme(false);
        } else {
            // Включаем темную
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            manager.setNightTheme(true);
        }
    }

}