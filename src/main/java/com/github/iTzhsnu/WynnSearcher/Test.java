package com.github.iTzhsnu.WynnSearcher;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<JsonObject> json = new ArrayList<>();
        GetAPI.setIngredientData(json);

        for (int i = 0; json.size() > i; ++i) {
            System.out.println(json.get(i).get("skills").getAsJsonArray().get(0).getAsString());
        }
    }
}
