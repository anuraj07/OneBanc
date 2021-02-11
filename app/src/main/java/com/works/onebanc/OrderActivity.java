package com.works.onebanc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    String CuisineName = "";
//    TextView test;
    int TotalPrice=0;
    RecyclerView northRV, southRV, chineseRV, mexicanRV;
    Button Cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
//        test = findViewById(R.id.text);
        northRV = findViewById(R.id.north_rv);
        southRV = findViewById(R.id.south_rv);
        chineseRV = findViewById(R.id.chinese_rv);
        mexicanRV = findViewById(R.id.mexican_rv);
        Cart = findViewById(R.id.cart_goto);

        northRV.setVisibility(View.GONE);
        southRV.setVisibility(View.GONE);
        chineseRV.setVisibility(View.GONE);
        mexicanRV.setVisibility(View.GONE);

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("custom-message"));

        Intent intentReceived = getIntent();
        Bundle data = intentReceived.getExtras();
        if(data != null){
            CuisineName = data.getString("CuisineName");
        } else {
            CuisineName = "";
        }
//        test.setText(CuisineName);
        ArrayList<OrderModel> items = new ArrayList<>();
        OrderAdapter orderAdapter = new OrderAdapter(this,items);


        switch(CuisineName) {
            case "North":
                // code block
                northRV.setVisibility(View.VISIBLE);
                northRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
                items.add(new OrderModel("Chole Bhature",R.drawable.north,"100"));
                items.add(new OrderModel("Rajma Chawal",R.drawable.north,"200"));
                items.add(new OrderModel("Mattar Paneer",R.drawable.north,"250"));
                items.add(new OrderModel("Aloo paratha",R.drawable.north,"300"));
                northRV.setAdapter(orderAdapter);
                orderAdapter.notifyDataSetChanged();
                break;
            case "South":
                // code block
                southRV.setVisibility(View.VISIBLE);
                southRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
                items.add(new OrderModel("Dosa",R.drawable.south,"150"));
                items.add(new OrderModel("Idli Vada",R.drawable.south,"200"));
                items.add(new OrderModel("Utpam",R.drawable.south,"350"));
                items.add(new OrderModel("Curd Rice",R.drawable.south,"550"));
                southRV.setAdapter(orderAdapter);
                orderAdapter.notifyDataSetChanged();
                break;
            case "Chinese":
                // code block
                chineseRV.setVisibility(View.VISIBLE);
                chineseRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
                items.add(new OrderModel("Hakka Noodles",R.drawable.chinese,"65"));
                items.add(new OrderModel("Momos",R.drawable.chinese,"150"));
                items.add(new OrderModel("Manchurian",R.drawable.chinese,"250"));
                items.add(new OrderModel("Fried Rice",R.drawable.chinese,"255"));
                chineseRV.setAdapter(orderAdapter);
                orderAdapter.notifyDataSetChanged();
                break;
            case "Mexican":
                // code block
                mexicanRV.setVisibility(View.VISIBLE);
                mexicanRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
                items.add(new OrderModel("Pizza",R.drawable.mexican,"150"));
                items.add(new OrderModel("Tako",R.drawable.mexican,"140"));
                items.add(new OrderModel("Burrito",R.drawable.mexican,"200"));
                items.add(new OrderModel("Tex Mex",R.drawable.mexican,"450"));
                mexicanRV.setAdapter(orderAdapter);
                orderAdapter.notifyDataSetChanged();
                break;
        }



        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, CartActivity.class);
                Bundle data = new Bundle();
                data.putString("check","something");
                data.putString("CuisineName", CuisineName);
                data.putInt("price",TotalPrice);
                intent.putExtras(data);
                startActivity(intent);
            }
        });
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String p = intent.getStringExtra("price");
            TotalPrice = Integer.parseInt(p);

        }
    };

}