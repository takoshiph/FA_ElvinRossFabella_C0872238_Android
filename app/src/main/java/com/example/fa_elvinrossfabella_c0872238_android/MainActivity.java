package com.example.fa_elvinrossfabella_c0872238_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton addProductBtn;
    private RecyclerView productRecyclerView;

    //db
    private DBHelper dbHelper;

    //adapter
    private ProductAdapter productAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DBHelper(this);
        addProductBtn = findViewById(R.id.addProduct);
        productRecyclerView = findViewById(R.id.productRV);

        productRecyclerView.setHasFixedSize(true);

        // add listener
        addProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // move to new activity to add contact
                Intent intent = new Intent(MainActivity.this,AddEditProduct.class);
                intent.putExtra("isEditMode",false);
                startActivity(intent);
            }
        });

        loadData();
    }

    private void loadData() {
        productAdapter = new ProductAdapter(this,dbHelper.getAllProduct());
        productRecyclerView.setAdapter(productAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeItem((int) viewHolder.itemView.getTag());
            }


        }).attachToRecyclerView(productRecyclerView);

    }
    private void removeItem(int id) {
        dbHelper.deleteProduct(id);
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadData(); // refresh data
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem item = menu.findItem(R.id.searchProduct);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchProduct(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchProduct(newText);
                return true;
            }
        });
        return true;
    }

    private void searchProduct(String query) {
        productAdapter = new ProductAdapter(this,dbHelper.getSearchProduct(query));
        productRecyclerView.setAdapter(productAdapter);
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.deleteAllProduct:
                dbHelper.deleteAllProduct();
                onResume();
                break;
        }

        return true;
    }
}