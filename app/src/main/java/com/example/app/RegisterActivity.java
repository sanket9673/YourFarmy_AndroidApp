package com.example.app;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.app.Db.SqliteDatabase;
import com.example.app.Model.Farmer;
import com.example.app.Model.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    String[] typelist = {"Farmer", "User"};
    String type;
    Toolbar toolbar;
    ProgressBar pb;
    TextInputEditText username, pass, contact, email;
    Button register;
    LinearLayout layout;
    Spinner spin;
    SqliteDatabase sqliteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initUI();
    }

    private void initUI() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Registration");
        setSupportActionBar(toolbar);
        spin = (Spinner) findViewById(R.id.spinner_reg);
        spin.setOnItemSelectedListener(this);



        sqliteDatabase = new SqliteDatabase(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, typelist);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        username = (TextInputEditText) findViewById(R.id.uname_edt);
        pass = (TextInputEditText) findViewById(R.id.upass_etd);
        pass.setInputType(android.text.InputType.TYPE_CLASS_TEXT | android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD);
        layout = (LinearLayout) findViewById(R.id.layout_reg);
        contact = (TextInputEditText) findViewById(R.id.ucontact_edt);
        register = (Button) findViewById(R.id.registration_btn);
        email = (TextInputEditText) findViewById(R.id.uemail_edt);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty()) {
                    Snackbar.make(layout, "Enter Name", Snackbar.LENGTH_SHORT).show();
                } else if (email.getText().toString().isEmpty()) {
                    Snackbar.make(layout, "Enter Email", Snackbar.LENGTH_SHORT).show();
                } else if (contact.getText().toString().isEmpty()) {
                    Snackbar.make(layout, "Enter Contact", Snackbar.LENGTH_SHORT).show();
                } else if (pass.getText().toString().isEmpty()) {
                    Snackbar.make(layout, "Enter Password", Snackbar.LENGTH_SHORT).show();
                } else {
                    register();
                }
            }
        });

    }

    private void register() {

        if (type.compareTo("Farmer") == 0) {

            Farmer farmer = new Farmer();
            farmer.setName(username.getText().toString());
            farmer.setEmail(email.getText().toString());
            farmer.setContact(contact.getText().toString());
            farmer.setPass(pass.getText().toString());
            sqliteDatabase.registerFarmer(farmer);
            Toast.makeText(this, "Farmer Registered!", Toast.LENGTH_SHORT).show();
            finish();


        } else {

            User supplier = new User();
            supplier.setName(username.getText().toString());
            supplier.setEmail(email.getText().toString());
            supplier.setContact(contact.getText().toString());
            supplier.setPass(pass.getText().toString());
            sqliteDatabase.registerUser(supplier);
            Toast.makeText(this, "User Registered!", Toast.LENGTH_SHORT).show();
            finish();

        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        type = typelist[position];
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}