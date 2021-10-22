package com.example.py7.appbiodata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    //memberikan akses database dan tabel
    public static final String database_name = "db_biodata";
    public static final String table_name = "tabel_biodata";
    //akses row item pada menu id sampai alamat untuk id default
    public static final String row_id = "_id";
    public static final String row_nomor = "Nomor";
    public static final String row_nama = "Nama";
    public static final String row_jk = "JK";
    public static final String row_tempatLahir = "TempatLahir";
    public static final String row_tglLahir = "Tanggal";
    public static final String row_alamat = "Alamat";
    //akses SQLite Database
    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, database_name, null, 2);
        db = getWritableDatabase();
    }
    //membuat inisiasi dalam SQLiteDatabase dan membarikan inisiasi dari deklarasi row
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name + "(" + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_nomor + " TEXT, " + row_nama + " TEXT, " + row_jk + " TEXT, "
                + row_tempatLahir + " TEXT, " + row_tglLahir + " TEXT, " + row_alamat + " TEXT)";
        db.execSQL(query);
    }
    //onUpgrade untuk mengubah
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int x) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
    }
    //mendapatkan semua data
    public Cursor allData(){
        Cursor cur = db.rawQuery("SELECT * FROM " + table_name, null);
        return cur;
    }
    //mendapatkan 1 data dari id
    public Cursor oneData(Long id){
        Cursor cur = db.rawQuery("SELECT * FROM " + table_name + " WHERE " + row_id + "=" + id, null);
        return cur;
    }
    //memasukkan data ke database
    public void insertData(ContentValues values){
        db.insert(table_name, null, values);
    }
    //mengupdate data
    public void updateData(ContentValues values, long id){
        db.update(table_name, values, row_id + "=" + id, null);
    }
    //menghapus data
    public void deleteData(long id){
        db.delete(table_name, row_id + "=" + id, null);
    }
}
