package com.example.profi26.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.profi26.R;
import com.example.profi26.model.Api;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import retrofit2.Call;

public class MainScreenActivity extends AppCompatActivity {
    TextView name;
    private boolean isMicOn = false;

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

        name = findViewById(R.id.main_name);
        String d;


        Retrofit r = new Retrofit.Builder().baseUrl("https://api-junior.matule.ru/api/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        r.create(Api.class).auth(Map.of("identity", "nynail@gmail.com", "password", "123456789"))
                .enqueue(new Callback<>() {
                    public void onResponse(Call<ResponseBody> c, Response<ResponseBody> res) {
                        try {
                            String firstname = new JSONObject(res.body().string())
                                    .getJSONObject("record").getString("firstname");

                            name.setText("Hello, " + firstname);


                        } catch (Exception e) {
                            Toast.makeText(MainScreenActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                    public void onFailure(Call<ResponseBody> c, Throwable t) { t.printStackTrace(); }
                });


        r.create(Api.class).getWords()  // ваш метод API
                .enqueue(new Callback<>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            // 🔹 Парсим JSON вручную, как в вашем примере
                            JSONObject json = new JSONObject(response.body().string());

                            // 🔹 Получаем ПЕРВЫЙ элемент массива "items" и значение "ru"
                            String russian = json
                                    .getJSONArray("items")      // массив items
                                    .getJSONObject(0)           // первый элемент [0]
                                    .getString("ru");           // поле "ru" → "летать"

                            // 🔹 Обновляем UI
                            name.setText(russian);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO}, 100);
        }


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
            System.out.println(1);
        } else {
            Toast.makeText(this, "Нет разрешения", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Разрешение получено", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Разрешение отклонено", Toast.LENGTH_SHORT).show();
            }
        }
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