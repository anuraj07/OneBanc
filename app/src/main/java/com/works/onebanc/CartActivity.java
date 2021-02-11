package com.works.onebanc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CartActivity extends AppCompatActivity {

    TextView netTotal, grandTotal;
    int price = 0;
    Button placeOrder;
    String check = "";
    LinearLayout empty, NoneEmpty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        netTotal = findViewById(R.id.net_total);
        grandTotal = findViewById(R.id.grand_total);
        placeOrder = findViewById(R.id.place_order);
        empty = findViewById(R.id.empty_cart);
        NoneEmpty = findViewById(R.id.none_empty);

        empty.setVisibility(View.GONE);
        NoneEmpty.setVisibility(View.GONE);

        Intent intentReceived = getIntent();
        Bundle data = intentReceived.getExtras();
        if(data != null){
            check = data.getString("check");
            price = data.getInt("price");
        } else {
            check = "";
            price = 0;
        }

        if (!check.equals("")) {
            NoneEmpty.setVisibility(View.VISIBLE);
            netTotal.setText("RS: "+price);

            float gTotal = price +(price * (2.5f / 100f)) + (price * (2.5f / 100f));

            grandTotal.setText("RS: "+gTotal);

            placeOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(CartActivity.this, "Your Order is placed\nThanks for ordering food with us", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CartActivity.this, MainActivity.class));
                    finish();
                }
            });
        } else {
            empty.setVisibility(View.VISIBLE);
        }



    }
}