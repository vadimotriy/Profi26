package com.example.profi26.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.profi26.R;
import com.example.profi26.model.SharedManger;

public class OnBoarding2Activity extends AppCompatActivity {
    private SharedManger manager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Base_Theme_Profi26);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_on_boarding2);

        manager = SharedManger.getInstance(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void changeActivity(View view) {
        manager.checkedOnBoarding2();

        Intent onBoarding2 = new Intent(OnBoarding2Activity.this, OnBoarding3Activity.class);
        startActivity(onBoarding2);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void skip(View view) {
        Intent languageSelect = new Intent(OnBoarding2Activity.this, LanguageSelectActivity.class);
        startActivity(languageSelect);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}