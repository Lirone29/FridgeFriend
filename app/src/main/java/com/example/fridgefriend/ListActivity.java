package com.example.fridgefriend;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fridgefriend.Model.Recipes;
import com.example.fridgefriend.Model.ShoppingList;
import com.example.fridgefriend.Model.ShoppingListProduct;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity implements AddToListDialog.ExampleDialogListener  {

    ArrayList<Recipes> productsArrayList;
    ShoppingList shoppingList;
    ShoppingListApi shoppingListApi;
    private Button _returnButton;
    @Override
    public void applyTexts(int position) {

        ShoppingListPostItem item = new ShoppingListPostItem(1,position+1);
        postShoppingListItem(item);
        myItemsListAdapter.notifyDataSetChanged();
        getShoppingList();
        myItemsListAdapter.notifyDataSetChanged();
    }

    public class Item {
        boolean checked;
        String ItemString;
        Item( String t, boolean b){
            ItemString = t;
            checked = b;
        }

        public boolean isChecked(){
            return checked;
        }
    }

    static class ViewHolder {
        CheckBox checkBox;
        //ImageView icon;
        TextView text;
    }

    public class ItemsListAdapter extends BaseAdapter {

        private Context context;
        private List<Item> list;

        ItemsListAdapter(Context c, List<Item> l) {
            context = c;
            list = l;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public boolean isChecked(int position) {
            return list.get(position).checked;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View rowView = convertView;

            ViewHolder viewHolder = new ViewHolder();
            if (rowView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                rowView = inflater.inflate(R.layout.row, null);

                viewHolder.checkBox = (CheckBox) rowView.findViewById(R.id.rowCheckBox);
                viewHolder.text = (TextView) rowView.findViewById(R.id.rowTextView);
                rowView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) rowView.getTag();
            }

            viewHolder.checkBox.setChecked(list.get(position).checked);

            final String itemStr = list.get(position).ItemString;
            viewHolder.text.setText(itemStr);

            viewHolder.checkBox.setTag(position);

            viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean newState = !list.get(position).isChecked();
                    list.get(position).checked = newState;
                    Toast.makeText(getApplicationContext(),
                            itemStr + "setOnClickListener\nchecked: " + newState,
                            Toast.LENGTH_LONG).show();
                }
            });

            viewHolder.checkBox.setChecked(isChecked(position));

            return rowView;
        }
    }

    Button banClearCheck;
    List<Item> items = new ArrayList<Item>();
    ListView listView;
    ItemsListAdapter myItemsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mtx.pmlabs.net:8888/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        shoppingListApi = retrofit.create(ShoppingListApi.class);

        getShoppingList();

        listView = (ListView)findViewById(R.id.listview);
        banClearCheck = (Button)findViewById(R.id.lookup);

        FloatingActionButton addButton = (FloatingActionButton)findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Toast.makeText(ListActivity.this,
                        "Button",
                        Toast.LENGTH_LONG).show();

                AddToListDialog addToListDialog = new AddToListDialog();
                addToListDialog.show(getSupportFragmentManager(), "Search produce");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

            }});

        banClearCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = "Check items:\n";
                List<Item> toDelete = new ArrayList<Item>();
                for (int i=0; i<items.size(); i++){
                    if (items.get(i).isChecked()){
                        str += i + "\n";
                       // toDelete.add(items.get(i));
                        ShoppingListBoughtItem item = new ShoppingListBoughtItem(shoppingList.getProducts().get(i-1).getId(), true);
                        postShoppingListBoughtItem(item);

                    }
                }

                myItemsListAdapter.notifyDataSetChanged();
                getShoppingList();
                myItemsListAdapter.notifyDataSetChanged();
            }

        });
        _returnButton =  (Button) findViewById(R.id.ListReturnButton);
        _returnButton.setEnabled(true);
        _returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnButton();
            }
        });
    }

    void getShoppingList() {

        Call<ShoppingList> call = shoppingListApi.getShoppingList();

        call.enqueue(new Callback<ShoppingList>() {
            @Override
            public void onResponse(Call<ShoppingList> all, Response<ShoppingList> response) {
                if (!response.isSuccessful()) {
                    //Toast.makeText(ListActivity.this, "Fail shopping list", Toast.LENGTH_LONG).show();
                    return;
                }

                shoppingList = response.body();
               // Toast.makeText(ListActivity.this, "Success shopping list", Toast.LENGTH_LONG).show();

                ArrayList<ShoppingListProduct> dummyList = new ArrayList<ShoppingListProduct>();
              //  Toast.makeText(ListActivity.this, shoppingList.getProducts().get(0).getName(), Toast.LENGTH_LONG).show();
                for(int i=0; i<shoppingList.getProducts().size(); i++){
                    if(!shoppingList.getProducts().get(i).isBought()) {
                        dummyList.add(shoppingList.getProducts().get(i));
                    }
                }

                shoppingList.setProducts(dummyList);
                items = new ArrayList<Item>();
                items.add(new Item("example", false));
                for(int i=0; i<shoppingList.getProducts().size(); i++){

                    String s = shoppingList.getProducts().get(i).getName();
                    boolean b = shoppingList.getProducts().get(i).isBought();
                    Item item = new Item(s, b);
                    items.add(item);

                }

                myItemsListAdapter = new ItemsListAdapter(ListActivity.this, items);
                listView.setAdapter(myItemsListAdapter);

                myItemsListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ShoppingList> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
              //  Toast.makeText(ListActivity.this, "Failure shopping list", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void postShoppingListItem(ShoppingListPostItem item){

        Call<ShoppingListPostItem> call = shoppingListApi.postShoppingListItem(item);

        call.enqueue(new Callback<ShoppingListPostItem>() {
            @Override
            public void onResponse(Call<ShoppingListPostItem> call, Response<ShoppingListPostItem> response) {

                if (!response.isSuccessful()) {
                  //  Toast.makeText(ListActivity.this, "Failure post", Toast.LENGTH_LONG).show();
                    return;
                }

               // Toast.makeText(ListActivity.this, "Success post", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ShoppingListPostItem> call, Throwable t) {
              //  Toast.makeText(ListActivity.this, "Fail post", Toast.LENGTH_LONG).show();

            }
        });
    }

    public void postShoppingListBoughtItem(ShoppingListBoughtItem item) {

        Call<ShoppingListBoughtItem> call = shoppingListApi.postShoppingListBoughtItem(item);

        call.enqueue(new Callback<ShoppingListBoughtItem>() {
            @Override
            public void onResponse(Call<ShoppingListBoughtItem> call, Response<ShoppingListBoughtItem> response) {

                if (!response.isSuccessful()) {
                   // Toast.makeText(ListActivity.this, "Failure post", Toast.LENGTH_LONG).show();
                    return;
                }
               // Toast.makeText(ListActivity.this, "Success post", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ShoppingListBoughtItem> call, Throwable t) {
              //  Toast.makeText(ListActivity.this, "Fail post", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void returnButton(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        //intent.putExtra(TAG_TOKEN, TOKEN);
        startActivityForResult(intent, 1);
        finish();
    }
}
