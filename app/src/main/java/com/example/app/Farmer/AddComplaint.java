package com.example.app.Farmer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.Controller.ComplaintController;
import com.example.app.Model.Complain;
import com.example.app.R;
import com.example.app.Util.Util;

public class AddComplaint extends AppCompatActivity {
    EditText edt;
    Button submit;
    androidx.appcompat.widget.Toolbar toolbar;
    Complain entity;
    ComplaintController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_complaint);
        initui();

    }

    private void initui() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Add Compliant");
        setSupportActionBar(toolbar);
        edt = (EditText) findViewById(R.id.addcomplaint_edt);
        submit = (Button) findViewById(R.id.addcomplaint_btn);

        controller = new ComplaintController(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt.getText().toString().isEmpty()) {
                    Toast.makeText(AddComplaint.this, "Enter Complaint", Toast.LENGTH_SHORT).show();
                } else {
                    entity = new Complain();
                    entity.setType(Util.gettype(getApplicationContext()));
                    entity.setName(Util.getName(getApplicationContext()));
                    entity.setComplain(edt.getText().toString());
                    entity.setComplainerid(Util.getSP(getApplicationContext()));
                    entity.setDt("");
                    entity.setReply("");
                    entity.setStatus("");
                    controller.addComplaint(entity);

                    Toast.makeText(AddComplaint.this, "Added !", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

    }

}