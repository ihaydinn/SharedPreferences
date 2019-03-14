package com.ismailhakkiaydin.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView tvIsim, tvMeslek;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvIsim = findViewById(R.id.txvName);
        tvMeslek = findViewById(R.id.txvProfession);

    }

    public void loadAccountData(View view) {

        SharedPreferences sharedPreferences=getSharedPreferences(getPackageName()+".myFile.xml", MODE_PRIVATE);
        String isim=sharedPreferences.getString(Sabitler.KEY_ISIM,"N/A");
        String meslek=sharedPreferences.getString(Sabitler.KEY_MESLEK, "N/A");
        int id=sharedPreferences.getInt(Sabitler.KEY_ID, 0);

        tvIsim.setText(isim);
        tvMeslek.setText(meslek + " - " + ""+id);

    }

    public void clearAccountData(View view) {
        SharedPreferences sharedPreferences=getSharedPreferences(getPackageName()+".myFile.xml", MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();

        editor.clear();
        editor.apply();
        //editor.commit()

    }

    public void removeProfessionKey(View view) {

        SharedPreferences sharedPreferences=getSharedPreferences(getPackageName()+".myFile.xml", MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.remove(Sabitler.KEY_ID);
        editor.apply();

    }
}
