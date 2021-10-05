package com.example.recyclerviewhani;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    //ImageView dan TextView untuk memanggil yang sudah dibuat dari activity_second.xml
    ImageView mainImageView;
    TextView title, description;

    //menggunakan string untuk tipedata text dan int untuk gambar
    String data1, data2;
    int myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mainImageView = findViewById(R.id.mainImageView);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        //aktifkan getData dan setData
        getData();
        setData();
    }
    //buat private void getData untuk mendapatkan data data dari variable diatas
    private void getData() {
        if(getIntent().hasExtra("myImage") && getIntent().hasExtra("data1") && getIntent().hasExtra("data2")){
            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            myImage = getIntent().getIntExtra("myImage", 1);
        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_LONG).show();
        }
    }
    //buat private void setData untuk menyesuaikan data dari title, description, dan image
    private void setData() {
        title.setText(data1);
        description.setText(data2);
        mainImageView.setImageResource(myImage);
    }
}
