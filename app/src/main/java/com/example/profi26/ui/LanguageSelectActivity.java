package com.example.profi26.ui;

import android.os.Build;
import android.os.Bundle;
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
import com.google.android.material.button.MaterialButton;

public class LanguageSelectActivity extends AppCompatActivity {
    MaterialButton buttonRussian, buttonEnglish, buttonChinese, buttonBelarus, buttonKazakh, lastChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Base_Theme_Profi26);

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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void chooseLanguage(View view) {
    }

    public void languageRussia(View view) {
    }

    public void languageEnglish(View view) {
    }

    public void languageChinese(View view) {
    }

    public void languageBelarus(View view) {
    }

    public void languageKazakh(View view) {
    }
}