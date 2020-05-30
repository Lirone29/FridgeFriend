package com.example.fridgefriend.recipe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fridgefriend.MainActivity;
import com.example.fridgefriend.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    ArrayList<Recipes> recipesArrayList;
    Context context;

    public ListViewAdapter(Context context, ArrayList<Recipes> recipesArrayList) {
        super();
        this.context = context;
        this.recipesArrayList = recipesArrayList;
    }

    @Override
    public int getCount() {
        return recipesArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return recipesArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItemId(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_list_view_row_items, parent, false);

        }
        final Recipes recipes = (Recipes) getItem(position);
        TextView textViewItemName = (TextView)
                convertView.findViewById(R.id.text_view_item_name);
        textViewItemName.setText(recipes.getName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, recipes.getDescription(), Toast.LENGTH_SHORT).show();
                openRecipeActivity(recipes);

            }
        });



        return convertView;
    }

    private void openRecipeActivity(Recipes recipes){
        Intent intent = new Intent(context, RecipeByIdActivity.class);
        intent.putExtra("id", recipes.getId());
        context.startActivity(intent);

    }
}


