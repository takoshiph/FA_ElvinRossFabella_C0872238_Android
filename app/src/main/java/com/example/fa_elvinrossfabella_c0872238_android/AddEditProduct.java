package com.example.fa_elvinrossfabella_c0872238_android;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.security.Permission;

public class AddEditProduct extends AppCompatActivity {

    private EditText nameET,descriptionET,priceET,latitudeET,longitudeET;
    private FloatingActionButton doneBtn;

    private String id,name,description;
    private String price;
    private String latitude;
    private String longitude;
    private Boolean isEditMode;
    private DBHelper dbHelper;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_product);

        //init db
        dbHelper = new DBHelper(this);

        //init actionBar
        actionBar = getSupportActionBar();

        //back button
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        //init view
        nameET = findViewById(R.id.nameEditText);
        descriptionET = findViewById(R.id.descriptionEditText);
        priceET = findViewById(R.id.priceEditText);
        latitudeET = findViewById(R.id.latitudeEditText);
        longitudeET = findViewById(R.id.longitudeEditText);

        doneBtn = findViewById(R.id.doneButton);

        // get intent data
        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEditMode",false);

        if (isEditMode){
            //set toolbar title
            actionBar.setTitle("Update Product");

            //get the other value from intent
            id = intent.getStringExtra("ID");
            name = intent.getStringExtra("Name");
            description = intent.getStringExtra("Description");
            price = intent.getStringExtra("Price");
            latitude = intent.getStringExtra("Latitude");
            longitude = intent.getStringExtra("Longitude");

            //set value in editText field
            nameET.setText(name);
            descriptionET.setText(description);
            priceET.setText(price);
            longitudeET.setText(longitude);
            latitudeET.setText(latitude);

        }else {
            // add mode on
            actionBar.setTitle("Add Product");
        }
        // add even handler
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

    }

    private void saveData() {
        name = nameET.getText().toString();
        description = descriptionET.getText().toString();
        price = priceET.getText().toString();
        longitude = longitudeET.getText().toString();
        latitude = latitudeET.getText().toString();

        if (!name.isEmpty() || !description.isEmpty()){
            if (isEditMode){
                dbHelper.updateProduct(
                        ""+id,
                        ""+name,
                        ""+description,
                        ""+price,
                        ""+latitude,
                        ""+longitude
                );

                Toast.makeText(getApplicationContext(), "Updated Successfully....", Toast.LENGTH_SHORT).show();

            }else {
                // add mode
                long id =  dbHelper.insertProduct(
                        ""+name,
                        ""+description,
                        ""+price,
                        ""+latitude,
                        ""+longitude
                );
                //To check insert data successfully ,show a toast message
                Toast.makeText(getApplicationContext(), "Inserted Successfully.... "+id, Toast.LENGTH_SHORT).show();
            }

        }else {
            // show toast message
            Toast.makeText(getApplicationContext(), "Nothing to save....", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}