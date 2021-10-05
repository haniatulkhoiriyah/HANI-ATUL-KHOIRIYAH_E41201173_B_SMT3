package com.example.autocompletetextviewhani;
//import untuk support source code yang digunakan
import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends Activity {
    //nama-nama dalam list menggunakan string
    private static final String[] RESTO = new String[]{
            "Kopi Maryam","Taman Mangli Indah","Graffity Jaya Jember",
            "Rumah Makan Saponyono","Lagian Restaurant","LiDoy Kitchen",
            "Resto Ikan Goreng Cianjur Jember","Grand Cafe Jember","Foodgasm Bistro",
            "Queen Cafe and Resto Jember", "Legian Restaurant", "GloVic Coffee"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //menghubungkan nama-nama dalam list menggunakan string dan adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, RESTO);

        //menghubungkan autocompletetextview dengan source code yang ada pada activity_main.xml
        //menampilkan daftar saran dalam menu drop down, pengguna hanya dapat memilih satu nilai.
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.resto_list);
        textView.setAdapter(adapter);
    }
}