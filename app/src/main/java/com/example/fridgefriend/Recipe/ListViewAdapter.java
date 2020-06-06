package com.example.fridgefriend.Recipe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fridgefriend.FridgeActivity;
import com.example.fridgefriend.Model.Recipe;
import com.example.fridgefriend.Model.Recipes;
import com.example.fridgefriend.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    ArrayList<Recipes> recipesArrayList;
    Context context;
    Button deleteButton;

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
        TextView recipeName = (TextView)
                convertView.findViewById(R.id.recipeName);
        deleteButton = (Button) convertView.findViewById(R.id.deleteButtonId);

        recipeName.setText(recipes.getName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecipeActivity(recipes);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteRecipeImp deleteRecipeImp = new DeleteRecipeImp(recipes.getId());
                Intent intent = new Intent(context, RecipeActivity.class);
                context.startActivity(intent);
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


