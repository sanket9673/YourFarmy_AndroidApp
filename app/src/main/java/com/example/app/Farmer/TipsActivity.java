package com.example.app.Farmer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.Adapter.TipsAdapter;
import com.example.app.Admin.AddUpdateTips;
import com.example.app.Controller.TipsController;
import com.example.app.Model.Tips;
import com.example.app.R;
import com.example.app.Util.Util;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TipsActivity extends AppCompatActivity {
    Toolbar toolbar;
    TipsAdapter adapter;
    RecyclerView rv;
    Tips entity;
    TipsController controller;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        toolbar = findViewById(R.id.toolbar);
        controller = new TipsController(this);
        entity = new Tips();
        toolbar.setTitle("Tips");
        fab = (FloatingActionButton) findViewById(R.id.fab_addtip);
        rv = (RecyclerView) findViewById(R.id.rv_tips);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AddUpdateTips.class));
            }
        });
        if (Util.gettype(getApplicationContext()).compareTo("Admin") == 0) {
            fab.setVisibility(View.VISIBLE);
        }else {
            fab.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        List<Tips> tipsList = controller.getTips();
        if (tipsList != null) {
            adapter = new TipsAdapter(tipsList, getApplicationContext());
            rv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(), "No Tips", Toast.LENGTH_SHORT).show();
        }
    }

}