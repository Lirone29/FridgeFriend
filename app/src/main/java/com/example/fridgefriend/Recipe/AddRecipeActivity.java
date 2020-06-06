package com.example.fridgefriend.Recipe;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fridgefriend.FridgeActivity;
import com.example.fridgefriend.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddRecipeActivity extends AppCompatActivity {

    private static final String TAG_TOKEN = "TOKEN";
    String TOKEN;

    private EditText editRecipeName;
    private EditText editDescription;
    private Button _buttonAddRecipe;
    private Button _returnButton;

    private RecipeApi recipeApi;

    String recipeName;
    String recipeDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            TOKEN = bundle.getString(TAG_TOKEN);

        editRecipeName = (EditText) findViewById(R.id.editRecipeName);
        editDescription= (EditText) findViewById(R.id.editDescription);
        _buttonAddRecipe = (Button) findViewById(R.id.buttonAddRecipe);
        _returnButton = (Button) findViewById(R.id.addProductReturnButton);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mtx.pmlabs.net:8888/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recipeApi = retrofit.create(RecipeApi.class);

        _buttonAddRecipe.setEnabled(true);
        _buttonAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeName = editRecipeName.getText().toString();
                recipeDescription = editDescription.getText().toString();

                if(recipeName.length()==0 && recipeDescription.length()==0) {
                    editRecipeName.requestFocus();
                    editRecipeName.setError("FIELD CANNOT BE EMPTY");

                    editDescription.requestFocus();
                    editDescription.setError("FIELD CANNOT BE EMPTY");
                }
                else {
                    createPostRecipe();
                }

            }
        });

        _returnButton.setEnabled(true);
        _returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnButton();
            }
        });

    }

    private void createPostRecipe(){

        System.out.println(recipeName);

        PostRecipe postRecipe = new PostRecipe(recipeName,recipeDescription) ;


        Call<PostRecipe> call = recipeApi.createPost("82dae18b776fe80c6d299b59627249f1ef57fcf4", postRecipe);

        call.enqueue(new Callback<PostRecipe>() {
            @Override
            public void onResponse(Call<PostRecipe> call, Response<PostRecipe> response) {
                if (!response.isSuccessful()) {
                   // textViewResult.setText("Code" + response.code());
                    System.out.println("Code" + response.code());
                    return;
                }

                PostRecipe postResponse = response.body();
                System.out.println("Code" + response.code());
                openDialog();


            }

            @Override
            public void onFailure(Call<PostRecipe> call, Throwable t) {
                //textViewResult.setText(t.getMessage());
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

   private  void openDialog(){
        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(),"dialog");
    }

    private boolean validateRecipeName (){
        String nameInput = editRecipeName.getText().toString().trim();

        if(nameInput.isEmpty()){
            System.out.println("empty");
            return false;
        }
        else
            return true;
    }


    private void returnButton(){
        Intent intent = new Intent(getApplicationContext(), FridgeActivity.class);
        intent.putExtra(TAG_TOKEN, TOKEN);
        startActivityForResult(intent, 1);
        finish();
    }

}
