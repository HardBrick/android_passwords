package com.android.joseg.android_claves.Repositorio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.joseg.android_claves.ListaClavesActivity;
import com.android.joseg.android_claves.Modelo.Password;
import com.android.joseg.android_claves.Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table usuario(user text primary key, password text)");
        db.execSQL("create table password(titulo text primary key, url text, user text, pass text, observaciones text, user_prop text, FOREIGN KEY(user_prop) REFERENCES usuario(user))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists usuario");
        db.execSQL("drop table if exists password");
        db.execSQL("create table usuario(user text primary key, password text)");
        db.execSQL("create table password(titulo text primary key, url text, user text, pass text, observaciones text, user_prop text, FOREIGN KEY(user_prop) REFERENCES usuario(user))");


    }

    public List<Password> obtenerClavesDeUsaurio(Usuario usuarioProp, Context context){
        AdminSQLiteOpenHelper adminBD = new AdminSQLiteOpenHelper(context,"administracion",null,1);
        SQLiteDatabase db = adminBD.getWritableDatabase();

        String query = "select titulo, url, user, pass, observaciones, user_prop from password where user_prop = " + "'"+usuarioProp.getUser() + "'";
        Cursor cursor = db.rawQuery(query,null);
        List<Password> lista = new ArrayList<>();

        if (cursor.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                Password passwordRow = new Password(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5)
                );
                lista.add(passwordRow);
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lista;
    }

    public boolean insertClave(Password password, Context context){
        AdminSQLiteOpenHelper adminBD = new AdminSQLiteOpenHelper(context,"administracion",null,1);
        SQLiteDatabase db = adminBD.getWritableDatabase();

        ContentValues registro = new ContentValues();

        registro.put("titulo", password.getTitulo());
        registro.put("url", password.getUrl());
        registro.put("user", password.getUser());
        registro.put("pass", password.getPass());
        registro.put("observaciones", password.getObservaciones());
        registro.put("user_prop", password.getUserDueno());

        // los inserto en la base de datos
        db.insert("password", null, registro);
        db.close();
        return true;
    }

}
