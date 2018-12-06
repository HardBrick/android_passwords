package com.android.joseg.android_claves;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ListaClavesActivity extends AppCompatActivity {

    TextView tvUserLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_claves);


        tvUserLista = (TextView) findViewById(R.id.tvUserLista);

        try{
            String user = getIntent().getExtras().getString("user");
            tvUserLista.setText(user);
        }catch (Exception e){
            Log.d("TAG_", e.getMessage());
        }


    }
}
