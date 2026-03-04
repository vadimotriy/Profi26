package com.example.profi26.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.profi26.R;
import com.example.profi26.model.SharedManger;
import com.google.android.material.button.MaterialButton;

public class LanguageSelectActivity extends AppCompatActivity {
    MaterialButton buttonRussian, buttonEnglish, buttonChinese, buttonBelarus, buttonKazakh, lastChecked;
    SharedManger manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Base_Theme_Profi26);

        manager = SharedManger.getInstance(this);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_language_select);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.deep_blue));

        buttonRussian = findViewById(R.id.chooseRussian);
        buttonEnglish = findViewById(R.id.chooseEnglish);
        buttonChinese = findViewById(R.id.chooseChinese);
        buttonBelarus = findViewById(R.id.chooseBelarus);
        buttonKazakh = findViewById(R.id.chooseKazakh);
        lastChecked = null;

        View.OnClickListener toggleChecked = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaterialButton clickedButton = (MaterialButton) v;

                manager.setLanguage(clickedButton.getText().toString());

                if (lastChecked != null) {
                    lastChecked.setEnabled(true);
                }

                clickedButton.setEnabled(false);
                lastChecked = clickedButton;
            }
        };

        buttonRussian.setOnClickListener(toggleChecked);
        buttonEnglish.setOnClickListener(toggleChecked);
        buttonChinese.setOnClickListener(toggleChecked);
        buttonBelarus.setOnClickListener(toggleChecked);
        buttonKazakh.setOnClickListener(toggleChecked);

        switch (manager.getLanguage()) {
            case "Russian":
                buttonRussian.setEnabled(false); lastChecked = buttonRussian; break;
            case "English":
                buttonEnglish.setEnabled(false); lastChecked = buttonEnglish; break;
            case "Chinese":
                buttonChinese.setEnabled(false); lastChecked = buttonChinese; break;
            case "Belarus":
                buttonBelarus.setEnabled(false); lastChecked = buttonBelarus; break;
            case "Kazakh":
                buttonKazakh.setEnabled(false); lastChecked = buttonKazakh; break;
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void chooseLanguage(View view) {
        Intent login = new Intent(LanguageSelectActivity.this, LgoinActivity.class);
        startActivity(login);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}