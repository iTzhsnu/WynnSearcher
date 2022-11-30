package com.github.iTzhsnu.WynnSearcher;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateAPIArchive {
    public static final String WYNN_ITEM_API = "https://api.wynncraft.com/public_api.php?action=itemDB&category=all";
    public static final String WYNN_INGREDIENT_API = "https://api.wynncraft.com/v2/ingredient/search/tier/";
    public static final String WYNN_RECIPE_API = "https://api.wynncraft.com/v2/recipe/search/skill/";

    public static void main(String[] args) {
        updateItemsArchive();
        updateIngredientsArchive();
        updateRecipesArchive();
    }

    public static void updateItemsArchive() {
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new URL(WYNN_ITEM_API).openStream()));
            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = buffer.readLine()) != null) {
                builder.append(line);
            }

            FileWriter w = new FileWriter("items.json");
            w.write(builder.toString());
            w.close();

            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateIngredientsArchive() {
        for (int i = 0; i <= 3; ++i) {
            try {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(new URL(WYNN_INGREDIENT_API + i).openStream()));
                String line;
                StringBuilder builder = new StringBuilder();

                while ((line = buffer.readLine()) != null) {
                    builder.append(line);
                }

                FileWriter w = new FileWriter("ings_" + i + ".json");
                w.write(builder.toString());
                w.close();

                buffer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateRecipesArchive() {
        String[] ss = new String[] {"armouring", "tailoring", "weaponsmithing", "woodworking", "jeweling", "scribing", "cooking", "alchemism"};

        for (String s : ss) {
            try {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(new URL(WYNN_RECIPE_API + s).openStream()));
                String line;
                StringBuilder builder = new StringBuilder();

                while ((line = buffer.readLine()) != null) {
                    builder.append(line);
                }

                FileWriter w = new FileWriter("recipes_" + s + ".json");
                w.write(builder.toString());
                w.close();

                buffer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
