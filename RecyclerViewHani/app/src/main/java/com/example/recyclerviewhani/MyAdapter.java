package com.example.recyclerviewhani;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    //gunakan tipedata string untuk text, int untuk gambar, panggil Context untuk variable context
    String data1[], data2[];
    int images[];
    Context context;

    //hubungkan variable diatas dengan variable yang sudah dibuat dalam MainActivity.java
    public MyAdapter(Context ct, String s1[], String s2[], int img[]) {
        context = ct;
        data1 = s1;
        data2 = s2;
        images = img;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    //untuk menyesuaikan posisi letak gambar
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.myText1.setText(data1[position]);
        holder.myText2.setText(data2[position]);
        holder.myImage.setImageResource(images[position]);

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra("data1", data1[position]);
                intent.putExtra("data2", data2[position]);
                intent.putExtra("myImage", images[position]);
                context.startActivity(intent);
            }
        });
    }

    //untuk meletakkan hitungan atau panjang image yang ada dalam array
    @Override
    public int getItemCount() {
        return images.length;
    }

    //MyViewHolder mengambil dari RecyclerView.viewholder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        //gunakan element textview untuk text, Imageview untuk image, dan layout saya menggunakan elemtn ConstraintLayout
        TextView myText1, myText2;
        ImageView myImage;
        ConstraintLayout mainLayout;

        //panggil yang ada dalam MyViewHolder supaya bisa muncul menggunakan @NonNull View itemView
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.myText1);
            myText2 = itemView.findViewById(R.id.myText2);
            myImage = itemView.findViewById(R.id.myImageView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
