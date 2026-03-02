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

/* Класс OnBoarding1Activity
 * Назначение: окрытие активности activity_on_boarding1
 * Дата создания: 01.03.2026
 * Автор создания: Блинов Вадим
 */
public class OnBoarding1Activity extends AppCompatActivity {
    /* Метод onCreate
     * Метод активируется при создании активности
     * Метод открывает саму активность, меняет тему и проверяет наличие интренета
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Base_Theme_Profi26);

        if (!isOnline()) {
            changeToNoInternet();
        }

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_on_boarding1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /* Метод changeActivity
     * Метод активируется при нажатии на кнопку Next
     * Метод служит для переход на активность activity_on_boarding2
     */
    public void changeActivity(View view) {
        Intent onBoarding2 = new Intent(OnBoarding1Activity.this, OnBoarding2Activity.class);
        startActivity(onBoarding2);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /* Метод skip
     * Метод активируется при нажатии на текст skip onboarding
     * Метод служит для переход на активность activity_language_select
     */
    public void skip(View view) {
        Intent languageSelect = new Intent(OnBoarding1Activity.this, LanguageSelectActivity.class);
        startActivity(languageSelect);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /* Метод isOnline
     * Метод активируется из метода onCreate
     * Метод служит для проверки наличия интернета
     */
    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = manager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public void changeToNoInternet() {
        Intent noInternet = new Intent(OnBoarding1Activity.this, NoInternetActivity.class);
        startActivity(noInternet);
    }
}