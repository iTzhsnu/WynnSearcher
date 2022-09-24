package com.github.iTzhsnu.WynnSearcher;

import me.bed0.jWynn.WynncraftAPI;
import me.bed0.jWynn.api.common.WynncraftIdentification;
import me.bed0.jWynn.api.v2.ingredient.WynncraftIngredient;
import me.bed0.jWynn.api.v2.ingredient.WynncraftIngredientIdentificationDetails;

public class Main {
    public static void main(String[] args) {
        WynncraftAPI api = new WynncraftAPI();
        for (int i = 0; api.v2().ingredient().search().level(50).run().length > i; ++i) {
            WynncraftIngredient ingredient = api.v2().ingredient().search().level(50).run()[i];
            if (ingredient.getIdentifications().containsKey(WynncraftIdentification.POISON)) {
                WynncraftIngredientIdentificationDetails ingId = ingredient.getIdentifications().get(WynncraftIdentification.POISON);
                System.out.println("Name: " + ingredient.getName() + " | Poison: " + ingId.getMinimum() + " to " + ingId.getMaximum());
            }
        }
    }


}
