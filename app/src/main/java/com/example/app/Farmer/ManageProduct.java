package com.example.app.Farmer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.ProductAdapter;
import com.example.app.Controller.ProductController;
import com.example.app.Model.Product;
import com.example.app.R;
import com.example.app.Util.Util;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ManageProduct extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rv;
    FloatingActionButton fab;
    ProductController controller;
    ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product);
        initUi();

    }

    private void initUi() {
        controller = new ProductController(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Manage Product");
        setSupportActionBar(toolbar);
        rv = (RecyclerView) findViewById(R.id.rv_product);
        fab = (FloatingActionButton) findViewById(R.id.fab_adproduct);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddorUpdateProduct.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        List<Product> productList = controller.getProduct(Util.getSP(getApplicationContext()));
        if (productList.size() > 0) {
            adapter = new ProductAdapter(productList, getApplicationContext());
            rv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "No Product Found", Toast.LENGTH_SHORT).show();
        }

    }
}