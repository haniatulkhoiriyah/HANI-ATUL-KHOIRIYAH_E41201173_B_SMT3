package com.example.recyclerviewhani;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    //tipe data dan variable untuk array
    String s1[], s2[];
    int images[] = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //panggil recyclerview dari activity_main.xml
        recyclerView = findViewById(R.id.recyclerView);

        //munculkan array yang sudah dibuat dan panggil array dari strings.xml
        s1 = getResources().getStringArray(R.array.movie);
        s2 = getResources().getStringArray(R.array.description);

        //menghubungkan menggunakan adapter variable s1 s2 dan imager
        MyAdapter myAdapter = new MyAdapter(this, s1, s2, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}