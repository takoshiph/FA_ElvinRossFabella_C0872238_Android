package com.example.fa_elvinrossfabella_c0872238_android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;
    private ArrayList<ProductModel> productList;
    private DBHelper dbHelper;

    public ProductAdapter(Context context, ArrayList<ProductModel> contactList) {
        this.context = context;
        this.productList = contactList;
        dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_row,parent,false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        ProductModel productModel = productList.get(position);

        int id = productModel.getId();
        String name = productModel.getName();
        String description = productModel.getDescription();

        holder.productName.setText(name);
        holder.productDescription.setText(description);
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView productName,productDescription;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.nameEditRow);
            productDescription = itemView.findViewById(R.id.descriptionEditRow);

        }
    }
}
