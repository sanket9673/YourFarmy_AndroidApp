package com.example.app.Farmer;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.OrderAdapter;
import com.example.app.Controller.OrderController;
import com.example.app.Model.Orders;
import com.example.app.R;
import com.example.app.Util.Util;

import java.util.List;

public class OrdersActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rv;
    OrderAdapter adapter;
    OrderController controller;
    Orders entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Orders");
        setSupportActionBar(toolbar);
        rv = (RecyclerView) findViewById(R.id.rv_orders);
        controller = new OrderController(this);
        entity = new Orders();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Util.gettype(getApplicationContext()).compareTo("Admin")==0){
            List<Orders> ordersList = controller.getallOrders();
            if (ordersList.size() > 0) {
                adapter = new OrderAdapter(ordersList, getApplicationContext());
                rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(), "No Orders", Toast.LENGTH_SHORT).show();
            }
        }else if (Util.gettype(getApplicationContext()).compareTo("Farmer")==0){
            List<Orders> ordersList = controller.getOrders(Util.getSP(getApplicationContext()));
            if (ordersList.size() > 0) {
                adapter = new OrderAdapter(ordersList, getApplicationContext());
                rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getApplicationContext(), "No Orders", Toast.LENGTH_SHORT).show();
            }
        }else{

        }

    }

}