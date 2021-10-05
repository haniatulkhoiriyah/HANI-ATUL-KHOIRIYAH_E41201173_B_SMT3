package com.example.spinnerhani;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinnermakan = (Spinner) findViewById(R.id.spinnermakan);
        spinnermakan.setOnItemSelectedListener(new MyOnItemSelectedListener());
        // pengisian items spinner yang dipanggil dari strings.xml
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.makanan_array,
                android.R.layout.simple_spinner_dropdown_item);
        adapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnermakan.setAdapter(adapter);
    }

    public class MyOnItemSelectedListener implements OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                   long id) {
            // TODO Auto-generated method stub
            Toast
                    .makeText(parent.getContext(),
                            parent.getItemAtPosition(pos).toString(),
                            Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }
}