package com.example.py7.appbiodata;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class EditActivity extends AppCompatActivity {
    //memberikan deklarasi
    DBHelper helper;
    EditText TxNomor, TxNama, TxTempatLahir, TxTanggal, TxAlamat;
    Spinner SpJK;
    long id;
    DatePickerDialog datePickerDialog;
    SimpleDateFormat dateFormatter;
    //mengambil inisiasi dan membuat aksi dari layout activity_edit.xml
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        //koneksikan dengan DBHelper
        helper = new DBHelper(this);
        //mengambil dari id (default)
        id = getIntent().getLongExtra(DBHelper.row_id, 0);
        //memberikan inisiasi dari deklrasi diatas
        TxNomor = (EditText)findViewById(R.id.txNomor_Edit);
        TxNama = (EditText)findViewById(R.id.txNama_Edit);
        TxTempatLahir = (EditText)findViewById(R.id.txTempatLahir_Edit);
        TxTanggal = (EditText)findViewById(R.id.txTglLahir_Edit);
        TxAlamat = (EditText)findViewById(R.id.txAlamat_Edit);
        SpJK = (Spinner)findViewById(R.id.spJK_Edit);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

        TxTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });
        getData();
    }
    //memberikan format calendar
    private void showDateDialog() {
        Calendar calendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month, dayOfMonth);
                TxTanggal.setText(dateFormatter.format(newDate.getTime()));
            }
        },calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    //mengambil data dari row untuk di edit
    private void getData(){
        Cursor cursor = helper.oneData(id);
        if(cursor.moveToFirst()){
            String nomor = cursor.getString(cursor.getColumnIndex(DBHelper.row_nomor));
            String nama = cursor.getString(cursor.getColumnIndex(DBHelper.row_nama));
            String tempatLahir = cursor.getString(cursor.getColumnIndex(DBHelper.row_tempatLahir));
            String jk = cursor.getString(cursor.getColumnIndex(DBHelper.row_jk));
            String tanggal = cursor.getString(cursor.getColumnIndex(DBHelper.row_tglLahir));
            String alamat = cursor.getString(cursor.getColumnIndex(DBHelper.row_alamat));
            //ubar nomor dan nama
            TxNomor.setText(nomor);
            TxNama.setText(nama);
            //ubah jenis kelamin
            if (jk.equals("Laki-laki")){
                SpJK.setSelection(0);
            }else if(jk.equals("Perempuan")){
                SpJK.setSelection(1);
            }
            //ubah tempat lahir, tanggal lahir, alamat
            TxTempatLahir.setText(tempatLahir);
            TxTanggal.setText(tanggal);
            TxAlamat.setText(alamat);
        }
    }
    //opsi ubah data
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }
    //penyimpanan file yang sudah di edit
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_edit:
                String nomor = TxNomor.getText().toString().trim();
                String nama = TxNama.getText().toString().trim();
                String tempatLahir = TxTempatLahir.getText().toString().trim();
                String tanggal = TxTanggal.getText().toString().trim();
                String alamat = TxAlamat.getText().toString().trim();
                String jk = SpJK.getSelectedItem().toString().trim();
    //koneksi dengan DBHelper dan penyesuaian pada row
                ContentValues values = new ContentValues();
                values.put(DBHelper.row_nomor, nomor);
                values.put(DBHelper.row_nama, nama);
                values.put(DBHelper.row_tempatLahir, tempatLahir);
                values.put(DBHelper.row_tglLahir, tanggal);
                values.put(DBHelper.row_alamat, alamat);
                values.put(DBHelper.row_jk, jk);

                if (nomor.equals("") || nama.equals("") || tempatLahir.equals("") || tanggal.equals("") || alamat.equals("")){
                    Toast.makeText(EditActivity.this, "Data tidak boleh kosong", Toast.LENGTH_SHORT);
                }else{
                    helper.updateData(values, id);
                    Toast.makeText(EditActivity.this, "Data Tersimpan", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
        //pilihan untuk dihapus pada menu edit
        switch (item.getItemId()){
            case R.id.delete_edit:
                AlertDialog.Builder builder = new AlertDialog.Builder(EditActivity.this);
                builder.setMessage("Data ini akan dihapus.");
                builder.setCancelable(true);
                builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        helper.deleteData(id);
                        Toast.makeText(EditActivity.this, "Data Terhapus", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
        }
        //opsi klik item
        return super.onOptionsItemSelected(item);
    }
}
