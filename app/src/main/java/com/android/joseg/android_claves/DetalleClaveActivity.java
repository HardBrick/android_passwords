package com.android.joseg.android_claves;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.joseg.android_claves.Modelo.Password;
import com.android.joseg.android_claves.Repositorio.AdminSQLiteOpenHelper;

public class DetalleClaveActivity extends AppCompatActivity {


    TextView tvTituloClaveDetalle;
    TextView tvUrlClaveDetalle;
    TextView tvUserClaveDetalle;
    TextView tvPassClaveDetalle;
    TextView tvObservacionClaveDetalle;
    Button btnEditarClave;
    Button btnEliminarClave;

    AdminSQLiteOpenHelper adminBD = new AdminSQLiteOpenHelper(this,"administracion",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_clave);

        tvTituloClaveDetalle =(TextView)findViewById(R.id.tvTituloClaveDetalle);
        tvUrlClaveDetalle =(TextView)findViewById(R.id.tvUrlClaveDetalle);
        tvUserClaveDetalle =(TextView)findViewById(R.id.tvUserClaveDetalle);
        tvPassClaveDetalle =(TextView)findViewById(R.id.tvPassClaveDetalle);
        tvObservacionClaveDetalle =(TextView)findViewById(R.id.tvObservacionClaveDetalle);
        btnEditarClave = (Button)findViewById(R.id.btnEditarClave);
        btnEliminarClave = (Button)findViewById(R.id.btnEliminarClave);

        final Password password = (Password)(getIntent().getSerializableExtra("pass"));


        btnEliminarClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(DetalleClaveActivity.this);
                builder.setMessage(R.string.msgDeletePassword)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                adminBD.eliminarClave(password,DetalleClaveActivity.this);
                                Toast.makeText(DetalleClaveActivity.this, "Clave Eliminada con Exito", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        })
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        }).create().show();
            }
        });

        btnEditarClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalleClaveActivity.this, IngresaModificaClaveActivity.class);
                intent.putExtra("pass",password);
                startActivity(intent);
            }
        });

    }
}
