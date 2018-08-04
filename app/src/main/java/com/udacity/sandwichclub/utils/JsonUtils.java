package com.udacity.sandwichclub.utils;


import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static Sandwich parseSandwichJson(String json) throws JSONException {

        List<String> names = new ArrayList<>();
        List<String> ingredients = new ArrayList<>();
        JSONObject all = new JSONObject(json);
        JSONObject name = all.getJSONObject("name");
        String mainname = name.getString("mainName");
        if(!name.isNull("alsoKnownAs")){
            JSONArray knownnames = name.getJSONArray("alsoKnownAs");
            for(int i = 0 ; i<knownnames.length() ; i++){
                String knownname = knownnames.getString(i);
                names.add(knownname);
            }
        }
       JSONArray ingredients1 = all.getJSONArray("ingredients");
            for(int i = 0 ; i<ingredients1.length() ; i++){
                String knownname = ingredients1.getString(i);
                ingredients.add(knownname);
            }

        String description = all.getString("description");
        String imgurl = all.getString("image");
       String placeoforigin = all.getString("placeOfOrigin");
       Sandwich sandwich = new Sandwich(mainname, names, placeoforigin, description, imgurl, ingredients);

        return sandwich;
    }



}
