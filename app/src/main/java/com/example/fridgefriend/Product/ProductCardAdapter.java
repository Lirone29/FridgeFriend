package com.example.fridgefriend.Product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridgefriend.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class ProductCardAdapter extends RecyclerView.Adapter<ProductCardAdapter.ProductHolder>{

    private ArrayList<Product> mItemList;
    private final OnProductCardAdapterListener mlistener;

    public ProductCardAdapter(ArrayList<Product> productsList, OnProductCardAdapterListener listener) {
        this.mItemList = productsList;
        this.mlistener = listener;
    }

    public void addAllItems(ArrayList<Product> items) {
        mItemList.addAll(items);
        notifyDataSetChanged();
    }

    public void removeItems() {
        mItemList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_product_item, parent, false);
        ProductHolder productHolder = new ProductHolder(view);
        return productHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        holder.bind(mItemList.get(position),mlistener);

        Product product = mItemList.get(position);

        holder._productName.setText("" + product.getName());
        holder._productWeight.setText("Weight: " +  product.getWeight());
        holder._productCalories.setText("Cal: " + product.getCalories());
        holder._productDaysToExpire.setText("Day to Expire" + product.getDaysToExpire());
    }


    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        private TextView _productName;
        private TextView _productWeight;
        private TextView _productCalories;
        private TextView _productDaysToExpire;

        public ProductHolder(View itemView) {
            super(itemView);
            _productName = itemView.findViewById(R.id.searchProductName);
            _productWeight = itemView.findViewById(R.id.searchWeight);
            _productCalories = itemView.findViewById(R.id.searchCalories);
            _productDaysToExpire = itemView.findViewById(R.id.searchExpirationDateText);
        }

        public void bind(final Product item, final OnProductCardAdapterListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
