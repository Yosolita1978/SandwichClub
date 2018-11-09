package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    //Create all the variables in the Sandwich Json

    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    //For debugging reasons

    private static String TAG = JsonUtils.class.toString();


    //Parse the Json to create the new Sandwich object

    public static Sandwich parseSandwichJson(String json) {

        try {
            JSONObject jsonData = new JSONObject(json);

            JSONObject names = jsonData.getJSONObject(NAME);

            String mainName = names.getString(MAIN_NAME);

            JSONArray alsoKnownAsJsonArray = names.getJSONArray(ALSO_KNOWN_AS);

            ArrayList<String> alsoKnownAs = new ArrayList<>();
            for (int i = 0; i < alsoKnownAsJsonArray.length(); i++) {
                alsoKnownAs.add(alsoKnownAsJsonArray.getString(i));
            }

            String placeOfOrigin = jsonData.getString(PLACE_OF_ORIGIN);

            String description = jsonData.getString(DESCRIPTION);

            String image = jsonData.getString(IMAGE);

            JSONArray ingredientsJsonArray = jsonData.getJSONArray(INGREDIENTS);

            ArrayList<String> ingredients = new ArrayList<>();
            for (int i = 0; i < ingredientsJsonArray.length(); i++) {
                ingredients.add(ingredientsJsonArray.getString(i));
            }

            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);


        } catch (JSONException e) {
            Log.d(TAG, "An error had occurred on JSON Parsing");
            e.printStackTrace();
        }

        return null;
    }
}
