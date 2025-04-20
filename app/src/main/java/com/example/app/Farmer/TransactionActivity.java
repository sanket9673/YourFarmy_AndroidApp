package com.example.app.Farmer;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.TransactionAdapter;
import com.example.app.Controller.OrderController;
import com.example.app.Model.Orders;
import com.example.app.R;
import com.example.app.Util.Util;

import java.util.List;

public class TransactionActivity extends AppCompatActivity {
    RecyclerView rv;
    TransactionAdapter adapter;
    androidx.appcompat.widget.Toolbar toolbar;
    Orders entity;
    OrderController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("View Transaction");
        entity = new Orders();
        controller = new OrderController(this);
        rv = (RecyclerView) findViewById(R.id.rv_transaction);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();

        gettransaction();
    }

    private void gettransaction() {
        List<Orders> ordersList = controller.getOrders(Util.getSP(getApplicationContext()));
        if (ordersList != null) {
            adapter = new TransactionAdapter(ordersList, getApplicationContext());
            rv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(), "No Transactions", Toast.LENGTH_SHORT).show();
        }
    }
}