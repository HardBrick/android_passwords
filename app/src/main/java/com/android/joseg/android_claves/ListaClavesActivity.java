package com.android.joseg.android_claves;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.joseg.android_claves.Modelo.Password;
import com.android.joseg.android_claves.Modelo.Usuario;
import com.android.joseg.android_claves.Repositorio.AdminSQLiteOpenHelper;

import java.util.List;

public class ListaClavesActivity extends AppCompatActivity {

    TextView tvUserLista;
    ListView lvPasswords;
    Button btnActualizarVista;
    FloatingActionButton floatbtnNewPasswordList;
    AdminSQLiteOpenHelper adminBD = new AdminSQLiteOpenHelper(this,"administracion",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_claves);


        tvUserLista = (TextView) findViewById(R.id.tvUserLista);
        lvPasswords = (ListView) findViewById(R.id.lvPasswords);
        btnActualizarVista = (Button) findViewById(R.id.btnActualizarVista);
        floatbtnNewPasswordList = (FloatingActionButton) findViewById(R.id.floatbtnNewPasswordList);

       final String userReg = getIntent().getStringExtra("user");
        try{

            tvUserLista.setText(userReg);
            List<Password> listaPasswords = adminBD.obtenerClavesDeUsaurio(userReg,this);
            ArrayAdapter<Password> adapter = new ArrayAdapter<Password>(this,android.R.layout.simple_list_item_1,listaPasswords);
            lvPasswords.setAdapter(adapter);


        }catch (Exception e){
            Log.d("TAG_", e.getMessage());
        }


        floatbtnNewPasswordList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaClavesActivity.this, IngresaModificaClaveActivity.class);
                intent.putExtra("user",userReg);
                startActivity(intent);
            }
        });

        btnActualizarVista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Password> listaPasswords = adminBD.obtenerClavesDeUsaurio(userReg,ListaClavesActivity.this);
                ArrayAdapter<Password> adapter = new ArrayAdapter<Password>(ListaClavesActivity.this,android.R.layout.simple_list_item_1,listaPasswords);
                lvPasswords.setAdapter(adapter);
            }
        });

        lvPasswords.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Password pass = (Password) lvPasswords.getItemAtPosition(i);
                Intent intent = new Intent(ListaClavesActivity.this,DetalleClaveActivity.class);
                intent.putExtra("pass",pass);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
