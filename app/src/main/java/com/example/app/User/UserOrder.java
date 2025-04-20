package com.example.app.User;

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

public class UserOrder extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rv;
    OrderController controller;
    Orders entity;
    OrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Orders");
        rv = (RecyclerView) findViewById(R.id.rv_useroders);
        controller = new OrderController(this);
        entity = new Orders();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Orders> ordersList = controller.getOrdersbyuid(Util.getSP(getApplicationContext()));
        if (ordersList.size() > 0) {
            adapter = new OrderAdapter(ordersList, getApplicationContext());
            rv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "No Orders", Toast.LENGTH_SHORT).show();
        }
    }
}