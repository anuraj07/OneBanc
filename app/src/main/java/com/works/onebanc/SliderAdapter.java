package com.works.onebanc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder> {

    private Context context;
    private ArrayList<SliderModel> item;

    public SliderAdapter(Context context, ArrayList<SliderModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(LayoutInflater.from(context).inflate(R.layout.cuisine_category,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, final int position) {
        final int realPos = position%item.size();
        holder.itemTitle.setText(item.get(realPos).getTitle());
        holder.itemImage.setImageResource(item.get(realPos).getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.get(realPos).getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,OrderActivity.class);
                Bundle data = new Bundle();
                data.putString("CuisineName",item.get(realPos).getTitle());
                intent.putExtras(data);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
//        return item.size();
    }

    public class SliderViewHolder extends RecyclerView.ViewHolder{

        private ImageView itemImage;
        private TextView itemTitle;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = itemView.findViewById(R.id.cuisine_img);
            itemTitle = itemView.findViewById(R.id.cuisine_name);

        }
    }

}
