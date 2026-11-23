package com.example.r1_navdrawe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseHelper extends SQLiteOpenHelper {

    private static final String BD_NOMBRE = "diskitos";
    public BaseHelper(Context context) {
        super(context, BD_NOMBRE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tablita("+
                "clave TEXT PRIMARY KEY,"+
                "nombre TEXT,"+
                "precio REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS tablita");
        onCreate(db);
    }

    public boolean crear (String clave, String nombre, double precio){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues datos = new ContentValues();
        datos.put("clave",clave);
        datos.put("nombre",nombre);
        datos.put("precio",precio);


        long resultado = db.insert("tablita", null,datos);
        return resultado !=-1;
    }

    public Cursor consultar ( String clave){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery ("SELECT * FROM tablita WHERE clave= ?",
        new String[]{clave});

    }
}
