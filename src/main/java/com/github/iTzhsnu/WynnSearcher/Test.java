package com.github.iTzhsnu.WynnSearcher;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<JsonObject> list = new ArrayList<>();
        Identifications id = Identifications.POISON;
        GetAPI.setIngredientData(list);


        System.out.println(list.get(0).get(id.getIngFieldPos()).getAsJsonObject().get(id.getIngName()));
    }

    //if (string.indexOf("-") != -1) String[] s = string.split("-");
    //use for String[] => String
}
