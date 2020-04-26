package com.example.fridgefriend.recipe;

import androidx.appcompat.app.AppCompatActivity;
import com.example.fridgefriend.R;

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


    private EditText editRecipeName;
    private EditText editDescription;
    private Button buttonAddRecipe;

    private RecipeApi recipeApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        editRecipeName = (EditText) findViewById(R.id.editRecipeName);
        editDescription= (EditText) findViewById(R.id.editDescription);
        buttonAddRecipe = (Button) findViewById(R.id.buttonAddRecipe);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://mtx.pmlabs.net:8888/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recipeApi = retrofit.create(RecipeApi.class);


        buttonAddRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPostRecipe();
            }
        });
    }


    private void createPostRecipe(){

        PostRecipe postRecipe = new PostRecipe(editRecipeName.getText().toString(), editDescription.getText().toString()) ;


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


}
