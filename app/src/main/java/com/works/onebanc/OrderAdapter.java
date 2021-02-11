package com.works.onebanc;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{

    int price=0;
    private Context context;
    private ArrayList<OrderModel> item;

    public OrderAdapter(Context context, ArrayList<OrderModel> item) {
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderAdapter.OrderViewHolder(LayoutInflater.from(context).inflate(R.layout.order_image,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, final int position) {
        holder.dishName.setText(item.get(position).getDishName());
        holder.dishPrice.setText("RS: "+item.get(position).getPrice());
        holder.imageView.setImageResource(item.get(position).getImage());
        final TextView currentQuant = holder.itemView.findViewById(R.id.current_quant);
        Button Inc = holder.itemView.findViewById(R.id.inc);
        Button Dec = holder.itemView.findViewById(R.id.dec);


        Inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(currentQuant.getText().toString());
                num++;
                price = price+Integer.parseInt(item.get(position).getPrice());
                currentQuant.setText(String.valueOf(num));
                Log.d("check", String.valueOf(price));
                Intent intent = new Intent("custom-message");
                intent.putExtra("price",String.valueOf(price));
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });

        Dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(currentQuant.getText().toString());

                if (num>0){
                    price = price-Integer.parseInt(item.get(position).getPrice());
                }
                num--;
                if (num<=0){
                    num=0;
                }
                currentQuant.setText(String.valueOf(num));
                Log.d("check", String.valueOf(price));
                Intent intent = new Intent("custom-message");
                intent.putExtra("price",String.valueOf(price));
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{

        private TextView dishName;
        private TextView currentQuant;
        private TextView dishPrice;
        private ImageView imageView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            dishName = itemView.findViewById(R.id.dish_name);
            currentQuant = itemView.findViewById(R.id.current_quant);
            dishPrice = itemView.findViewById(R.id.dish_price);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

}
