package com.example.listviewhani;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    //Yang akan muncul pada ListView
    String items[] = { "Kopi Maryam","Taman Mangli Indah","Graffity Jaya Jember",
            "Rumah Makan Saponyono","Lagian Restaurant","LiDoy Kitchen",
            "Resto Ikan Goreng Cianjur Jember","Grand Cafe Jember","Foodgasm Bistro",
            "Queen Cafe and Resto Jember", "Legian Restaurant", "GloVic Coffee",
            "Cafe Cangkir Kita", "MOX Cafe", "Kedai Apresiasi Jember", "My Way Coffe"
    };

    @Override
        public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Memasukkan ListView
        //Adapter ditugaskan ke ListView melalui setAdapter pada objek ListView
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items));
    }
    //Action klik atau method apabila diklik muncul keterangan seperti alert
    public void onListItemClick(ListView parent, View v, int position, long id) {
        Toast.makeText(this, ((TextView) v).getText(), Toast.LENGTH_SHORT).show();
    }
}