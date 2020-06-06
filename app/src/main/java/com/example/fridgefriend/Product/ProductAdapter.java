package com.example.fridgefriend.Product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fridgefriend.Model.Product;
import com.example.fridgefriend.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private ArrayList<Product> mProductItemList;
    private final OnProductClikListener mlistener;


    public ProductAdapter (ArrayList<Product> productItemList,  OnProductClikListener listener){
        mProductItemList = productItemList;
        mlistener = listener;
    }



    public void addAllItems(ArrayList<Product> items) {
        mProductItemList.addAll(items);
        notifyDataSetChanged();
    }

    public void removeItems() {
        mProductItemList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_product_item, parent, false);
        ProductAdapter.ProductViewHolder productViewHolder = new ProductAdapter.ProductViewHolder(view);
        return productViewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        holder.bind(mProductItemList.get(position),mlistener);

        Product product = mProductItemList.get(position);

        holder._productName.setText("" + product.getName());
        holder._productWeight.setText("Weight: " +  product.getWeight());
        holder._productCalories.setText("Cal: " + product.getCalories());
        //holder._productDaysToExpire.setText("Day to Expire: " + product.getDaysToExpire());
        //if(product.getDaysToExpire()>=4)
        holder. _productDaysToExpire .setText("Day to expire: " +  product.getDaysToExpire());
        //else  {
        //    holder. _productDaysToExpire .setText("Day to expire: " +  product.getDaysToExpire());
       //     holder._productDaysToExpire.setBackgroundResource(R.color.colorRed);
       // }
    }

    @Override
    public int getItemCount() {
        return mProductItemList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder{

        private TextView _productName;
        private TextView _productWeight;
        private TextView _productCalories;
        private TextView _productDaysToExpire;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            _productName = itemView.findViewById(R.id.searchProductName);
            _productWeight = itemView.findViewById(R.id.searchWeight);
            _productCalories = itemView.findViewById(R.id.searchCalories);
            _productDaysToExpire = itemView.findViewById(R.id.searchProductExpirationDate);
        }
        public void bind(final Product item, final OnProductClikListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
