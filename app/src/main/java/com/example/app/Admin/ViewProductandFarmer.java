package com.example.app.Admin;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.ProductandFArmerAdapter;
import com.example.app.Controller.ProductController;
import com.example.app.Model.Product;
import com.example.app.R;

import java.util.List;

public class ViewProductandFarmer extends AppCompatActivity {

    RecyclerView rv;
    Toolbar toolbar;
    ProductandFArmerAdapter adapter;
    Product entity;
    ProductController controller;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_productand_farmer);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("View Products");
        setSupportActionBar(toolbar);
        controller = new ProductController(this);

        rv = (RecyclerView) findViewById(R.id.rv_productandfarmer);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Product> productList = controller.getProductwithFarmer();
        if (productList.size() > 0) {
            adapter = new ProductandFArmerAdapter(productList, getApplicationContext());
            rv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(), "No Product", Toast.LENGTH_SHORT).show();
        }

    }
}