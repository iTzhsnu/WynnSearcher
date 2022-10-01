//Original Code Created By: soramame_256
package com.github.iTzhsnu.WynnSearcher;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class GetAPI {
    public static final String WYNN_ITEM_API = "https://api.wynncraft.com/public_api.php?action=itemDB&category=all";
    public static final String WYNN_INGREDIENT_API = "https://api.wynncraft.com/v2/ingredient/search/tier/";

    public static void setItemData(List<JsonObject> list) {
        try {
            BufferedReader buffer = new BufferedReader(new InputStreamReader(new URL(WYNN_ITEM_API).openStream()));
            String line;
            StringBuilder builder = new StringBuilder();

            while ((line = buffer.readLine()) != null) {
                builder.append(line);
            }

            JsonElement element = JsonParser.parseString(builder.toString());
            JsonObject object = element.getAsJsonObject();
            for (JsonElement je : object.get("items").getAsJsonArray()) {
                list.add(je.getAsJsonObject());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setIngredientData(List<JsonObject> list) {
        for (int i = 0; i <= 3; ++i) {
            try {
                BufferedReader buffer = new BufferedReader(new InputStreamReader(new URL(WYNN_INGREDIENT_API + i).openStream()));
                String line;
                StringBuilder builder = new StringBuilder();

                while ((line = buffer.readLine()) != null) {
                    builder.append(line);
                }

                JsonElement element = JsonParser.parseString(builder.toString());
                JsonObject object = element.getAsJsonObject();
                for (JsonElement je : object.get("data").getAsJsonArray()) {
                    list.add(je.getAsJsonObject());
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
