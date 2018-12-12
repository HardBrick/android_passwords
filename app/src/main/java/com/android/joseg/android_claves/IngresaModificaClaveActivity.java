package com.android.joseg.android_claves;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.joseg.android_claves.Modelo.Password;
import com.android.joseg.android_claves.Modelo.Usuario;
import com.android.joseg.android_claves.Repositorio.AdminSQLiteOpenHelper;

public class IngresaModificaClaveActivity extends AppCompatActivity {

    EditText etTituloClave;
    EditText etUrlClave;
    EditText etUserClave;
    EditText etPasswordClave;
    EditText etObservacionesClave;
    Button btnGuardarClave;
    Button btnLimpiarClave;

    AdminSQLiteOpenHelper adminBD = new AdminSQLiteOpenHelper(this,"administracion",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresa_modifica_clave);

        etTituloClave = (EditText)findViewById(R.id.etTituloClave);
        etUrlClave = (EditText)findViewById(R.id.etUrlClave);
        etUserClave = (EditText)findViewById(R.id.etUserClave);
        etPasswordClave = (EditText)findViewById(R.id.etPasswordClave);
        etObservacionesClave = (EditText)findViewById(R.id.etObservacionesClave);

        btnGuardarClave = (Button)findViewById(R.id.btnGuardarClave);
        btnLimpiarClave = (Button)findViewById(R.id.btnLimpiarClave);

        String userReg = null;
        Password pass = null;
        try{
             userReg = getIntent().getStringExtra("user");
             pass = (Password) getIntent().getSerializableExtra("pass");
        }catch (Exception e){
        }

        final String userRegistrado = userReg;
        final Password passwordAsoc = pass;



        btnGuardarClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = etTituloClave.getText().toString();
                String url = etUrlClave.getText().toString();
                String user = etUserClave.getText().toString();
                String pass = etPasswordClave.getText().toString();
                String obs = etObservacionesClave.getText().toString();

                if(titulo.isEmpty()){
                    etTituloClave.setError("");
                }
                if(url.isEmpty()){
                    etUrlClave.setError("");
                }
                if(user.isEmpty()){
                    etUserClave.setError("");
                }
                if(pass.isEmpty()){
                    etPasswordClave.setError("");
                }

                if(!titulo.isEmpty() && !url.isEmpty() && !user.isEmpty() && !pass.isEmpty()){
                    Password password = new Password(
                            titulo,
                            url,
                            user,
                            pass,
                            obs,
                            ((passwordAsoc == null) ? userRegistrado : passwordAsoc.getUserDueno())
                    );

                    if(passwordAsoc==null){
                        adminBD.insertClave(password,IngresaModificaClaveActivity.this);
                    }else{
                        adminBD.modificarClave(password,IngresaModificaClaveActivity.this);
                    }
                    Toast.makeText(IngresaModificaClaveActivity.this, "Clave guardada con éxito!", Toast.LENGTH_SHORT).show();
                }





            }
        });


    }
}
