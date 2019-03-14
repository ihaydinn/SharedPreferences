package com.ismailhakkiaydin.sharedpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText edtIsim, edtMeslek;
    private TextView txtIsim, txtMeslek, txtId;
    private Switch switchCompat;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sayfaArkaPlaniDegistir(isChecked);
            }
        });

        //Dosyaya yazılan ayarları getirmek için
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + ".myFile.xml", MODE_PRIVATE);
        boolean isChecked = sharedPreferences.getBoolean(Sabitler.KEY_RENK, false);
        switchCompat.setChecked(isChecked);



    }

    private void sayfaArkaPlaniDegistir(boolean isChecked) {

        //switch butonunun durumunu dosyaya kaydetmek için
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName() + ".myFile.xml", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(Sabitler.KEY_RENK, isChecked);
        editor.apply();

         /*
         if(isChecked==true){
         layout.setBackgroundColor(Color.GREEN);
         }else
         layout.setBackgroundColor(Color.WHITE);
         */
        linearLayout.setBackgroundColor(isChecked ? Color.CYAN: Color.WHITE);

    }

    private void init() {
        edtIsim = findViewById(R.id.etName);
        edtMeslek = findViewById(R.id.etProfession);
        txtIsim = findViewById(R.id.txvName);
        txtMeslek = findViewById(R.id.txvProfession);
        txtId = findViewById(R.id.txvId);
        switchCompat = findViewById(R.id.pageColorSwitch);
        linearLayout = findViewById(R.id.pageLayout);
    }

    public void saveAccountData(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName()+".myFile.xml", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(Sabitler.KEY_ISIM, edtIsim.getText().toString());
        editor.putString(Sabitler.KEY_MESLEK, edtMeslek.getText().toString());
        editor.putInt(Sabitler.KEY_ID,24);

        editor.apply();
        Toast.makeText(this, "Veriler Kayıt Edildi.", Toast.LENGTH_LONG).show();
        //editor.commit();

    }

    public void loadAccountData(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName()+".myFile.xml", MODE_PRIVATE);
        String isim = sharedPreferences.getString(Sabitler.KEY_ISIM, "N/A");
        String meslek = sharedPreferences.getString(Sabitler.KEY_MESLEK, "N/A");
        int id = sharedPreferences.getInt(Sabitler.KEY_ID,0);

        txtIsim.setText(isim);
        txtMeslek.setText(meslek);
        txtId.setText(""+id);

    }

    public void newActivity(View view) {

        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);

    }
}
