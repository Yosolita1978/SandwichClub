package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    //Create all the variables in the Sandwich Class
    static String mainName;
    static List<String> alsoKnownAs = new ArrayList<String>();
    static String placeOfOrigin;
    static String description;
    static String image;
    static List<String> ingredients = new ArrayList<String>();

    //For debugging reasons
    private static String TAG = JsonUtils.class.toString();


    //Parse the Json to create the new Sandwich object

    public static Sandwich parseSandwichJson(String json) {


        try {
            JSONObject sandwichJson = new JSONObject(json);

            JSONObject name = sandwichJson.getJSONObject("name");

            mainName = name.getString("mainName");

            JSONArray alsoKnown = name.getJSONArray("alsoKnownAs");

            for (int i = 0; i < alsoKnown.length(); i++) {
                if (i == alsoKnown.length() - 1) {
                    alsoKnownAs.add(alsoKnown.getString(i) + ".");

                } else {
                    alsoKnownAs.add(alsoKnown.getString(i) + " , ");

                }
            }
            placeOfOrigin = sandwichJson.getString("placeOfOrigin");

            description = sandwichJson.getString("description");

            image = sandwichJson.getString("image");

            JSONArray mIngredients = sandwichJson.getJSONArray("ingredients");

            for (int x = 0; x < mIngredients.length(); x++) {
                if (x == mIngredients.length() - 1) {
                    ingredients.add(mIngredients.getString(x) + ".");
                } else {
                    ingredients.add(mIngredients.getString(x) + " , ");
                }
            }


        } catch (JSONException e) {
            Log.d(TAG, "An error had occurred on JSON Parsing");
            e.printStackTrace();
        }


        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }
}
