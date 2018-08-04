package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    private TextView description1,name,placeoforg,knownas,ingredients1,placelabel,alsolabl;
    private ImageView image;
    Sandwich sandwich = new Sandwich();
    // when i write Sandwich sandwich = null the app crashes.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
         placelabel = findViewById(R.id.plac_label);
        description1 = findViewById(R.id.description_tv);
        name = findViewById(R.id.origin_tv);
        placeoforg = findViewById(R.id.placeof_org);
        knownas = findViewById(R.id.also_known_tv);
        ingredients1 = findViewById(R.id.ingredients_tv);
        image = findViewById(R.id.image_iv);

        alsolabl = findViewById(R.id.also_label);



        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        //Sandwich sandwich = null;
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(image);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

        name.setText(sandwich.getMainName());
       description1.setText(String.valueOf( sandwich.getDescription()));
       List<String> knownas1 = sandwich.getAlsoKnownAs();
       for(String name2 : knownas1){
           knownas.append(name2 + "\n");
       }
       if(knownas1.isEmpty()){
           alsolabl.setVisibility(View.INVISIBLE);
       }

       String place1 = sandwich.getPlaceOfOrigin();
        placeoforg.setText(place1);

        if(place1.equals("")) {
            placelabel.setVisibility(View.INVISIBLE);
        }
        List<String> ingredients = sandwich.getIngredients();
        for(String ingredelement : ingredients){
            ingredients1.append(ingredelement + "\n");
            }
        }


        }


