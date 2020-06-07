package com.example.fridgefriend.Product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridgefriend.Model.FridgeProduct;
import com.example.fridgefriend.Model.Product;
import com.example.fridgefriend.R;

import java.util.ArrayList;

public class FridgeProductAdapter extends RecyclerView.Adapter<FridgeProductAdapter.FridgeProductViewHolder>{

    private ArrayList<FridgeProduct> mProductItemList;
    private final OnFridgeProductClickListener mlistener;


    public FridgeProductAdapter (ArrayList<FridgeProduct> productItemList, OnFridgeProductClickListener listener){
        mProductItemList = productItemList;
        mlistener = listener;
    }


    public void addAllItems(ArrayList<FridgeProduct> items) {
        mProductItemList.addAll(items);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        mProductItemList.remove(position);
        notifyDataSetChanged();
    }

    public void removeItems() {
        mProductItemList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FridgeProductAdapter.FridgeProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fridge_item, parent, false);
        FridgeProductAdapter.FridgeProductViewHolder fridgeProductViewHolder = new FridgeProductAdapter.FridgeProductViewHolder(view);
        return fridgeProductViewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull FridgeProductAdapter.FridgeProductViewHolder holder, int position) {
        holder.bind(mProductItemList.get(position),mlistener);

        FridgeProduct product = mProductItemList.get(position);
        holder._productName.setText("" + product.getName());
        if(product.getDaysToExpire()>=2)
        holder. _productDayToExpire .setText("Day to expire: " +  product.getDaysToExpire());
        else if(product.getDaysToExpire()>=0) {
            holder. _productDayToExpire .setText("Day to expire: " +  product.getDaysToExpire());
            holder. _productDayToExpire.setBackgroundResource(R.color.colorOrange);
        }else {
                holder. _productDayToExpire .setText("Day to expire: " +  product.getDaysToExpire());
                holder. _productDayToExpire.setBackgroundResource(R.color.colorRed);
            }

    }

    @Override
    public int getItemCount() {
        return mProductItemList.size();
    }

    public static class FridgeProductViewHolder extends RecyclerView.ViewHolder{

        private TextView _productName;
        private TextView _productDayToExpire;

        public FridgeProductViewHolder(@NonNull View itemView) {
            super(itemView);
            _productName = itemView.findViewById(R.id.fridgeProductName);
            _productDayToExpire = itemView.findViewById(R.id.fridgeProductExpirationDate);
        }
        public void bind(final FridgeProduct item, final OnFridgeProductClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}