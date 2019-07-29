package com.exoapp.basededatos;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        //BaseDeDatos.execSQL("CREATE TABLE ciudad(codigo int PRIMARY KEY, descripcion text)");
        BaseDeDatos.execSQL("CREATE TABLE universidad(codigo int PRIMARY KEY, tipo_institucion int, direccion text, correo text, telef text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
